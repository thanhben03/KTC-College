package vn.edu.likelion.assign2.Service.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.assign2.Entity.UserEntity;
import vn.edu.likelion.assign2.Repository.User.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public UserEntity create(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(int id, UserEntity userEntity) {
        Optional<UserEntity> findUserData = userRepository.findById(id);
        UserEntity _userData = null;
        if (findUserData.isPresent()) {
            _userData = findUserData.get();
            _userData.setUsername(userEntity.getUsername());
            _userData.setPassword(userEntity.getPassword());
            _userData.setRoleId(userEntity.getRoleId());
        }
        return userRepository.save(_userData);
    }

    @Override
    public void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity login(String username, String password) {
        UserEntity user = findByUsername(username);

        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }


}
