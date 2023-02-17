package com.example.my_store_spring.services;

import com.example.my_store_spring.dto.UsersDto;
import com.example.my_store_spring.model.Users;
import com.example.my_store_spring.repository.UsersRepository;
import com.example.my_store_spring.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Artem Kovalov on 05.02.2023
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;

    public void createUser(UsersDto usersDto) throws EntityExistsException {
        Users users = mapper.toEntity(usersDto, Users.class);
        users.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        usersRepository.save(users);
        usersDto.setUserId(users.getUserId());
    }

    public List<UsersDto> findAllUsers() {
        Stream<Users> stream = StreamSupport.stream(usersRepository.findAll().spliterator(), false);
        List<Users> userInfoList = stream.collect(Collectors.toList());
        return mapper.collectToDto(userInfoList, UsersDto.class);
    }

    public boolean isUserNameExists(String name) {
        return usersRepository.existsUserInfoByName(name);
    }
}
