package com.huayi.doupo.base.util.logic.wordfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.SysFilterWords;
import com.huayi.doupo.base.util.logic.system.SpringUtil;

/**
 * 字符过滤工具类
 * @author mp
 * 此程序由北京师范大学2000级计算机系张人杰开发制作,基于多叉树的查找[具体是不是多叉树没细瞅],谨表纪念吧
 * @date 2014-8-28 上午11:20:26
 */
public class WordFilterUtil {
	
	private static Node tree = null;

	/**
	 * 加入缓存
	 * @author mp
	 * @date 2014-8-28 上午11:21:46
	 * @param list
	 * @Description
	 */
	public static void insertFilterWordsList(){
		List<SysFilterWords> filterWordList = DALFactory.getSysFilterWordsDAL().getList("", 0);
		try {
			tree = new Node();
			if (filterWordList != null) {
				for (SysFilterWords obj : filterWordList) {
					if (obj != null && obj.getName() != null && !(obj.getName().trim().equals("")) && obj.getLevel() >= 0) {
						insertWord(obj.getName().trim(), obj.getLevel());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 加入
	 * @author mp
	 * @date 2014-8-28 上午11:21:57
	 * @param word
	 * @param level
	 * @Description
	 */
	private static void insertWord(String word, int level) {
		Node node = tree;
		for (int i = 0; i < word.length(); i++) {
			node = node.addChar(word.charAt(i));
		}
		node.setEnd(true);
		node.setLevel(level);
	}

	private static boolean isPunctuationChar(String c) {
		String regex = "[\\pP\\pZ\\pS\\pM\\pC]";
		Pattern p = Pattern.compile(regex, 2);
		Matcher m = p.matcher(c);
		return m.find();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static PunctuationOrHtmlFilteredResult filterPunctation(String originalString) {
		StringBuffer filteredString = new StringBuffer();
		ArrayList charOffsets = new ArrayList();
		for (int i = 0; i < originalString.length(); i++) {
			String c = String.valueOf(originalString.charAt(i));
			if (!isPunctuationChar(c)) {
				filteredString.append(c);
				charOffsets.add(Integer.valueOf(i));
			}
		}
		PunctuationOrHtmlFilteredResult result = new PunctuationOrHtmlFilteredResult();
		result.setOriginalString(originalString);
		result.setFilteredString(filteredString);
		result.setCharOffsets(charOffsets);
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static PunctuationOrHtmlFilteredResult filterPunctationAndHtml(String originalString) {
		StringBuffer filteredString = new StringBuffer();
		ArrayList charOffsets = new ArrayList();
		int i = 0;
		for (int k = 0; i < originalString.length(); i++) {
			String c = String.valueOf(originalString.charAt(i));
			if (originalString.charAt(i) == '<') {
				for (k = i + 1; k < originalString.length(); k++) {
					if (originalString.charAt(k) == '<') {
						k = i;
						break;
					}
					if (originalString.charAt(k) == '>') {
						break;
					}
				}
				i = k;
			} else if (!isPunctuationChar(c)) {
				filteredString.append(c);
				charOffsets.add(Integer.valueOf(i));
			}
		}

		PunctuationOrHtmlFilteredResult result = new PunctuationOrHtmlFilteredResult();
		result.setOriginalString(originalString);
		result.setFilteredString(filteredString);
		result.setCharOffsets(charOffsets);
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	private static FilteredResult filter(PunctuationOrHtmlFilteredResult pohResult, char replacement) {
		StringBuffer sentence = pohResult.getFilteredString();
		ArrayList charOffsets = pohResult.getCharOffsets();
		StringBuffer resultString = new StringBuffer(pohResult.getOriginalString());
		StringBuffer badWords = new StringBuffer();
		int level = 0;
		Node node = tree;
		int start = 0;
		int end = 0;
		for (int i = 0; i < sentence.length(); i++) {
			start = i;
			end = i;
			node = tree;
			for (int j = i; j < sentence.length(); j++) {
				node = node.findChar(sentence.charAt(j));
				if (node == null) {
					break;
				}
				if (node.isEnd()) {
					end = j;
					level = node.getLevel();
				}
			}
			if (end > start) {
				for (int k = start; k <= end; k++) {
					resultString.setCharAt(((Integer) charOffsets.get(k))
							.intValue(), replacement);
				}
				if (badWords.length() > 0)
					badWords.append(",");
				badWords.append(sentence.substring(start, end + 1));
				i = end;
			}
		}
		FilteredResult result = new FilteredResult();
		result.setOriginalContent(pohResult.getOriginalString());
		result.setFilteredContent(resultString.toString());
		result.setBadWords(badWords.toString());
		result.setLevel(Integer.valueOf(level));
		return result;
	}

	public static String simpleFilter(String sentence, char replacement) {
		StringBuffer sb = new StringBuffer();
		Node node = tree;
		int start = 0;
		int end = 0;
		for (int i = 0; i < sentence.length(); i++) {
			start = i;
			end = i;
			node = tree;
			for (int j = i; j < sentence.length(); j++) {
				node = node.findChar(sentence.charAt(j));
				if (node == null) {
					break;
				}
				if (node.isEnd()) {
					end = j;
				}
			}
			if (end > start) {
				for (int k = start; k <= end; k++) {
					sb.append(replacement);
				}
				i = end;
			} else {
				sb.append(sentence.charAt(i));
			}
		}
		return sb.toString();
	}

	public static FilteredResult filterText(String originalString,char replacement) {
		return filter(filterPunctation(originalString), replacement);
	}

	public static FilteredResult filterHtml(String originalString,char replacement) {
		return filter(filterPunctationAndHtml(originalString), replacement);
	}

	public static void main(String[] args) {
		
		SpringUtil.getSpringContext();
		WordFilterUtil.insertFilterWordsList();
		
		//过滤后的字符串
		String filteredStr = simpleFilter("江泽民哈哈", '*');
		System.out.println(filteredStr);
		
		//是否有敏感字符
		FilteredResult result = filterText("江泽民haha, 法轮功", '*');
		System.out.println(result.getFilteredContent());
		System.out.println(result.getBadWords());
		System.out.println(result.getLevel());
		
		/*		result = filterHtml("网站<font>黄</font>.<色<script>,漫,画,网站", '*');
		System.out.println(result.getFilteredContent());
		System.out.println(result.getBadWords());
		long start = System.currentTimeMillis();
		result = filterHtml(str, '*');
		long end = System.currentTimeMillis();
		System.out.println("====Time====" + (end - start));
		System.out.println("original:" + result.getOriginalContent());
		System.out.println("result:" + result.getFilteredContent());
		System.out.println("badWords:" + result.getBadWords());
		System.out.println("level:" + result.getLevel());
		start = System.currentTimeMillis();
		result = filterText(str, '*');
		end = System.currentTimeMillis();
		System.out.println("====Time====" + (end - start));
		System.out.println("original:" + result.getOriginalContent());
		System.out.println("result:" + result.getFilteredContent());
		System.out.println("badWords:" + result.getBadWords());
		System.out.println("level:" + result.getLevel());*/
	}

	public static class PunctuationOrHtmlFilteredResult {
		
		private String originalString;
		
		private StringBuffer filteredString;
		
		private ArrayList<Integer> charOffsets;

		public String getOriginalString() {
			return this.originalString;
		}

		public void setOriginalString(String originalString) {
			this.originalString = originalString;
		}

		public StringBuffer getFilteredString() {
			return this.filteredString;
		}

		public void setFilteredString(StringBuffer filteredString) {
			this.filteredString = filteredString;
		}

		public ArrayList<Integer> getCharOffsets() {
			return this.charOffsets;
		}

		public void setCharOffsets(ArrayList<Integer> charOffsets) {
			this.charOffsets = charOffsets;
		}
	}
}