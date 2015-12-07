package mmo.tools.util.string;

public class Expression {
	public final static int SIGN_VARIABLE       = 0x40000000;

	public final static int SIGN_FORMULA        = 0x50000000;

	public final static int SIGN_OPERATOR       = 0x60000000;

	public final static int SIGN_TEXT           = 0x70000000;

	public final static int SIGN_TYPE_QUOTE     = 0x80000000;

	public final static int SIGN_PRO_INDEX      = 0x90000000;

	public final static int SIGN_TEMP           = 0xa0000000;

	public final static int SIGN_CHAR           = 0xb0000000;

	public final static int SIGN_PRO_VALUE      = 0xc0000000;

	public final static int CHAR_TYPE_VARIABLE  = 0xa000;
	public final static int CHAR_TYPE_FONTSTYLE = 0xa100;
	public final static int CHAR_TYPE_ICON      = 0x1000;

	public static int Calculate(String str) {

		TestStack st = new TestStack();
		TestStack tSt = new TestStack();
		try {
			st.init(getPostfixString(str));
			tSt.init();
			while (!st.isEmpty()) {

				String a = st.pop();

				if (isOperator(a)) {

					int d2 = getValue(tSt.pop());

					int d1 = getValue(tSt.pop());

					int d3 = mathMethod(d1, d2, a);
					tSt.push(String.valueOf(d3));
				} else {
					tSt.push(a);
				}
			}

			return getValue(tSt.pop());
		} catch (Exception e) {
			return 0;
		}
	}

	public static String convertToPostfix(CharSplit x) {
		String s = x.getNext();
		TestStack st = new TestStack();
		TestStack rSt = new TestStack();
		while (s != null) {
			if (isBlank(s)) {
				// break;
			} else if (isOpenParenthesis(s)) {
				st.push(s);
			} else if (isCloseParenthesis(s)) {
				String as = null;
				if (st.isEmpty()) {
					System.out.println("缺少(");
					return null;
				} else {
					as = st.pop();
					while (!isOpenParenthesis(as)) {
						rSt.push(as);
						if (st.isEmpty()) {
							System.out.println("缺少(");
							return null;
							// break;
						} else {
							as = st.pop();
						}
					}
				}
			} else if (isOperator(s)) {
				if (!st.isEmpty()) {
					String as = st.pop();
					while (priority(as) >= priority(s)) {
						rSt.push(as);
						if (st.isEmpty()) {
							as = null;
						} else {
							as = st.pop();
						}
					}
					if (as != null) {
						st.push(as);
					}
					st.push(s);
				} else {
					st.push(s);
				}
			} else {
				rSt.push(s);
			}
			s = x.getNext();
		}

		while (!st.isEmpty()) {
			String as = st.pop();
			if (isOpenParenthesis(as)) {
				System.out.println("缂哄皯)");
				return null;
			} else if (isCloseParenthesis(as)) {
				System.out.println("缂哄皯(");
				return null;
			} else {
				rSt.push(as);
			}
		}

		TestStack nSt = new TestStack();
		while (!rSt.isEmpty()) {
			nSt.push(rSt.pop());
		}

		return nSt.toString();

	}

	private static boolean equals(String str, String... strArray) {
		for (int i = 0; i < strArray.length; i++) {
			if (str.equals(strArray[i])) {
				return true;
			}
		}
		return false;
	}

	private static int getObjectValue(String str) {
		return 0;
	}

	public static String getPostfixString(String infixStr) {
		return convertToPostfix(new CharSplit(infixStr));
	}

	public static int[] getPostfixString_intArray(String infixStr) {

		TestStack nts = null;
		try {
			TestStack ts = new TestStack(convertToPostfix(new CharSplit(infixStr)));

			String temp = "";

			nts = new TestStack();
			boolean isUnknown = false;

			while (!ts.isEmpty()) {
				temp = ts.pop();
				if (!isNumString(temp)) {
					isUnknown = true;
				}

				// if (isLetter(temp.charAt(0))) {
				// isUnknown = true;
				// int valueListId = -1;
				// int startCharId=-1;
				// switch (temp.charAt(0)) {
				// case 'U':
				// case 'S':
				// valueListId = Info.getValueListIdByIntro(temp
				// .substring(temp.indexOf('.') + 1));
				//
				// break;
				// default:
				// valueListId = Info.getValueListIdByIntro(temp);
				// break;
				// }

				// if (valueListId == -1) {
				nts.push(temp);
				// } else {
				// switch (temp.charAt(0)) {
				// case 'U':
				// case 'S':
				// nts.push("$"
				// + temp.substring(0, temp.indexOf('.') + 1)
				// + valueListId);
				// break;
				// default:
				// nts.push("$" + valueListId);
				// break;
				// }
				// }
				// } else {
				nts.push(temp);

				// }

			}
			if (!isUnknown) {
				return new int[] { Calculate(infixStr) };

			}
		} catch (Exception e) {
			System.out.println("算式生成错误，返回字符串： " + infixStr);
			return null;
		}

		return nts.toIntArray();

	}

	private static int getValue(String s) {
		return Integer.parseInt(s);

	}

	public static boolean isBlank(String s) {
		if (" ".equals(s) || "\t".equals(s)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCloseParenthesis(String s) {
		return ")".equals(s);
	}

	/** 是否是后 方括号 */
	public static boolean isCloseSquareBracket(char c) {
		return c == ']';
	}

	/**
	 * 字符是等号
	 */
	public static boolean isEqualSign(char c) {
		return c == '=';
	}

	/**
	 * 是否是赋值类符号
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEqualSign(String s) {
		return equals(s, "+=", "-=", "*=", "/=", "%=", "^=", "|=", "=");
	}

	/** 是否是字母 */
	public static boolean isLetter(char s) {
		return (s >= 'A' && s <= 'Z') || (s >= 'a' && s <= 'z') || (s >= '\u4e00' && s <= '\u9fa5') || (s >= '\uf900' && s <= '\ufa2d') || s == '_';
	}

	public static boolean isNum(char s) {
		return s >= '0' && s <= '9';
	}

	/**
	 * 是否是数字
	 */
	public static boolean isNumString(String str) {
		if (str.length() < 1) {
			return false;
		}
		char c = str.charAt(0);
		if (!(isNum(c) || (isPlusOrMinus(c)) && str.length() > 1)) {
			return false;
		}
		for (int i = 1, j = str.length(); i < j; i++) {
			if (!isNum(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isOpenParenthesis(String s) {
		return "(".equals(s);
	}

	/** 是否是前 方括号 */
	public static boolean isOpenSquareBracket(char c) {
		return c == '[';
	}

	public static boolean isOperator(String s) {
		return equals(s, "^", "*", "/", "%", "\\", "+", "-", "|", ">", "<", "#", ">=", "<=", "==", "!=", "+=", "-=", "*=", "/=", "%=", "^=", "=",
		        "|=",/* "!", */"||", "&&");
	}

	public static boolean isOperatorSign(char c) {
		switch (c) {
			case '^':
			case '*':
			case '/':
			case '%':
			case '\\':
			case '+':
			case '-':
			case '|':
			case '>':
			case '<':
			case '#':
			case '=':
			case '!':
			case '&':
				return true;
			default:
				return false;

		}
	}

	/**
	 * 字符是正负号
	 */
	public static boolean isPlusOrMinus(char c) {
		switch (c) {
			case '+':
			case '-':
				return true;
			default:
				return false;
		}
	}

	public static boolean isString(char s) {
		return s == '\'';
	}

	/**
	 * 是否是临时变量符号
	 */
	public static boolean isTempSign(char s) {
		return s == '?';
	}

	public static boolean isValueListIdSign(char c) {
		return c == '$';
	}

	public static boolean isVariableSign(char s) {
		return s == '@';
	}

	private static int mathMethod(int num1, int num2, String operator) {
		if (equals(operator, "+", "|", "+=")) {
			return num1 + num2;
		} else if (equals(operator, "-", "-=")) {
			return num1 - num2;
		} else if (equals(operator, "*", "*=")) {
			return num1 * num2;
		} else if (equals(operator, "/", "/=")) {
			return num1 / num2;
		} else if (equals(operator, "\\")) {
			return num2 / num1;
		} else if (equals(operator, "^", "^=")) {
			int num = num1;
			for (int i = 0; i < num2 - 1; i++) {
				num1 *= num;
			}
			return num1;
		} else if (equals(operator, "%", "%=")) {
			return num1 % num2;
		} else if (equals(operator, ">")) {
			return num1 > num2 ? 1 : 0;
		} else if (equals(operator, "<")) {
			return num1 < num2 ? 1 : 0;
		} else if (equals(operator, ">=")) {
			return num1 >= num2 ? 1 : 0;
		} else if (equals(operator, "<=")) {
			return num1 <= num2 ? 1 : 0;
		} else if (equals(operator, "==", "#")) {
			return num1 == num2 ? 1 : 0;
		} else if (equals(operator, "!=")) {
			return num1 != num2 ? 1 : 0;
		} else if (equals(operator, "=", "|=")) {
			return num2;
		} else if (equals(operator, "||")) {
			return num1 + num2 > 0 ? 1 : 0;
		} else if (equals(operator, "&&")) {
			return num1 + num2 == 0 ? 0 : 1;
		}
		return 0;
	}

	public static int priority(String s) {
		if (s == null) {
			return -1;
		}
		if (equals(s, "^")) {
			return 10;
		} else if (equals(s, "*", "/", "%", "\\")) {
			return 9;
		} else if (equals(s, "+", "-", "|")) {
			return 8;
		} else if (equals(s, ">", "<", "#", ">=", "<=", "==", "!=")) {
			return 7;
		}
		// else if(equals(s, "!")){
		// return 6;
		// }
		else if (equals(s, "&&", "||")) {
			return 5;
		} else if (equals(s, "+=", "-=", "*=", "/=", "%=", "^=", "|=", "=")) {
			return 4;
		} else {
			return 0;
		}
	}
}
