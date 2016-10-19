package com.lotus.core.sys;

import java.util.Locale;

/**
 * locale 线程变量
 * @author wyy
 */
public class LocaleContext {
    private static final ThreadLocal<Locale> localeContextHolder = new ThreadLocal<Locale>();

    public static void setContext(Locale locale) {
        localeContextHolder.set(locale);
    }

    public static Locale getContext() {
        return localeContextHolder.get();
    }

    public static void setContextChinese() {
        localeContextHolder.set(Locale.SIMPLIFIED_CHINESE);
    }

    public static void setContextEnglish() {
        localeContextHolder.set(Locale.US);
    }

    public static boolean isChinese() {
        return localeContextHolder.get() == null || Locale.SIMPLIFIED_CHINESE.equals(localeContextHolder.get());
    }

    public static boolean isEnglish() {
        return Locale.US.equals(localeContextHolder.get());
    }

    public static void reset() {
        localeContextHolder.remove();
    }
}
