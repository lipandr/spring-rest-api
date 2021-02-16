package com.example.springrestapi.repository;

import com.example.springrestapi.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
