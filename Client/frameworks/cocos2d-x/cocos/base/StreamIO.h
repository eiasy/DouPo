#pragma once

#include "base/ccMacros.h"

NS_CC_BEGIN

class CC_DLL IOStream : public Ref {
protected:
    IOStream() {}

    IOStream(const IOStream& io) {}

    virtual ~IOStream() {}

public:
    virtual int read(unsigned char *data, int len) = 0;
    virtual void write(const unsigned char *data, int len) = 0;
    virtual int seek(int mode, int len) = 0;
    virtual void flush() = 0;
    virtual void reset() = 0;
    virtual unsigned int size() = 0;
    virtual void close() = 0;
    virtual unsigned int remainSize() = 0;
};

class DataInputStream : public Ref {
public:
    IOStream *in;
private:
    bool isByteOrderHHLL;

public:

    ~DataInputStream() {}

    DataInputStream(IOStream *in, bool isByteOrderHHLL = true) : in(in), isByteOrderHHLL(isByteOrderHHLL) {}

    static DataInputStream *create(IOStream *in) {
        DataInputStream *dis = new DataInputStream(in);
        dis->autorelease();
        return dis;
    }

    void readBytes(char *buf, int length) {
        in->read((unsigned char *)buf, length);
    }

    char readByte() {
        char buf = 0;
        in->read((unsigned char *)&buf, sizeof(buf));
        return buf;
    }

    int readInt() {
        int buf = 0;
        in->read((unsigned char *)&buf, sizeof(buf));

        if(isByteOrderHHLL) {
            buf = CC_SWAP32(buf);
        }

        return buf;
    }

    short readShort() {
        short buf = 0;
        in->read((unsigned char *)&buf, sizeof(buf));

        if(isByteOrderHHLL) {
            buf = CC_SWAP16(buf);
        }

        return buf;
    }

    short readVarShort() {
        int data1 = readByte() & 0xFF;

        if((data1 & 0x80) == 0) {
            if((data1 & 0x40) == 0) {
                return (short)(data1 & 0x3F);
            } else {
                return (short)(0xFFC0 + (data1 & 0x3F));
            }
        } else {
            int data2 = readByte() & 0xFF;
            return (short)(((data1 & 0x40) << 9) + ((data1 & 0x7F) << 8) + data2);
        }
    }

    short readUnsignedVarShort() {
        int data1 = readByte() & 0xFF;

        if((data1 & 0x80) == 0) {
            return (short) data1;
        } else {
            int data2 = readByte() & 0xFF;
            return (short)((data1 & 0x7F) << 8 | data2);
        }
    }

    unsigned char readUByte() {
        unsigned char buf = 0;
        in->read(&buf, sizeof(buf));
        return buf;
    }

    unsigned short readUShort() {
        unsigned short buf = 0;
        in->read((unsigned char *)&buf, sizeof(buf));

        if(isByteOrderHHLL) {
            buf = CC_SWAP16(buf);
        }

        return buf;
    }

    unsigned int readUInt() {
        unsigned int buf = 0;
        in->read((unsigned char *)&buf, sizeof(buf));

        if(isByteOrderHHLL) {
            buf = CC_SWAP32(buf);
        }

        return buf;
    }

    char readChar() {
        return readByte();
    }

    long long readLong() {
        long long high = readUInt();
        int low = readUInt();
        return (high << 32) | low;
    }

    float readFloat() {
        int bits = readInt();
        float result = (float&) bits;
        return result;
    }

    double readDouble() {
        long long bits = readLong();
        double result = (double&) bits;
        return result;
    }

    char *readChars() {
        short length = readShort();

        if(length != 0) {
            char *buf = new char[length + 1];
            in->read((unsigned char *)buf, length);
            buf[length] = 0;
            return buf;
        } else {
            return nullptr;
        }
    }

    std::string readString() {
        std::string temp = "";
        char *str = readChars();

        if(str) {
            temp = (char *)str;
            delete [] str;
        }

        return temp;
    }

    char *readBytes(unsigned int& length) {
        unsigned int len = in->remainSize();
        char *buf = new char[len];
        length = len;
        in->read((unsigned char *)buf, len);
        return buf;
    }

    bool readBoolean() {
        char c = readChar();
        bool b = c > 0;
        return b;
    }

    int available() {
        return in->remainSize();
    }

    int seek(int mode, int len) {
        return in->seek(mode, len);
    }
};

class DataOutputStream : public Ref {
private:
    bool isByteOrderHHLL;

    IOStream *out;

    DataOutputStream() {}

public:
    ~DataOutputStream() {}

    DataOutputStream(IOStream *out, bool isByteOrderHHLL = true) : out(out), isByteOrderHHLL(isByteOrderHHLL) {}

    static DataOutputStream *create(IOStream *in) {
        DataOutputStream *dos = new DataOutputStream(in);
        dos->autorelease();
        return dos;
    }

    void writeBytes(const unsigned char *buf, int aLength) {
        out->write((unsigned char *)buf, aLength);
    }

    void writeByte(unsigned char v) {
        out->write((unsigned char *)&v, sizeof(v));
    }

    void writeInt(int v) {
        if(isByteOrderHHLL) {
            v = CC_SWAP32(v);
        }

        out->write((unsigned char *)&v, sizeof(v));
    }

    void writeShort(short v) {
        if(isByteOrderHHLL) {
            v = CC_SWAP16(v);
        }

        out->write((unsigned char *)&v, sizeof(v));
    }

    void writeUByte(unsigned char v) {
        out->write((unsigned char *)&v, sizeof(v));
    }

    void writeUInt(unsigned int v) {
        if(isByteOrderHHLL) {
            v = CC_SWAP32(v);
        }

        out->write((unsigned char *)&v, sizeof(v));
    }

    void writeUShort(unsigned short v) {
        if(isByteOrderHHLL) {
            v = CC_SWAP16(v);
        }

        out->write((unsigned char *)&v, sizeof(v));
    }

    void writeChar(char v) {
        writeUByte(v);
    }

    void writeLong(long long v) {
        unsigned int high = (unsigned int)(v >> 32);
        unsigned int low = (unsigned int) v;
        writeUInt(high);
        writeUInt(low);
    }

    void writeFloat(float v) {
        out->write((unsigned char *)&v, sizeof(v));
    }

    void writeDouble(double v) {
        long long l(v);
        writeLong(l);
    }

    void writeChars(const char *v) {
        unsigned int length = strlen((char *)v);
        writeUShort(length);
        out->write((unsigned char *)v, length);
    }

    void writeString(const std::string& str) {
        writeChars((char *)str.c_str());
    }

    void writeBoolean(bool b) {
        writeByte((char)b);
    }
};

NS_CC_END