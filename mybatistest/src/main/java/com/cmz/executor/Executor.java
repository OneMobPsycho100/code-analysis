package com.cmz.executor;

import com.cmz.binding.MapperMethod;

import java.util.List;

/**执行器
 * @Author: chenmingzhe
 * @Date: 2020/2/13 22:18
 */
public interface Executor {

    <T> T query(MapperMethod mapperMethod, Object object);
}
