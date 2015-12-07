package mmo.common.protocol.command.sub;

public interface SubPro_15023_dataUpload {
	/** 上传成功 */
	int success   = 0;
	/** 未知文件 */
	int ingore    = 1;
	/** 写入文件失败 */
	int writeFail = 2;
	/** 加载时出现异常 */
	int loadError = 3;
}
