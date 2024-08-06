package vn.edu.likelion.assign2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.assign2.AuthAnotation.CheckLogin;
import vn.edu.likelion.assign2.Configuration.SessionConfig;
import vn.edu.likelion.assign2.Entity.UserEntity;
import vn.edu.likelion.assign2.Service.User.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    SessionConfig sessionConfig;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> request) {

        try {
            UserEntity user = userService.login(request.get("username").toString(), request.get("password").toString());
            if (user == null) {
                return null;
            }
            sessionConfig.setUserEntity(user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity user) {
        try {
            userService.create(user);
        } catch (Exception e) {
            return null;
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> findById(@PathVariable("id") int id) {
        UserEntity user = new UserEntity();

        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public UserEntity update(@PathVariable("id") int id, @RequestBody UserEntity request) {
        return userService.update(id, request);
    }

    @GetMapping
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            Optional<UserEntity> userEntity = userService.getById(id);
            userEntity.ifPresent(entity -> userService.delete(entity));
            return ResponseEntity.status(200).body("Delete success !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
