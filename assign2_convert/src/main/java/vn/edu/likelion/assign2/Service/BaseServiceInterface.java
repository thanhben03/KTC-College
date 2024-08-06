package vn.edu.likelion.assign2.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface BaseServiceInterface<T> {
    T create(T t);
    T update(int id, T t);
    void delete(T t);

    void changePassword(String oldPassword, String newPassword);

    List<T> findAll();
    Optional<T> getById(int id);
}
