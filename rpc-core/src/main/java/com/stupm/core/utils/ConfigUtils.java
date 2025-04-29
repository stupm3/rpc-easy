package com.stupm.core.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import io.netty.util.internal.StringUtil;

public class ConfigUtils {
    public static <T> T loadConfig(String prefix, Class<T> clazz) {
        return loadConfig(  prefix , clazz , "");
    }

    public static <T> T loadConfig(String prefix, Class<T> clazz, String environment) {
        StringBuilder stringBuilder = new StringBuilder("application");
        if(StrUtil.isNotBlank(environment)) {
            stringBuilder.append("-").append(environment);
        }
        stringBuilder.append(".properties");
        Props props = new Props(stringBuilder.toString() , "utf-8");
        return props.toBean(clazz , prefix);
    }
}

