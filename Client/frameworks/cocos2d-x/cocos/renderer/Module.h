#pragma once

#include <string>
#include <vector>
#include <unordered_set>

#include "base/CCRef.h"
#include "base/ccTypes.h"
#include "base/StreamIO.h"

NS_CC_BEGIN

class TexModule;

class CC_DLL Module : public Ref {
public:
    enum class Format {
        PNG_MODULE,
        PNG_QUANT,
        JPG_ALL
    };

    std::string filename;

    Module(const std::string& filename);

    ~Module();

    bool isOutOfBounds(int moduleId) {
        return !moduleInfo || moduleId < 0 || moduleId >= moduleCount;
    }

	int getModuleCount() {
		return moduleCount;
	}

	Rect getModuleRect(int moduleId) {
		if (isOutOfBounds(moduleId)) return Rect::ZERO;

		int x = moduleInfo[(moduleId << 2) + 0];
		int y = moduleInfo[(moduleId << 2) + 1];
		int w = moduleInfo[(moduleId << 2) + 2];
		int h = moduleInfo[(moduleId << 2) + 3];

		return Rect(x, y, w, h);
	}

	Vec2 getModuleOffset(int moduleId) {
		if (isOutOfBounds(moduleId)) return Vec2::ZERO;

		int x = moduleInfo[(moduleId << 2) + 0];
		int y = moduleInfo[(moduleId << 2) + 1];

		return Vec2(x, y);
	}

	std::string getModuleName(int moduleId) {
		if (isOutOfBounds(moduleId)) return "";

		return moduleNames[moduleId];
	}

    Size getModuleSize(int moduleId) {
		if (isOutOfBounds(moduleId)) return Size::ZERO;

		int w = moduleInfo[(moduleId << 2) + 2];
		int h = moduleInfo[(moduleId << 2) + 3];

		return Size(w, h);
    }

    void load(DataInputStream& dis);

    TexModule *getTexModule(int moduleId);
private:
    unsigned short *moduleInfo;

    int moduleCount;

	std::string *moduleNames;

    GLuint *texModuleInfo;

    int *skipInfo;

    unsigned char *paletteInfo;

    Format format;
};

NS_CC_END