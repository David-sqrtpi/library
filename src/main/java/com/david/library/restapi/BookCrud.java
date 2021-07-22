package com.david.library.restapi;

import com.david.library.entity.Book;
import com.david.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("books")
public class BookCrud {
    @Autowired
    private BookService bookService;

    @PostMapping
    public void create(@RequestBody Book book) {
        bookService.create(book);
    }

    @GetMapping("/{id}")
    public Book read(@PathVariable Long id) {
        return bookService.read(id);
    }

    @PutMapping
    public void update(@RequestBody Book book) {
        bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
