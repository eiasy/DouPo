package mmo.common.bean.mission.extension;

public class Action {
	private static final String TAG_START_ITEM = "<选项";
	private static final String TAG_END_ITEM   = "/>";
	private static final String ATT_NOTE       = " 描述=";
	private static final String ATT_IMG        = " 图片=";
	private static final String ATT_CATE       = " 类型=";
	private static final String ATT_CMD        = " 指令=";

	/** 操作码 */
	private byte                code;
	/** 操作项名称 */
	private String              name;
	/** 指令 */
	private String              command;
	private String              icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Action() {

	}

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public String toString() {
		return "Action [code=" + code + ", name=" + name + ", command=" + command + ", icon=" + icon + "]";
	}

	public final String toTag() {
		StringBuilder sb = new StringBuilder();
		sb.append(TAG_START_ITEM);
		if (name != null) {
			sb.append(ATT_NOTE).append("\"").append(name).append("\"");
		} else {
			sb.append(ATT_NOTE).append("\"\"");
		}
		
		if (icon != null) {
			sb.append(ATT_IMG).append("\"").append(icon).append("\"");
		} else {
			sb.append(ATT_IMG).append("\"\"");
		}

		sb.append(ATT_CATE).append("\"").append(code).append("\"");
		if (command != null) {
			sb.append(ATT_CMD).append("\"").append(command).append("\"");
		} else {
			sb.append(ATT_CMD).append("\"\"");
		}
		sb.append(TAG_END_ITEM);
		return sb.toString();
	}
}
