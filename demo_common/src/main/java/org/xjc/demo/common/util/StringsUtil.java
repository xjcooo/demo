package org.xjc.demo.common.util;

import com.google.common.base.Strings;

/**
 * 字符串工具类
 *
 * Created by xjc on 2019-2-26.
 */
public final class StringsUtil {

    public static boolean isEmpty(String str) {
        return Strings.isNullOrEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !Strings.isNullOrEmpty(str);
    }
}
