#pragma once

#include <string>

#include "base/CCRef.h"
#include "base/StreamIO.h"
#include "platform/CCGL.h"

NS_CC_BEGIN

class TexModule;

class CC_DLL ResImage : public Ref {
private:
    GLuint texModuleId;

    unsigned width;

    unsigned height;
public:
    std::string name;

    ResImage(const std::string& name);

    ~ResImage();

    unsigned getWidth() {
        return width;
    }

    unsigned getHeight() {
        return height;
    }

    GLuint getTexModuleId() {
        return texModuleId;
    }

    void load(DataInputStream& dis, bool onlyGetSize = false);

    TexModule *getTexModule();
};

NS_CC_END