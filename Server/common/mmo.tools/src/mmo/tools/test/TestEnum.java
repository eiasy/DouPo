package mmo.tools.test;

public class TestEnum {
	/* 最普通的枚举 */
	public enum ColorSelect {
		red, green, yellow, blue;
	}

	/* 枚举也可以象一般的类一样添加方法和属性,你可以为它添加静态和非静态的属性或方法,这一切都象你在一般的类中做的那样. */
	public enum Season {
		// 枚举列表必须写在最前面，否则编译出错
		winter, spring, summer, fall;

		private final static String location = "Phoenix";

		public static Season getBest() {
			if (location.equals("Phoenix"))
				return winter;
			else
				return summer;
		}
	}

	/* 还可以有构造方法 */
	public enum Temp {
		/*
		 * 通过括号赋值,而且必须有带参构造器和一属性跟方法，否则编译出错 赋值必须是都赋值或都不赋值，不能一部分赋值一部分不赋值 如果不赋值则不能写构造器，赋值编译也出错
		 */
		absoluteZero(-459), freezing(32), boiling(212), paperBurns(451);

		private final int value;

		public int getValue() {
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		Temp(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		/*
		 * 枚举类型是一种类型，用于定义变量，以限制变量的赋值 赋值时通过"枚举名.值"来取得相关枚举中的值
		 */
		ColorSelect m = ColorSelect.blue;
		switch (m) {
			/*
			 * 注意:枚举重写了ToString(),说以枚举变量的值是不带前缀的所以为blue而非ColorSelect.blue
			 */
			case red:
				System.out.println("color is red");
				break;
			case green:
				System.out.println("color is green");
				break;
			case yellow:
				System.out.println("color is yellow");
				break;
			case blue:
				System.out.println("color is blue");
				break;
		}
		System.out.println("遍历ColorSelect中的值");
		/* 通过values()获得枚举值的数组 */
		for (ColorSelect c : ColorSelect.values()) {
			System.out.println(c);
		}
		System.out.println("枚举ColorSelect中的值有：" + ColorSelect.values().length + "个");
		/* ordinal()返回枚举值在枚举中的索引位置，从0开始 */
		System.out.println(ColorSelect.red.ordinal());// 0
		System.out.println(ColorSelect.green.ordinal());// 1
		System.out.println(ColorSelect.yellow.ordinal());// 2
		System.out.println(ColorSelect.blue.ordinal());// 3

		/* 枚举默认实现了java.lang.Comparable接口 */
		System.out.println(ColorSelect.red.compareTo(ColorSelect.green));

		System.out.println(Season.getBest());

		for (Temp t : Temp.values()) {
			/* 通过getValue()取得相关枚举的值 */
			System.out.println(t + "的值是" + t.getValue() + " , " + t.ordinal());
		}

	}
}