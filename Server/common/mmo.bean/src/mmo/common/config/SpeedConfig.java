package mmo.common.config;

import mmo.common.bean.role.Role;

public class SpeedConfig {
	public static short       ROLE_MOVE_SPEED        = 8;            // 移动速度基数（像素）
	public static short       MONSTER_MOVE_SPEED     = 4;            // 移动速度基数（像素）
	public static short       BASE_SPEED             = 100;          // 速度加成的基础值
	public final static int   TILE_SIZE              = 32;
	public final static int   HALF_TILE              = 16;

	/** 场景分区：x方向占据格子数量 */
	public static final int   GRID_WIDTH             = 15;
	/** 场景分区：y方向上占据格子的数量 */
	public static final int   GRID_HEIGHT            = 15;
	/** 默认视野容量 */
	public static final short visibilityDefault      = 20;
	/** 视野距离（像素） */
	public static final int   visibleDistance        = 550 * 550;
	/** 视野距离（像素） */
	public static final int   visibleMonsterDistance = 640 * 640;

	/** 视野距离（像素） */
	public static final int   visibleMaxDistance     = 550 * 550;

	public static int         xStart                 = 0;
	public static int         yStart                 = 0;
	public static int         fillXStart             = xStart + 1;
	public static int         fillYStart             = yStart + 1;
	public static int         height                 = TILE_SIZE - 1;
	public static int         width                  = TILE_SIZE - 1;

	/**
	 * 获取角色速度
	 * 
	 * @param role
	 *            角色
	 * @return 像素速度
	 */
	public static final int getRolePixelSpeed(Role role) {
		return ROLE_MOVE_SPEED * role.getSumMoveSpeed() / BASE_SPEED;
	}

	/**
	 * 通过百分比获取计算后的速度值
	 * 
	 * @param percent
	 * @return
	 */
	public static final int getSpeedByPercent(int percent) {
		return ROLE_MOVE_SPEED * percent / BASE_SPEED;
	}
}