package mmo.common.bean.xml;

import java.util.List;

import mmo.common.bean.mission.GameMission;
import mmo.common.bean.mission.extension.Award;
import mmo.common.bean.mission.extension.SectAward;
import mmo.common.config.MissionConfig;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AwardHandler extends DefaultHandler {
	private final static String TAG_AWARD            = "award";
	private final static String ATTRIBUTE_FLAG       = "flag";
	private final static String ATTRIBUTE_ID         = "id";
	private final static String ATTRIBUTE_COUNT      = "count";
	private final static String ATTRIBUTE_PROFESSION = "profession";
	private final static String ATT_BINDED           = "binded";
	private final static String ATT_QUALITY          = "quality";
	private final static String ATT_SECT_GOLD        = "sectgold";
	private final static String ATT_SECT_STONE       = "sectstone";
	private final static String ATT_SECT_WOOD        = "sectwood";
	private final static String ATT_SECT_IRON        = "sectiron";
//	private final static String ATT_SECT_PERFECT     = "sectperfect";
//	private final static String ATT_SECT_REPUTE      = "sectrepute";
	private List<Award>         awards               = null;
	private GameMission         mission;

	public AwardHandler(GameMission mission) {
		this.awards = mission.getAwards();
		this.mission = mission;
	}

	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (TAG_AWARD.equals(localName)) {
			int flag = Integer.parseInt(attributes.getValue(ATTRIBUTE_FLAG));
			switch (flag) {
				case MissionConfig.Award.GOODS: {
					Award award = new Award();
					award.setFlag(flag);
					String attribute = attributes.getValue(ATTRIBUTE_ID);
					if (attribute != null) {
						award.setId(Integer.parseInt(attribute));
					}
					attribute = attributes.getValue(ATTRIBUTE_COUNT);
					if (attribute != null) {
						award.setCount(Integer.parseInt(attribute));
					}
					attribute = attributes.getValue(ATTRIBUTE_PROFESSION);
					if (attribute != null) {
						award.setProfession(Byte.parseByte(attribute));
					}
					attribute = attributes.getValue(ATT_QUALITY);
					if (attribute != null) {
						award.setQuality(Byte.parseByte(attribute));
					}
					attribute = attributes.getValue(ATT_BINDED);
					if (attribute != null && attribute.trim().length() > 0) {
						if ("true".equalsIgnoreCase(attribute.trim())) {
							award.setBinded(true);
						}
					}
					this.awards.add(award);
					break;
				}
				case MissionConfig.Award.MONEY: {
					Award award = new Award();
					award.setFlag(flag);
					String attribute = attributes.getValue(ATTRIBUTE_ID);
					if (attribute != null) {
						award.setId(Integer.parseInt(attribute));
					}
					attribute = attributes.getValue(ATTRIBUTE_COUNT);
					if (attribute != null) {
						award.setCount(Integer.parseInt(attribute));
					}
					this.awards.add(award);
					break;
				}
//				case MissionConfig.Award.EXPERIENCE: {
//					String attribute = attributes.getValue(ATTRIBUTE_COUNT);
//					if (attribute != null) {
//						mission.setAwardExperience(Integer.parseInt(attribute));
//					}
//					break;
//				}
				case MissionConfig.Award.CONTRIBUTE: {
					String attribute = attributes.getValue(ATTRIBUTE_COUNT);
					if (attribute != null) {
						Award award = new Award();
						award.setFlag(flag);
						award.setCount(Integer.parseInt(attribute));
						this.awards.add(award);
					}
					break;
				}
				case MissionConfig.Award.REALM_POINT: {
					String attribute = attributes.getValue(ATTRIBUTE_COUNT);
					if (attribute != null) {
						Award award = new Award();
						award.setFlag(flag);
						award.setCount(Integer.parseInt(attribute));
						this.awards.add(award);
					}
					break;
				}
				case MissionConfig.Award.SECT_AWARD: {
					Award award = new SectAward();
					award.setFlag(flag);
					String attribute = attributes.getValue(ATT_SECT_GOLD);
					if (attribute != null) {
						award.setSectGold(Integer.parseInt(attribute));
					}
					attribute = attributes.getValue(ATT_SECT_WOOD);
					if (attribute != null) {
						award.setSectWood(Integer.parseInt(attribute));
					}
					attribute = attributes.getValue(ATT_SECT_STONE);
					if (attribute != null) {
						award.setSectStone(Integer.parseInt(attribute));
					}
					attribute = attributes.getValue(ATT_SECT_IRON);
					if (attribute != null) {
						award.setSectIron(Integer.parseInt(attribute));
					}
//					attribute = attributes.getValue(ATT_SECT_PERFECT);
//					if (attribute != null) {
//						award.setSectPerfect(Integer.parseInt(attribute));
//					}
//					attribute = attributes.getValue(ATT_SECT_REPUTE);
//					if (attribute != null) {
//						award.setSectRepute(Integer.parseInt(attribute));
//					}
					this.awards.add(award);
					break;
				}
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
	}

}
