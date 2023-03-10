package com.example.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.example.rest.entity.Book;
import com.example.rest.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;



@WebMvcTest(BookController.class)
public class BookControllerTest {
    @MockBean
    BookService bookService;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testfindAll() throws Exception {
        Book book = new Book(1L, "Re", LocalDate.now());
        List<Book> books = Collections.singletonList(book);

        Mockito.when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/books/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Re")));
    }
}