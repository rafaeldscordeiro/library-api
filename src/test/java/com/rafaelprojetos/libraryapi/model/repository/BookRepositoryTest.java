package com.rafaelprojetos.libraryapi.model.repository;


import com.rafaelprojetos.libraryapi.model.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    TestEntityManager entitymanager;

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um livro na base com o isbn informado")
    public void returnTrueWhenIsbnExists(){
        //cenario
        String isbn = "123";

        Book book = createNewBook(isbn);;
        entitymanager.persist(book);

        //execucao
        boolean exists = repository.existsByIsbn(isbn);

        //verificacao
        assertThat(exists).isTrue();

    }

    private Book createNewBook(String isbn) {
        return Book.builder().title("Aventuras").author("Fulano").isbn(isbn).build();
    }

    @Test
    @DisplayName("Deve obter um livro por id")
    public void findByIdTest(){
        //cenario
        Book book = createNewBook("123");
        entitymanager.persist(book);

        //execucao
        Optional<Book> foundBook = repository.findById(book.getId());

        //
        assertThat(foundBook.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void saveBookTest(){

        Book book = createNewBook("123");

        Book savedBook = repository.save(book);

        assertThat(savedBook.getId()).isNotNull();

    }

    @Test
    @DisplayName("Deve deletar um livro")
    public void deleteBookTest(){

        Book book = createNewBook("123");
        entitymanager.persist(book);

        Book foundBook = entitymanager.find(Book.class, book.getId());

        repository.delete(foundBook);

        Book deletedBook = entitymanager.find(Book.class, book.getId());
        assertThat(deletedBook).isNull();

    }


}
