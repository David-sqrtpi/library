package com.david.library.restapi;

import com.david.library.entity.User;
import com.david.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("login")
public class LogIn {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User login(@RequestBody User intent) {
        Optional<User> user = userRepository.findByEmail(intent.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(intent.getPassword())){
            user.get().getReserves().clear();
            return user.get();
        }

        if (user.isPresent() && !user.get().getPassword().equals(intent.getPassword())){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Contraseña incorrecta");
        }

        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Email no registrado");
    }
}
