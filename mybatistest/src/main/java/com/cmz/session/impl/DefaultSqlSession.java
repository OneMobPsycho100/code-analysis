package com.cmz.session.impl;

import com.cmz.binding.MapperMethod;
import com.cmz.binding.MapperProxy;
import com.cmz.configuration.Configuration;
import com.cmz.executor.Executor;
import com.cmz.session.SqlSession;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * @Author: chenmingzhe
 * @Date: 2020/2/13 22:21
 */
@Slf4j
@Getter
@Setter
public class DefaultSqlSession implements SqlSession {


    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(MapperMethod mapperMethod, Object object) {
        return (T) executor.query(mapperMethod, object);
    }

    public <T> T getMapper(Class<T> type) {
       return (T) Proxy.newProxyInstance(type.getClassLoader()
               ,new Class[]{type},new MapperProxy<>(this,type));
    }
}
