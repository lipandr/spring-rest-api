package com.example.springrestapi.service;

import com.example.springrestapi.entity.UserEntity;
import com.example.springrestapi.exceptions.UserAlreadyExistsException;
import com.example.springrestapi.exceptions.UserNotFoundException;
import com.example.springrestapi.model.User;
import com.example.springrestapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        Optional<UserEntity> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(user.get());
    }

    public Long deleteUser(Long id) {
        userRepo.deleteById(id);
        return id;
    }
}
