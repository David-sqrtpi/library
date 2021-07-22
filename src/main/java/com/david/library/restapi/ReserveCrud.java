package com.david.library.restapi;

import com.david.library.entity.Reserve;
import com.david.library.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("reserves")
@RestController
public class ReserveCrud{
    @Autowired
    private ReserveService reserveService;

    @PostMapping
    public Reserve create(@RequestBody Reserve reserve) {
        return reserveService.create(reserve);
    }

    @GetMapping("/{reserveId}")
    public Reserve read(@PathVariable Long reserveId) {
        return reserveService.read(reserveId);
    }

    @PutMapping
    public void update(@RequestBody Reserve reserve) {
        reserveService.update(reserve);
    }

    @DeleteMapping("/{reserveId}")
    public void delete(@PathVariable Long reserveId) {
        reserveService.delete(reserveId);
    }
}
