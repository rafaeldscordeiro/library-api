package com.rafaelprojetos.libraryapi.service.impl;

import com.rafaelprojetos.libraryapi.model.entity.Book;
import com.rafaelprojetos.libraryapi.model.repository.BookRepository;

public class BookServiceImpl implements com.rafaelprojetos.libraryapi.model.repository.BookRepository {


    private BookRepository repository;

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
