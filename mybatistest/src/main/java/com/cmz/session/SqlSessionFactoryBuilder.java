package com.cmz.session;

import com.cmz.configuration.Configuration;

/**构造SqlSessionFactory
 * @Author: chenmingzhe
 * @Date: 2020/2/14 13:36
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Configuration configuration){
        configuration.loadConfigurations();
        return new SqlSessionFactory();
    }
}
