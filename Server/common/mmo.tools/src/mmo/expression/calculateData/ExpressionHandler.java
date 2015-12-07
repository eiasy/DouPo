package mmo.expression.calculateData;

import mmo.expression.IExpressionData;

public abstract class ExpressionHandler {
	/**
	 * 变量, 脚本参数
	 */
	public final static int SIGN_VARIABLE                 = 0x40000000;
	/**
	 * 公式
	 */
	public final static int SIGN_FORMULA                  = 0x50000000;
	/**
	 * 运算符
	 */
	public final static int SIGN_OPERATOR                 = 0x60000000;
	/**
	 * 文本
	 */
	public final static int SIGN_TEXT                     = 0x70000000;
	/**
	 * 脚本字符串数组文本
	 */
	public final static int SIGN_SCRIPT_STRING_ARRAY_TEXT = 0x70700000;
	/**
	 * UI引用
	 */
	public final static int SIGN_TYPE_QUOTE               = 0x80000000;
	/**
	 * 值列表索引
	 */
	public final static int SIGN_PRO_INDEX                = 0x90000000;
	/**
	 * 临时变量
	 */
	public final static int SIGN_TEMP                     = 0xa0000000;
	/**
	 * 字符
	 */
	public final static int SIGN_CHAR                     = 0xb0000000;

	/**
	 * 根据当前UI的前三位分类信息加上proID获取值
	 */
	public final static int SIGN_PRO_VALUE                = 0xc0000000;
	public final static int SIGN_PRO_HASHCODE             = 0xe0000000;

	/**
	 * 加
	 */
	public final static int OPERATOR_ADD                  = 43;

	/**
	 * 减
	 */
	public final static int OPERATOR_MINUS                = 45;

	/**
	 * 乘
	 */
	public final static int OPERATOR_MULTIPLE             = 42;
	/**
	 * 除
	 */
	public final static int OPERATOR_DIVISION             = 47;
	/**
	 * 求余
	 */
	public final static int OPERATOR_REMAINDERS           = 37;

	/**
	 * 反除
	 */
	public final static int OPERATOR_REVERSE_DIVIDE       = 92;

	/**
	 * 乘方
	 */
	public final static int OPERATOR_POWER                = 94;

	/**
	 * |
	 */
	public final static int OPERATOR_OR                   = 124;

	/**
	 * 大于
	 */
	public final static int OPERATOR_MORE_THAN            = 62;

	/**
	 * 大于等于
	 */
	public final static int OPERATOR_MORE_THAN_EQUAL      = 1983;

	/**
	 * 小于
	 */
	public final static int OPERATOR_LESS_THAN            = 60;

	/**
	 * 小于等于
	 */
	public final static int OPERATOR_LESS_THAN_EQUAL      = 1921;
	/**
	 * 恒等于
	 */
	public final static int OPERATOR_EQUIV                = 1952;

	/**
	 * #号，相当于恒等于
	 */
	public final static int OPERATOR_POUND                = 35;
	/**
	 * 不等于
	 */
	public final static int OPERATOR_NOT_EQUAL            = 1084;

	/**
	 * 等于，赋值用的
	 */
	public final static int OPERATOR_EQUAL                = 61;

	/**
	 * 加等于
	 */
	public final static int OPERATOR_ADD_EQUAL            = 1394;

	/**
	 * 减等于
	 */
	public final static int OPERATOR_MINUS_EQUAL          = 1456;

	/**
	 * 乘等于
	 */
	public final static int OPERATOR_MULTIPLE_EQUAL       = 1363;
	/**
	 * 除等于
	 */
	public final static int OPERATOR_DIVISION_EQUAL       = 1518;
	/**
	 * 求余等于
	 */
	public final static int OPERATOR_REMAINDERS_EQUAL     = 1208;
	/**
	 * 乘方等于
	 */
	public final static int OPERATOR_POWER_EQUAL          = 2975;
	/**
	 * 强制等于
	 */
	public final static int OPERATOR_FORCE_EQUAL          = 3905;

	/**
	 * &&
	 */
	public final static int OPERATOR_AND_AND              = 1216;

	/**
	 * ||
	 */
	public final static int OPERATOR_OR_OR                = 3968;

	/**
	 * !
	 */
	public final static int OPERATOR_NOT                  = 33;
	/**
	 * ++
	 */
	public final static int OPERATOR_ADD_ADD              = 1376;
	/**
	 * --
	 */
	public final static int OPERATOR_MINUS_MINUS          = 1440;

	public final static int OPERATOR_MINUS_MORE           = 1457;

	/**
	 * 一元 运算符
	 */
	public final static int OPERATOR_TYPE_UNARY           = 1;
	/**
	 * 二元运算符
	 */
	public final static int OPERATOR_TYPE_BINARY          = 2;
	public final static int OPERATOR_TYPE_TYPE_INFO_VALUE = 3;

	/** 是否是文本 */
	boolean isText(int value) {
		return ((0xff000000 & value) == SIGN_TEXT);
	}

	/**
	 * 是否是脚本字符串数组文本
	 * 
	 * @param value
	 */
	boolean isScriptStringArrayText(int value) {
		return ((0xffff0000 & value) == SIGN_SCRIPT_STRING_ARRAY_TEXT);
	}

	int getOperatorType(int aOperator) {
		switch (aOperator) {
			case OPERATOR_NOT:
			case OPERATOR_ADD_ADD:
			case OPERATOR_MINUS_MINUS:
				return OPERATOR_TYPE_UNARY;

			case OPERATOR_MINUS_MORE:
				return OPERATOR_TYPE_TYPE_INFO_VALUE;
		}

		return OPERATOR_TYPE_BINARY;
	}

	protected IExpressionData parameter;

	public int Calculate(int[] math, IExpressionData parameter) {
		IntegerStack st = IntegerStackManager.getStack();
		st.init(math);
		this.parameter = parameter;
		int v = Calculate(st);
		return v;
	}

	public int Calculate(IntegerStack st) {
		IntegerStack tSt = IntegerStackManager.getStack();
		tSt.init();
		int d1, d2, result = 0, temp, data1 = 0;

		while (!st.isEmpty()) {
			temp = st.pop();

			if (isOperator(temp)) {
				temp &= 0xffffff;

				switch (getOperatorType(temp)) {

					case OPERATOR_TYPE_UNARY:
						data1 = tSt.pop();
						d2 = getValue(data1);
						result = mathMethod(d2, temp);
						break;

					case OPERATOR_TYPE_BINARY:
						d2 = getValue(tSt.pop());
						data1 = tSt.pop();
						d1 = getValue(data1);
						result = mathMethod(d1, d2, temp);
						break;
				}

				// if(isEqualSign(temp)) {
				// switch(data1 & 0xff000000) {
				//
				//
				// case SIGN_PRO_VALUE:
				// c->setProValue(data1 & 0xffffff, result);
				// break;
				//
				// }
				// }

				if (st.isEmpty()) {
					return result;
				} else {
					tSt.push(result);
				}
			} else {
				if (st.isEmpty()) {
					return getValue(temp);
				} else {
					tSt.push(temp);
				}
			}
		}

		return getValue(tSt.pop());
	}

	int mathMethod(int num, int _operator) {
		switch (_operator) {
			case OPERATOR_ADD_ADD:
				return num + 1;

			case OPERATOR_MINUS_MINUS:
				return num - 1;

			case OPERATOR_NOT:
				return num == 0 ? 1 : 0;
		}

		return 0;
	}

	/**
	 * 对两个数值进行双目运算符运算
	 * 
	 * @param num1
	 * @param num2
	 * @param operator
	 */
	int mathMethod(int num1, int num2, int operator_) {
		int num;

		switch (operator_) {
			case OPERATOR_ADD:
			case OPERATOR_ADD_EQUAL:
			case OPERATOR_OR:
				return num1 + num2;

			case OPERATOR_MINUS:
			case OPERATOR_MINUS_EQUAL:
				return num1 - num2;

			case OPERATOR_MULTIPLE:
			case OPERATOR_MULTIPLE_EQUAL:
				return num1 * num2;

			case OPERATOR_DIVISION:
			case OPERATOR_DIVISION_EQUAL:
				if (num2 == 0) {
					return 0;
				}

				return num1 / num2;

			case OPERATOR_REVERSE_DIVIDE:
				if (num1 == 0) {
					return 0;
				}

				return num2 / num1;

			case OPERATOR_POWER_EQUAL:
			case OPERATOR_POWER:
				if (num2 == 0) {
					return 1;
				}

				// int num = num1;
				num = num1;

				for (int i = 0; i < num2 - 1; i++) {
					num1 *= num;
				}

				return num1;

			case OPERATOR_REMAINDERS:
			case OPERATOR_REMAINDERS_EQUAL:
				return num1 % num2;

			case OPERATOR_EQUAL:
			case OPERATOR_FORCE_EQUAL:
				return num2;

			case OPERATOR_MORE_THAN:
				return num1 > num2 ? 1 : 0;

			case OPERATOR_MORE_THAN_EQUAL:
				return num1 >= num2 ? 1 : 0;

			case OPERATOR_LESS_THAN:
				return num1 < num2 ? 1 : 0;

			case OPERATOR_LESS_THAN_EQUAL:
				return num1 <= num2 ? 1 : 0;

			case OPERATOR_EQUIV:
			case OPERATOR_POUND:
				return num1 == num2 ? 1 : 0;

			case OPERATOR_NOT_EQUAL:
				return num1 != num2 ? 1 : 0;

			case OPERATOR_AND_AND:
				return num1 == 0 || num2 == 0 ? 0 : 1;

			case OPERATOR_OR_OR:
				return num1 + num2 > 0 ? 1 : 0;
		}

		return 0;
	}

	int getValue(int i) {
		switch (i & 0xff000000) {
			// case SIGN_VARIABLE: {
			// if(c == NULL) {
			// return 0;
			// }
			//
			// int value = c->getScriptArg(i & 0xffffff);
			// return value;
			// }

			// case SIGN_PRO_INDEX:
			// if((i & 0xff8000) != 0xff8000) {
			// return UIManager::shellManager->getShell((i & 0xff8000) >> 15)->uiArray[(i & 0x7f00) >> 8]->getValue(i &
			// 0xff);
			// } else if((i & 0x7f00) != 0x7f00) {
			// int vvvvt = ((UI*)((UIInfo*)c)->getLeader())->shell->uiArray[(i & 0x7f00) >> 8]
			// ->getValue(i & 0xff);
			// return vvvvt;
			// } else {
			// return ((UIInfo*)c)->getLeader()->getValue(i & 0xff);
			// }

			case SIGN_PRO_VALUE:
				return getProValue(i & 0xffffff);

			case SIGN_PRO_HASHCODE:
				return getProHashcode(i & 0xffffff);
			default:
				return i;
		}
	}

	public abstract int getProValue(int proId);

	public abstract int getProHashcode(int proId);

	/**
	 * 是否是等号类型的赋值符号, 如+=
	 * 
	 * @param operator
	 */

	boolean isEqualSign(int operator_) {
		switch (operator_) {
			case OPERATOR_EQUAL:
			case OPERATOR_ADD_EQUAL:
			case OPERATOR_MINUS_EQUAL:
			case OPERATOR_MULTIPLE_EQUAL:
			case OPERATOR_DIVISION_EQUAL:
			case OPERATOR_REMAINDERS_EQUAL:
			case OPERATOR_POWER_EQUAL:
			case OPERATOR_FORCE_EQUAL:
			case OPERATOR_ADD_ADD:
			case OPERATOR_MINUS_MINUS:
				return true;

			default:
				return false;
		}
	}

	boolean isOperator(int i) {
		if ((i & 0xff000000) != SIGN_OPERATOR) {
			return false;
		}

		i &= 0xffffff;

		switch (i) {
			case OPERATOR_ADD:
			case OPERATOR_MINUS:
			case OPERATOR_MULTIPLE:
			case OPERATOR_DIVISION:
			case OPERATOR_REMAINDERS:
			case OPERATOR_REVERSE_DIVIDE:
			case OPERATOR_POWER:
			case OPERATOR_OR:
			case OPERATOR_MORE_THAN:
			case OPERATOR_MORE_THAN_EQUAL:
			case OPERATOR_LESS_THAN:
			case OPERATOR_LESS_THAN_EQUAL:
			case OPERATOR_EQUIV:
			case OPERATOR_POUND:
			case OPERATOR_NOT_EQUAL:
			case OPERATOR_EQUAL:
			case OPERATOR_ADD_EQUAL:
			case OPERATOR_MINUS_EQUAL:
			case OPERATOR_MULTIPLE_EQUAL:
			case OPERATOR_DIVISION_EQUAL:
			case OPERATOR_REMAINDERS_EQUAL:
			case OPERATOR_POWER_EQUAL:
			case OPERATOR_FORCE_EQUAL:
			case OPERATOR_AND_AND:
			case OPERATOR_OR_OR:
			case OPERATOR_MINUS_MORE:
			case OPERATOR_ADD_ADD:
			case OPERATOR_MINUS_MINUS:
			case OPERATOR_NOT:
				return true;

			default:
				return false;
		}
	}

	boolean isProIndex(int value) {
		return (0xff000000 & value) == SIGN_PRO_INDEX;
	}

}
