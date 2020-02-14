package com.cmz.test;

import com.cmz.configuration.Configuration;
import com.cmz.dao.BookMapper;
import com.cmz.entity.Book;
import com.cmz.session.SqlSessionFactory;
import com.cmz.session.SqlSessionFactoryBuilder;
import com.cmz.session.impl.DefaultSqlSession;

import java.io.InputStream;

/**
 * @Author: chenmingzhe
 * @Date: 2020/2/14 13:40
 */
public class MybatisTest {

    public static void main(String[] args) {
        InputStream inputStream = MybatisTest.class.getClassLoader()
                .getResourceAsStream("mybatis-config.xml");
        Configuration configuration = new Configuration();
        configuration.setInputStream(inputStream);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        DefaultSqlSession sqlSession = (DefaultSqlSession) sqlSessionFactory.openSession(configuration);
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        Book book = bookMapper.selectBookById(1);
        System.out.println(book);
    }
}
