package mmo.common.config.skill;

/**
 * BUF重复创建时的操作
 * 
 * @author 李天喜
 * 
 */
public class BufRepeat {
	/** 不能重复创建 */
	public static final int REPEAR_RETURN       = 0;
	/** 如果BUF已经存在则重置时间 */
	public static final int REPEAT_RESET        = 1;
	/** 可以叠加的BUF */
	public static final int REPEAT_PILE_UP      = 2;
	/** 如果BUF已经存在则在时间上进行累加 */
	public static final int REPEAT_TIME_PILE_UP = 3;
}
