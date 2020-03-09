package org.xjc.demo.util;

import java.util.Locale;

/**
 * Created by xjc on 2019-2-22.
 */
public class UserUtil {

    private static final ThreadLocal<String> tlUser = new ThreadLocal<>();

    private static final ThreadLocal<Locale> tlLocale = new ThreadLocal<Locale>(){

        @Override
        protected Locale initialValue() {
            return Locale.CHINESE;
        }
    };

    public static final String KEY_LANG = "lang";

    public static final String KEY_USER = "user";

    public static void setUser(String userId) {
        tlUser.set(userId);
    }

    public static String getTlUser() {
        return tlUser.get();
    }

    public static void setLocale(String locale){
        setLocale(new Locale(locale));
    }

    private static void setLocale(Locale locale) {
        tlLocale.set(locale);
    }

    public static Locale getLocale() {
        return tlLocale.get();
    }
    public static void clearAllUserInfo() {
        tlUser.remove();
        tlLocale.remove();
    }

}
