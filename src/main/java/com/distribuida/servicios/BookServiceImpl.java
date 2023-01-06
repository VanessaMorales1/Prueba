package com.distribuida.servicios;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import com.distribuida.rest.BookMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Handle;

import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class BookServiceImpl implements BookService{
    @Inject
    private BookService bookService;
    private List<Book> books = new ArrayList<>();
    @Inject
    private DbConfig dbConfig;


   private Book book;

    @Override
    public Book findById(int id) {
        Handle h = dbConfig.jdbi().open();
        Book book = h.select("SELECT * FROM books where id=?", id).map(new BookMapper()).one();
        return book;

    }

    public List<Book> findAll() {
        Handle h = dbConfig.jdbi().open();
        books=h.createQuery("SELECT * FROM books").mapToBean(Book.class).list();
        return books;
    }



   /* @Override
    public void insert(Book book) {

    }*/

    @Override
    public Book insert(Book book) {
        String sql = "INSERT INTO \"books\" ( \"isbn\",\"title\",\"author\",\"price\") VALUES (:isbn,:title,:author, :price)" ;
        try (Handle handle = dbConfig.test2()) {
            handle.createUpdate(sql)
                    .bindBean(book)
                    .execute();
        }
        return book;

    }

    @Override
    public void update(Integer id, Book book) {
        Handle jdbi = dbConfig.test2();
        jdbi.createUpdate("UPDATE books SET isbn=:isbn, title=:title, author=:author, price=:price where id=:id").bind("id",id).bind("isbn",book.getIsbn()).bind("title",book.getTitle()).bind("author",book.getAuthor()).bind("price",book.getPrice());
    }

    @Override
    public void delete(Integer id) {
            Book del = new Book();
            Handle jdbi = dbConfig.test2();
            del=jdbi.createQuery("DELETE FROM books WHERE id=?;").bind(0,id).mapToBean(Book.class).findOnly();

        }
    }






