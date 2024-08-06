package vn.edu.likelion.assign2.Service.User;

import vn.edu.likelion.assign2.Entity.UserEntity;
import vn.edu.likelion.assign2.Service.BaseServiceInterface;


public interface UserServiceInterface extends BaseServiceInterface<UserEntity> {

    void changePassword(String oldPassword, String newPassword);
    UserEntity findByUsername(String username);
}
