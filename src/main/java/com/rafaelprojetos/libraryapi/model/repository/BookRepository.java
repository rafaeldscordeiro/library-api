package com.rafaelprojetos.libraryapi.model.repository;

import com.rafaelprojetos.libraryapi.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long> {

}
