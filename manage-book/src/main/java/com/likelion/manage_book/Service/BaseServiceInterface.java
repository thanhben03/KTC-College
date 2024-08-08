package com.likelion.manage_book.Service;

import java.util.List;
import java.util.Optional;

public interface BaseServiceInterface<T> {
    T create(T t);
    T update(int id, T t);
    void delete(T t);

    void changePassword(String oldPassword, String newPassword);

    Iterable<T> findAll();
    Optional<T> findById(int id);
}