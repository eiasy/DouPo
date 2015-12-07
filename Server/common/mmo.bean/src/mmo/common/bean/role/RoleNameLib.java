package mmo.common.bean.role;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mmo.common.config.role.RoleSex;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MathUtil;
import mmo.tools.util.StringUtil;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class RoleNameLib {
	protected final static String     TAG_ROOT_START   = "<names>";
	protected final static String     TAG_ROOT_END     = "</names>";
	protected final static String     TAG_XING_START   = "<xing text=\"";
	protected final static String     TAG_XING_END     = "\" />";
	protected final static String     TAG_MALE_START   = "<name-male text=\"";
	protected final static String     TAG_MALE_END     = "\" />";
	protected final static String     TAG_FEMALE_START = "<name-female text=\"";
	protected final static String     TAG_FEMALE_END   = "\" />";

	protected final static String     TAG_NAMES        = "names";
	protected final static String     TAG_XING         = "xing";
	protected final static String     TAG_CHAR         = "char";
	protected final static String     TAG_CODE         = "code";

	protected final static String     TAG_NAME_MALE    = "name-male";
	protected final static String     TAG_NAME_FEMALE  = "name-female";
	protected final static String     ATT_TEXT         = "text";
	/** 女角色名称队列 */
	private static List<String>       femaleNameQueue  = new LinkedList<String>();
	/** 男角色名称队列 */
	private static List<String>       maleNameQueue    = new LinkedList<String>();
	private static Map<Short, String> charCode         = new HashMap<Short, String>();

	public final static void loadNameLib(String charFile, String nameFile) {
		initName(charFile, nameFile, false);
	}

	/**
	 * 初始化角色名称库，并返回过滤后的姓及名称组成的新文本
	 * 
	 * @param charFile
	 *            字符库配置文件
	 * @param nameFile
	 *            名称库配置文件
	 * @param filter
	 *            过滤开关
	 * @return 过滤后的姓及名称组成的新文本
	 */
	public static String initName(String charFile, String nameFile, boolean filter) {
		parserCharCode(charFile);
		return parserNameXML(nameFile, filter);
	}

	public static void initName(String charFile, String nameFile) {
		initName(charFile, nameFile, false);
	}

	public static String nextRoleName(byte sex) {
		String name = null;
		if (sex == RoleSex.MALE) {
			name = maleNameQueue.get(MathUtil.getRandom(0, maleNameQueue.size()));
		} else {
			name = femaleNameQueue.get(MathUtil.getRandom(0, femaleNameQueue.size()));
		}
		if (StringUtil.validateKeyword(name)) {
			if (sex == RoleSex.MALE) {
				name = maleNameQueue.get(MathUtil.getRandom(0, maleNameQueue.size()));
			} else {
				name = femaleNameQueue.get(MathUtil.getRandom(0, femaleNameQueue.size()));
			}
		}
		return name;
	}

	protected static final void parserCharCode(String charFile) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(charFile);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String text = null;
		Element notices = doc.getRootElement();
		List<Element> eleCodeList = notices.getChildren(TAG_CODE);
		for (Element codeEle : eleCodeList) {
			text = codeEle.getAttributeValue(ATT_TEXT);
			if (text != null && text.trim().length() > 0) {
				short[] codes = StringUtil.string2ShortArray(text.trim());
				addCharCode(codes);
			}
		}
	}

	private static final void addCharCode(short[] codes) {
		if (codes == null) {
			return;
		}
		int length = codes.length;
		for (int li = 0; li < length; li++) {
			charCode.put(codes[li], TAG_CODE);
		}
	}

	/**
	 * 
	 * @param name
	 * @return true通过，false未通过
	 */
	public static final boolean validateName(String name) {
		if (name == null) {
			return false;
		}
		char[] chars = name.toCharArray();
		int cl = chars.length;
		for (int ci = 0; ci < cl; ci++) {
			if (charCode.get((short) chars[ci]) == null) {
				return false;
			}
		}
		return true;
	}

	private static final String parserNameXML(String nameFile, boolean filter) {
		StringBuilder fileContent = new StringBuilder();
		List<String> xingList = new ArrayList<String>();
		List<String> namesFemale = new ArrayList<String>();
		List<String> namesMale = new ArrayList<String>();
		List<String> femaleNames = new ArrayList<String>();
		List<String> maleNames = new ArrayList<String>();

		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(nameFile);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String text = null;
		Element notices = doc.getRootElement();
		List<Element> eleXingList = notices.getChildren(TAG_XING);
		if (eleXingList != null) {
			for (Element ele : eleXingList) {
				text = ele.getAttributeValue(ATT_TEXT);
				if (text != null && text.trim().length() > 0) {
					if (filter) {
						if (filter(text.trim())) {
							xingList.add(text.trim());
						}
					} else {
						xingList.add(text.trim());
					}
				}
			}
		}

		List<Element> eleNameFemaleList = notices.getChildren(TAG_NAME_FEMALE);
		if (eleNameFemaleList != null) {
			for (Element ele : eleNameFemaleList) {
				text = ele.getAttributeValue(ATT_TEXT);
				if (text != null && text.trim().length() > 0) {
					if (filter) {
						if (filter(text.trim())) {
							namesFemale.add(text.trim());
						}
					} else {
						namesFemale.add(text.trim());
					}
				}
			}
		}
		List<Element> eleNameMaleList = notices.getChildren(TAG_NAME_MALE);
		if (eleNameMaleList != null) {
			for (Element ele : eleNameMaleList) {
				text = ele.getAttributeValue(ATT_TEXT);
				if (text != null && text.trim().length() > 0) {
					if (filter) {
						if (filter(text.trim())) {
							namesMale.add(text.trim());
						}
					} else {
						namesMale.add(text.trim());
					}
				}
			}
		}
		int xingSize = xingList.size();
		int femaleNameSize = namesFemale.size();
		int maleNameSize = namesMale.size();

		if (filter) {
			fileContent.append(TAG_ROOT_START);

			for (int xi = 0; xi < xingSize; xi++) {
				fileContent.append(TAG_XING_START);
				fileContent.append(xingList.get(xi));
				fileContent.append(TAG_XING_END);
			}
			for (int ni = 0; ni < femaleNameSize; ni++) {
				fileContent.append(TAG_FEMALE_START);
				fileContent.append(namesFemale.get(ni));
				fileContent.append(TAG_FEMALE_END);
			}
			for (int ni = 0; ni < maleNameSize; ni++) {
				fileContent.append(TAG_MALE_START);
				fileContent.append(namesMale.get(ni));
				fileContent.append(TAG_MALE_END);
			}

			fileContent.append(TAG_ROOT_END);
		}

		String xing = null;
		String name = null;
		StringBuilder sb = new StringBuilder();
		for (int xi = 0; xi < xingSize; xi++) {
			xing = xingList.get(xi);
			for (int ni = 0; ni < femaleNameSize; ni++) {
				sb.setLength(0);
				sb.append(xing).append(namesFemale.get(ni));
				name = sb.toString();
				if (!RoleManager.validateName(name)) {
					continue;
				}
				femaleNames.add(name);
			}
			for (int ni = 0; ni < maleNameSize; ni++) {
				sb.setLength(0);
				sb.append(xing).append(namesMale.get(ni));
				name = sb.toString();
				if (!RoleManager.validateName(name)) {
					continue;
				}
				maleNames.add(sb.toString());
			}
		}

		int a = 0;
		int b = 0;
		int nsize = femaleNames.size();
		int exchangeCount = nsize << 1;
		for (int ii = 0; ii < exchangeCount; ii++) {
			a = MathUtil.getRandom(0, nsize - 1);
			b = MathUtil.getRandom(0, nsize - 1);
			if (a == b) {
				continue;
			}
			name = femaleNames.get(a);
			femaleNames.set(a, femaleNames.get(b));
			femaleNames.set(b, name);
		}

		for (int ii = 0; ii < nsize; ii++) {
			femaleNameQueue.add(femaleNames.get(ii));
		}

		nsize = maleNames.size();
		exchangeCount = nsize << 1;
		for (int ii = 0; ii < exchangeCount; ii++) {
			a = MathUtil.getRandom(0, nsize - 1);
			b = MathUtil.getRandom(0, nsize - 1);
			if (a == b) {
				continue;
			}
			name = maleNames.get(a);
			maleNames.set(a, maleNames.get(b));
			maleNames.set(b, name);
		}
		for (int ii = 0; ii < nsize; ii++) {
			maleNameQueue.add(maleNames.get(ii));
		}
		LoggerError.messageLog.warn("女角色名剩余数量： " + femaleNameQueue.size());
		LoggerError.messageLog.warn("男角色名剩余数量： " + maleNameQueue.size());

		xingList.clear();
		namesFemale.clear();
		namesMale.clear();
		femaleNames.clear();
		maleNames.clear();

		xingList = null;
		namesFemale = null;
		namesMale = null;
		femaleNames = null;
		maleNames = null;

		return fileContent.toString();
	}

	private static final boolean filter(String text) {
		return validateName(text) && !StringUtil.validateKeyword(text);
	}
}
