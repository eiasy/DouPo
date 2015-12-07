package mmo.common.config.chargeaward;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.tools.util.DateUtil;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class GetChargeAward {
	public static GetChargeAward  instance    = null;

	/**
	 * 首次充值奖励活动截止日期
	 */
	private String                chargeFirstLimitdate;
	private long                  firstLimitTime;
	/**
	 * 充值奖励活动截止日期
	 */
	private String                chargeAwardLimitdate;
	private long                  awardLimitTime;
	/** 首次充值奖励物品 key = 物品ID，value = 物品数量 */
	private Map<Integer, Integer> chargeFirst = new HashMap<Integer, Integer>();
	/** 充值奖励物品 key = 充值金额 value = 奖励比列 */
	private Map<Integer, Integer> chargeAward = new HashMap<Integer, Integer>();
	private int                   minMoney;
	private int                   maxMoney;

	public static final GetChargeAward getInstance() {
		if (instance == null) {
			instance = new GetChargeAward();
		}
		return instance;
	}

	public void init(String filePath) {
		SAXBuilder builder = new SAXBuilder();
		try {
			FileInputStream input = new FileInputStream(filePath);
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			List<Element> children = root.getChildren();
			List<Integer> moneys = new ArrayList<Integer>();
			for (Element child : children) {
				List<Element> elements = child.getChildren();
				if ("charge-first".equals(child.getName())) {
					chargeFirstLimitdate = child.getAttributeValue("limitdate");
					firstLimitTime = DateUtil.stringToDate(chargeFirstLimitdate, "yyyy-MM-dd").getTime();
					for (Element ele : elements) {
						int goodsId = Integer.parseInt(ele.getAttributeValue("id"));
						int count = Integer.parseInt(ele.getAttributeValue("count"));
						chargeFirst.put(goodsId, count);
					}
				} else if ("charge-award".equals(child.getName())) {
					chargeAwardLimitdate = child.getAttributeValue("limitdate");
					awardLimitTime = DateUtil.stringToDate(chargeAwardLimitdate, "yyyy-MM-dd").getTime();
					for (Element ele : elements) {
						int percent = Integer.parseInt(ele.getAttributeValue("award"));
						int money = Integer.parseInt(ele.getAttributeValue("value"));
						chargeAward.put(money, percent);
						int msize = moneys.size();
						boolean added = false;
						for (int mi = 0; mi < msize; mi++) {
							if (money < moneys.get(mi)) {
								moneys.add(mi, money);
								added = true;
								break;
							}
						}
						if (!added) {
							moneys.add(money);
						}
					}
					int msize = moneys.size();
					if (msize > 0) {
						minMoney = moneys.get(0);
						maxMoney = moneys.get(msize - 1);
						int sub = 0;
						int sup = 0;
						int award = 0;
						for (int mi = 0; mi < msize - 1; mi++) {
							sub = moneys.get(mi);
							sup = moneys.get(mi + 1) - 1;
							award = chargeAward.get(moneys.get(mi));
							for (; sub <= sup; sub++) {
								chargeAward.put(sub, award);
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public String getChargeFirstLimitdate() {
//		return chargeFirstLimitdate;
//	}

//	public String getChargeAwardLimitdate() {
//		return chargeAwardLimitdate;
//	}

	public Map<Integer, Integer> getChargeFirst() {
		return chargeFirst;
	}

	// public Map<Integer, Integer> getChargeAward() {
	// return chargeAward;
	// }

	public int getChargeAward(int money) {
		Integer award = chargeAward.get(money);
		if (award == null) {
			if (money > maxMoney) {
				return chargeAward.get(maxMoney);
			}
			return 0;
		}
		return award;
	}

	public long getFirstLimitTime() {
    	return firstLimitTime;
    }

	public long getAwardLimitTime() {
    	return awardLimitTime;
    }
}
