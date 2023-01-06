package com.distribuida.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;
    private String isbn;
    private String title;
    private String author;
    private double price;



}

