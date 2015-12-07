package mmo.module.gm.gui.composite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mmo.common.config.role.RoleRealmConfig;
import mmo.common.protocol.command.ProGmClient_17000;
import mmo.module.cache.role.CacheRole;
import mmo.module.cache.role.RoleGoods;
import mmo.module.cache.role.RolePet;
import mmo.module.gm.bean.TreeNode;
import mmo.module.gm.gui.dialog.MyDialog;
import mmo.module.gm.gui.provider.tree.TreeContentProvider;
import mmo.module.gm.gui.provider.tree.TreeLableProvider;
import mmo.module.gm.net.GMNetManager;
import mmo.module.logger.develop.GmOperate;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.log.LoggerFilter;
import mmo.tools.util.DateUtil;
import mmo.tools.util.StringUtil;

import org.apache.mina.core.buffer.IoBuffer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import com.swtdesigner.SWTResourceManager;

public class CompositeRoleDetail extends GmComposite {
	private Label      labelGame;
	private Label      labelServer;
	private Composite  compositeInfo;
	private TreeViewer complexTreeViewer;
	private TreeViewer baseTreeViewer;
	private Control    controlOperate;
	private Label      labelOperate;
	private Button     buttonCommit;
	private Group      groupOperate;
	private Composite  compositeOperate;

	public CompositeRoleDetail(Composite parent, int style) {
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

		Label label_2 = new Label(compositeInfo, SWT.NONE);
		label_2.setText(">>");

		Label label_3 = new Label(compositeInfo, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_3.setText("\u670D\u52A1\u5668\uFF1A");

		labelServer = new Label(compositeInfo, SWT.NONE);
		labelServer.setText("\u670D\u52A1\u5668");

		Label label_4 = new Label(compositeInfo, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		label_4.setText(">>\u89D2\u8272\u8BE6\u7EC6\u4FE1\u606F#");

		Composite composite_4 = new Composite(composite, SWT.NONE);
		GridData gd_composite_4 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite_4.heightHint = 80;
		composite_4.setLayoutData(gd_composite_4);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));

		TextViewer textViewer = new TextViewer(composite_4, SWT.BORDER | SWT.WRAP);
		StyledText styledText = textViewer.getTextWidget();
		styledText.setEnabled(false);
		styledText.setEditable(false);
		styledText.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		styledText
		        .setText("\u63D0\u793A\uFF1A\r\n\t(1)\u4E0B\u9762\u7684\u6570\u636E\u662F\u89D2\u8272\u5728\u6570\u636E\u5E93\u6216\u5185\u5B58\u4E2D\u7684\u4E00\u4E2A\u526F\u672C\uFF0C\u63A8\u8350\u5728\u89D2\u8272\u79BB\u7EBF\u72B6\u6001\u65F6\u4FEE\u6539\u89D2\u8272\u6570\u636E\uFF0C\u57FA\u7840\u6570\u636E\u53EF\u4EE5\u4FEE\u6539\u524D\u9762\u5E26\u201C*\u201D\u7684\u6570\u636E\uFF0C\u590D\u5408\u6570\u636E\u53EF\u4EE5\u4FEE\u6539\u80CC\u5305\u7269\u54C1\u548C\u5BA0\u7269\uFF08\u4FEE\u6539\u65B9\u5F0F\u53CC\u51FB\u8282\u70B9\uFF09;\r\n\t(2)\u6263\u9664\u4F19\u4F34\u662F\u901A\u8FC7\u4F19\u4F34\u6A21\u578B\u3001\u7B49\u7EA7\u548C\u661F\u7EA7\u8FDB\u884C\u5339\u914D\uFF0C\u82E5\u4F19\u4F34\u7B49\u7EA7\u6216\u661F\u7EA7\u88AB\u63D0\u5347\u5C06\u4E0D\u80FD\u6B63\u5E38\u5339\u914D\uFF1B");

		groupOperate = new Group(composite, SWT.NONE);
		groupOperate.setLayout(new RowLayout(SWT.HORIZONTAL));
		groupOperate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		groupOperate.setText("\u64CD\u4F5C\u680F");

		Composite composite_5 = new Composite(groupOperate, SWT.NONE);
		composite_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		composite_5.setLayout(new RowLayout(SWT.HORIZONTAL));

		labelOperate = new Label(composite_5, SWT.NONE);
		labelOperate.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));

		compositeOperate = new Composite(groupOperate, SWT.NONE);
		compositeOperate.setLayoutData(new RowData(SWT.DEFAULT, 25));
		compositeOperate.setLayout(null);

		Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		SashForm form = new SashForm(composite_3, SWT.SMOOTH);
		form.setLayout(new FillLayout());

		Group group_1 = new Group(form, SWT.NONE);
		group_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		group_1.setText("\u57FA\u7840\u6570\u636E");

		Composite composite_2 = new Composite(group_1, SWT.NONE);
		composite_2.setLayout(new TreeColumnLayout());

		baseTreeViewer = new TreeViewer(composite_2, SWT.BORDER);
		baseTreeViewer.addDoubleClickListener(new ClickBaseDataTree());
		Tree baseDataTree = baseTreeViewer.getTree();
		baseDataTree.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		baseDataTree.setLinesVisible(true);
		baseTreeViewer.setLabelProvider(new TreeLableProvider());
		baseTreeViewer.setContentProvider(new TreeContentProvider());

		Group group = new Group(form, SWT.NONE);
		group.setLayout(new FillLayout(SWT.HORIZONTAL));
		group.setText("\u89D2\u8272\u6570\u636E");

		Composite composite_1 = new Composite(group, SWT.NONE);
		composite_1.setLayout(new TreeColumnLayout());

		complexTreeViewer = new TreeViewer(composite_1, SWT.BORDER);
		complexTreeViewer.addSelectionChangedListener(new SelectChanged());
		Tree tree = complexTreeViewer.getTree();
		// complexTreeViewer.addDoubleClickListener(new ClickComplexDataTree());
		tree.setLinesVisible(true);
		form.setWeights(new int[] { 256, 654 });
		complexTreeViewer.setLabelProvider(new TreeLableProvider());
		complexTreeViewer.setContentProvider(new TreeContentProvider());
	}

	public void setUIData(Object data) {
		setData(dataKey, data);
		if (data != null) {
			if (data instanceof CacheRole) {
				CacheRole role = (CacheRole) data;
				labelGame.setText(role.getGameName());
				labelServer.setText(role.getServerName());
				compositeInfo.layout();
				List<CacheRole> roleList = new ArrayList<CacheRole>();
				roleList.add(role);
				complexTreeViewer.setInput(role.getGoodsBag());
				baseTreeViewer.setInput(role.getBaseData());
			}
		}
	}

	public class ClickBaseDataTree implements IDoubleClickListener {
		@Override
		public void doubleClick(DoubleClickEvent arg0) {
			ISelection is = arg0.getSelection();
			if (is != null) {
				TreeSelection ts = (TreeSelection) is;
				if (!ts.isEmpty()) {
					labelOperate.setText("");
					if (buttonCommit != null) {
						buttonCommit.dispose();
						buttonCommit = null;
					}
					if (controlOperate != null) {
						controlOperate.dispose();
						controlOperate = null;
					}

					TreeNode node = (TreeNode) ts.getFirstElement();
					if (node.getNodeName().startsWith("*")) {
						String nodeName = node.getNodeName().replace('：', ':');
						String[] array = StringUtil.splitString(nodeName, ':');
						labelOperate.setText(array[0] + "(目标值)：");

						if (nodeName.contains("境界")) {
							Combo textOperate = new Combo(compositeOperate, SWT.NONE);
							textOperate.setItems(RoleRealmConfig.getNames());
							textOperate.select(0);
							CompositeRoleDetail.this.controlOperate = textOperate;
						} else {
							Text textOperate = new Text(compositeOperate, SWT.BORDER);
							textOperate.setText(array[1]);
							CompositeRoleDetail.this.controlOperate = textOperate;
						}

						if (controlOperate != null && !controlOperate.isDisposed()) {
							controlOperate.setBounds(3, 3, 100, 23);
							buttonCommit = new Button(groupOperate, SWT.NONE);
							buttonCommit.addSelectionListener(new CommitUpdateButton());
							buttonCommit.setText("\u63D0\u4EA4");
						}
					}
					groupOperate.layout();
				}
			}
		}
	}

	class CommitUpdateButton extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String labelText = labelOperate.getText();
			if (labelText.contains("等级") && !labelText.contains("伙伴")) {
				updateRoleLevel();
			} else if (labelText.contains("经验") && !labelText.contains("伙伴")) {
				updateRoleExp();
			} else if (labelText.contains("VIP")) {
				updateRoleVip();
			} else if (labelText.contains("境界")) {
				updateRoleRealm();
			} else if (labelText.contains("物品")) {
				if (labelText.contains("变化量")) {
					updateRoleGoods();
				} else if (labelText.contains("有效期")) {
					updateGoodsOvertime();
				}
			} else if (labelText.contains("伙伴") && labelText.contains("等级")) {
				updateRolePetLevel();
			} else if (labelText.contains("伙伴") && labelText.contains("经验")) {
				updateRolePetExp();
			} else if (labelText.contains("游戏币")) {
				updateRoleMoney();
			}
		}

	}

	private void updateGoodsOvertime() {
		ISelection is = complexTreeViewer.getSelection();
		if (is != null) {
			TreeSelection ts = (TreeSelection) is;
			if (!ts.isEmpty()) {
				int count = 0;
				try {
					count = Integer.parseInt(((Text) controlOperate).getText());
				} catch (Exception exe) {
					MyDialog.openError("有效期必须是不等于0的数字");
					return;
				}
				if (count == 0) {
					MyDialog.openError("有效期必须是不等于0的数字");
					return;
				}

				RoleGoods goods = (RoleGoods) ts.getFirstElement();
				CacheRole role = (CacheRole) getData(dataKey);
				if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的背包内物品【" + goods.getName() + "】有效期设置为："
				        + DateUtil.formatDate(new Date(System.currentTimeMillis() + count * 60000)))) {
					if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的背包内物品【" + goods.getName() + "】有效期设置为："
					        + DateUtil.formatDate(new Date(System.currentTimeMillis() + count * 60000)))) {
						IoBuffer buf = IoBuffer.getPacketBuffer();
						buf.setProtocol(ProGmClient_17000.P_17056_GM_UPDATE_ROLE);
						buf.putInt(role.getGameId());
						buf.putInt(role.getServerId());
						buf.putInt(role.getAccountId());
						buf.putInt(role.getRoleId());
						buf.putString("gmgoodstime " + goods.getBag() + " " + goods.getId() + " " + goods.getGoodsId() + " " + count);
						sendData(buf, true);

						StringBuilder sb_log = new StringBuilder();
						sb_log.append(role.getGameId());
						sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getServerId());
						sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getAccountId());
						sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getRoleId());
						sb_log.append(LoggerFilter.DIVIDE_CHAR).append(goods.getBag());
						sb_log.append(LoggerFilter.DIVIDE_CHAR).append(goods.getId());
						sb_log.append(LoggerFilter.DIVIDE_CHAR).append(goods.getGoodsId());
						sb_log.append(LoggerFilter.DIVIDE_CHAR).append(DateUtil.formatDate(new Date(System.currentTimeMillis() + count * 60000)));
						LoggerDevelop.gm(GmOperate.CLIENT_UPDATE_BAG_GOODS_TIME, GMNetManager.getGmUserId(), sb_log.toString());
					}
				}
			}
		}

	}

	private void updateRolePetLevel() {
		short level = 0;
		try {
			level = Short.parseShort(((Text) controlOperate).getText());
		} catch (Exception exe) {
			MyDialog.openError("伙伴等级必须为大于0的数字");
			return;
		}
		if (level < 1) {
			MyDialog.openError("伙伴等级必须为大于0的数字");
			return;
		}
		RolePet pet = (RolePet) labelOperate.getData("pet");
		CacheRole role = (CacheRole) getData(dataKey);
		if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的伙伴【" + pet.getUsername() + "】的等级设置为>>" + level)) {
			if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的伙伴【" + pet.getUsername() + "】的等级设置为>>" + level)) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17056_GM_UPDATE_ROLE);
				buf.putInt(role.getGameId());
				buf.putInt(role.getServerId());
				buf.putInt(role.getAccountId());
				buf.putInt(role.getRoleId());
				buf.putString("gmpetlevel " + pet.getId() + " " + level);
				sendData(buf, true);

				StringBuilder sb_log = new StringBuilder();
				sb_log.append(role.getGameId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getServerId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getAccountId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getRoleId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getRealId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getLevel());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getStar());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(level);
				LoggerDevelop.gm(GmOperate.CLIENT_ROLE_PET_LEVEL, GMNetManager.getGmUserId(), sb_log.toString());
			}
		}
	}

	private void updateRolePetExp() {
		short exp = 0;
		try {
			exp = Short.parseShort(((Text) controlOperate).getText());
		} catch (Exception exe) {
			MyDialog.openError("伙伴等级必须为大于或等于0的数字");
			return;
		}
		if (exp < 0) {
			MyDialog.openError("伙伴等级必须为大于或等于0的数字");
			return;
		}
		RolePet pet = (RolePet) labelOperate.getData("pet");
		CacheRole role = (CacheRole) getData(dataKey);
		if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的伙伴【" + pet.getUsername() + "】的经验变化量为>>" + exp)) {
			if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的伙伴【" + pet.getUsername() + "】的经验变化量为>>" + exp)) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17056_GM_UPDATE_ROLE);
				buf.putInt(role.getGameId());
				buf.putInt(role.getServerId());
				buf.putInt(role.getAccountId());
				buf.putInt(role.getRoleId());
				buf.putString("gmpetexp " + pet.getId() + " " + exp);
				sendData(buf, true);

				StringBuilder sb_log = new StringBuilder();
				sb_log.append(role.getGameId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getServerId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getAccountId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getRoleId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getRealId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getLevel());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getStar());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(exp);
				LoggerDevelop.gm(GmOperate.CLIENT_ROLE_PET_EXP, GMNetManager.getGmUserId(), sb_log.toString());
			}
		}
	}

	private void updateRoleGoods() {
		int count = 0;
		try {
			count = Integer.parseInt(((Text) controlOperate).getText());
		} catch (Exception exe) {
			MyDialog.openError("物品变化量必须是不等于0的数字");
			return;
		}
		if (count == 0) {
			MyDialog.openError("物品变化量必须是不等于0的数字");
			return;
		}
		RoleGoods goods = (RoleGoods) labelOperate.getData("goods");
		CacheRole role = (CacheRole) getData(dataKey);
		if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的背包内物品【" + goods.getName() + "】的变化量设置为>>" + count)) {
			if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的背包内物品【" + goods.getName() + "】的变化量设置为>>" + count)) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17056_GM_UPDATE_ROLE);
				buf.putInt(role.getGameId());
				buf.putInt(role.getServerId());
				buf.putInt(role.getAccountId());
				buf.putInt(role.getRoleId());
				buf.putString("gmgoodscount " + goods.getBag() + " " + goods.getId() + " " + goods.getGoodsId() + " " + count);
				sendData(buf, true);

				StringBuilder sb_log = new StringBuilder();
				sb_log.append(role.getGameId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getServerId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getAccountId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getRoleId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(goods.getBag());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(goods.getId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(goods.getGoodsId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(count);
				LoggerDevelop.gm(GmOperate.CLIENT_UPDATE_BAG_GOODS_COUNT, GMNetManager.getGmUserId(), sb_log.toString());
			}
		}
	}

	private void updateRoleMoney() {
		int count = 0;
		try {
			count = Integer.parseInt(((Text) controlOperate).getText());
		} catch (Exception exe) {
			MyDialog.openError("游戏币变化量必须是不等于0的数字");
			return;
		}
		if (count == 0) {
			MyDialog.openError("游戏币变化量必须是不等于0的数字");
			return;
		}
		RoleGoods goods = (RoleGoods) labelOperate.getData("goods");
		CacheRole role = (CacheRole) getData(dataKey);
		if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的游戏币【" + goods.getName() + "】的变化量设置为>>" + count)) {
			if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的游戏币【" + goods.getName() + "】的变化量设置为>>" + count)) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17056_GM_UPDATE_ROLE);
				buf.putInt(role.getGameId());
				buf.putInt(role.getServerId());
				buf.putInt(role.getAccountId());
				buf.putInt(role.getRoleId());
				if (count > 0) {
					buf.putString("gm get money " + goods.getName() + " " + count);
				} else {
					buf.putString("gm cost money " + goods.getName() + " " + count);
				}
				sendData(buf, true);

				StringBuilder sb_log = new StringBuilder();
				sb_log.append(role.getGameId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getServerId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getAccountId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getRoleId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(goods.getName());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(count);
				LoggerDevelop.gm(GmOperate.CLIENT_UPDATE_ROLE_MONEY, GMNetManager.getGmUserId(), sb_log.toString());
			}
		}
	}

	private void updateRoleRealm() {
		String text = ((Combo) controlOperate).getText();
		CacheRole role = (CacheRole) getData(dataKey);
		if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的境界修改为>>" + text + "(境界会影响角色等级上限)")) {
			if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的境界修改为>>" + text + "(境界会影响角色等级上限)")) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17056_GM_UPDATE_ROLE);
				buf.putInt(role.getGameId());
				buf.putInt(role.getServerId());
				buf.putInt(role.getAccountId());
				buf.putInt(role.getRoleId());
				buf.putString("gmrealm " + RoleRealmConfig.getRealm(text));
				sendData(buf, true);

				StringBuilder sb_log = new StringBuilder();
				sb_log.append(role.getGameId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getServerId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getAccountId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getRoleId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(text);
				LoggerDevelop.gm(GmOperate.CLIENT_UPDATE_ROLE_REALM, GMNetManager.getGmUserId(), sb_log.toString());
			}
		}
	}

	private void updateRoleExp() {
		int exp = 0;
		try {
			exp = Integer.parseInt(((Text) controlOperate).getText());
		} catch (Exception exe) {
			MyDialog.openError("经验值必须是大于或等于0的数字");
			return;
		}
		if (exp < 0) {
			MyDialog.openError("经验值必须是大于或等于0的数字");
			return;
		}
		CacheRole role = (CacheRole) getData(dataKey);
		if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的经验值修改为>>" + exp + "(经验值会影响角色等级)")) {
			if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的经验值修改为>>" + exp + "(经验值会影响角色等级)")) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17056_GM_UPDATE_ROLE);
				buf.putInt(role.getGameId());
				buf.putInt(role.getServerId());
				buf.putInt(role.getAccountId());
				buf.putInt(role.getRoleId());
				buf.putString("gm role exp " + exp);
				sendData(buf, true);

				StringBuilder sb_log = new StringBuilder();
				sb_log.append(role.getGameId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getServerId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getAccountId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getRoleId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(exp);
				LoggerDevelop.gm(GmOperate.CLIENT_UPDATE_ROLE_EXP, GMNetManager.getGmUserId(), sb_log.toString());
			}
		}
	}

	private void updateRoleVip() {
		short vip = 0;
		try {
			vip = Short.parseShort(((Text) controlOperate).getText());
		} catch (Exception exe) {
			MyDialog.openError("VIP必须是大于或等于0的数字");
			return;
		}
		if (vip < 0) {
			MyDialog.openError("VIP必须是大于或等于0的数字");
			return;
		}
		CacheRole role = (CacheRole) getData(dataKey);
		if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的VIP等级修改为>>" + vip)) {
			if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的VIP等级修改为>>" + vip)) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17056_GM_UPDATE_ROLE);
				buf.putInt(role.getGameId());
				buf.putInt(role.getServerId());
				buf.putInt(role.getAccountId());
				buf.putInt(role.getRoleId());
				buf.putString("gmvip " + vip);
				sendData(buf, true);

				StringBuilder sb_log = new StringBuilder();
				sb_log.append(role.getGameId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getServerId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getAccountId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getRoleId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(vip);
				LoggerDevelop.gm(GmOperate.CLIENT_UPDATE_ROLE_VIP, GMNetManager.getGmUserId(), sb_log.toString());
			}
		}
	}

	private void updateRoleLevel() {
		short level = 1;
		try {
			level = Short.parseShort(((Text) controlOperate).getText());
		} catch (Exception exe) {
			MyDialog.openError("等级必须是大于0的数字");
			return;
		}
		if (level < 1) {
			MyDialog.openError("等级必须是大于0的数字");
			return;
		}
		CacheRole role = (CacheRole) getData(dataKey);
		if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的等级修改为>>" + level)) {
			if (MyDialog.openQuestion("是否要把角色【" + role.getUsername() + "】的等级修改为>>" + level)) {
				IoBuffer buf = IoBuffer.getPacketBuffer();
				buf.setProtocol(ProGmClient_17000.P_17056_GM_UPDATE_ROLE);
				buf.putInt(role.getGameId());
				buf.putInt(role.getServerId());
				buf.putInt(role.getAccountId());
				buf.putInt(role.getRoleId());
				buf.putString("gm level up " + level);
				sendData(buf, true);

				StringBuilder sb_log = new StringBuilder();
				sb_log.append(role.getGameId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getServerId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getAccountId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getRoleId());
				sb_log.append(LoggerFilter.DIVIDE_CHAR).append(level);
				LoggerDevelop.gm(GmOperate.CLIENT_UPDATE_ROLE_LEVEL, GMNetManager.getGmUserId(), sb_log.toString());
			}
		}
	}

	class ActionGoodsDelete extends Action {
		public ActionGoodsDelete() {
			setText("删除物品");
		}

		@Override
		public void run() {
			ISelection is = complexTreeViewer.getSelection();
			if (is != null) {
				TreeSelection ts = (TreeSelection) is;
				if (!ts.isEmpty()) {

					RoleGoods goods = (RoleGoods) ts.getFirstElement();
					CacheRole role = (CacheRole) getData(dataKey);
					if (MyDialog.openQuestion("是否要删除角色【" + role.getUsername() + "】的背包内物品【" + goods.getName() + "】？")) {
						if (MyDialog.openQuestion("是否要删除角色【" + role.getUsername() + "】的背包内物品【" + goods.getName() + "】？")) {
							IoBuffer buf = IoBuffer.getPacketBuffer();
							buf.setProtocol(ProGmClient_17000.P_17056_GM_UPDATE_ROLE);
							buf.putInt(role.getGameId());
							buf.putInt(role.getServerId());
							buf.putInt(role.getAccountId());
							buf.putInt(role.getRoleId());
							buf.putString("gmgoodscount " + goods.getBag() + " " + goods.getId() + " " + goods.getGoodsId() + " "
							        + (-goods.getCount()));
							sendData(buf, true);
						}
					}
				}
			}

		}
	}

	class ActionGoodsCount extends Action {
		public ActionGoodsCount() {
			setText("修改物品数量");
		}

		@Override
		public void run() {
			ISelection is = complexTreeViewer.getSelection();
			if (is != null) {
				TreeSelection ts = (TreeSelection) is;
				if (!ts.isEmpty()) {
					labelOperate.setText("");
					if (buttonCommit != null) {
						buttonCommit.dispose();
						buttonCommit = null;
					}
					if (controlOperate != null) {
						controlOperate.dispose();
						controlOperate = null;
					}

					Object obj = ts.getFirstElement();
					if (obj instanceof RoleGoods) {
						RoleGoods goods = (RoleGoods) obj;
						labelOperate.setText("修改物品【" + goods.getName() + "】数量(变化量)：");

						Text textOperate = new Text(compositeOperate, SWT.BORDER);
						textOperate.setText("");
						CompositeRoleDetail.this.controlOperate = textOperate;
						labelOperate.setData("goods", goods);
					}
					if (controlOperate != null && !controlOperate.isDisposed()) {
						controlOperate.setBounds(3, 3, 100, 23);
						buttonCommit = new Button(groupOperate, SWT.NONE);
						buttonCommit.addSelectionListener(new CommitUpdateButton());
						buttonCommit.setText("\u63D0\u4EA4");
					}
					groupOperate.layout();
				}
			}

		}
	}

	class ActionGoodsOvertime extends Action {
		public ActionGoodsOvertime() {
			setText("修改有效期");
		}

		@Override
		public void run() {
			ISelection is = complexTreeViewer.getSelection();
			if (is != null) {
				TreeSelection ts = (TreeSelection) is;
				if (!ts.isEmpty()) {
					labelOperate.setText("");
					if (buttonCommit != null) {
						buttonCommit.dispose();
						buttonCommit = null;
					}
					if (controlOperate != null) {
						controlOperate.dispose();
						controlOperate = null;
					}

					Object obj = ts.getFirstElement();
					if (obj instanceof RoleGoods) {
						RoleGoods goods = (RoleGoods) obj;
						labelOperate.setText("物品【" + goods.getName() + "】有效期（分）：");
						Text textOperate = new Text(compositeOperate, SWT.BORDER);
						textOperate.setText("0");
						CompositeRoleDetail.this.controlOperate = textOperate;
						labelOperate.setData("goods", goods);
					}
					if (controlOperate != null && !controlOperate.isDisposed()) {
						controlOperate.setBounds(3, 3, 100, 23);
						buttonCommit = new Button(groupOperate, SWT.NONE);
						buttonCommit.addSelectionListener(new CommitUpdateButton());
						buttonCommit.setText("\u63D0\u4EA4");
					}
					groupOperate.layout();
				}
			}

		}
	}

	class ActionMoneyCount extends Action {
		public ActionMoneyCount() {
			setText("修改游戏币数量");
		}

		@Override
		public void run() {
			ISelection is = complexTreeViewer.getSelection();
			if (is != null) {
				TreeSelection ts = (TreeSelection) is;
				if (!ts.isEmpty()) {
					labelOperate.setText("");
					if (buttonCommit != null) {
						buttonCommit.dispose();
						buttonCommit = null;
					}
					if (controlOperate != null) {
						controlOperate.dispose();
						controlOperate = null;
					}

					Object obj = ts.getFirstElement();
					if (obj instanceof RoleGoods) {
						RoleGoods goods = (RoleGoods) obj;
						labelOperate.setText("游戏币【" + goods.getName() + "】(变化量)：");
						Text textOperate = new Text(compositeOperate, SWT.BORDER);
						textOperate.setText("0");
						CompositeRoleDetail.this.controlOperate = textOperate;
						labelOperate.setData("goods", goods);
					}
					if (controlOperate != null && !controlOperate.isDisposed()) {
						controlOperate.setBounds(3, 3, 100, 23);
						buttonCommit = new Button(groupOperate, SWT.NONE);
						buttonCommit.addSelectionListener(new CommitUpdateButton());
						buttonCommit.setText("\u63D0\u4EA4");
					}
					groupOperate.layout();
				}
			}

		}
	}

	class ActionPetUpdateExp extends Action {
		public ActionPetUpdateExp() {
			setText("更新伙伴经验");
		}

		@Override
		public void run() {
			ISelection is = complexTreeViewer.getSelection();
			if (is != null) {
				TreeSelection ts = (TreeSelection) is;
				if (!ts.isEmpty()) {
					labelOperate.setText("");
					if (buttonCommit != null) {
						buttonCommit.dispose();
						buttonCommit = null;
					}
					if (controlOperate != null) {
						controlOperate.dispose();
						controlOperate = null;
					}

					Object obj = ts.getFirstElement();
					if (obj instanceof RolePet) {
						RolePet pet = (RolePet) obj;
						labelOperate.setText("伙伴【" + pet.getUsername() + "】经验(变化量)：");
						Text textOperate = new Text(compositeOperate, SWT.BORDER);
						textOperate.setText(pet.getExperience() + "");
						CompositeRoleDetail.this.controlOperate = textOperate;
						labelOperate.setData("pet", pet);
					}
					if (controlOperate != null && !controlOperate.isDisposed()) {
						controlOperate.setBounds(3, 3, 100, 23);
						buttonCommit = new Button(groupOperate, SWT.NONE);
						buttonCommit.addSelectionListener(new CommitUpdateButton());
						buttonCommit.setText("\u63D0\u4EA4");
					}
					groupOperate.layout();
				}
			}

		}
	}

	class ActionPetUpdateLevel extends Action {
		public ActionPetUpdateLevel() {
			setText("更新伙伴等级");
		}

		@Override
		public void run() {
			ISelection is = complexTreeViewer.getSelection();
			if (is != null) {
				TreeSelection ts = (TreeSelection) is;
				if (!ts.isEmpty()) {
					labelOperate.setText("");
					if (buttonCommit != null) {
						buttonCommit.dispose();
						buttonCommit = null;
					}
					if (controlOperate != null) {
						controlOperate.dispose();
						controlOperate = null;
					}

					Object obj = ts.getFirstElement();
					if (obj instanceof RolePet) {
						RolePet pet = (RolePet) obj;
						labelOperate.setText("伙伴【" + pet.getUsername() + "】等级(最终值)：");
						Text textOperate = new Text(compositeOperate, SWT.BORDER);
						textOperate.setText(pet.getLevel() + "");
						CompositeRoleDetail.this.controlOperate = textOperate;
						labelOperate.setData("pet", pet);
					}
					if (controlOperate != null && !controlOperate.isDisposed()) {
						controlOperate.setBounds(3, 3, 100, 23);
						buttonCommit = new Button(groupOperate, SWT.NONE);
						buttonCommit.addSelectionListener(new CommitUpdateButton());
						buttonCommit.setText("\u63D0\u4EA4");
					}
					groupOperate.layout();
				}
			}

		}
	}

	class ActionPetDelete extends Action {
		public ActionPetDelete() {
			setText("删除伙伴");
		}

		@Override
		public void run() {
			ISelection is = complexTreeViewer.getSelection();
			TreeSelection ts = (TreeSelection) is;
			if (!ts.isEmpty()) {

				Object obj = ts.getFirstElement();
				if (obj instanceof RolePet) {
					RolePet pet = (RolePet) obj;
					CacheRole role = (CacheRole) getData(dataKey);
					if (MyDialog.openQuestion("是否要删除角色【" + role.getUsername() + "】的伙伴【" + pet.getUsername() + "】?")) {
						if (MyDialog.openQuestion("是否要删除角色【" + role.getUsername() + "】的伙伴【" + pet.getUsername() + "】?")) {
							IoBuffer buf = IoBuffer.getPacketBuffer();
							buf.setProtocol(ProGmClient_17000.P_17056_GM_UPDATE_ROLE);
							buf.putInt(role.getGameId());
							buf.putInt(role.getServerId());
							buf.putInt(role.getAccountId());
							buf.putInt(role.getRoleId());
							buf.putString("gmpetdel " + pet.getId());
							sendData(buf, true);

							StringBuilder sb_log = new StringBuilder();
							sb_log.append(role.getGameId());
							sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getServerId());
							sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getAccountId());
							sb_log.append(LoggerFilter.DIVIDE_CHAR).append(role.getRoleId());
							sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getId());
							sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getRealId());
							sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getLevel());
							sb_log.append(LoggerFilter.DIVIDE_CHAR).append(pet.getStar());
							LoggerDevelop.gm(GmOperate.CLIENT_DELETE_ROLE_PET, GMNetManager.getGmUserId(), sb_log.toString());
						}
					}
				}
			}
		}
	}

	class SelectChanged implements ISelectionChangedListener {
		public void selectionChanged(SelectionChangedEvent arg0) {
			ISelection selection = arg0.getSelection();
			TreeSelection ts = (TreeSelection) selection;
			if (ts.isEmpty()) {
				complexTreeViewer.getTree().setMenu(null);
			} else if (ts.getFirstElement() instanceof RoleGoods) {
				RoleGoods goods = (RoleGoods) ts.getFirstElement();
				MenuManager menuBar = new MenuManager();
				if ("货币".equals(goods.getCategoryName())) {
					menuBar.add(new ActionMoneyCount());
				} else {
					menuBar.add(new ActionGoodsDelete());
					menuBar.add(new ActionGoodsOvertime());
					menuBar.add(new ActionGoodsCount());
				}
				Menu menu = menuBar.createContextMenu(getShell());
				complexTreeViewer.getTree().setMenu(menu);
			} else if (ts.getFirstElement() instanceof RolePet) {
				MenuManager menuBar = new MenuManager();
				menuBar.add(new ActionPetDelete());
				menuBar.add(new ActionPetUpdateExp());
				menuBar.add(new ActionPetUpdateLevel());
				Menu menu = menuBar.createContextMenu(getShell());
				complexTreeViewer.getTree().setMenu(menu);
			} else {
				complexTreeViewer.getTree().setMenu(null);
			}

		}
	}
}
