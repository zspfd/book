package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "程序设计竞赛", "刘汝佳", new BigDecimal(42.50), 1100000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(3);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(2, "数据结构与算法", "严蔚敏", new BigDecimal(38.5), 1232, 12322, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice(){
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }
    @Test
    public void queryForPageItemsByPrice(){
        for(Book book : bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE, 10, 50))
            System.out.println(book);
    }

}
