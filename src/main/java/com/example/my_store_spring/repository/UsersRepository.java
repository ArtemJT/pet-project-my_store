package com.example.my_store_spring.repository;

import com.example.my_store_spring.enums.UserRole;
import com.example.my_store_spring.model.Users;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

    Optional<Users> findUsersByName(String name);

    boolean existsUserInfoByName(String name);

    Optional<List<Users>> findAllByRole(UserRole role);
}
