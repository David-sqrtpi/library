package com.david.library.restapi;

import com.david.library.entity.Reserve;
import com.david.library.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("users/{userId}/reserves")
@RestController
public class UserReserveList {
    @Autowired
    private ReserveService reserveService;

    @GetMapping
    public List<Reserve> list(@PathVariable long userId) {
        return reserveService.reservationList(userId);
    }
}
