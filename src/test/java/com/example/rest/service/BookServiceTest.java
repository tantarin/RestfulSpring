package com.example.rest.service;

import com.example.rest.entity.Book;
import com.example.rest.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookService service;
    @Mock
    private BookRepository repository;
    private Book book;

    @BeforeEach
    public void setup(){
        book = Book.builder()
                .id(1L)
                .title("Test title")
                .build();
    }

    @Test
    void shouldCreateNewBook() {
        given(repository.save(book)).willReturn(book);
        System.out.println(repository);
        System.out.println(service);

        Book saveBook = service.add(book);
        assertThat(saveBook.getTitle()).isEqualTo("Test title");
    }
}
