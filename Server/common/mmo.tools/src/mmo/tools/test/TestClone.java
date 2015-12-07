package mmo.tools.test;

import java.text.DecimalFormat;

public class TestClone {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DecimalFormat nf = new DecimalFormat("0.00");
		System.out.println(nf.format(0.015f)); // 输出为：0.02
		System.out.println(nf.format(0.015d)); // 输出为：0.02
		System.out.println(nf.format(100.015d)); // 输出为：0.02
	}
}
