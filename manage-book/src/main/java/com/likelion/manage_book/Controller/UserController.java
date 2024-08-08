package com.likelion.manage_book.Controller;

import com.likelion.manage_book.Entity.UserEntity;
import com.likelion.manage_book.Jwt.JwtUtil;
import com.likelion.manage_book.Request.AuthRequest;
import com.likelion.manage_book.Service.User.UserInfoService;
import com.likelion.manage_book.Service.User.UserService;
import com.likelion.manage_book.Util.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtService;

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok("ahihi");
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserEntity userEntity) {
        try {
            UserEntity user = userInfoService.addUser(userEntity);
            return ResponseHandler
                    .generateResponse(HttpStatus.OK, false, "Register Success !", userEntity);
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(HttpStatus.OK, true, e.getMessage(), "");
        }
    }

//    @PostMapping("/login")
//    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//        Authentication authentication = null;
//        UserEntity userEntity = new UserEntity();
//        try {
//            authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            authRequest.getUsername(),
//                            authRequest.getPassword()));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(authRequest.getUsername());
//        } else {
//            throw new UsernameNotFoundException("invalid user request ");
//        }
//    }

    @PostMapping("/login")
    public  ResponseEntity<?> loginUser(@RequestBody AuthRequest userDTO) {
        UserEntity userEntity = userInfoService.authenticateUser(userDTO.getUsername(), userDTO.getPassword());
        if(userEntity != null) {
            String token = jwtService.generateToken(userEntity.getUsername());
            return ResponseHandler
                    .generateResponse(HttpStatus.OK, false, "Login success !", token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
