package mmo.common.config.buttons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class FunButtonManager {
	protected static final String                TAG_ROOT     = "fun-button";
	protected static final String                TAG_BUTTON   = "button";

	protected static final String                ATT_ID       = "id";
	protected static final String                ATT_FLAG     = "flag";
	protected static final String                ATT_VALUE    = "value";
	protected static final String                ATT_NOTE     = "note";

	private static Map<Integer, List<FunButton>> acceptActive = new HashMap<Integer, List<FunButton>>();
	private static Map<Integer, List<FunButton>> commitActive = new HashMap<Integer, List<FunButton>>();
	private static Map<Integer, List<FunButton>> levelActive  = new HashMap<Integer, List<FunButton>>();

	public static final void parserXML(String file) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String text = null;
		int id = 0;
		int value = 0;
		int flag = 0;
		String note = null;
		Element ele = null;
		FunButton btn = null;
		Element buttons = doc.getRootElement();
		List<Element> eleBtnList = buttons.getChildren(TAG_BUTTON);
		int bsize = eleBtnList.size();
		for (int bi = 0; bi < bsize; bi++) {
			ele = eleBtnList.get(bi);
			text = ele.getAttributeValue(ATT_FLAG);
			if (text != null && text.trim().length() > 0) {
				flag = Integer.parseInt(text.trim());
			}
			text = ele.getAttributeValue(ATT_ID);
			if (text != null && text.trim().length() > 0) {
				id = Integer.parseInt(text.trim());
			}
			text = ele.getAttributeValue(ATT_VALUE);
			if (text != null && text.trim().length() > 0) {
				value = Integer.parseInt(text.trim());
			}
			text = ele.getAttributeValue(ATT_NOTE);
			if (text != null && text.trim().length() > 0) {
				note = text.trim();
			}
			btn = new FunButton();
			btn.setFlag(flag);
			btn.setId(id);
			btn.setNote(note);
			btn.setValue(value);
			addFunButton(btn);
		}
	}

	private static final void addFunButton(FunButton btn) {
		Map<Integer, List<FunButton>> btns = null;
		switch (btn.getFlag()) {
			case IButtonFlag.MISSION_ACCEPT: {
				btns = acceptActive;
				break;
			}
			case IButtonFlag.MISSION_COMMIT: {
				btns = commitActive;
				break;
			}
			case IButtonFlag.TO_LEVEL: {
				btns = levelActive;
				break;
			}
		}
		List<FunButton> btnList = btns.get(btn.getValue());
		if (btnList == null) {
			btnList = new ArrayList<FunButton>();
			btns.put(btn.getValue(), btnList);
		}
		btnList.add(btn);
	}

	public static final List<FunButton> getAcceptButton(int missionId) {
		return acceptActive.get(missionId);
	}

	public static final List<FunButton> getCommitButton(int missionId) {
		return commitActive.get(missionId);
	}
	
	public static final List<FunButton> getLevelButton(int missionId) {
		return levelActive.get(missionId);
	}
}
