package mmo.tools.util;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

public class HtmlUtil {
	/**
	 * 过滤文本中的html标签
	 * 
	 * @param source
	 * @return
	 */
	public static String extractText(String source) {
		StringBuilder result = new StringBuilder();

		Parser parser = null;
		try {
			parser = Parser.createParser(new String(source.getBytes(), "GBK"), "GBK");
			// 遍历所有的节点
			NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter() {
				private static final long serialVersionUID = 1L;

				public boolean accept(Node node) {
					return true;
				}
			});

			Node nodet = null;
			for (int i = 0; i < nodes.size(); i++) {
				nodet = nodes.elementAt(i);
				if (nodet.getParent() != null) {
					continue;
				}
				result.append(new String(nodet.toPlainTextString().getBytes("GBK")));
			}
			return result.toString();
		} catch (Exception e) {
			System.out.println("过滤html标签出错！");
			e.printStackTrace();
			return source;
		}
	}

	public static void main(String[] arg) {
		System.out.println(extractText("中国人<br><b>牛肉<h1>哇哦了</h1>"));
	}
}
