package mmo.common.config.role;
/**
	 * 阵营
	 * 
	 * @author hp
	 * 
	 */
	public  final class RoleCamp {
		/** 游戏世界的玩家标识 */
		public static final short USER      = 0;
		/** 怪物标识 */
		public static final short MONSTER   = 1;
		/** NPC标识 */
		public static final short NPC       = 2;
		/** 用于生产战场中敌对阵营的标识 */
		private static short      COPY_CAMP = 101;

		public static final synchronized short getCampFlag() {
			return COPY_CAMP++;
		}
	}
