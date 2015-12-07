
/*
* Copyright (c) 2013-2015 Tony Zhengjq <zhengjq@163.com>
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
* 1. Redistributions of source code must retain the above copyright
*    notice, this list of conditions and the following disclaimer.
* 2. Redistributions in binary form must reproduce the above copyright
*    notice, this list of conditions and the following disclaimer in the
*    documentation and/or other materials provided with the distribution.
* 3. The name of the author may not be used to endorse or promote products
*    derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
* IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
* OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
* IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
* INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
* NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
* DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
* THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
* THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

#ifndef yvpacket_sdk_h
#define yvpacket_sdk_h


#ifdef _MSC_VER
#ifdef YVPACKET_EXPORTS
#define YVPACKET_API  extern "C" __declspec(dllexport)
#else
#define YVPACKET_API /*__declspec(dllimport)*/
#endif

#else
#define YVPACKET_API	
#endif

#define OK_YVPACKET			0
#define ERROR_YVPACKET		-1

#define YVPACKET	unsigned int
#define YV_PARSER	unsigned int

#define  praser_null 0

#ifdef __cplusplus
extern "C" {
#endif

//创建yvPacket句柄
YVPACKET_API YVPACKET sdk_yvpacket();

//关闭yvPacket句柄
YVPACKET_API void sdk_close(YVPACKET handle);

//内存分模式默认回收
YVPACKET_API int sdk_recycling(YV_PARSER parser);

//获取默认解析器
YVPACKET_API YV_PARSER yvpacket_get_parser();

//获取默认解析器
YVPACKET_API YV_PARSER yvpacket_get_parser_object(YV_PARSER parser);

//解析器数据复制
YVPACKET_API int parser_copy(YV_PARSER dst, YV_PARSER src);

//==================encode===========================
YVPACKET_API void parser_set_object(YV_PARSER parser, unsigned char cmdId, YV_PARSER value);
YVPACKET_API void parser_set_uint8(YV_PARSER parser, unsigned char cmdId, unsigned char value);
YVPACKET_API void parser_set_uint32(YV_PARSER parser, unsigned char cmdId, unsigned int value);
YVPACKET_API void parser_set_integer(YV_PARSER parser, unsigned char cmdId, int value);
YVPACKET_API void parser_set_string(YV_PARSER parser, unsigned char cmdId, char* value);
 YVPACKET_API void parser_set_buffer(YV_PARSER parser, unsigned char cmdId, char* value, int len);

//调用后可以直接读出数据
YVPACKET_API void parser_ready(YV_PARSER parser);
//=================decode============================
YVPACKET_API void parser_get_object(YV_PARSER parser, unsigned char cmdId, YV_PARSER object, int index);
YVPACKET_API unsigned char parser_get_uint8(YV_PARSER parser, unsigned char cmdId, int index);
YVPACKET_API unsigned int parser_get_uint32(YV_PARSER parser, unsigned char cmdId, int index);
YVPACKET_API int parser_get_integer(YV_PARSER parser, unsigned char cmdId, int index);
YVPACKET_API char* parser_get_string(YV_PARSER parser, unsigned char cmdId, int index);
YVPACKET_API char* parser_get_buffer(YV_PARSER parser, unsigned char cmdId, int* len, int index);
YVPACKET_API bool parser_is_empty(YV_PARSER parser, unsigned char cmdId, int index);

#ifdef __cplusplus
}
#endif
#endif

