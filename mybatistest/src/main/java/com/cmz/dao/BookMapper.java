package com.cmz.dao;

import com.cmz.entity.Book;

/**
 * @Author: chenmingzhe
 * @Date: 2020/2/14 11:13
 */
public interface BookMapper {

    /**
     *根据id查询book
     * @param id id
     * @return Book
     */
    Book selectBookById(Integer id);
}
