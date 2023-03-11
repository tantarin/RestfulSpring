package com.example.rest.service;

import com.example.rest.entity.Book;
import com.example.rest.repository.BookRepository;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService service;
    @Mock
    private BookRepository repository;
    private Book book;

    @BeforeEach
    public void setup(){
        book = Book.builder()
                .id(3L)
                .title("Test title")
                .build();
    }

    @Test
    void shouldCreateNewBook() {
        // Arrange
        when(repository.save(book)).thenReturn(book);

        // Act
        Book actual = service.add(book);

        // Assert
        assertThat(actual.getTitle()).isEqualTo("Test title");
        assertThat(actual).usingRecursiveComparison().isEqualTo(book);
    }
}
