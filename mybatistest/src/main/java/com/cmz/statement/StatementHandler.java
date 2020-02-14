package com.cmz.statement;

import com.cmz.binding.MapperMethod;
import com.cmz.configuration.Configuration;
import com.cmz.resultset.ResultSetHandler;
import com.cmz.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: chenmingzhe
 * @Date: 2020/2/14 15:03
 */
public class StatementHandler {

    private Configuration configuration;

    private ResultSetHandler resultSetHandler;

    public StatementHandler(Configuration configuration) {
        this.configuration = configuration;
        this.resultSetHandler = new ResultSetHandler();
    }

    /**
     * 最终查询方法
     * @param method
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> T query(MapperMethod method, Object parameter) {
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement(String.format(method.getSql(), Integer.valueOf(String
                            .valueOf(parameter))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            assert preparedStatement != null;
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 通过反射封装返回的属性值
        return resultSetHandler.handler(preparedStatement,method);
    }
}
