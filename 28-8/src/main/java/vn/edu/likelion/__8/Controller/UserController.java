package vn.edu.likelion.__8.Controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.__8.Entity.UserEntity;
import vn.edu.likelion.__8.Service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity userEntity) throws Exception {
        UserEntity user = userService.create(userEntity);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @GetMapping
    public ResponseEntity<?> findAll() {
        Iterable<UserEntity> userEntities = userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(userEntities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable int id) {
        UserEntity user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found !"));

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable int id,@RequestBody UserEntity userEntity)
            throws Exception {
        System.out.println(userEntity.getUsername());
        UserEntity user = userService.update(id, userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws ResourceNotFoundException {
        userService.remove(id);

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


}
