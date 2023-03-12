package com.example.rest.controller;

import com.example.rest.entity.Book;
import com.example.rest.exception.BookNotFoundException;
import com.example.rest.repository.BookRepository;
import com.example.rest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value="/all")
    List<Book> getAll(@RequestParam(required = false) String title){
        return bookService.findAllBy(title);
    }

    @GetMapping(value="/{id}")
    ResponseEntity<Book> getById(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/find")
    public List<Book> getByLocalDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return bookService.findByPublicationDate(date);
    }

    @PostMapping("/add")
    public Book add(@RequestBody Book book) {
        return bookService.add(book);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.delete(id);
    }

//    @PatchMapping("/employees/{id}/{firstName}")
//    public ResponseEntity<Employee> updateEmployeePartially(@PathVariable Long id, @PathVariable String firstName) {
//        try {
//            Employee employee = employeeRepository.findById(id).get();
//            employee.setFirstName(firstName);
//            return new ResponseEntity<Employee>(employeeRepository.save(employee), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
