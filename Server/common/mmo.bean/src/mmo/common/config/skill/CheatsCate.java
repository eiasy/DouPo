package mmo.common.config.skill;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CheatsCate {
		public final static short               CONTINUE_SKILL = 1;
		public final static short               FINAL_SKILL    = 2;
		public final static short               TALENT_1       = 3;
		public final static short               TALENT_2       = 4;
		public final static short               TALENT_3       = 5;
		public final static short               TALENT_4       = 6;
		private final static Map<Short, String> CATES          = new HashMap<Short, String>();
		static {
			CATES.put(CONTINUE_SKILL, "连续技");
			CATES.put(FINAL_SKILL, "终结技");
			CATES.put(TALENT_1, "天赋1");
			CATES.put(TALENT_2, "天赋2");
			CATES.put(TALENT_3, "天赋3");
			CATES.put(TALENT_4, "天赋4");
		}

		public final static String[] getCateNames() {
			Collection<String> values = CATES.values();
			String[] cateNames = new String[values.size()];
			values.toArray(cateNames);
			return cateNames;
		}

		public final static Short[] getCateValue() {
			Set<Short> values = CATES.keySet();
			Short[] cateValues = new Short[values.size()];
			values.toArray(cateValues);
			return cateValues;
		}

		public final static String getCateName(short cate) {
			String name = CATES.get(cate);
			if (name == null) {
				name = cate + ":NULL";
			}
			return name;
		}

	}