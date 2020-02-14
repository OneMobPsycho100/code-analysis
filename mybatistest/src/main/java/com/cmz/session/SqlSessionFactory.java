package com.cmz.session;

import com.cmz.configuration.Configuration;
import com.cmz.executor.impl.SimpleExecutor;
import com.cmz.session.impl.DefaultSqlSession;

/**
 * 用于构造SqlSession
 * @Author: chenmingzhe
 * @Date: 2020/2/14 13:21
 */
public class SqlSessionFactory {

    public SqlSession openSession(Configuration configuration) {
        return new DefaultSqlSession(configuration,new SimpleExecutor(configuration));
    }
}
