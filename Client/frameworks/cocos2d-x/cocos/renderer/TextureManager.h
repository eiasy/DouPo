#pragma once

#include <unordered_set>
#include <unordered_map>
#include <vector>

#include "base/CCEventListenerCustom.h"
#include "base/CCMap.h"
#include "base/CCRef.h"
#include "renderer/CCTexture2D.h"
#include "renderer/ResImage.h"
#include "renderer/Module.h"

NS_CC_BEGIN

class CC_DLL IDGenerator {
private:
    std::unordered_set<GLuint> sets;
    GLuint nextId;

public:
    IDGenerator();

    void genId(GLsizei n, GLuint *ids);

    void deleteId(GLuint id);

    void clear();
};

class CC_DLL TexModule {
public:
    Texture2D *tex;

    unsigned short x;
    unsigned short y;
    unsigned short width;
    unsigned short height;

    unsigned int lastCallCounter;

    TexModule()
        : tex(0),
          x(0),
          y(0),
          width(0),
          height(0),
          lastCallCounter(0) {
    }
};

class TextureBinPack;

class CC_DLL TextureManager : public Ref {
private:
    IDGenerator texCoordGenerator;
    std::unordered_map<GLuint, Texture2D *> texCoordCalculatedMap;

    GLuint nextTexModuleId;

    IDGenerator texGenerator;
    std::unordered_map<GLuint, std::string> texFilenameMap;

    Map<std::string, ResImage *> imageMap;
    Map<std::string, Module *> moduleMap;

    std::vector<Texture2D *> textures;
    std::vector<TextureBinPack *> textureBins;
    std::unordered_map<GLuint, TexModule *> texModuleMap;

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    EventListenerCustom *_backToForegroundlistener;
#endif

    TextureManager();

    static TextureManager *instance;

    void clear();
public:

    virtual ~TextureManager();

    static TextureManager *getInstance() {
        if(instance == nullptr) {
            instance = new TextureManager();
        }

        return instance;
    }

    static void destroyInstance() {
        CC_SAFE_DELETE(instance);
    }

    void reloadShaders();

    bool hasCalculatedTexCoord(GLuint texCoordId);

    void calculatedTexCoord(GLuint texCoordId, TexModule *tm);

    void refreshTexCoordId(GLuint& id) {
        GLuint tmp = id;
        genTexCoordId(1, &id);

        if(tmp) {
            delTexCoordId(tmp);
        }
    }

    void genTexCoordId(GLsizei n, GLuint *ids) {
        texCoordGenerator.genId(n, ids);
    }

    void delTexCoordId(GLuint& texCoordId);

    void genTexId(const std::string filename, GLuint *texId) {
        texGenerator.genId(1, texId);
        texFilenameMap[*texId] = filename;
    }

    void delTexId(GLuint& texId) {
        if(texId) {
            texGenerator.deleteId(texId);
            texFilenameMap.erase(texId);
            texId = 0;
        }
    }

    TexModule *getTexModule(GLuint texModuleId);

    bool hasTexModule(GLuint texModuleId);

    void delTexModule(GLuint texModuleId);

    TexModule *genTexModule(int width, int height, unsigned char *data, GLuint *texModuleId);

    void reloadTextures();

    ResImage *getImage(const std::string& name);

    Module *getModule(const std::string& name);

    std::string getTextureFilename(Texture2D *tex);

    TexModule *getImageTexModule(Texture2D *tex);
};

NS_CC_END