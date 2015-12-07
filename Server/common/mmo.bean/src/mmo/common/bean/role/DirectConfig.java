package mmo.common.bean.role;

import java.util.HashMap;
import java.util.Map;

public class DirectConfig {
	/** 方向对应的角度 */
	private final static Map<String, Short> DIRECT_ANGLE      = new HashMap<String, Short>();
	/** 怪物可以行走的最大方向数 */
	public final static byte                MAX_DIR           = 8;
	/** 右 */
	public final static int                 DIRECT_RIGHT      = 0;
	/** 右下 */
	public final static int                 DIRECT_RIGHT_DOWN = 1;
	/** 下 */
	public final static int                 DIRECT_DOWN       = 2;
	/** 左下 */
	public final static int                 DIRECT_LEFT_DOWN  = 3;
	/** 左 */
	public final static int                 DIRECT_LEFT       = 4;
	/** 左上 */
	public final static int                 DIRECT_LEFT_UP    = 5;
	/** 上 */
	public final static int                 DIRECT_UP         = 6;
	/** 右上 */
	public final static int                 DIRECT_RIGHT_UP   = 7;
	static {
		DIRECT_ANGLE.put("右", (short) 0);
		DIRECT_ANGLE.put("右下", (short) 45);
		DIRECT_ANGLE.put("下", (short) 90);
		DIRECT_ANGLE.put("左下", (short) 135);
		DIRECT_ANGLE.put("左", (short) 180);
		DIRECT_ANGLE.put("左上", (short) 225);
		DIRECT_ANGLE.put("上", (short) 270);
		DIRECT_ANGLE.put("右上", (short) 315);
	}

	public static final int degree2Direction(int dirAngle) {
		while (dirAngle < 0) {
			dirAngle += 360;
		}
		dirAngle += 22;
		int dir = dirAngle / 45;
		if (dirAngle > 360) {
			dir = DirectConfig.DIRECT_RIGHT;
		}
		return dir;
	}

	public static final short getAngle(String directString) {
		Short angle = DIRECT_ANGLE.get(directString);
		if (angle == null) {
			return (short) 90;
		}
		return angle;
	}
}
