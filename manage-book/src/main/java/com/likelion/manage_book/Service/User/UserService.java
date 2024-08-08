package com.likelion.manage_book.Service.User;

import com.likelion.manage_book.Entity.UserEntity;
import com.likelion.manage_book.Repository.UserRepository;
import com.likelion.manage_book.Service.BaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements BaseServiceInterface<UserEntity> {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity update(int id, UserEntity userEntity) {
        return null;
    }

    @Override
    public void delete(UserEntity userEntity) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public Iterable<UserEntity> findAll() {
        return List.of();
    }


    @Override
    public Optional<UserEntity> findById(int id) {
        return Optional.empty();
    }

    public UserEntity findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }



}
