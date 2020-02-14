package com.cmz.binding;

import lombok.Getter;
import lombok.Setter;

/**
 * 解析mapper文件 加载sql及类型
 * @Author: chenmingzhe
 * @Date: 2020/2/14 12:33
 */
@Getter
@Setter
public class MapperMethod<T> {

    private String sql;
    private Class<T> type;

    public MapperMethod(String sql, Class<T> type) {
        this.sql = sql;
        this.type = type;
    }

    public MapperMethod() {
    }
}
