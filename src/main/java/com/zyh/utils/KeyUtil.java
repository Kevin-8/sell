package com.zyh.utils;

import java.util.Date;

/**
 * @author zhangyanghui
 * @Title KeyUtil
 * @Description
 * @date 2018/7/20 0:46
 */
public class KeyUtil {

    private final static Integer DEFAULT_LENGTH = 2;
    private final static Integer RANDOM_LENGTH_FOUR = 4;
    private final static Integer RANDOM_LENGTH_SIX = 6;
    private final static Integer RANDOM_LENGTH_EIGTH = 8;

    public static synchronized String getUniqueKey(int random) {
        String dateStr = DateFormatUtil.parseDateToSimpleMsString(new Date());
        String length = "";
        if (0 == random) {
            length = getRandomString(DEFAULT_LENGTH);
        } else {
            length = getRandomString(random);
        }

        dateStr = TextUtil.joinString(dateStr, length);
        return dateStr;
    }

    /**
     * 获取指定位数的随机数
     *
     * @param length 随机数长度
     * @return
     */
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = (int) ((Math.random() * 9 + 1));
            sb.append(number);
        }
        return sb.toString();
    }

    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }

}
