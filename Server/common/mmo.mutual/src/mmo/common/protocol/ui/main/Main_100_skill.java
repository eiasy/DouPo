package mmo.common.protocol.ui.main;

public interface Main_100_skill {
	/** 技能面板主类别 */
	byte  main_100_skill       = 100;
	/** 主类别-通天灵宝 */
	byte  main_101_lingBaoTT   = 101;
	/** 天榜主类别 */
	short main_103_grid_sky    = 103;
	/** 地榜主类别 */
	short main_104_grid_earth  = 104;
	/** 人榜主类别 */
	short main_105_grid_human  = 105;
	/** 符箓能量珠 */
	byte  main_109_fulu        = 109;
	/** 法宝 */
	byte  main_110_fabao       = 110;
	/**法宝详细*/
	byte  main_114_fabaoDetail = 114;

	interface Sub_100 {
		byte sub_0_skill    = 0;
		/** 快捷键面板子类别 */
		byte sub_1_shortcut = 1;
	}

	interface Sub_103 {
		/** 子类别 */
		byte sub_0_SKY = 0;
	}

	interface Sub_104 {
		byte sub_1_EARTH = 1;

	}

	interface Sub_105 {
		byte sub_2_HUMAN = 2;
	}

}
