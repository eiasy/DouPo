#include "Module.h"
#include "base/StreamFile.h"
#include "base/StreamIO.h"
#include "platform/CCImage.h"
#include "platform/CCFileUtils.h"
#include "renderer/TextureManager.h"

#include <zlib.h>

NS_CC_BEGIN

Module::Module(const std::string& filename)
    : Ref(),
      filename(filename),
      moduleInfo(nullptr),
      moduleCount(0),
      texModuleInfo(nullptr),
      skipInfo(nullptr),
      paletteInfo(nullptr),
      moduleNames(nullptr),
      format(Format::PNG_MODULE) {
    FileStream fs;

    if(!fs.open(FileUtils::getInstance()->fullPathForFilename(filename))) return;

    DataInputStream dis(&fs);
    load(dis);
}

Module::~Module() {
    if(texModuleInfo) {
        for(int i = 0; i < moduleCount; ++i) {
            TextureManager::getInstance()->delTexModule(texModuleInfo[i]);
        }
    }

    CC_SAFE_DELETE_ARRAY(moduleNames);
    CC_SAFE_DELETE_ARRAY(skipInfo);
    CC_SAFE_DELETE_ARRAY(paletteInfo);
    CC_SAFE_DELETE_ARRAY(texModuleInfo);
    CC_SAFE_DELETE_ARRAY(moduleInfo);
}

void Module::load(DataInputStream& dis) {
    int fileLength = dis.available();
    moduleCount = dis.readShort();
    moduleCount &= 0x7FFF;

	texModuleInfo = new GLuint[moduleCount];
	memset(texModuleInfo, 0, sizeof(GLuint) * moduleCount);

    int length = moduleCount << 2;
    moduleInfo = new unsigned short[length];

    for(int i = 0; i < length; ++i) {
        moduleInfo[i] = dis.readUShort();
    }

	moduleNames = new std::string[moduleCount];
	for (int i = 0; i < moduleCount; ++i) {
		moduleNames[i] = dis.readString();
	}

    format = (Format) dis.readByte();
    skipInfo = new int[moduleCount];

    if(format == Format::JPG_ALL) {
        skipInfo[0] = fileLength - dis.available();
    } else {
        for(int i = 0; i < moduleCount; ++i) {
            skipInfo[i] = dis.readInt();
        }
    }

    if(format == Format::PNG_QUANT) {
        length = dis.readShort() << 10;
        paletteInfo = new unsigned char[length];
        dis.readBytes((char *) paletteInfo, length);
    }
}

TexModule *Module::getTexModule(int moduleId) {
    if(isOutOfBounds(moduleId)) return nullptr;

    TexModule *tm = TextureManager::getInstance()->getTexModule(texModuleInfo[moduleId]);

    if(tm) return tm;

    FileStream fs;

    if(!fs.open(FileUtils::getInstance()->fullPathForFilename(filename))) return nullptr;

    DataInputStream dis(&fs);
    auto size = getModuleSize(moduleId);
    int width = size.width;
    int height = size.height;
    int count = width * height;
    dis.seek(SEEK_SET, skipInfo[moduleId]);

    if(format == Format::PNG_MODULE || format == Format::PNG_QUANT) {
        do {
            if(format == Format::PNG_QUANT) {
                int paletteId = dis.readShort();

                if(paletteId >= 0) {
                    int palette[256];
                    memcpy(palette, paletteInfo + (paletteId << 10), 1024);

                    int compressedLength = dis.readInt();
                    int bufLen = (count << 2) + compressedLength;
                    unsigned char *buf = new unsigned char[bufLen];

                    Bytef *source = buf + bufLen - compressedLength;
                    dis.readBytes((char *) source, compressedLength);

                    uLongf destlen = count;
                    unsigned char *indexData = source - count;
                    uncompress((Bytef *) indexData, &destlen, source, compressedLength);

                    int *rgbaData = (int *) buf;

                    for(int i = 0; i < count; ++i) {
                        rgbaData[i] = palette[indexData[i]];
                    }

                    tm = TextureManager::getInstance()->genTexModule(width, height, buf, &texModuleInfo[moduleId]);
                    CC_SAFE_DELETE_ARRAY(buf);
                    break;
                }
            }

            int compressedLength = dis.readInt();
            int bufLen = (count << 2) + compressedLength;
            unsigned char *buf = new unsigned char[bufLen];
            Bytef *source = buf + bufLen - compressedLength;
            dis.readBytes((char *) source, compressedLength);
            uLongf destlen = bufLen - compressedLength;
            uncompress((Bytef *) buf, &destlen, source, compressedLength);

            tm = TextureManager::getInstance()->genTexModule(width, height, buf, &texModuleInfo[moduleId]);
            CC_SAFE_DELETE_ARRAY(buf);
        } while(0);
    } else if(format == Format::JPG_ALL) {
        int count = width * height;
        int compressedLen = dis.readInt();
        int jpgSize = dis.available() - compressedLen;
        int bufLen = std::max(compressedLen, jpgSize) + (count << 2);
        unsigned char *buf = new unsigned char[bufLen];
        dis.readBytes((char *) buf, compressedLen);

        Image *image = nullptr;

        do {
            uLongf destlen = count;
            Bytef *alpha = buf + (bufLen - count);
            CC_BREAK_IF(uncompress(alpha, &destlen, (Bytef *)buf, compressedLen) != Z_OK);
            dis.readBytes((char *) buf, jpgSize);

            image = new Image();
            CC_BREAK_IF(!image->initWithImageData(buf, jpgSize));
            unsigned char *srcData = image->getData();
            int bits = image->hasAlpha() ? 4 : 3;
            int srcIndex = 0;

            for(int i = 0; i < height; ++i) {
                for(int j = 0; j < width; ++j) {
                    unsigned char *srcPixel = srcData + srcIndex * bits;
                    unsigned char *dstPixel = buf + (srcIndex << 2);
                    dstPixel[0] = srcPixel[0];
                    dstPixel[1] = srcPixel[1];
                    dstPixel[2] = srcPixel[2];
                    dstPixel[3] = alpha[srcIndex];
                    srcIndex++;
                }
            }

            tm = TextureManager::getInstance()->genTexModule(width, height, buf, &texModuleInfo[moduleId]);
        } while(0);

        CC_SAFE_DELETE(image);
        CC_SAFE_DELETE_ARRAY(buf);
    }

    return tm;
}

NS_CC_END