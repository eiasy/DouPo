package com.hygame.dpcq.tools;

public class TransCodeUtil {
    /**
     * 将ISO8859_1编码的字符串转化为GB2312编码的字符串，主要用来处理中文显示乱码的问题
     * 
     * @param ISO8859_1str
     *            通过ISO8859_1编码的字符串
     * @return 通过GB2312编码的字符串
     */
    public static String GB2312FromISO8859_1(String ISO8859_1str) {
        if (ISO8859_1str == null) {
            return "";
        } else {
            try {
                return new String(ISO8859_1str.getBytes("ISO8859_1"), "GB2312");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    
    /**
     * 将ISO8859_1编码的字符串转化为GB2312编码的字符串，主要用来处理中文显示乱码的问题
     * 
     * @param ISO8859_1str
     *            通过ISO8859_1编码的字符串
     * @return 通过GB2312编码的字符串
     */
    public String GBString(String ISO8859_1str) {
        if (ISO8859_1str == null) {
            return "";
        } else {
            try {
                return new String(ISO8859_1str.getBytes("ISO8859_1"), "GB2312");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    
    /**
     * 将UTF编码的字符串转化为GB2312编码的字符串，主要用来处理中文显示乱码的问题
     * 
     * @param UTF
     *            通过UTF编码的字符串
     * @return 通过GB2312编码的字符串
     */
    public static String GB2312FromUTF(String UTF) {
        if (UTF == null) {
            return "";
        } else {
            try {
                return new String(UTF.getBytes("UTF-8"), "GB2312");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 将GB2312编码的字符串转化为UTF-8编码的字符串，主要用来处理中文显示乱码的问题
     * 
     * @param GB2312
     *            通过GB2312编码的字符串
     * @return 通过UTF-8编码的字符串
     */
    public static String UTFFromGB2312(String GB2312) {
        if (GB2312 == null) {
            return "";
        } else {
            try {
                return new String(GB2312.getBytes("GB2312"), "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static String GBKFromISO8859_1(String ISO8859_1) {
        if (ISO8859_1 == null) {
            return "";
        } else {
            try {
                return new String(ISO8859_1.getBytes("ISO8859_1"), "GBK");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static String GBKFromUTF(String UTF) {
        if (UTF == null) {
            return "";
        } else {
            try {
                return new String(UTF.getBytes("UTF-8"), "GBK");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 将ISO8859_1编码的字符串转化为UTF-8编码的字符串，主要用来处理中文显示乱码的问题
     * 
     * @param ISO8859_1str
     *            通过ISO8859_1编码的字符串
     * @return 通过UTF-8编码的字符串
     */
    public static String UTFFromISO8859_1(String ISO8859_1str) {
        return ISO8859_1str;
    }

    public static String UTFFromGBK(String GBK) {
        if (GBK == null) {
            return "";
        } else {
            try {
                return new String(GBK.getBytes("GBK"), "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 将UTF-8编码的字符串转化为ISO8859_1编码的字符串，主要用来处理中文显示乱码的问题
     * 
     * @param UTF
     *            通过UTF编码的字符串
     * @return 通过ISO8859_1编码的字符串
     */
    public static String ISO8859_1FromUTF(String UTFstr) {
        if (UTFstr == null) {
            return "";
        } else {
            try {
                return new String(UTFstr.getBytes("UTF-8"), "ISO8859_1");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 将GB2312编码的字符串转化为ISO8859_1编码的字符串
     * @param GBstr
     *            GB2312编码的字符串
     * @return ISO8859_1编码的字符串
     */
    public static String ISO8859_1String(String GBstr) {
        if (GBstr == null) {
            return "";
        } else {
            try {
                return new String(GBstr.getBytes("GB2312"), "ISO8859_1");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 将GB2312编码的字符串转化为ISO8859_1编码的字符串
     * 
     * @param GBstr
     *            GB2312编码的字符串
     * @return ISO8859_1编码的字符串
     */
    public String ISO8859_1FromGB2312(String GBstr) {
        if (GBstr == null) {
            return "";
        } else {
            try {
                return new String(GBstr.getBytes("GB2312"), "ISO8859_1");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static String ISO8859_1FromGBK(String GBK) {
        if (GBK == null) {
            return "";
        } else {
            try {
                return new String(GBK.getBytes("GBK"), "ISO8859_1");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
