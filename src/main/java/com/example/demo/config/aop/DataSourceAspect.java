package com.example.demo.config.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Before("@annotation(com.example.demo.config.aop.WriteToReaderDB)")
    public void setReaderDataSource() {
        DataSourceContextHolder.setDataSourceKey("reader");
    }

    @After("@annotation(com.example.demo.config.aop.WriteToReaderDB)")
    public void clearDataSource() {
        DataSourceContextHolder.clearDataSourceKey();
    }
}
