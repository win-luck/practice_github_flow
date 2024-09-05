package com.example.demo.config.aop;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setDataSourceKey(String key) {
        log.info("Switch DataSource to {}", key);
        CONTEXT.set(key);
    }

    public static String getDataSourceKey() {
        return CONTEXT.get();
    }

    public static void clearDataSourceKey() {
        CONTEXT.remove();
    }
}
