package mmo.tools.util;


public class RandomString {
	private static final int DIGIT      = 56;
	final static char[]      digits     = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L',
	        'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q',
	        'r', 's', 't', 'u', 'w', 'x', 'y', 'z' };

	final static char[]      digits_sub = { '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
	final static char[]      digits_sup = { 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'w', 'x', 'y', 'z' };

	/**
	 * 将十进制的数字转换为指定进制的字符串。
	 * 
	 * @param value
	 *            十进制的数字。
	 * @param system
	 *            指定的进制，常见的2/8/16。
	 * @return 转换后的字符串。
	 */
	public final static String numericToString(long value) {
		long num = 0;
		if (value < 0) {
			num = ((long) 2 * 0x7fffffff) + value + 2;
		} else {
			num = value;
		}
		char[] buf = new char[DIGIT];
		int charPos = DIGIT;
		while ((num / DIGIT) > 0) {
			buf[--charPos] = digits[(int) (num % DIGIT)];
			num /= DIGIT;
		}
		buf[--charPos] = digits[(int) (num % DIGIT)];
		return new String(buf, charPos, (DIGIT - charPos));
	}

	/**
	 * 将其它进制的数字（字符串形式）转换为十进制的数字。
	 * 
	 * @param s
	 *            其它进制的数字（字符串形式）
	 * @return 转换后的数字。
	 */
	public final static long stringToNumeric(String s) {
		char[] buf = new char[s.length()];
		s.getChars(0, s.length(), buf, 0);
		long num = 0;
		for (int i = 0; i < buf.length; i++) {
			for (int j = 0; j < digits.length; j++) {
				if (digits[j] == buf[i]) {
					num += j * Math.pow(DIGIT, buf.length - i - 1);
					break;
				}
			}
		}
		return num;
	}

	final static long getSubDigit(int length) {
		if (length < 3) {
			length = 3;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(digits_sub[MathUtil.getRandom(0, digits_sub.length - 1)]);
		}
		return stringToNumeric(sb.toString());
	}

	final static long getSupDigit(int length) {
		if (length < 3) {
			length = 3;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(digits_sup[MathUtil.getRandom(0, digits_sup.length - 1)]);
		}
		return stringToNumeric(sb.toString());
	}

	public final static String[] generate(String tag, int length, int count) {
		String[] codes = new String[count];
		int remain = length - tag.length();
		long sub = getSubDigit(remain);
		long sup = getSupDigit(remain);

		long offset = (sup - sub) / count;
		for (int ii = 0; ii < count; ii++) {
			codes[ii] = tag + numericToString(MathUtil.random(sub, sub + offset));
			sub += offset;
		}
		return codes;
	}

	public static void main(String[] args) {
		String[] codes = generate("wy", 6, 5000);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < codes.length; i++) {
			sb.append(codes[i]).append('\n');
		}
		System.out.println(FileUtil.ROOT_DIR + FileUtil.FILE_SEPARATOR + "code.txt");
		FileUtil.writeDataToFile(FileUtil.ROOT_DIR + FileUtil.FILE_SEPARATOR + "code.txt", sb.toString());
	}
}