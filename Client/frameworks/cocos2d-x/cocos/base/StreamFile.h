#pragma once

#include "StreamIO.h"

NS_CC_BEGIN

class MemStream;

#define WRITE_BINARY "wb"
#define READ_BINARY "rb"

class CC_DLL FileStream : public IOStream {
private:
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    MemStream *memStream;
    bool useMemStream;
#endif

    FILE *fp;
    int length;
public:
    FileStream() :
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
        memStream(nullptr), useMemStream(false),
#endif
        fp(nullptr),
        length(-1) {}

    ~FileStream();

    static FileStream *create() {
        FileStream *fs = new FileStream();
        fs->autorelease();
        return fs;
    }

    bool open(const std::string& filename, const char *mode = READ_BINARY);

    static bool isFileExist(const std::string& filename);

    int read(unsigned char *data, int len) override;

    void write(const unsigned char *data, int len) override;

    int seek(int mode, int len) override;

    void flush() override;

    void reset() override;

    unsigned int size() override;

    unsigned int remainSize() override;

    void close() override;
};

NS_CC_END