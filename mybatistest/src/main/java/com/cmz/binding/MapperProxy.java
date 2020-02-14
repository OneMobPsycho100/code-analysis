package com.cmz.binding;

import com.cmz.session.impl.DefaultSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理Mapper
 * @Author: chenmingzhe
 * @Date: 2020/2/14 14:00
 */
public class MapperProxy<T> implements InvocationHandler {

    private final DefaultSqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(DefaultSqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 根据类+方法名获取MapperMethod
        MapperMethod mapperMethod = sqlSession.getConfiguration()
                .getMapperRegister().getKnownMappers()
                .get(method.getDeclaringClass().getName()+"."+method.getName());
        if (mapperMethod != null) {
            return sqlSession.selectOne(mapperMethod,String.valueOf(args[0]));
        }
        return null;
    }
}
