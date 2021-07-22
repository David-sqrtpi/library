package com.david.library.services;

import com.david.library.entity.User;
import com.david.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public void create(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "El correo electrónico ya está registrado");
        }
    }

    public User read(Long id) {
        User user = userRepository.findById(id).get();
        user.getReserves().clear();
        return user;
    }

    public void update(User user) {
        create(user); //It is the same code
    }

    public void delete(Long id) {
        userRepository.delete(read(id));
    }
}
