package vn.edu.likelion.__8.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.likelion.__8.Entity.UserEntity;
import vn.edu.likelion.__8.Repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity create(UserEntity user) throws Exception{
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());

        return userRepository.save(userEntity);
    }

    public UserEntity save(UserEntity playerEntity) {
        return userRepository.save(playerEntity);
    }

    public void remove(int id) throws ResourceNotFoundException {
        userRepository.deleteById(id);
    }

    public Iterable<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(int id) {
        return userRepository.findById(id);
    }

    public UserEntity update(int id, UserEntity userEntity) throws Exception {
        UserEntity userEntity1 = userRepository.findById(id).get();

        userEntity1.setUsername(userEntity.getUsername());
        userEntity1.setPassword(userEntity.getPassword());


        return userRepository.save(userEntity1);
    }


}
