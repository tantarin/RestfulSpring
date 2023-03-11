package com.example.rest.service;

import com.example.rest.entity.Book;
import com.example.rest.exception.BookNotFoundException;
import com.example.rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBy(String title) {
        if (title != null) {
            return bookRepository.findByTitleContaining(title);
        }
        return findAll();
    }

    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public List<Book> findByPublicationDate(LocalDate date) {
        return bookRepository.findByPublicationDate(date);
    }

    public Book findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException("No Book with ID : "+id));
        return book;
    }

    public Book add(Book book) {
       return bookRepository.save(book);
    }


    public List<Book> findBy(String title) {
        return bookRepository.findByTitleContaining(title);
    }

}
