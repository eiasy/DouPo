package mmo.tools.util.string;

public class CharSplit {
	private String s;

	private int    pos = 0;
	private int    arg = 0;
	private int    len = 0;
	private char   c;

	// private ScriptVM script;

	public CharSplit(String str) {
		s = str;
		len = s.length();
		// this.script = script;
	}

	private boolean isNum = false;

	public String getNext() {
		if (pos < len) {
			c = s.charAt(pos);
			if (Expression.isTempSign(c) || Expression.isValueListIdSign(c) || Expression.isVariableSign(c) || Expression.isNum(c)) {
				isNum = true;
				String result = "";
				if (pos == len - 1) {
					result = s.charAt(pos++) + "";
				} else {
					for (int i = pos + 1; i < len; i++) {
						if (!(Expression.isLetter(s.charAt(i)) || Expression.isNum(s.charAt(i)))) {
							arg = pos;
							pos = i;
							result = s.substring(arg, pos);
							break;
						}
						if (i == len - 1) {
							arg = pos;
							pos = i + 1;
							result = s.substring(arg, pos);
							break;
						}
					}
				}

				// if (Expression.isVariableSign(c)) {
				// return c+""+ScriptVM.getArgId(result.substring(1));
				// }

				return result;
			} else if (Expression.isPlusOrMinus(c) && !Expression.isEqualSign(s.charAt(pos + 1))) {
				if (isNum) {
					isNum = false;
					return s.charAt(pos++) + "";
				} else {
					isNum = true;
					if (pos == len - 1) {
						return s.charAt(pos++) + "";
					}
					for (int i = pos + 1; i < len; i++) {
						if (!Expression.isNum(s.charAt(i))) {
							arg = pos;
							pos = i;
							return s.substring(arg, pos);
						}
						if (i == len - 1) {
							arg = pos;
							pos = i + 1;
							return s.substring(arg, pos);
						}
					}
				}
			} else if (Expression.isString(c)) {
				isNum = false;
				for (int i = pos + 1; i < len; i++) {
					if (Expression.isString(s.charAt(i))) {
						arg = pos;
						pos = i + 1;
						return s.substring(arg, i);
					}
					if (i == len - 1) {
						arg = pos;
						pos = i + 1;
						return s.substring(arg, pos);
					}
				}
			} else {
				if (Expression.isOpenParenthesis(String.valueOf(c))) {
					isNum = false;
				} else if (Expression.isOperatorSign(c)) {
					isNum = false;
					String ss = null;
					for (int i = pos + 1; i < len; i++) {
						ss = s.substring(pos, i + 1);

						if (!Expression.isOperator(ss)) {
							arg = pos;
							pos = i;
							ss = s.substring(arg, pos);
							break;
						} else if (i == len - 1) {
							arg = pos;
							pos = i + 1;
							ss = s.substring(arg, pos);
							break;
						}
					}

					if (Expression.isOperator(ss)) {
						return ss;
					} else {
						System.out.println("算式符号截取出错！！！！！！！！！" + ss);
					}
				} else {
					if (Expression.isLetter(c)) {
						isNum = true;
						String result = "";
						if (pos == len - 1) {
							result = s.charAt(pos++) + "";
						} else {
							for (int i = pos + 1; i < len; i++) {
								if (!(Expression.isLetter(s.charAt(i)) || Expression.isNum(s.charAt(i)) || s.charAt(i) == '.')) {
									arg = pos;
									pos = i;
									result = s.substring(arg, pos);
									break;
								}
								if (i == len - 1) {
									arg = pos;
									pos = i + 1;
									result = s.substring(arg, pos);
									break;
								}
							}
						}
						// if (script.获取变量ID(result) != -1) {
						// return script.获取变量ID(result) + "";
						// }

						return result;
					} else if (Expression.isOpenSquareBracket(c)) {
						isNum = true;
						for (int i = pos + 1; i < len; i++) {
							if (Expression.isCloseSquareBracket(s.charAt(i))) {
								arg = pos;
								pos = i + 1;
								return s.substring(arg, pos);
							}
						}
					}
				}
				return s.charAt(pos++) + "";
			}
			return null;
		} else {
			return null;
		}

	}
}
