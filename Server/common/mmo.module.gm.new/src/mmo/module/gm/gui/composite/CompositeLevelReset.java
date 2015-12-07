package mmo.module.gm.gui.composite;

import mmo.common.protocol.command.ProGmClient_17000;
import mmo.module.gm.bean.AccountRole;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.net.GMNetManager;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.log.LoggerFilter;

import org.apache.mina.core.buffer.IoBuffer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;

public class CompositeLevelReset extends GmComposite {
	private Label      labelGame;
	private Label      labelServer;
	private Composite  compositeInfo;
	private Text       textAccountId;
	private Text       textRoleId;
	private Text       textRoleName;
	private Text       textLevelCurr;
	private Text       textLevelTarget;
	private StyledText textReason;

	public CompositeLevelReset(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		compositeInfo = new Group(composite, SWT.NONE);
		compositeInfo.setLayout(new RowLayout(SWT.HORIZONTAL));
		compositeInfo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label = new Label(compositeInfo, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label.setText("\u6E38\u620F\uFF1A");

		labelGame = new Label(compositeInfo, SWT.NONE);
		labelGame.setText("\u6E38\u620F");

		Label label_3 = new Label(compositeInfo, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_3.setText(">>\u670D\u52A1\u5668\uFF1A");

		labelServer = new Label(compositeInfo, SWT.NONE);
		labelServer.setText("\u670D\u52A1\u5668");

		Label label_4 = new Label(compositeInfo, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_4.setText(">>\u4FEE\u6539\u4ED9\u5E9C\u7B49\u7EA7#");

		Group group = new Group(composite, SWT.NONE);
		group.setLayout(new GridLayout(1, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group.setText("\u4FEE\u6539\u7B49\u7EA7");

		Composite composite_1 = new Composite(group, SWT.BORDER);
		composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_5 = new Composite(composite_1, SWT.NONE);
		composite_5.setLayout(null);

		Label lblid = new Label(composite_5, SWT.NONE);
		lblid.setBounds(3, 3, 49, 17);
		lblid.setText("\u8D26\u53F7ID\uFF1A");

		textAccountId = new Text(composite_5, SWT.BORDER);
		textAccountId.setEditable(false);
		textAccountId.setBounds(55, 3, 100, 23);

		Composite composite_6 = new Composite(composite_1, SWT.NONE);
		composite_6.setLayout(null);

		Label lblid_1 = new Label(composite_6, SWT.NONE);
		lblid_1.setBounds(3, 3, 49, 17);
		lblid_1.setText("\u89D2\u8272ID\uFF1A");

		textRoleId = new Text(composite_6, SWT.BORDER);
		textRoleId.setEditable(false);
		textRoleId.setBounds(55, 3, 100, 23);

		Composite composite_7 = new Composite(composite_1, SWT.NONE);
		composite_7.setLayout(null);

		Label label_7 = new Label(composite_7, SWT.NONE);
		label_7.setBounds(3, 3, 60, 17);
		label_7.setText("\u89D2\u8272\u540D\u79F0\uFF1A");

		textRoleName = new Text(composite_7, SWT.BORDER);
		textRoleName.setEditable(false);
		textRoleName.setBounds(66, 3, 100, 23);

		Composite composite_8 = new Composite(group, SWT.BORDER);
		composite_8.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_9 = new Composite(composite_8, SWT.NONE);
		composite_9.setLayout(null);

		Label label_8 = new Label(composite_9, SWT.NONE);
		label_8.setBounds(3, 3, 84, 17);
		label_8.setText("\u4ED9\u5E9C\u5F53\u524D\u7B49\u7EA7\uFF1A");

		textLevelCurr = new Text(composite_9, SWT.BORDER);
		textLevelCurr.setBounds(90, 3, 73, 23);
		textLevelCurr.setEditable(false);

		Composite composite_10 = new Composite(composite_8, SWT.NONE);
		composite_10.setLayout(null);

		Label label_9 = new Label(composite_10, SWT.NONE);
		label_9.setBounds(3, 3, 84, 17);
		label_9.setText("\u4ED9\u5E9C\u76EE\u6807\u7B49\u7EA7\uFF1A");

		textLevelTarget = new Text(composite_10, SWT.BORDER);
		textLevelTarget.setBounds(90, 3, 73, 23);

		Composite composite_11 = new Composite(group, SWT.NONE);
		composite_11.setLayout(new GridLayout(2, false));
		GridData gd_composite_11 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite_11.heightHint = 60;
		composite_11.setLayoutData(gd_composite_11);

		Label label_10 = new Label(composite_11, SWT.NONE);
		label_10.setText("\u4FEE\u6539\u539F\u56E0\uFF1A");

		textReason = new StyledText(composite_11, SWT.BORDER);
		textReason.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite_12 = new Composite(group, SWT.NONE);
		composite_12.setLayout(null);
		composite_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button button_1 = new Button(composite_12, SWT.NONE);
		button_1.addSelectionListener(new ListenerUpdate());
		button_1.setBounds(3, 3, 36, 27);
		button_1.setText("\u63D0\u4EA4");
	}

	public void setUIData(Object data) {
		setData(dataKey, data);
		if (data != null) {
			if (data instanceof AccountRole) {
				AccountRole role = (AccountRole) data;
				labelGame.setText(role.getGameName());
				labelServer.setText(role.getServerName());
				textAccountId.setText(role.getAccountId() + "");
				textLevelCurr.setText("" + role.getMansionLevel());
				textLevelTarget.setText("" + role.getLevel());
				textReason.setText("");
				textRoleId.setText("" + role.getRoleId());
				textRoleName.setText(role.getRoleName());
			}
			compositeInfo.layout();
		}
	}

	class ListenerUpdate extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			Object data = getData(dataKey);
			if (data == null || !(data instanceof AccountRole)) {
				MyDialog.openInformation("未选中角色");
				return;
			}
			AccountRole role = (AccountRole) data;
			try {
				String value = textAccountId.getText().trim();
				if (value.length() < 1) {
					MyDialog.openInformation("未选中角色");
					return;
				}

				int accountId = Integer.parseInt(value);
				value = textRoleId.getText().trim();
				if (value.length() < 1) {
					MyDialog.openInformation("未选中角色");
					return;
				}
				int roleId = Integer.parseInt(value);

				value = textLevelTarget.getText();
				if (value.length() < 1) {
					MyDialog.openInformation("请输入新等级");
					return;
				}
				short level = Short.parseShort(value);
				if (level < 1) {
					MyDialog.openInformation("新等级不能小于1");
					return;
				}
				value = textReason.getText().trim();
				if (value.length() < 1) {
					MyDialog.openInformation("请输入修改原因");
					return;
				}
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17045_LEVEL_RESET);
				buf.putInt(role.getGameId());
				buf.putInt(role.getServerId());
				buf.putInt(accountId);
				buf.putInt(roleId);
				buf.putShort(level);
				buf.putString(value);
				sendData(buf, true);

				StringBuilder sb_log = new StringBuilder();
				sb_log.append(role.getGameId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getServerId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(roleId);
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(accountId);
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(roleId);
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(level);
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(value);

				LoggerDevelop.gm(GmOperate.CLIENT_RESET_ROLE_LEVEL, GMNetManager.getGmUserId(), sb_log.toString());
			} catch (Exception err) {
				MyDialog.openInformation(err.getMessage());
				return;
			}
		}
	}
}
