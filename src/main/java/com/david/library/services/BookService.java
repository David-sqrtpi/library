package com.david.library.services;

import com.david.library.entity.Book;
import com.david.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public void create(Book book) {
        if (book.getStock() < 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Stock negativo invalido");
        }

        book.setAvailableQuantity(book.getStock());

        bookRepository.save(book);
    }

    public Book read(Long id) {
        Book book = bookRepository.findById(id).get();
        book.getReserves().clear();
        return book;
    }

    public void update(Book book) {
        if (book.getStock() < book.getReservedQuantity()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Hay libros reservados");
        }

        if (book.getStock() < 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Valor inválido");
        }

        book.setAvailableQuantity(book.getStock() - book.getReservedQuantity());

        bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.delete(read(id));
    }

    public List<Book> list() {
        List<Book> books = bookRepository.findAll();
        books.forEach(book -> book.getReserves().clear());
        return books;
    }
}
