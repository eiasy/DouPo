
////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2014 华益天信科技(北京)有限公司. All rights reserved.
//
//  File:      Encrypt.h
//
//  Purpose:   加密、解密模块
//
//  History:
//  2014-05-21 安迷             Created.
//  0000-00-00 ____YourName____ ____TheDescription____
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _ENCRYPT_H_
#define _ENCRYPT_H_

// ZIP压缩等级
#define ENCRYPT_ZIP_LEVEL	9

class Encrypt
{
public:		// function
	// OUT dstLength
	// IN  src
	// IN  srcLength
	// RETURN dst// use delete[] to free memory
	// if failed return NULL
	static void* encrypt(int* dstLength,void* src,int srcLength);	// 适用于（非）压缩的文件
	static void* decrypt(int* dstLength,void* src,int srcLength);
	static void* encryptWithoutZip(int* dstLength,void* src,int srcLength);	// 适用于（已）压缩的文件
	static void* decryptWithoutZip(int* dstLength,void* src,int srcLength);
	static void* doZip(int* dstLength,void* src,int srcLength);
	static void* unZip(int* dstLength,void* src,int srcLength);
	static void* doXor(int* dstLength,void* src,int srcLength);
	static void* unXor(int* dstLength,void* src,int srcLength);
	static void* encryptForNet(int* dstLength,void* src,int srcLength,int random);	// 用于网络数据加密
	static void* decryptForNet(int* dstLength,void* src,int srcLength);
	static void* doZipForNet(int* dstLength,void* src,int srcLength);
	static void* unZipForNet(int* dstLength,void* src,int srcLength);
	static void* doXorForNet(int* dstLength,void* src,int srcLength,int random);
	static void* unXorForNet(int* dstLength,void* src,int srcLength);
protected:	// function
	static int getRandom(void* data,int length);
private:	// function
public:		// member
protected:	// member
private:	// member
};

#endif	//_ENCRYPT_H_
