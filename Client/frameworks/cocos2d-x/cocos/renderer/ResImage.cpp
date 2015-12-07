#include "ResImage.h"
#include "base/StreamFile.h"
#include "platform/CCImage.h"
#include "platform/CCFileUtils.h"
#include "renderer/CCRenderer.h"
#include "renderer/TextureManager.h"

#include <zlib.h>

NS_CC_BEGIN

ResImage::ResImage(const std::string& name)
    : Ref(),
      name(name),
      texModuleId(0),
      width(0),
      height(0) {
    FileStream fs;

    if(!fs.open(FileUtils::getInstance()->fullPathForFilename(name))) return;

    DataInputStream dis(&fs);
    load(dis, true);
}

ResImage::~ResImage() {
    TextureManager::getInstance()->delTexModule(texModuleId);
}

void ResImage::load(DataInputStream& dis, bool onlyGetSize) {
    bool justJpg = name.rfind(".jpg") != std::string::npos;

    if(justJpg) {
        int jpgSize = dis.available();
        unsigned char *data = new unsigned char[jpgSize];
        dis.readBytes((char *) data, jpgSize);

        Image *image = nullptr;
        byte *buf = nullptr;

        do {
            image = new Image();
            CC_BREAK_IF(!image->initWithImageData(data, jpgSize));
            unsigned char *srcData = image->getData();
            int bits = image->hasAlpha() ? 4 : 3;
            int srcIndex = 0;
            width = image->getWidth();
            height = image->getHeight();
            CC_BREAK_IF(onlyGetSize);
            buf = new unsigned char[width * height * 4];

            for(int i = 0; i < height; ++i) {
                for(int j = 0; j < width; ++j) {
                    byte *srcPixel = srcData + srcIndex * bits;
                    byte *dstPixel = buf + (srcIndex << 2);
                    dstPixel[0] = srcPixel[0];
                    dstPixel[1] = srcPixel[1];
                    dstPixel[2] = srcPixel[2];
                    dstPixel[3] = 255;
                    srcIndex++;
                }
            }

            TextureManager::getInstance()->genTexModule(width, height, buf, &texModuleId);
        } while(0);

        CC_SAFE_DELETE(image);
        CC_SAFE_DELETE_ARRAY(data);
        CC_SAFE_DELETE_ARRAY(buf);
    } else {
        width = dis.readUShort();
        height = dis.readUShort();

        if(onlyGetSize) return;

        int type = dis.readByte();

        if(type == 1) {
            int palette[256];
            dis.readBytes((char *) palette, sizeof(palette));

            int compressedLength = dis.readInt();
            int count = width * height;
            int bufLen = (count << 2) + compressedLength;
            unsigned char *buf = new unsigned char[bufLen];

            Bytef *source = buf + bufLen - compressedLength;
            dis.readBytes((char *) source, compressedLength);

            uLongf destlen = count;
            byte *indexData = source - count;
            uncompress((Bytef *) indexData, &destlen, source, compressedLength);

            int *rgbaData = (int *) buf;

            for(int i = 0; i < count; ++i) {
                rgbaData[i] = palette[indexData[i]];
            }

            TextureManager::getInstance()->genTexModule(width, height, buf, &texModuleId);
            CC_SAFE_DELETE_ARRAY(buf);
		} else if (type == 2) {
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

				TextureManager::getInstance()->genTexModule(width, height, buf, &texModuleId);
			} while(0);

			CC_SAFE_DELETE(image);
			CC_SAFE_DELETE_ARRAY(buf);
		} else {
            int compressedLength = dis.readInt();
            int count = width * height;
            int bufLen = (count << 2) + compressedLength;
            unsigned char *buf = new unsigned char[bufLen];

            Bytef *source = buf + bufLen - compressedLength;
            dis.readBytes((char *) source, compressedLength);

            uLongf destlen = bufLen - compressedLength;
            uncompress((Bytef *) buf, &destlen, source, compressedLength);

            TextureManager::getInstance()->genTexModule(width, height, buf, &texModuleId);
            CC_SAFE_DELETE_ARRAY(buf);
        }
    }
}

TexModule *ResImage::getTexModule() {
    TexModule *tm = TextureManager::getInstance()->getTexModule(texModuleId);

    if(!tm) {
        FileStream fs;

        if(!fs.open(FileUtils::getInstance()->fullPathForFilename(name))) return nullptr;

        DataInputStream dis(&fs);
        load(dis);
    }

    return TextureManager::getInstance()->getTexModule(texModuleId);
}

NS_CC_END