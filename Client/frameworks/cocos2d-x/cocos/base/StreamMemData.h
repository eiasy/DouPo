#pragma once

#include "StreamIO.h"

NS_CC_BEGIN

class CC_DLL MemStream : public IOStream {
private:
    unsigned char *buf;

    mutable int pos;

    int length;

    bool toRelease;

public:

    MemStream(unsigned char *buf, int length, bool toRelease = true) : buf(buf), pos(0), length(length), toRelease(toRelease) {}

    static MemStream *createfromFile(const std::string& filename);

    static MemStream *createfromZipFile(DataInputStream& dis);

    ~MemStream();

    int read(unsigned char *data, int len) override;

    void write(const unsigned char *data, int len) override;

    int seek(int mode, int len) override;

    void flush() override;

    void reset() override;

    unsigned int size() override;

    unsigned char *getBytes();

    unsigned int remainSize() override;

    unsigned int getPos();

    void close() override;
};

NS_CC_END