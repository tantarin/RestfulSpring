package com.example.rest;

import com.example.rest.beans.Person;
import com.example.rest.controller.BookController;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
public class RestApplicationTests {
	private static final String NAME = "John Smith";
	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void contextLoads()  {}
	@Test
	public void givenSingletonScope_whenSetName_thenEqualNames() {
		Person personSingletonA = (Person) applicationContext.getBean("personSingleton");
		Person personSingletonB = (Person) applicationContext.getBean("personSingleton");

		personSingletonA.setName(NAME);
		assertEquals(NAME, personSingletonB.getName());
	}
}