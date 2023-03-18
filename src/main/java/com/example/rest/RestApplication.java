package com.example.rest;

import com.example.rest.beans.Message;
import com.example.rest.entity.Book;
import com.example.rest.repository.BookRepository;
import com.example.rest.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}
}
