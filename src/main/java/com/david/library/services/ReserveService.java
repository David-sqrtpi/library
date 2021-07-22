package com.david.library.services;

import com.david.library.entity.Book;
import com.david.library.entity.Reserve;
import com.david.library.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private BookService bookService;

    public Reserve create(Reserve reserve) {
        Book book = bookService.read(reserve.getBook().getId());

        if(reserve.getQuantity() > book.getAvailableQuantity()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "No hay libros suficientes");
        }

        long daysOfReservation = ChronoUnit.DAYS.between(LocalDateTime.now(), reserve.getEndDate());

        reserve.setTotal(reserve.getQuantity() * book.getRate() * daysOfReservation);
        book.setReservedQuantity(book.getReservedQuantity() + reserve.getQuantity());
        book.setAvailableQuantity(book.getAvailableQuantity() - reserve.getQuantity());

        bookService.update(book);

        return reserveRepository.save(reserve);
    }

    public Reserve read(Long id) {
        Reserve reserve = reserveRepository.findById(id).get();
        reserve.getBook().getReserves().clear();
        reserve.getUser().getReserves().clear();
        return reserve;
    }

    public void update(Reserve reserve) {
        reserveRepository.save(reserve);
    }

    public void delete(Long id) {
        reserveRepository.delete(read(id));
    }

    public List<Reserve> reservationList(long userId) {
        List<Reserve> reserves = reserveRepository.findByUserId(userId);
        reserves.forEach(reservation -> {
            reservation.getBook().getReserves().clear();
            reservation.getUser().getReserves().clear();
        });

        return reserves;
    }
}
