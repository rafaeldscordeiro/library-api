package com.rafaelprojetos.libraryapi.service;

import com.rafaelprojetos.libraryapi.model.entity.Book;
import com.rafaelprojetos.libraryapi.service.impl.BooKServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    BookService service;

    @BeforeEach
    public void setUP(){
        this.service = new BooKServiceImpl();

    }

    @Test
    @DisplayName("Deve Salvar Livro")
    public void saveBookTest (){

        //cenario
        Book book = Book.builder().isbn("123").author("Fulano").title("As Aventuras").build();

        //execucao
        Book savedBook = service.save(book);

        //verificacao
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getIsbn()).isEqualTo("123");
        assertThat(savedBook.getTitle()).isEqualTo("As Aventuras");
        assertThat(savedBook.getAuthor()).isEqualTo("Fulano");
    }
}