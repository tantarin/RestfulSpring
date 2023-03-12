package com.example.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.example.rest.entity.Book;
import com.example.rest.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(BookController.class)
public class BookControllerTest {
    @MockBean
    BookService bookService;
    @Autowired
    MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testfindAll() throws Exception {
        Book book = new Book(1L, "Re", LocalDate.now());
        List<Book> books = Collections.singletonList(book);

        when(bookService.findAll()).thenReturn(books);

        mockMvc.perform(get("/books/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Re")));
    }

    @Test
    public void createBook() throws Exception {
        Book book = new Book(1L, "Re", LocalDate.now());

        when(bookService.add(any())).thenReturn(book);

        mockMvc.perform(post("/books/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Re")));
    }

//    @Test
//    public void getEmployeeByIdAPI() throws Exception
//    {
//        mvc.perform( MockMvcRequestBuilders
//                        .get("/employees/{id}", 1)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(1));
//    }

//    @Test
//    public void testPostExample() throws Exception {
//        Student student = new Student();
//        student.setId(1);
//        student.setName("Arun");
//        Mockito.when(studentService.saveStudent(ArgumentMatchers.any())).thenReturn(student);
//        String json = mapper.writeValueAsString(student);
//        mockMvc.perform(post("/postMapping").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
//                        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
//                .andExpect(jsonPath("$.name", Matchers.equalTo("Arun")));
//    }
//    @Test
//    public void testPutExample() throws Exception {
//        Student student = new Student();
//        student.setId(2);
//        student.setName("John");
//        Mockito.when(studentService.updateStudent(ArgumentMatchers.any())).thenReturn(student);
//        String json = mapper.writeValueAsString(student);
//        mockMvc.perform(put("/putMapping").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
//                        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
//                .andExpect(jsonPath("$.name", Matchers.equalTo("John")));
//    }
    @Test
    public void givenAnyId_whenDeleteBook_thenReturn200() throws Exception {
        willDoNothing().given(bookService).delete(any());

        mockMvc.perform(delete("/books/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(print());
    }
}