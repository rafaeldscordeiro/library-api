package com.rafaelprojetos.libraryapi.service.impl;

import com.rafaelprojetos.libraryapi.model.entity.Book;
import com.rafaelprojetos.libraryapi.service.BookService;

public class BooKServiceImpl implements BookService {


    private BookRepository repository;

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
