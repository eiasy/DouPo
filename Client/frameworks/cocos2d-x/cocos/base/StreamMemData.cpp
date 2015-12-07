#include "StreamMemData.h"
#include "StreamFile.h"
#include "platform/CCFileUtils.h"
#include "base/ZipUtils.h"

NS_CC_BEGIN

MemStream *MemStream::createfromFile(const std::string& filename) {
    Data data = FileUtils::getInstance()->getDataFromFile(filename);

    if(data.getSize() <= 0) return nullptr;

    MemStream *stream = new MemStream(data.getBytes(), data.getSize());
    data.fastSet(nullptr, 0);
    return stream;
}

MemStream *MemStream::createfromZipFile(DataInputStream& dis) {
    unsigned int length;
    char *inBuf = dis.readBytes(length);
    unsigned char *outBuf;
    length = ZipUtils::inflateMemory((unsigned char *)inBuf, length, &outBuf);
    delete [] inBuf;
    MemStream *stream = new MemStream(outBuf, length);
    return stream;
}

MemStream::~MemStream() {
    if(toRelease) {
        CC_SAFE_DELETE_ARRAY(buf);
    }
}

int MemStream::read(unsigned char *data, int len) {
    if(pos + len > length) {
        len = length - pos;
    }

    memcpy(data, buf + pos, len);
    pos += len;
    return len;
}

void MemStream::write(const unsigned char *data, int len) {
    if(pos + len > length) {
        length = pos + len + length / 4;
        unsigned char *newArray = new unsigned char[length];
        memcpy(newArray, buf, pos);
        delete [] buf;
        buf = newArray;
    }

    memcpy((buf + pos), data, len);
    pos += len;
}

int MemStream::seek(int mode, int  len) {
    switch(mode) {
        case SEEK_CUR:
            pos += len;
            break;

        case SEEK_SET:
            pos = len;
            break;

        case SEEK_END:
            pos = length - len;
            break;
    }

    return 1;
}

void MemStream::flush() {}

void MemStream::reset() {
    pos = 0;
}

unsigned int MemStream::size() {
    return length;
}

unsigned char *MemStream::getBytes() {
    return buf;
}

void MemStream::close() {

}

unsigned int MemStream::remainSize() {
    return length - pos;
}

unsigned int MemStream::getPos() {
    return pos;
}

NS_CC_END