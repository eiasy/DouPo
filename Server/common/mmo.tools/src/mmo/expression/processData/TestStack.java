package mmo.expression.processData;


public class TestStack {
	private StringBuilder s;

	public TestStack() {
		s = new StringBuilder();
	}

	public TestStack(String str) {
		s = new StringBuilder(str);
	}

	public void init() {
		s = new StringBuilder();
	}

	public void init(String str) {
		s = new StringBuilder(str);
	}

	public void push(String str) {
		if (!isEmpty()) {
			s.append(',');
		}
		s.append(str);
	}

	public String pop() {

		int id = s.lastIndexOf(",");
		String ss;
		if (id == -1) {
			ss = s.toString();
			s.delete(0, s.length());
			return ss;
		}
		ss = s.substring(id + 1, s.length());
		s.delete(id, s.length());
		return ss;
	}

	public boolean isEmpty() {
		return s.length() <= 0;
	}

	public String toString() {
		return s.toString();
	}

	public String getLast() {
		String s = pop();
		push(s);
		return s;
	}

	private static int[] mathArray = new int[100];

	public int[] toIntArray() {

		String temp = null;
		char c;

		int mathLen = 0;
		while (!isEmpty()) {

			temp = pop();
			c = temp.charAt(0);

			if (Expression.isNumString(temp)) {
				mathArray[mathLen] = Integer.parseInt(temp);
			} else
				switch (c) {
				case 'P':
					if(temp.charAt(1)=='H'){
						mathArray[mathLen] = Expression.SIGN_PRO_HASHCODE
								| Integer.parseInt(temp.substring(2));
					}else{
						mathArray[mathLen] = Expression.SIGN_PRO_VALUE
								| Integer.parseInt(temp.substring(1));
					}
					
					break;
				case '@':
					mathArray[mathLen] = Expression.SIGN_VARIABLE
							| Integer.parseInt(temp.substring(1));
					break;
				case '?':
					mathArray[mathLen] = Expression.SIGN_TEMP
							| Integer.parseInt(temp.substring(1));
					break;
				case '$':
					int result = 0;
					int id1 = temp.indexOf('S');
					if (id1 != -1) {
						result |= ((Integer.valueOf(temp.substring(id1 + 1,
								temp.indexOf('U'))) | 0x80) & 0xff) << 16;
					}
					id1 = temp.indexOf('U');
					if (id1 != -1) {
						result |= ((Integer.valueOf(temp.substring(id1 + 1,
								temp.indexOf('.'))) | 0x80) & 0xff) << 8;
						mathArray[mathLen] = Expression.SIGN_PRO_INDEX
								| Integer.parseInt(temp.substring(temp
										.indexOf('.') + 1)) | result;
					} else {
						mathArray[mathLen] = Expression.SIGN_PRO_INDEX
								| Integer.parseInt(temp.substring(1));
					}

					//
					//
					//
					// mathArray[mathLen]=Expression.SIGN_PRO_INDEX|Integer.parseInt(temp.substring(1));
					break;
				case 'S':
				case 'U':
					int uId = 0;

					if (c == 'U') {
						if (temp.length() == 1) {
							uId = 0x1ff;
						} else {
							uId = Integer.parseInt(temp.substring(1)) | 0x100;
						}
					} else if (c == 'S') {
						int id = temp.indexOf("U");
						if (id == -1) {
							uId = (Integer.parseInt(temp.substring(1)) | 0x100) << 12;
						} else {
							uId = ((Integer.parseInt(temp.substring(1, id)) | 0x100) << 12)
									| (Integer.parseInt(temp.substring(id + 1)) | 0x100);
						}
					}

					mathArray[mathLen] = Expression.SIGN_TYPE_QUOTE | uId;
					break;
				default:
					if (Expression.isOperator(temp)) {
						mathArray[mathLen] = Expression.SIGN_OPERATOR
								| (temp.hashCode());
					} else {
						System.out.println("生成算式出错！！！" + temp);
						return null;
					}
					break;

				}

			mathLen++;
		}
		return (int[]) Tools.newArray(mathArray, mathLen);
	}

}
