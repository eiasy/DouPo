
#include "Encrypt.h"
#include <stdlib.h>
#include <memory.h>
#include <zconf.h>
#include <zlib.h>
#include <time.h>
#include "cocos2d.h"

#if CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID
#include <netinet/in.h>
#endif

void* Encrypt::encrypt(int* dstLength,void* src,int srcLength)
{
	int zipLen;
	void* zipData = doZip(&zipLen,src,srcLength);
	if(zipData == NULL)
	{
		return NULL;
	}
	int xorLen;
	void* xorData = doXor(&xorLen,zipData,zipLen);
	delete[]((char *) zipData);
	if(xorData == NULL)
	{
		return NULL;
	}
	*dstLength = xorLen;
	return xorData;
}

void* Encrypt::decrypt(int* dstLength,void* src,int srcLength)
{
	int zipLen;
	void* zipData = unXor(&zipLen,src,srcLength);
	if(zipData == NULL)
	{
		return NULL;
	}
	int oriLen;
	void* oriData = unZip(&oriLen,zipData,zipLen);
	delete[]((char *) zipData);
	if(oriData == NULL)
	{
		return NULL;
	}
	*dstLength = oriLen;
	return oriData;
}

void* Encrypt::encryptWithoutZip(int* dstLength,void* src,int srcLength)
{
	int xorLen;
	void* xorData = doXor(&xorLen,src,srcLength);
	if(xorData == NULL)
	{
		return NULL;
	}
	*dstLength = xorLen;
	return xorData;
}

void* Encrypt::decryptWithoutZip(int* dstLength,void* src,int srcLength)
{
	int oriLen;
	void* oriData = unXor(&oriLen,src,srcLength);
	if(oriData == NULL)
	{
		return NULL;
	}
	*dstLength = oriLen;
	return oriData;
}

void* Encrypt::doZip(int* dstLength,void* src,int srcLength)
{
	uLong boundSize = compressBound(srcLength);
	char* ret = new char[sizeof(int)+boundSize];
	if(ret == NULL)
	{
		return NULL;
	}
	if(compress2((Byte*)ret+sizeof(int),&boundSize,(Byte*)src,srcLength,ENCRYPT_ZIP_LEVEL) != 0)
	{
		delete[](ret);
		return NULL;
	}
	*(int*)ret = srcLength;
	*dstLength = sizeof(int) + boundSize;
	return ret;
}

void* Encrypt::unZip(int* dstLength,void* src,int srcLength)
{
	uLong retLength = *(int*)src;
	char* ret = new char[retLength];
	if(ret == NULL)
	{
		return NULL;
	}
	if(uncompress((Byte*)ret,&retLength,(Byte*)src+sizeof(int),srcLength) != 0)
	{
		delete[](ret);
		return NULL;
	}
	*dstLength = retLength;
	return ret;
}

void* Encrypt::doXor(int* dstLength,void* src,int srcLength)
{
	int retLength = (sizeof(int) + srcLength+sizeof(int)-1)/sizeof(int)*sizeof(int);	// 对齐后长度
	int difference = srcLength & 0x00000003;
	if(difference)
	{
		difference = 4 - difference;
	}
	int randomNumber = getRandom(src,srcLength);
	randomNumber = (randomNumber&0xFFFFFFFC) | difference;
	char* ret = new char[retLength];
	if(ret == NULL)
	{
		return NULL;
	}
	memset(ret,0,retLength);
	memcpy(ret+sizeof(int),src,srcLength);
	*(int*)ret = randomNumber;
	for(int i=sizeof(int);i<retLength;i+=sizeof(int))
	{
		*(int*)(ret+i) ^= randomNumber ^= 0x1F3D5B79;
	}
	*dstLength = retLength;
	return ret;
}

void* Encrypt::unXor(int* dstLength,void* src,int srcLength)
{
	int randomNumber = *(int*)src;
	int difference = randomNumber & 0x00000003;
	int memLength = srcLength - sizeof(int);
	int retLength = memLength - difference;
	char* ret = new char[memLength];
	if(ret == NULL)
	{
		return NULL;
	}
	memcpy(ret,(char*)src+sizeof(int),memLength);
	for(int i=0;i<memLength;i+=sizeof(int))
	{
		*(int*)(ret+i) ^= randomNumber ^= 0x1F3D5B79;
	}
	*dstLength = retLength;
	return ret;
}

// ForNet

union IntBytes
{
	int i;
	char c[4];
};

void* Encrypt::encryptForNet(int* dstLength,void* src,int srcLength,int random)
{
	int zipLength;
	void* zip = doZipForNet(&zipLength,src,srcLength);
	if(zip == NULL)
	{
		return NULL;
	}
	int retLength;
	void* ret = doXorForNet(&retLength,zip,zipLength,random);
	delete[]((char *) zip);
	if(ret == NULL)
	{
		return NULL;
	}
	*dstLength = retLength;
	return ret;
}

void* Encrypt::decryptForNet(int* dstLength,void* src,int srcLength)
{
	int zipLength;
	void* zip = unXorForNet(&zipLength,src,srcLength);
	if(zip == NULL)
	{
		return NULL;
	}
	int retLength;
	void* ret = unZipForNet(&retLength,zip,zipLength);
	delete[]((char *) zip);
	if(ret == NULL)
	{
		return NULL;
	}
	*dstLength = retLength;
	return ret;
}

void* Encrypt::doZipForNet(int* dstLength,void* src,int srcLength)
{
	int* ret = (int*)doZip(dstLength,src,srcLength);
	*ret = htonl(*ret);
	return ret;
}

void* Encrypt::unZipForNet(int* dstLength,void* src,int srcLength)
{
	int* rev((int*)src);
	*rev = htonl(*rev);
	void* ret = unZip(dstLength,src,srcLength);
	*rev = htonl(*rev);
	return ret;
}

void* Encrypt::doXorForNet(int* dstLength,void* src,int srcLength,int random)
{
	int retLength = (sizeof(int) + srcLength+sizeof(int)-1)/sizeof(int)*sizeof(int);	// 对齐后长度
	int difference = srcLength & 0x00000003;
	if(difference)
	{
		difference = 4 - difference;
	}
	IntBytes rand;
	rand.i = random;
	rand.c[2] = (rand.c[2]&0xFC) | difference;
	char* ret = new char[retLength];
	if(ret == NULL)
	{
		return NULL;
	}
	memset(ret,0,retLength);
	memcpy(ret+sizeof(int),src,srcLength);
	ret[0] = rand.c[0];
	ret[1] = rand.c[1];
	ret[2] = rand.c[2];
	ret[3] = rand.c[3];
	for(int i=sizeof(int);i<retLength;i+=sizeof(int))
	{
		ret[i+0] ^= (rand.c[0] += 0x1F);
		ret[i+1] ^= (rand.c[1] += 0x3D);
		ret[i+2] ^= (rand.c[2] += 0x5B);
		ret[i+3] ^= (rand.c[3] += 0x79);
	}
	*dstLength = retLength;
	return ret;
}

void* Encrypt::unXorForNet(int* dstLength,void* src,int srcLength)
{
	IntBytes rand;
	rand.c[0] = ((char*)src)[0];
	rand.c[1] = ((char*)src)[1];
	rand.c[2] = ((char*)src)[2];
	rand.c[3] = ((char*)src)[3];
	int difference = rand.c[2] & 0x03;
	int memLength = srcLength - sizeof(int);
	int retLength = memLength - difference;
	char* ret = new char[memLength];
	if(ret == NULL)
	{
		return NULL;
	}
	memcpy(ret,(char*)src+sizeof(int),memLength);
	for(int i=0;i<memLength;i+=sizeof(int))
	{
		ret[i+0] ^= (rand.c[0] += 0x1F);
		ret[i+1] ^= (rand.c[1] += 0x3D);
		ret[i+2] ^= (rand.c[2] += 0x5B);
		ret[i+3] ^= (rand.c[3] += 0x79);
	}
	*dstLength = retLength;
	return ret;
}

int Encrypt::getRandom(void* data,int length)
{
	int randomNumber=0x18998874;
	int* pInt = (int*)data;
	int len = length>>2;
	for(int i=0;i<len;i++)
	{
		randomNumber ^= pInt[i];
	}
	return randomNumber;
}
