package mmo.common.protocol.command.sub;

public interface SubPro_15024_levelUpdate {
	/** 更新成功 */
	int SUCCESS    = 0;
	/** 角色未被冻结 */
	int NOT_FREEZE = 1;
	/** 账号异常 */
	int NO_ACCOUNT = 2;
	/** 角色不存在 */
	int NO_ROLE    = 3;
	/**更新失败*/
	int FAIL      = 4;
}
