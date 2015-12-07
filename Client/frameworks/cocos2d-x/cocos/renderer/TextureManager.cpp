#include "TextureManager.h"
#include "TextureBinPack.h"
#include "2d/CCSprite.h"
#include "base/StreamFile.h"
#include "base/CCDirector.h"
#include "base/CCEventType.h"
#include "base/CCEventDispatcher.h"
#include "base/ZipUtils.h"
#include "platform/CCImage.h"
#include "platform/CCFileUtils.h"
#include "renderer/CCRenderer.h"
#include "renderer/ccGLProgramCache.h"
#include "renderer/ccGLStateCache.h"
#include "renderer/ccShaders.h"

#include <stdio.h>
#include <zlib.h>

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include <jni.h>
#include <android/log.h>

#define  LOG_TAG    "TextureManager"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#endif

#define TEXTURE_SIZE 2048
#define TEXTURE_COUNT 4

NS_CC_BEGIN

TextureManager *TextureManager::instance = nullptr;

long long currentTimeMillis() {
    return std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::high_resolution_clock::now().time_since_epoch()).count();
}

IDGenerator::IDGenerator() : nextId(1) {
}

void IDGenerator::genId(GLsizei n, GLuint *ids) {
    while(n > 0) {
        --n;

        while(sets.find(nextId) != sets.end() || (nextId == 0)) {
            ++nextId;
        }

        ids[n] = nextId;
        sets.insert(nextId);
    }
}

void IDGenerator::deleteId(GLuint id) {
    sets.erase(id);
}

void IDGenerator::clear() {
    sets.clear();
    nextId = 0;
}

TextureManager::TextureManager() : nextTexModuleId(0) {
    int length = TEXTURE_SIZE * TEXTURE_SIZE * 4;
    unsigned char *data = new unsigned char[length];
    memset(data, 0, sizeof(unsigned char) * length);

    for(int i = 0; i < TEXTURE_COUNT; ++i) {
        Texture2D *tex = new Texture2D();
        tex->initWithData(data, length, Texture2D::PixelFormat::RGBA8888, TEXTURE_SIZE, TEXTURE_SIZE, Size(TEXTURE_SIZE, TEXTURE_SIZE));
        textures.push_back(tex);
        TextureBinPack *bin = new TextureBinPack(TEXTURE_SIZE, TEXTURE_SIZE);
        textureBins.push_back(bin);
    }

    CC_SAFE_DELETE_ARRAY(data);

    reloadShaders();
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    /** listen the event that renderer was recreated on Android/WP8 */
    _backToForegroundlistener = EventListenerCustom::create(EVENT_RENDERER_RECREATED, [this](EventCustom *) {
		CCLOG("texturemanager reload textures");
        reloadShaders();
        reloadTextures();
    });
    Director::getInstance()->getEventDispatcher()->addEventListenerWithFixedPriority(_backToForegroundlistener, -1);
#endif
}

TextureManager::~TextureManager() {
    imageMap.clear();
    moduleMap.clear();

    for(auto& e : textureBins) {
        CC_SAFE_DELETE(e);
    }

    for(auto& e : textures) {
        CC_SAFE_DELETE(e);
    }

    for(auto& e : texModuleMap) {
        CC_SAFE_DELETE(e.second);
    }

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    Director::getInstance()->getEventDispatcher()->removeEventListener(_backToForegroundlistener);
#endif
}

void TextureManager::reloadShaders() {
}

void TextureManager::reloadTextures() {
    for(auto& e : texModuleMap) {
        CC_SAFE_DELETE(e.second);
    }

    texModuleMap.clear();
	texCoordCalculatedMap.clear();
    int length = TEXTURE_SIZE * TEXTURE_SIZE * 4;
    unsigned char *data = new unsigned char[length];
    memset(data, 0, sizeof(unsigned char) * length);

    for(int i = 0; i < TEXTURE_COUNT; ++i) {
        Texture2D *tex = textures[i];
        tex->initWithData(data, length, Texture2D::PixelFormat::RGBA8888, TEXTURE_SIZE, TEXTURE_SIZE, Size(TEXTURE_SIZE, TEXTURE_SIZE));
        TextureBinPack *bin = textureBins[i];
        bin->init(TEXTURE_SIZE, TEXTURE_SIZE);
    }

    CC_SAFE_DELETE_ARRAY(data);
}

bool TextureManager::hasCalculatedTexCoord(GLuint texCoordId) {
    return texCoordCalculatedMap.find(texCoordId) != texCoordCalculatedMap.end();
}

void TextureManager::calculatedTexCoord(GLuint texCoordId, TexModule *tm) {
    texCoordCalculatedMap[texCoordId] = tm->tex;
}

void TextureManager::delTexCoordId(GLuint& texCoordId) {
	if(texCoordId) {
		texCoordCalculatedMap.erase(texCoordId);
		texCoordGenerator.deleteId(texCoordId);
		texCoordId = 0;
	}
}

void TextureManager::clear() {
    Director *director = Director::getInstance();
    auto glView = director->getOpenGLView();
    auto renderer = director->getRenderer();

    long long start = currentTimeMillis();
    CCLOG("texMgr clear texModuleCount=%d", texModuleMap.size());
    renderer->render();
    CCLOG("render before success");

    int textureCosts[TEXTURE_COUNT] = {0};

    int curLoopCounter = Director::loopCounter;

    for(auto& entry : texModuleMap) {
        auto tm = entry.second;

        for(int i = 0; i < TEXTURE_COUNT; ++i) {
            if(textures[i] == tm->tex) {
                textureCosts[i] += (tm->lastCallCounter - curLoopCounter) * tm->width * tm->height;
                break;
            }
        }
    }

    int min = INT_MAX;
    int index = -1;

    for(int i = 0; i < TEXTURE_COUNT; ++i) {
        CCLOG("textureCosts[%d]=%d", i, textureCosts[i]);

        if(min > textureCosts[i]) {
            min = textureCosts[i];
            index = i;
        }
    }

    int length = TEXTURE_SIZE * TEXTURE_SIZE << 2;
    byte *data = new byte[length];
    memset(data, 0, length);
    textures[index]->initWithData(data, length, Texture2D::PixelFormat::RGBA8888, TEXTURE_SIZE, TEXTURE_SIZE, Size(TEXTURE_SIZE, TEXTURE_SIZE));
    textureBins[index]->init(TEXTURE_SIZE, TEXTURE_SIZE);

    std::vector<int> toDeletes;

    for(auto& entry : texModuleMap) {
        auto tm = entry.second;

        if(tm->tex == textures[index]) {
            CC_SAFE_DELETE(tm);
            toDeletes.push_back(entry.first);
        }
    }

    for(auto id : toDeletes) {
        texModuleMap.erase(id);
    }

	toDeletes.clear();

	for (auto& entry : texCoordCalculatedMap) {
		auto tex = entry.second;
		if (tex == textures[index]) {
			toDeletes.push_back(entry.first);
		}
	}

	for (auto id : toDeletes) {
		texCoordCalculatedMap.erase(id);
	}

    CC_SAFE_DELETE_ARRAY(data);
    CCLOG("after texMgr clear texModuleCount=%d, clearTexId=%d, recalculate count %d, cost Time=%lld", texModuleMap.size(), index, toDeletes.size(), currentTimeMillis() - start);
}

TexModule *TextureManager::genTexModule(int width, int height, unsigned char *data, GLuint *texModuleId) {
    TexModule *tm = new TexModule();

    do {
        for(int i = 0; i < textureBins.size(); ++i) {
            TextureBinPack *bin = textureBins[i];
            TextureRect tr = bin->insert(width, height);

            if(tr.width != 0 && tr.height != 0) {
                tm->tex = textures[i];
                tm->x = tr.x;
                tm->y = tr.y;
                tm->width = tr.width;
                tm->height = tr.height;
                break;
            }
        }

        if(tm->tex)
            break;

        clear();
    } while(1);

    GL::bindTexture2D(tm->tex->getName());
    glTexSubImage2D(GL_TEXTURE_2D, 0, (GLint)tm->x, (GLint)tm->y, (GLsizei)tm->width, (GLsizei)tm->height, GL_RGBA, GL_UNSIGNED_BYTE, data);
    *texModuleId = ++nextTexModuleId;
    texModuleMap.insert(std::make_pair(*texModuleId, tm));
#if 0
    GLint oldFBO;
    glGetIntegerv(GL_FRAMEBUFFER_BINDING, &oldFBO);
    GLint oldRBO;
    glGetIntegerv(GL_RENDERBUFFER_BINDING, &oldRBO);
    GLuint fbo;
    glGenFramebuffers(1, &fbo);
    glBindFramebuffer(GL_FRAMEBUFFER, fbo);

    GLubyte *imageData = new GLubyte[TEXTURE_SIZE * TEXTURE_SIZE * 4];

    glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, tm->tex->getName(), 0);
    CCASSERT(glCheckFramebufferStatus(GL_FRAMEBUFFER) == GL_FRAMEBUFFER_COMPLETE, "Could not attach texture to framebuffer");

    Image *image = new Image();
    glPixelStorei(GL_PACK_ALIGNMENT, 1);
    glReadPixels(0, 0, TEXTURE_SIZE, TEXTURE_SIZE, GL_RGBA, GL_UNSIGNED_BYTE, imageData);
    image->initWithRawData(imageData, TEXTURE_SIZE * TEXTURE_SIZE * 4, TEXTURE_SIZE, TEXTURE_SIZE, 8);
    char filepath[128];
    sprintf(filepath, "texture0.png");
    image->saveToFile(FileUtils::getInstance()->getWritablePath() + filepath, true);
    CC_SAFE_DELETE(image);
    CC_SAFE_DELETE_ARRAY(imageData);

    glBindRenderbuffer(GL_RENDERBUFFER, oldRBO);
    glBindFramebuffer(GL_FRAMEBUFFER, oldFBO);
    glDeleteFramebuffers(1, &fbo);
#endif
    return tm;
}

bool TextureManager::hasTexModule(GLuint texModuleId) {
    return texModuleMap.find(texModuleId) != texModuleMap.end();
}

void TextureManager::delTexModule(GLuint texModuleId) {
    auto iter = texModuleMap.find(texModuleId);

    if(iter != texModuleMap.end()) {
        auto tm = iter->second;
        CC_SAFE_DELETE(tm);
        texModuleMap.erase(iter);
    }
}

TexModule *TextureManager::getTexModule(GLuint texModuleId) {
    auto iter = texModuleMap.find(texModuleId);

    if(iter != texModuleMap.end()) {
		iter->second->lastCallCounter = Director::loopCounter;
        return iter->second;
	}

    return nullptr;
}

ResImage *TextureManager::getImage(const std::string& name) {
    auto image = imageMap.at(name);

    if(image == nullptr && (name.rfind(".md") == std::string::npos)) {
        image = new ResImage(name);
        imageMap.insert(name, image);
        image->release();
    }

    return image;
}

Module *TextureManager::getModule(const std::string& name) {
    auto module = moduleMap.at(name);

    if(module == nullptr && (name.rfind(".md") != std::string::npos)) {
        module = new Module(name);
        moduleMap.insert(name, module);
        module->release();
    }

    return module;
}

std::string TextureManager::getTextureFilename(Texture2D *tex) {
    GLuint name = tex->getName();
    auto iter = texFilenameMap.find(name);

    if(iter != texFilenameMap.end()) {
        return iter->second;
    }

    return std::string();
}

TexModule *TextureManager::getImageTexModule(Texture2D *tex) {
    CCASSERT(tex->isReference(), "texture is real!!!");
    GLuint name = tex->getName();
    auto iter = texFilenameMap.find(name);

    if(iter != texFilenameMap.end()) {
        auto image = getImage(iter->second);
        return image->getTexModule();
    }

    return nullptr;
}

NS_CC_END