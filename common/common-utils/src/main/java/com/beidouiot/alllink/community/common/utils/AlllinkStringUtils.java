package com.beidouiot.alllink.community.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class AlllinkStringUtils extends StringUtils {
	
	public static final String UNDERLINE = "_";
	
    /**
     * 获取银行返回的值
     * 
     * @param sBuf
     *            报文
     * @param sName
     *            名称
     * @return
     */
    public static String getParm(String sBuf, String sName) {
        return getParm(sBuf, sName, "&");
    }

    public static String getParm(String sBuf, String sName, String sTag) {
        String tagName = sName + "=";
        if (sBuf.indexOf(tagName) < 0)
            return null;
        String strlist[] = sBuf.split(sTag);
        for (int i = 0; i < strlist.length; i++) {
            if (strlist[i].indexOf(tagName) >= 0) {
                return strlist[i].substring(tagName.length());
            }
        }
        return null;
    }

    /**
     * 获取版本号数值
     * @param version   -- 如；1.1.2 --> 1102
     * @return
     */
    public static long getVersionNumber(String version) {
        String vs1[] = version.split("\\.");
        if (vs1.length == 1) {
            String s1 = vs1[0];
            int i1 = Integer.parseInt(s1);
            return i1 * 10000000;
        }

        if (vs1.length == 2) {
            String s1 = vs1[0];
            int i1 = Integer.parseInt(s1);
            String s2 = vs1[1];
            int i2 = Integer.parseInt(s2);

            return i1 * 10000000 + i2 * 100000;
        }

        if (vs1.length == 3) {
            String s1 = vs1[0];
            int i1 = Integer.parseInt(s1);
            String s2 = vs1[1];
            int i2 = Integer.parseInt(s2);
            String s3 = vs1[2];
            int i3 = Integer.parseInt(s3);
            return i1 * 10000000 + i2 * 100000 + i3 * 10;
        }

        String s1 = vs1[0];
        int i1 = Integer.parseInt(s1);
        String s2 = vs1[1];
        int i2 = Integer.parseInt(s2);
        String s3 = vs1[2];
        int i3 = Integer.parseInt(s3);
        String s4 = vs1[3];
        int i4 = Integer.parseInt(s4);
        return i1 * 10000000 + i2 * 100000 + i3 * 10 + i4;
    }

    /**
     * 省略邮箱号码
     * @param email
     * @return  flotage@163.com --> fl****ge@163.com
     */
    public static String splitEmail(String email) {
        if (isNull(email))
            return null;

        String ss[] = email.split("@");
        if (ss.length != 2)
            return null;

        String userName = ss[0];
        String host = ss[1];

        if (userName.length() == 1) {
            return userName + "****@" + host;
        } else if (userName.length() == 2) {
            String fc = userName.substring(0, 1);
            String lc = userName.substring(1);
            return fc + "****" + lc + "@" + host;
        } else if (userName.length() == 3) {
            String fc = userName.substring(0, 2);
            String lc = userName.substring(2);
            return fc + "****" + lc + "@" + host;
        } else {
            String fc = userName.substring(0, 2);
            String lc = userName.substring(userName.length() - 2);
            return fc + "****" + lc + "@" + host;
        }
    }

    /**
     * 省略电话号码
     * @param phone
     * @return 13372578395 --> 133****8395
     */
    public static String splitPhoneNo(String phone) {
        if (isNull(phone))
            return null;
        if (phone.length() < 6)
            return phone;
        String ft = phone.substring(0, 3);
        String lt = phone.substring(phone.length() - 4);

        return ft + "****" + lt;
    }

    public static boolean isNull(Object str) {
        if (null == str)
            return true;
        if (str instanceof String) {
            String value = (String) str;
            return value.trim().equals("");
        }

        return false;
    }

    /**
     * 判断字符串是否不为空，null和空字符串都视为空
     * 
     * @param str
     * @return
     */
    public static boolean isNotNull(Object str) {
        return !isNull(str);
    }

    /**
     * <p>
     * String 的工具类
     * </p>
     * 过期方法,getMd5(byte[] bytes)替代,字符串转字节数据时建议指定编码(s.getBytes(charset))，否则会获取操作系统编码,导致不兼容.
     */
    public static String getMd5(String s) {
        if (null == s)
            return null;

        return getMd5(s.getBytes());
    }

    /**
     * 获取星期
     * @param dt
     * @return
     * <br>-----------------------------------------------------<br>
     * @author	 wwwroot
     * @create 	 2015-5-17 下午10:28:18  
     * @note
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 去掉右边空格
     * 
     * @param s
     * @return <br>
     *         -----------------------------------------------------<br>
     * @author wwwroot
     * @create 2014-8-15 下午02:26:18
     * @note
     */
    public static String rightTrim(String s) {
        if (s == null || s.trim().length() == 0)
            return null;
        if (s.trim().length() == s.length())
            return s;
        if (!s.startsWith(" ")) {
            return s.trim();
        } else {
            return s.substring(0, s.indexOf(s.trim().substring(0, 1)) + s.trim().length());
        }
    }

    /**
     * 去掉左边空格
     * 
     * @param s
     * @return <br>
     *         -----------------------------------------------------<br>
     * @author wwwroot
     * @create 2014-8-15 下午02:26:26
     * @note
     */
    public static String leftTrim(String s) {
        if (s == null || s.trim().length() == 0)
            return null;
        if (s.trim().length() == s.length())
            return s;
        if (!s.startsWith(" ")) {
            return s;
        } else {
            return s.substring(s.indexOf(s.trim().substring(0, 1)));
        }
    }

    public static String getMd5(byte[] bytes) {

        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
                             'E', 'F' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(bytes);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取全局唯一编码
     * 
     * @return
     */
    public static String getUuid() {
        // String zs = getRand(50) + SERVER_UNIQUE +
        // Thread.currentThread().getName();
        // zs = zs + Long.toString(System.currentTimeMillis());
        // return getMd5(Identities.uuid());
        return Identities.getUuid();
    }

    /**
     * 得到一个n位的随机数 第一位不能为0
     * 
     * @param n
     *            位数
     * @return
     */
    public static String getRand(int n) {
        Random rnd = new Random();
        String pass = "0";
        int x = rnd.nextInt(10);
        /** 过滤第一位为0 */
        while (x == 0) {
            x = rnd.nextInt(10);
        }
        pass = String.valueOf(x);
        for (int i = 1; i < n; i++) {
            pass = pass + String.valueOf(rnd.nextInt(10));
        }
        return pass;
    }

    /**
     * 填充字符
     * 
     * @param source
     *            源字符串
     * @param fillChar
     *            填充字符
     * @param len
     *            填充到的长度
     * @return 填充后的字符串
     */
    public static String fillLeft(String source, char fillChar, long len) {
        StringBuffer ret = new StringBuffer();
        if (null == source)
            ret.append("");
        if (source.length() > len) {
            ret.append(source);
            return ret.toString();
        }
        long slen = source.length();
        while (ret.toString().length() + slen < len) {
            ret.append(fillChar);
        }
        ret.append(source);
        return ret.toString();
    }

    /**
     * 重复字符
     * 
     * @param len
     *            -- 重复次数
     * @param repStr
     *            -- 被重复的字符
     * @return
     */
    public static String fillStr(int len, String repStr) {
        if (len <= 0) {
            return repStr;
        }
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < len; i++) {
            ret.append(repStr);
        }
        return ret.toString();
    }

    /**
     * 右边填充字符
     * 
     * @param source
     *            源字符串
     * @param fillChar
     *            填充字符
     * @param len
     *            填充到的长度
     * @return 填充后的字符串
     */
    public static String fillRight(String source, char fillChar, int len) {
        StringBuffer ret = new StringBuffer();
        if (null == source)
            ret.append("");
        if (source.length() > len) {
            ret.append(source);
        } else {
            ret.append(source);
            while (ret.toString().length() < len) {
                ret.append(fillChar);
            }
        }
        return ret.toString();
    }

    /**
     * escape 编码
     * 
     * @param src
     * @return
     */
    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * unescape 解码
     * 
     * @param src
     * @return
     */
    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 判断字符是否超过长度
     * 
     * @param str
     * @param num
     * @return 超过规定字符返回true
     */
    public static boolean isOutLen(String str, int num) {
        if (isNull(str)) {
            return num < 0;
        }
        return str.getBytes().length > num;
    }

    /**
     * 转换字符城HTML字符
     * 
     * @param str
     * @return <br>
     *         -----------------------------------------------------<br>
     * @author wwwroot
     * @create 2013-7-25 上午09:25:26
     * @note
     */
    public static String replaceStrToHTML(String str) {
        if (isNull(str))
            return str;

        return str.replaceAll("\r\n", "<br>").replaceAll("\r", "<br>").replaceAll("\n", "<br>")
            .replaceAll(" ", "　");
    }

    /**
     * 转成
     * 
     * @param html
     * @return <br>
     *         -----------------------------------------------------<br>
     * @author wwwroot
     * @create 2013-7-25 上午09:28:28
     * @note
     */
    public static String replaceHtmlToStr(String html) {
        if (isNull(html))
            return null;

        return html.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ")
            .replaceAll("&quot;", "\"").replaceAll("<br>", "\r\n").replaceAll("&amp;", "&");
    }

    /**
     * 根据名称替换
     * 
     * @param str
     * @param name
     * @param value
     * @return <br>
     * @note "会员[{MBNAME }]代码无效，你不应该传[{MBNAME}]的","MBNAME","001001"
     *       =>会员[001001]代码无效，你不应该传[001001]的
     */
    public static String replaceTagName(String str, String tagName, String value) {
        if (isNull(value))
            return str;

        Pattern p = Pattern.compile("\\{(.*?)\\}");
        Matcher m = p.matcher(str);
        while (m.find()) {
            String name = m.group(1);
            if (null == name)
                continue;
            if (name.trim().equals(tagName)) {
                if (value.indexOf("$") >= 0) {
                    value = value.replaceAll("\\$", "\\\\\\$");
                }
                str = str.replaceFirst("\\{" + name + "\\}", value);
            }
        }
        return str;
    }

    /**
     * 替换格式化字符
     * 
     * @param str
     * @param values
     * @return <br>
     *         -----------------------------------------------------<br>
     * @author wwwroot
     * @create 2013-7-12 下午03:09:38
     * @note
     */
	public static String replace(String str, Object... values) {
        if (null == values)
            return str;

        Pattern p = Pattern.compile("\\{(.*?)\\}");
        Matcher m = p.matcher(str);
        int i = 0;
        while (m.find()) {
            if (i >= values.length)
                break;
            String name = m.group(1);
            String value = null == values[i] ? "null" : values[i].toString();
            if (value.indexOf("$") >= 0) {
                value = value.replaceAll("\\$", "\\\\\\$");
            }
            i++;

            str = str.replaceFirst("\\{" + name + "\\}", value);
        }
        return str;

    }

    /**
     * 查找介于开始标记和结束标记之间的内容(任意字符包括回车、换行、制表符等)
     * 
     * @param start
     *            开始标记(开始标签)
     * @param end
     *            结束标记(结束标签)
     * @param content
     *            被查找的内容
     * @param 正则下标
     *            ，取值从0开始，0包含开始和结束标记；1不包含开始和结束标记
     * @return 匹配的字符串集合
     */
    public static List<String> findText(String start, String end, String content, int i) {
        // 多个连续空白\\s*
        StringBuilder builder = new StringBuilder();
        builder.append(start);
        builder.append("([\\s\\S]*?)");
        builder.append(end);
        // 任意字符(包括回车换行)\\s\\S]*?
        return find(builder.toString(), i, content);
    }

    /**
     * 在文本内容中查找指定内容
     * 
     * @param regex
     *            正则表达式或文本
     * @param i
     *            正则下标,从1开始：第几个正则为要查找的内容。没有正则以纯文本来匹配查找的话
     * @param content
     *            文本内容
     * @return 被查找到的文本集合
     */
    public static List<String> find(String regex, int i, String content) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            try {
                list.add((m.group(i)));
            } catch (java.lang.IndexOutOfBoundsException e) {
                // 下标小于0或与大于实际的正则表达式个数
                list.add((m.group()));
            }
        }
        return list;
    }

    /**
     * 替换空格(\s)、回车(\n)、换行(\r)、制表符(\t)
     * 
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        if (isNull(str)) { // null或空格字符串时,去空格返回
            return str;
        }
        Matcher m = Pattern.compile("\\s*|\t|\r|\n").matcher(str);
        return m.replaceAll("");
    }

    /**
     * 本地格式化
     * 
     * @param amt
     *            数字
     * @return
     */
    public static String format(double amt) {
        Locale locale = new Locale("zh", "CN");
        return format(amt, locale);
    }

    /**
     * 格式化
     * 
     * @param amt
     * @param locale
     * @return
     */
    public static String format(double amt, Locale locale) {
        NumberFormat currFmt = NumberFormat.getCurrencyInstance(locale);
        return currFmt.format(amt);
    }

    /**
     * 获取生日
     * @param birthday
     * @return  10岁以上是数字，以下是年月
     */
    public static String getArge(Date birthday) {
        return getArge(birthday, new Date());
    }

    public static String getArge(Date birthday, Date startDate) {
        if (null == birthday || birthday.getTime() > System.currentTimeMillis())
            return null;

        Calendar bCalendar = Calendar.getInstance();
        Calendar nCalendar = Calendar.getInstance();
        bCalendar.setTime(birthday);
        nCalendar.setTime(startDate);

        // 获取生日年份
        int bYear = bCalendar.get(Calendar.YEAR);
        int nYear = nCalendar.get(Calendar.YEAR);

        if (nYear - bYear > 10) // 如果超过10岁，则直接返回
            return (nYear - bYear) + "";

        if (nYear - bYear > 0) { // 10岁内超过1岁
            int arge = nYear - bYear;
            int bMonth = bCalendar.get(Calendar.MONTH) + 1;
            int nMonth = nCalendar.get(Calendar.MONTH) + 1;

            String strArge = "";
            if (bMonth > nMonth) { // 生日的月份还没有到
                arge--;
                if (arge == 0) // 不满一年
                    return getOneYearArge(birthday, startDate);

                strArge = arge + "Y" + (12 - bMonth + nMonth) + "M";
            } else {
                strArge = arge + "Y" + (nMonth - bMonth) + "M";
            }

            return strArge;
        }

        return getOneYearArge(birthday, startDate);
    }

    /**
     * 计算不满一年的年龄
     * @param birthday
     * @return
     * @note
     */
    private static String getOneYearArge(Date birthday, Date startDate) {
        Calendar bCalendar = Calendar.getInstance();
        Calendar nCalendar = Calendar.getInstance();
        bCalendar.setTime(birthday);
        nCalendar.setTime(startDate);

        int bMonth = bCalendar.get(Calendar.MONTH) + 1; // 12.5
        int nMonth = nCalendar.get(Calendar.MONTH) + 1; // 03.6
        int arge = 0; // 月龄
        int date = 0; // 日龄
        if (bMonth > nMonth) { // 生日月大于当前月（说明跨年）
            arge = 12 - (bMonth - nMonth);
        } else {// 生日月小于当前月（说明当年）
            arge = nMonth - bMonth;
        }

        bCalendar.add(Calendar.MONTH, arge);
        // 得到当月几号
        int bDAY = bCalendar.get(Calendar.DAY_OF_MONTH);
        int nDAY = nCalendar.get(Calendar.DAY_OF_MONTH);
        if (bDAY > nDAY) { // 生日当天还没有到
            arge--;
            bCalendar.add(Calendar.MONTH, -1); // 退回一个月
            // 计算退回一个月后的日期和当前日期相差几天
            Date d1 = bCalendar.getTime();
            Date d2 = nCalendar.getTime();

            long l = d2.getTime() - d1.getTime();
            date = ((Long) (l / (24 * 60 * 60 * 1000))).intValue();

        } else { // 生日当天已经过了
            date = nDAY - bDAY;
        }

        if (arge == 0) {
            if (date == 0)
                date++;

            return date + "D";
        }

        return arge + "M" + date + "D";
    }

    /**
     * 获取月龄，如1Y2M3D，则返回14.1月
     * @param birthday
     * @return
     * @note
     */
    public static double getMonthArge(Date birthday) {
        return getMonthArge(birthday, new Date());
    }

    public static double getMonthArge(Date birthday, Date startDate) {
        Calendar bCalendar = Calendar.getInstance();
        Calendar nCalendar = Calendar.getInstance();
        bCalendar.setTime(birthday);
        nCalendar.setTime(startDate);
        if (bCalendar.getTimeInMillis() > nCalendar.getTimeInMillis())
            return -1d;

        int bYear = bCalendar.get(Calendar.YEAR);
        int nYear = nCalendar.get(Calendar.YEAR);

        if (nYear - bYear > 10) // 超过10岁了
            return (nYear - bYear) * 12;

        Integer arge = nYear - bYear;
        int bMonth = bCalendar.get(Calendar.MONTH) + 1;
        int nMonth = nCalendar.get(Calendar.MONTH) + 1;

        int month = 0;
        if (bMonth > nMonth) { // 生日的月份还没有到
            arge--;
            month = 12 - (bMonth - nMonth);
        } else {
            month = nMonth - bMonth;
        }

        // 计算天数
        Integer bDAY = bCalendar.get(Calendar.DAY_OF_MONTH);
        Integer nDAY = nCalendar.get(Calendar.DAY_OF_MONTH);

        Integer date = 0;
        if (bDAY > nDAY) { // 生日还没有到
            month--;
            bCalendar.set(Calendar.YEAR, nYear);
            bCalendar.set(Calendar.MONTH, nMonth - 2); // 3.11
            // 计算退回一个月后的日期和当前日期相差几天
            Date d1 = bCalendar.getTime();
            Date d2 = nCalendar.getTime();

            long l = d2.getTime() - d1.getTime();
            date = ((Long) (l / (24 * 60 * 60 * 1000))).intValue();
        } else {
            date = nDAY - bDAY;
        }

        // 计算整年的月份
        Integer m = arge * 12 + month;
        Double d = DoubleUtil.precise(date.doubleValue(), 30d, "/");
        return DoubleUtil.precise(m.doubleValue(), d, "+");
    }

    /**
     * 取得中文的首字母
     * @param strChinese
     * @param bUpCase
     * @return
     */
    public static String getPYIndexStr(String strChinese, boolean bUpCase) {
        try {
            StringBuffer buffer = new StringBuffer();

            byte b[] = strChinese.getBytes("GBK");//把中文转化成byte数组

            for (int i = 0; i < b.length; i++) {

                if ((b[i] & 255) > 128) {

                    int char1 = b[i++] & 255;

                    char1 <<= 8;//左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方

                    int chart = char1 + (b[i] & 255);

                    buffer.append(getPYIndexChar((char) chart, bUpCase));

                    continue;

                }

                char c = (char) b[i];

                if (!Character.isJavaIdentifierPart(c))//确定指定字符是否可以是 Java 标识符中首字符以外的部分。

                    c = 'A';

                buffer.append(c);

            }

            return buffer.toString();

        } catch (Exception e) {

            System.out
                .println((new StringBuilder()).append("\u53D6\u4E2D\u6587\u62FC\u97F3\u6709\u9519")
                    .append(e.getMessage()).toString());

        }

        return null;
    }

    /**
    
     * 得到首字母
    
     * @param strChinese
    
     * @param bUpCase
    
     * @return
    
     */

    private static char getPYIndexChar(char strChinese, boolean bUpCase) {

        int charGBK = strChinese;

        char result;

        if (charGBK >= 45217 && charGBK <= 45252)

            result = 'A';

        else

        if (charGBK >= 45253 && charGBK <= 45760)

            result = 'B';

        else

        if (charGBK >= 45761 && charGBK <= 46317)

            result = 'C';

        else

        if (charGBK >= 46318 && charGBK <= 46825)

            result = 'D';

        else

        if (charGBK >= 46826 && charGBK <= 47009)

            result = 'E';

        else

        if (charGBK >= 47010 && charGBK <= 47296)

            result = 'F';

        else

        if (charGBK >= 47297 && charGBK <= 47613)

            result = 'G';

        else

        if (charGBK >= 47614 && charGBK <= 48118)

            result = 'H';

        else

        if (charGBK >= 48119 && charGBK <= 49061)

            result = 'J';

        else

        if (charGBK >= 49062 && charGBK <= 49323)

            result = 'K';

        else

        if (charGBK >= 49324 && charGBK <= 49895)

            result = 'L';

        else

        if (charGBK >= 49896 && charGBK <= 50370)

            result = 'M';

        else

        if (charGBK >= 50371 && charGBK <= 50613)

            result = 'N';

        else

        if (charGBK >= 50614 && charGBK <= 50621)

            result = 'O';

        else

        if (charGBK >= 50622 && charGBK <= 50905)

            result = 'P';

        else

        if (charGBK >= 50906 && charGBK <= 51386)

            result = 'Q';

        else

        if (charGBK >= 51387 && charGBK <= 51445)

            result = 'R';

        else

        if (charGBK >= 51446 && charGBK <= 52217)

            result = 'S';

        else

        if (charGBK >= 52218 && charGBK <= 52697)

            result = 'T';

        else

        if (charGBK >= 52698 && charGBK <= 52979)

            result = 'W';

        else

        if (charGBK >= 52980 && charGBK <= 53688)

            result = 'X';

        else

        if (charGBK >= 53689 && charGBK <= 54480)

            result = 'Y';

        else

        if (charGBK >= 54481 && charGBK <= 55289)

            result = 'Z';

        else

            result = (char) (65 + (new Random()).nextInt(25));

        if (!bUpCase)

            result = Character.toLowerCase(result);

        return result;

    }

    /**
     * 将字符串截成指定长度
     * @param wztext00
     * @param i
     * @return
     * @note
     */
    public static String cut(String value, int len) {
        if (isNull(value))
            return value;
        if (len <= 0)
            return value;

        try {
            byte[] buf = value.getBytes("UTF-8");
            if (buf.length <= len)
                return value;

            return new String(value.getBytes("UTF-8"), 0, len - 1, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return value;
        }
    }

    /**
     * 解码
     * 
     * @param str
     * @return
     */
    public static String decode(String str) {
        return decode(str, "UTF-8");
    }

    /**
     * 解码
     * 
     * @param str
     * @param encode
     * @return
     */
    public static String decode(String str, String encode) {
        try {
            str = java.net.URLDecoder.decode(str, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * @disc 对字符串重新编码
     * @param src
     * @return
     */
    public static String isoToUTF(String src) {
        String strRet = null;
        try {
            strRet = new String(src.getBytes("ISO_8859_1"), "UTF-8");
        } catch (Exception e) {

        }
        return strRet;
    }

    /**
     * @disc 对字符串UTF-8转GBK重新编码
     * @param src
     * @return
     */
    public static String utfToGbk(String src) {
        String strRet = null;
        try {
            strRet = new String(src.getBytes("ISO_8859_1"), "UTF-8");
        } catch (Exception e) {

        }
        return strRet;
    }

    /**
     * 检测字符是否为空,为空的时候返回提示
     * @param str
     * @param msg 为空的时候返回提示
     * @return
     */
    public static String isBlankToMsg(String str, String msg) {
        String returnstr = "";
        if (StringUtils.isBlank(str)) {
            returnstr = msg + ",";
        }
        return returnstr;
    }

    /**
     * 填充左边字符
     * @param source 源字符串
     * @param fillChar 填充字符
     * @param len 填充到的长度
     * @return 填充后的字符串
     */
    public static String fillLeft(String source, char fillChar, int len) {
        StringBuffer ret = new StringBuffer();
        if (null == source)
            ret.append("");
        if (source.length() > len) {
            ret.append(source);
        } else {
            int slen = source.length();
            while (ret.toString().length() + slen < len) {
                ret.append(fillChar);
            }
            ret.append(source);
        }
        return ret.toString();
    }

    /**
     * 填充右边字符
     * @param source 源字符串
     * @param fillChar 填充字符
     * @param len 填充到的长度
     * @return 填充后的字符串
     */
    public static String filRight(String source, char fillChar, int len) {
        StringBuffer ret = new StringBuffer();
        if (null == source)
            ret.append("");
        if (source.length() > len) {
            ret.append(source);
        } else {
            ret.append(source);
            while (ret.toString().length() < len) {
                ret.append(fillChar);
            }
        }
        return ret.toString();
    }

    public static String filterStr(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.replaceAll("'", "''");
        return str;
    }

    /**
     * 获取一个字符在一个字符串里出现的次数
     * @param tagetStr
     * @param str
     * @return 
     */
    public static int indexOfAll(String tagetStr, String str) {
        int i = 0;
        if (null != tagetStr) {
            i = tagetStr.length() - tagetStr.replace(str, "").length();
        }
        return i;
    }

    /**
     * 只要有一个参数为空就返回true
     * @param args
     * @return true false
     */
    public static Boolean isBlankOne(Object... args) {
        Boolean flag = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                if (isBlank((String) args[i])) {
                    flag = true;
                }
            } else {
                if (null == args[i]) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * 检测是否为数字
     * @param obj
     * @return true-是 false-不是
     */
    public static boolean isNumber(String obj) {
        if (null == obj) {
            obj = "";
        }
        return obj.matches("-?\\d+\\.?\\d*");
    }

    /**
     * 字母Z使用了两个标签，这里有27个值
     * 
     * i, u, v都不做声母, 跟随前面的字母
     */
    private static char[] chartable  = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃',
                                         '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔',
                                         '压', '匝', '座' };

    private static char[] alphatable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                                         'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                                         'Y', 'Z' };
    private static int[]  table      = new int[27];
    static {// 初始化
        for (int i = 0; i < 27; ++i) {
            table[i] = gbValue(chartable[i]);
        }
    }

    private static boolean match(int i, int gb) {
        if (gb < table[i])
            return false;
        int j = i + 1;
        // 字母Z使用了两个标签
        while (j < 26 && (table[j] == table[i]))
            ++j;
        if (j == 26)
            return gb <= table[j];
        else
            return gb < table[j];
    }

    // 取出汉字的编码
    private static int gbValue(char ch) {
        String str = new String();
        str += ch;
        try {
            byte[] bytes = str.getBytes("GB2312");
            if (bytes.length < 2) {
                return 0;
            }
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * 主函数,输入字符得到他的声母,
     * 英文字母返回对应的大写字母
     * 其他非简体汉字返回 '0'
     * @param ch
     * @return
     */
    private static char Char2Alpha(char ch) {
        if (ch >= 'a' && ch <= 'z')
            return (char) (ch - 'a' + 'A');
        if (ch >= 'A' && ch <= 'Z')
            return ch;
        int gb = gbValue(ch);
        if (gb < table[0]) {
            return '0';
        }
        int i;
        for (i = 0; i < 26; ++i) {
            if (match(i, gb))
                break;
        }
        if (i >= 26)
            return '0';
        else
            return alphatable[i];
    }

    /**
     * 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
     * @param SourceStr
     * @return
     */
    public static String String2Alpha(String SourceStr) {
        String Result = "";
        int StrLength = SourceStr.length();
        int i;
        try {
            for (i = 0; i < StrLength; i++) {
                Result += Char2Alpha(SourceStr.charAt(i));
            }
        } catch (Exception e) {
            Result = "";
        }
        return Result;
    }

    /**
     * 数据加密（DES对称算法）
     * @param data 需要加密的数据
     * @param key 密钥（Base64格式）
     * @return
     * @throws Exception
     */
    public static byte[] DESEncrypt(byte[] data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, getDESKey(key));
        return cipher.doFinal(data);
    }

    /**
     * 数据解密（DES对称算法）
     * @param data 需要解密的数据
     * @param key   密钥
     * @return
     * @throws Exception
     * <br>-----------------------------------------------------<br>
     * @author   wwwroot
     * @create   2013-1-10 下午06:15:54  
     * @note
     */
    public static byte[] DESDecrypt(byte[] data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, getDESKey(key));
        return cipher.doFinal(data);
    }

    /**
     * 还原密钥
     * @param strkey 密钥（Base64格式）
     * @return
     * @throws Exception
     */
    private static Key getDESKey(String strkey) throws Exception {
        DESKeySpec keySpec = new DESKeySpec(strkey.getBytes());//设置密钥参数
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//获得密钥工厂
        SecretKey key = keyFactory.generateSecret(keySpec);//得到密钥对象

        return key;
    }

    /**  
     * BASE64解密  
     *   
     * @param key  
     * @return  
     * @throws Exception  
     */
	public static byte[] decryptBASE64(String data) throws IOException {
        return (new BASE64Decoder()).decodeBuffer(data);
    }

    /**  
     * BASE64加密  
     *   
     * @param key  
     * @return  
     * @throws Exception  
     */
	public static String encryptBASE64(byte[] data) {
        String base64 = (new BASE64Encoder()).encodeBuffer(data);
        return base64;
    }

    
    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    
    public static String camelToUnderline(String str) {
        if(StringUtils.isEmpty(str)) {
        	return "";
        }
        
        String regexStr = "[A-Z]";
        
        Matcher matcher = Pattern.compile(regexStr).matcher(str);
        
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String g = matcher.group();
            matcher.appendReplacement(sb, UNDERLINE + g.toLowerCase());
        }
        matcher.appendTail(sb);
        if (sb.charAt(0) == '_') {
            sb.delete(0, 1);
        }
        return sb.toString();
    }
}
