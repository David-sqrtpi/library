package com.david.library.restapi;

import com.david.library.entity.Book;
import com.david.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@CrossOrigin
public class BookList {
    @Autowired
    private BookService bookService;

    @GetMapping
    public java.util.List<Book> list() {
        return bookService.list();
    }
}
