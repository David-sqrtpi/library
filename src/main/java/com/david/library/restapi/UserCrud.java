package com.david.library.restapi;

import com.david.library.entity.User;
import com.david.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("users")
public class UserCrud {
    @Autowired
    private UserService userService;

    @PostMapping
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @GetMapping("/{id}")
    public User read(@PathVariable Long id) {
        return userService.read(id);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
