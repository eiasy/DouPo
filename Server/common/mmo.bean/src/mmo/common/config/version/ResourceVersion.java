package mmo.common.config.version;

public class ResourceVersion {
	/** 服务器代码版本号 */
	public static final String CODE_VERSION             = "0.9.1";
//	/** 角色数据原始版 */
//	public static final int    DATE_ROLE_VERSION_BASE   = 1;
//	/** 好友数据变化 */
//	public static final int    DATA_ROLE_FRIEND         = 2;
//	/** 每日任务数据变化 */
//	public static final int    DATA_EVERYDAY_MISSION    = 3;
//	/** 每日任务进度变化 */
//	public static final int    DATA_DAYMISSION_PROGRESS = 4;
//	/** 给每日数据增加day自段 */
//	public static final int    DATA_ADD_DAY             = 5;

	/** 宗门数据原始版 */
	public static final int    DATE_SECT_VERSION_BASE   = 1;
	/** 宗门数据-修改宗门成员的存储结构 */
	public static final int    DATE_SECT_MEMBER         = 2;
	/** 修改宗门成员存储 */
	public static final int    DATE_SECT_MEMBER_3       = 3;
	/** 修改宗门成员存储 (增加贡献值) */
	public static final int    DATE_SECT_MEMBER_4       = 4;
}
