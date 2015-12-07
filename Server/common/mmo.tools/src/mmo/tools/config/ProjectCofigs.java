package mmo.tools.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.tools.log.LoggerError;
import mmo.tools.util.ClassLoaderUtil;
import mmo.tools.util.StringUtil;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ProjectCofigs {
	private final static List<String>              MQ_LIST_NULL   = new ArrayList<String>();
	private final static Map<String, String>       files          = new HashMap<String, String>();
	private final static Map<String, NetAddress>   nets           = new HashMap<String, NetAddress>();
	private final static Map<String, String>       messageQueue   = new HashMap<String, String>();
	private final static Map<String, List<String>> queueList      = new HashMap<String, List<String>>();
	private final static Map<String, Integer>      packetConfigs  = new HashMap<String, Integer>();
	private final static Map<String, String>       parameters     = new HashMap<String, String>();

	private final static String                    TAG_FILES      = "files";
	private final static String                    TAG_PARAMETERS = "parameters";
	private final static String                    TAG_PARAMETER  = "parameter";
	private final static String                    TAG_FILE       = "file";
	private final static String                    TAG_NETS       = "nets";
	private final static String                    TAG_NET        = "net";
	private final static String                    TAG_PACKET     = "packet";
	private static final String                    TAG_MQ         = "mq";
	private static final String                    TAG_MQ_LIST    = "mq-list";

	private final static String                    ATT_NAME       = "name";
	private final static String                    ATT_FILE       = "file";
	private final static String                    ATT_IP         = "ip";
	private final static String                    ATT_PORT       = "port";
	private final static String                    ATT_URL        = "url";
	private final static String                    ATT_VALUE      = "value";
	private final static String                    ATT_USER       = "user";
	private final static String                    ATT_PASSWORD   = "password";

	public final static void init(String configFile) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = null;
			try {
				doc = builder.build(new InputStreamReader(new FileInputStream(new File(configFile)), "UTF-8"));
			} catch (Exception e) {
				LoggerError.error("解析文件报错#" + configFile);
			}
			Element root = doc.getRootElement();

			Element loggerEle = root.getChild(TAG_FILES);
			if (loggerEle != null) {
				List<Element> logList = loggerEle.getChildren(TAG_FILE);
				if (logList != null) {
					String name = null;
					String file = null;
					for (Element ele : logList) {
						name = ele.getAttributeValue(ATT_NAME);
						file = ele.getAttributeValue(ATT_FILE);
						if (name != null && file != null) {
							files.put(name, ClassLoaderUtil.getExtendResource(file));
						}
					}
				}
			}

			Element parameterEle = root.getChild(TAG_PARAMETERS);
			if (parameterEle != null) {
				List<Element> logList = parameterEle.getChildren(TAG_PARAMETER);
				if (logList != null) {
					String name = null;
					String file = null;
					for (Element ele : logList) {
						name = ele.getAttributeValue(ATT_NAME);
						file = ele.getAttributeValue(ATT_VALUE);
						if (name != null && file != null) {
							if (name.toLowerCase().startsWith("link_file")) {
								init(getFile(file));
							} else {
								parameters.put(name, file);
							}
						}
					}
				}
			}

			List<Element> eleMQ = root.getChildren(TAG_MQ);
			if (eleMQ != null) {
				String name = null;
				String value = null;
				for (Element ele : eleMQ) {
					name = ele.getAttributeValue(ATT_NAME);
					if (name == null || name.trim().length() < 1) {
						continue;
					}
					value = ele.getAttributeValue(ATT_VALUE);
					if (value == null || value.trim().length() < 1) {
						continue;
					}
					messageQueue.put(name.trim(), value.trim());
				}
			}

			List<Element> eleMQList = root.getChildren(TAG_MQ_LIST);
			if (eleMQList != null) {
				String name = null;
				for (Element ele : eleMQList) {
					name = ele.getAttributeValue(ATT_NAME);
					if (name == null || name.trim().length() < 1) {
						continue;
					}

					String[] array = StringUtil.splitString(ele.getAttributeValue(ATT_VALUE), ',');
					List<String> mqList = new ArrayList<String>();
					for (int qi = 0; qi < array.length; qi++) {
						mqList.add(array[qi].trim());
					}
					queueList.put(name.trim(), mqList);
				}
			}

			Element netsEle = root.getChild(TAG_NETS);
			if (netsEle != null) {
				List<Element> netList = netsEle.getChildren(TAG_NET);
				if (netList != null) {
					String name = null;
					String ip = null;
					int port = 0;
					String url = null;
					for (Element ele : netList) {
						try {
							name = ele.getAttributeValue(ATT_NAME);
							if (name == null) {
								continue;
							}
							ip = ele.getAttributeValue(ATT_IP);
							if (ele.getAttributeValue(ATT_PORT) != null) {
								port = Integer.parseInt(ele.getAttributeValue(ATT_PORT));
							}
							url = ele.getAttributeValue(ATT_URL);
							NetAddress nc = new NetAddress();
							nc.setIp(ip);
							nc.setPort(port);
							nc.setName(name);
							nc.setUrl(url);
							nc.setUser(ele.getAttributeValue(ATT_USER));
							nc.setPassword(ele.getAttributeValue(ATT_PASSWORD));
							nets.put(name, nc);
						} catch (Exception e) {
							System.out.println("解析文件报错#" + configFile);
							e.printStackTrace();
						}
					}
				}

				List<Element> pkgList = netsEle.getChildren(TAG_PACKET);
				if (netList != null) {
					String name = null;
					int size = 0;
					for (Element ele : pkgList) {
						try {
							name = ele.getAttributeValue(ATT_NAME);
							if (name == null) {
								continue;
							}
							if (ele.getAttributeValue(ATT_VALUE) != null) {
								size = Integer.parseInt(ele.getAttributeValue(ATT_VALUE));
							}
							packetConfigs.put(name.trim(), size);
						} catch (Exception e) {
							System.out.println("解析文件报错#" + configFile);
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			LoggerError.error("解析文件报错#" + configFile);
		}
	}

	public final static String getMessageQueue(String name) {
		return messageQueue.get(name);
	}

	public final static NetAddress getNetAddress(String name) {
		return nets.get(name);
	}

	public final static String getFile(String name) {
		return files.get(name);
	}

	public final static String getParameter(String name) {
		return parameters.get(name);
	}

	public final static int getPacketConfig(String name) {
		Integer v = packetConfigs.get(name);
		if (v == null) {
			return 0;
		}
		return v;
	}

	public final static List<String> getMQList(String name) {
		List<String> list = queueList.get(name);
		if (list == null) {
			return MQ_LIST_NULL;
		}
		return list;
	}
}
