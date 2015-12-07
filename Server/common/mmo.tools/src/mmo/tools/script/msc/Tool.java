package mmo.tools.script.msc;
import mmo.tools.script.msc.Struct.Op;

/**
 * Author: cuirongzhou Date: 2007-11-28 Time: 16:12:09
 */
public class Tool {
		public static boolean stricmp(String str1, String str2) {
				return str1.toUpperCase().equals(str2.toUpperCase());
		}

		public static boolean strcmp(String str1, String str2) {
				return str1.equals(str2);
		}

		/*
		 * Convert String to Integer.
		 */
		public static int atoi(String str) {
				return Integer.parseInt(str);
		}

		public static float atof(String str) {
				return Float.parseFloat(str);
		}

		public static void memcpy(Op src, Op dest) {
				dest.iType = src.iType;
				dest.unionValue = src.unionValue;
				dest.fFloatLiteral = src.fFloatLiteral;
				dest.iOffset = src.iOffset;
				dest.iOffsetSymbolIndex = src.iOffsetSymbolIndex;
		}
}
