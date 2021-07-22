package com.david.library;

import com.david.library.entity.Book;
import com.david.library.entity.Reserve;
import com.david.library.entity.User;
import com.david.library.enums.TipoUsuario;
import com.david.library.repository.BookRepository;
import com.david.library.repository.ReserveRepository;
import com.david.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class LibraryApplication {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReserveRepository reserveRepository;

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @PostConstruct
    public void initialData() {
        List<Book> books = Stream.of(
                new Book(0, new ArrayList<>(), "Un libro", "Un autor", "Educativo", 10, 1,  1, 0),
                new Book(0, new ArrayList<>(), "Otro libro", "Otro autor", "Ficción", 20, 3, 2, 1),
                new Book(0, new ArrayList<>(), "Cuento", "Jaime", "Infantil", 30, 5, 3, 2),
                new Book(0, new ArrayList<>(), "Novela", "Escritor famoso", "Novela", 40, 7, 4, 3)
        ).collect(Collectors.toList());

        Book book = new Book(0, new ArrayList<>(), "Mr. Trance", "Steman", "Varios", 50, 9, 5, 4);

        books.add(book);

        List<User> users = Stream.of(
                new User(0, new ArrayList<>(), "admin@admin.com", "admin", "password", TipoUsuario.ADMINSTRADOR),
                new User(0, new ArrayList<>(), "email@email.com", "Name", "1234", TipoUsuario.CLIENTE),
                new User(0, new ArrayList<>(), "carl@carl.com", "Carl", "1234", TipoUsuario.CLIENTE),
                new User(0, new ArrayList<>(), "aly@aly.com", "Aly", "1234", TipoUsuario.CLIENTE)
        ).collect(Collectors.toList());

        User user = new User(0, new ArrayList<>(), "hola@hola.com", "hola", "1234", TipoUsuario.CLIENTE);

        users.add(user);

        bookRepository.saveAll(books);
        userRepository.saveAll(users);

        Reserve reserve = new Reserve(0, book, user, LocalDateTime.now(), 1, 0);

        reserveRepository.save(reserve);
    }
}
