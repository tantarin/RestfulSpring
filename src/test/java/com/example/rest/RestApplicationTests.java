package com.example.rest;

import com.example.rest.controller.BookController;
import com.example.rest.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RestApplicationTests {
	@Autowired
	private BookController bookController;
	@Autowired
	private BookService bookService;
	@Test
	public void contextLoads() throws Exception {
		Assertions.assertThat(bookController).isNot(null);
	}
}