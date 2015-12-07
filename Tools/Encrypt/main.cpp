
#include <stdio.h>
#include <string.h>
#include <Encrypt.h>

int main(int argc,char* args[])
{
	if(argc == 1)
	{
		return 1;
	}
	FILE* fileRead = fopen(args[1],"rb");
	if(!fileRead)
	{
		return 2;
	}
	fseek(fileRead,0,SEEK_END);
	int fileSize = ftell(fileRead);
	char* fileData = new char[fileSize];
	fseek(fileRead,0,SEEK_SET);
	fread(fileData,fileSize,1,fileRead);
	fclose(fileRead);
	//////////////////////////////////////////////////////////////////////////
	int enLen;
	void* enData = Encrypt::encrypt(&enLen,fileData,fileSize);
	int deLen;
	void* deData = Encrypt::decrypt(&deLen,enData,enLen);

	bool same = (deLen == fileSize) && 0 == memcmp(fileData,deData,deLen);
	if(!same)
	{
		return 3;
	}
	//////////////////////////////////////////////////////////////////////////
	FILE* fileWrite = fopen(args[argc==2?1:2],"wb");
	if(!fileWrite)
	{
		return 2;
	}
	fwrite(enData,enLen,1,fileWrite);
	fclose(fileWrite);
	return 0;
}
