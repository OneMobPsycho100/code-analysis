package com.cmz.executor.impl;

import com.cmz.binding.MapperMethod;
import com.cmz.configuration.Configuration;
import com.cmz.executor.Executor;
import com.cmz.statement.StatementHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/2/13 22:30
 */
@Slf4j
@Getter
@Setter
public class SimpleExecutor implements Executor {

    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T query(MapperMethod mapperMethod, Object object) {
        // 构造StatementHandler
        StatementHandler statementHandler = new StatementHandler(configuration);
        return (T) statementHandler.query(mapperMethod,object);
    }

}
