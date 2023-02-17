package com.example.my_store_spring.repository;

import com.example.my_store_spring.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

    Optional<Users> findUserInfoByName(String name);
    boolean existsUserInfoByName(String name);
}
