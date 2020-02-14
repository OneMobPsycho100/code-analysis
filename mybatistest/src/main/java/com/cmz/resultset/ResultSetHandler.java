package com.cmz.resultset;

import com.cmz.binding.MapperMethod;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: chenmingzhe
 * @Date: 2020/2/14 15:27
 */
public class ResultSetHandler {

    public <T> T handler(PreparedStatement pstmt, MapperMethod mapperMethod) {
        // 创建返回对象
        Object resultObj = new DefaultObjectFactory().create(mapperMethod.getType());
        try {
            ResultSet rs = pstmt.getResultSet();
            if (rs.next()) {
                int i = 0;
                for (Field field : resultObj.getClass().getDeclaredFields()) {
                    try {
                        // 赋值
                        setVaule(resultObj,field,rs,i);
                    } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (T) resultObj;
    }

    private void setVaule(Object resultObj, Field field, ResultSet rs, int i) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        Method setMethod = resultObj.getClass()
                .getMethod("set" + upperCap(field.getName(),field.getType()),field.getType());
        setMethod.invoke(resultObj,getResult(field,rs));
    }

    /**
     * 匹配返回值类型
     * @param field
     * @param rs
     * @return
     * @throws SQLException
     */
    private Object getResult(Field field, ResultSet rs) throws SQLException {
        Class<?> type = field.getType();
        if (Integer.class == type) {
            return rs.getInt(field.getName());
        }
        if (String.class == type) {
            return rs.getString(field.getName());
        } else {
            throw new IllegalStateException("无匹配类型");
        }
    }

    /**
     * 将属性值第一个字母转为大写
     * “set”+"Name"
     * @param name
     * @param type
     * @return
     */
    private String upperCap(String name, Class<?> type) {
        String first = name.substring(0,1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }
}
