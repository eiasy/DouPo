package mmo.common.bean.mission.extension;

import java.util.ArrayList;
import java.util.List;

public class Dialog {
	private static final String      TAG_START_DIALOG  = "<对话>";
	private static final String      TAG_START_CONTENT = "<内容>";
	private static final String      TAG_END_DIALOG    = "</对话>";
	private static final String      TAG_END_CONTENT   = "</内容>";
	/** 默认操作项 */
	public static final List<Action> NULL              = new ArrayList<Action>();
	/** 对话 */
	private String                   dialog;
	/** 操作项 */
	private List<Action>             actions;
	/** 类型(0.普通1.显示奖励UI 2.完成奖励UI 3.可选任务奖励显示物品) */
	private byte                     cate;

	public Dialog() {

	}

	public final String toTag() {
		StringBuilder sb = new StringBuilder();
		sb.append(TAG_START_DIALOG);
		sb.append(TAG_START_CONTENT);
		if (dialog != null) {
			sb.append(dialog);
		}
		sb.append(TAG_END_CONTENT);
		if (actions != null) {
			for (Action action : actions) {
				sb.append(action.toTag());
			}
		}
		sb.append(TAG_END_DIALOG);
		return sb.toString();
	}

	public String getDialog() {
		return dialog;
	}

	public void setDialog(String dialog) {
		if (dialog != null) {
			this.dialog = dialog.trim();
		}
	}

	public List<Action> getActions() {
		if (actions == null) {
			return NULL;
		}
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public void addAction(Action action) {
		if (actions == null) {
			actions = new ArrayList<Action>();
		}
		actions.add(action);
	}

	public byte getCate() {
		return cate;
	}

	public void setCate(byte cate) {
		this.cate = cate;
	}

	@Override
	public String toString() {
		return "dialog=" + dialog + ", actions=" + actions;
	}

}
