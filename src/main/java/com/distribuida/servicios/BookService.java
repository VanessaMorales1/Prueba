package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;

public interface BookService {
    Book findById(int id);
    List<Book> findAll();
    Book insert (Book book);
    void update (Integer id, Book book);
    void delete (Integer id);
}
