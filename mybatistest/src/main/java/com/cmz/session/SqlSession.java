package com.cmz.session;

import com.cmz.binding.MapperMethod;

/**
 * @Author: chenmingzhe
 * @Date: 2020/2/13 22:17
 */
public interface SqlSession {

    /**
     * 查询单条记录
     * @param mapperMethod
     * @param object
     * @param <T>
     * @return
     */
    <T> T selectOne(MapperMethod mapperMethod, Object object);
}
