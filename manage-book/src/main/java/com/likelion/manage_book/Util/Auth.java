package com.likelion.manage_book.Util;

import com.likelion.manage_book.Entity.UserEntity;
import com.likelion.manage_book.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Auth {

    @Autowired
    UserService userService;

    UserEntity user;

    private List<UserEntity> userEntities;

    public UserEntity getUser(int userId) {
        return userEntities.stream().filter(user -> user.getId() == userId).findFirst().get();
    }

    public void addUser(UserEntity entity) {
        userEntities.add(entity);
    }

    public void login(String username, String password) {

        user = userService.findByUsernameAndPassword(username, password);
    }
}
