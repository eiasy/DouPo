package mmo.module.cache.role;

import mmo.tools.util.StringUtil;

public class ChargeLog {
	private final static String YUAN_BAO = "Ôª±¦";
	private final static String CARD     = "³é¿¨ÉÌµê";
	private String[]            fields   = null;
	private boolean             card;
	private boolean             yuanbao;
	private boolean             other;

	public ChargeLog(String line) {
		this(line, false);
	}

	public ChargeLog(String line, boolean check) {
		fields = StringUtil.splitString(line, '|');
		if (check) {
			card = line.contains(CARD);
			yuanbao = line.contains(YUAN_BAO);
			other = !(card | yuanbao);
		}
	}

	public boolean isCard() {
		return card;
	}

	public boolean isYuanbao() {
		return yuanbao;
	}

	public boolean isOther() {
		return other;
	}

	public String getField(int field) {
		if (fields == null) {
			return "";
		}
		if (field < 0) {
			return "";
		}
		if (field < fields.length) {
			return fields[field];
		}
		return "";
	}
}
