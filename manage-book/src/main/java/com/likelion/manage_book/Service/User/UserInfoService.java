package com.likelion.manage_book.Service.User;

import com.likelion.manage_book.Entity.UserEntity;
import com.likelion.manage_book.Model.UserInfoDetails;
import com.likelion.manage_book.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userDetail = repository.findByUsername(username);

        System.err.println(userDetail.get().getUsername());
        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found "));
    }

    public UserEntity addUser(UserEntity userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        return repository.save(userInfo);

    }

    public UserEntity authenticateUser(String username, String password) {
        Optional<UserEntity> user = repository.findByUsername(username);
        if (user != null && encoder.matches(password, user.get().getPassword())) {
            return user.get();
        }
        return null;
    }


}