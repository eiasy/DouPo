package mmo.common.config.role;

public final class RoleSex {
	/** 男 */
	public static final byte MALE          = 0;
	/** 女 */
	public static final byte FEMALE        = 1;

	private static String[]  sexNames      = new String[] { "男", "女" };
	private static String[]  sexStyleNames = new String[] { "(男)", "(女)" };

	public static String getSexName(byte index) {
		if (index > -1 && index < 2) {
			return sexNames[index];
		} else {
			return "SEX:null";
		}
	}

	public static final String getStyleName(byte sex) {
		if (sex > -1 && sex < 2) {
			return sexStyleNames[sex];
		} else {
			return "SEX:null";
		}
	}

	public static final byte getSexValue(String sexName) {
		if ("女".equals(sexName)) {
			return FEMALE;
		}
		return MALE;
	}
}