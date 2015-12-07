package mmo.common.bean.goods.filter;

import java.util.Arrays;

import mmo.expression.IExpressionData;
import mmo.expression.calculateData.ExpressionHandler;
import mmo.expression.processData.Expression;

/**
 * 商店过滤器
 * 
 * @author fanren
 * 
 */
public class ShopFilter {
	private static ExpressionHandler expressionHandler;

	public static void initExpressionHandler(ExpressionHandler expressionHandler) {
		ShopFilter.expressionHandler = expressionHandler;
	}

	/** 表达式 */
	private String expressionString;
	/** 把表达式转成参数值 */
	private int[]  expressionParameter;
	/** 提示消息 */
	private String message = "";

	public ShopFilter(String message, String expressionString) {
		setExpressionString(expressionString);
		if (message != null) {
			this.message = message;
		}
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 过滤结果
	 * 
	 * @param expressionData
	 *            过滤对象
	 * @return true未通过，false通过
	 */
	public boolean filterFail(IExpressionData expressionData) {
		if (expressionHandler == null || expressionParameter == null) {
			return false;
		}
		return expressionHandler.Calculate(expressionParameter, expressionData) < 1;
	}

	public String getExpressionString() {
		return expressionString;
	}

	public void setExpressionString(String expressionString) {
		this.expressionString = expressionString;
		if (expressionHandler != null && expressionString != null) {
			this.expressionParameter = Expression.getPostfixString_intArray(this.expressionString);
		}
	}

	@Override
	public String toString() {
		return "ShopFilter [expressionString=" + expressionString + ", expressionParameter=" + Arrays.toString(expressionParameter) + "]";
	}
}
