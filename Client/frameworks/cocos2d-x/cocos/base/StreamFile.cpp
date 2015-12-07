#include "StreamFile.h"
#include "StreamMemData.h"
#include "base/CCData.h"
#include "platform/CCFileUtils.h"

NS_CC_BEGIN

FileStream::~FileStream() {
    close();
}

bool FileStream::open(const std::string& filename, const char *mode) {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    useMemStream = filename.find("assets") != std::string::npos;

    if(useMemStream) {
        CC_SAFE_DELETE(memStream);
        Data data = FileUtils::getInstance()->getDataFromFile(filename);
        CCLOG("use memstream %s", filename.c_str());
        memStream = new MemStream(data.getBytes(), data.getSize());
        data.fastSet(nullptr, 0);
        return true;
    }

#endif

    close();
    fp = fopen(filename.c_str(), mode);

    if(!fp) {
        CCLOG("File open Failed!! %s", filename.c_str());
        return false;
    } else {
        length = size();
        return true;
    }
}

bool FileStream::isFileExist(const std::string& filename) {
    if(filename.find('/') == std::string::npos && filename.find('\\') == std::string::npos) return true;

    FILE *fp = fopen(filename.c_str(), READ_BINARY);

    if(fp) {
        fclose(fp);
        return true;
    }

    return false;
}

int FileStream::read(unsigned char *data, int len) {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

    if(useMemStream) {
        return memStream->read(data, len);
    }

#endif

    return fread(data, sizeof(unsigned char), len, fp);
}

void FileStream::write(const unsigned char *data, int len) {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

    if(useMemStream) {
        memStream->write(data, len);
    }

#endif
    fwrite(data, sizeof(unsigned char), len, fp);
}

int FileStream::seek(int mode, int len) {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

    if(useMemStream) {
        return memStream->seek(mode, len);
    }

#endif

    return fseek(fp, len, mode);
}

void FileStream::flush() {
}

unsigned int FileStream::size() {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

    if(useMemStream) {
        return memStream->size();
    }

#endif

    if(length == -1) {
        fseek(fp, 0, SEEK_END);
        length = ftell(fp);
        fseek(fp, 0, SEEK_SET);
    }

    return length;
}

void FileStream::reset() {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

    if(useMemStream) {
        memStream->reset();
    }

#endif
    fseek(fp, 0, SEEK_SET);
}

void FileStream::close() {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    CC_SAFE_DELETE(memStream);
    useMemStream = false;
#endif

    if(fp) {
        fclose(fp);
        fp = nullptr;
    }
}

unsigned int FileStream::remainSize() {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

    if(useMemStream) {
        return memStream->remainSize();
    }

#endif

    int pos = ftell(fp);
    unsigned int len = length - pos;
    return len;
}

NS_CC_END