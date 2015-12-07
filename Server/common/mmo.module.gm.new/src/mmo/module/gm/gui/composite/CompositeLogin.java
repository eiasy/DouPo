package mmo.module.gm.gui.composite;

import java.util.HashMap;
import java.util.Map;

import mmo.module.gm.AdminManager;
import mmo.module.gm.gui.GMWindow;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gui.composite.CComposite;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.net.HttpsUtil;
import net.sf.json.JSONObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CompositeLogin extends CComposite {
	private Text textAccount;
	private Text textPassword;

	public CompositeLogin(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));

		Composite composite_7 = new Composite(composite_1, SWT.NONE);
		composite_7.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_7.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));

		Label lblGm = new Label(composite_7, SWT.NONE);
		lblGm.setText("GM\u8D26\u53F7\u767B\u5F55");

		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayout(null);
		composite_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));

		Label label = new Label(composite_2, SWT.NONE);
		label.setBounds(3, 3, 36, 17);
		label.setText("\u8D26\u53F7\uFF1A");

		textAccount = new Text(composite_2, SWT.BORDER);
		textAccount.setText("admin");
		textAccount.setBounds(42, 3, 169, 23);

		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setLayout(null);
		composite_3.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));

		Label label_1 = new Label(composite_3, SWT.NONE);
		label_1.setBounds(3, 3, 36, 17);
		label_1.setText("\u5BC6\u7801\uFF1A");

		textPassword = new Text(composite_3, SWT.BORDER | SWT.PASSWORD);
		textPassword.setBounds(42, 3, 169, 23);

		Composite composite_6 = new Composite(composite_1, SWT.NONE);
		composite_6.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_6.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));

		Button button_1 = new Button(composite_6, SWT.NONE);
		button_1.setText("\u91CD\u7F6E");

		Button button = new Button(composite_6, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Map<String, String> map = new HashMap<String, String>();
					map.put("userId", textAccount.getText());
					map.put("userPwd", textPassword.getText());
					String result = HttpsUtil.request(ProjectCofigs.getParameter("datacenter_url")+"/adminLogin", HttpsUtil.httpBuildQuery(map));
					JSONObject json = JSONObject.fromObject(result);
					if ("ok".equalsIgnoreCase(json.getString("code"))) {
						GMWindow.getInstance().setTitle("GM管理器-" + textAccount.getText());
						GMWindow.getInstance().updateStatusLineMessage("已登录");
						GMWindow.getInstance().switchComposite(CompositeParent.COMPOSITE_2_GM);
						GMWindow.getInstance().updatePower();
						AdminManager.setSessionId(json.getString("sessionId"));
						AdminManager.setUserId(json.getString("userId"));
						AdminManager.setPowers(json.getString("powers"));
						LoggerDevelop.gm(GmOperate.S_GM_LOGIN, AdminManager.getUserId(), "成功");
					} else {
						MyDialog.openInformation(json.getString("message"));
					}
				} catch (Exception err) {
					LoggerError.error("网络连接异常", err);
				}
			}
		});
		button.setText("\u767B\u5F55");
	}
}
