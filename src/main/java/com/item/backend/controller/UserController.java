package com.book.backend.controller;


import com.book.backend.dto.ResponseDto;
import com.book.backend.dto.user.SignInDto;
import com.book.backend.dto.user.SignInResponseDto;
import com.book.backend.dto.user.SignupDto;
import com.book.backend.exceptions.AuthenticationFailException;
import com.book.backend.exceptions.CustomException;
import com.book.backend.model.User;
import com.book.backend.repository.UserRepository;
import com.book.backend.service.AuthenticationService;
import com.book.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> findAllUser() throws AuthenticationFailException {
        //authenticationService.authenticate(token); @RequestParam("token") String token
        return userRepository.findAll();
    }

    @PostMapping("/signup")
    public ResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    //TODO token should be updated
    @PostMapping("/signIn")
    public SignInResponseDto Signup(@RequestBody SignInDto signInDto) throws CustomException {
        return userService.signIn(signInDto);
    }

//    @PostMapping("/updateUser")
//    public ResponseDto updateUser(@RequestParam("token") String token, @RequestBody UserUpdateDto userUpdateDto) {
//        authenticationService.authenticate(token);
//        return userService.updateUser(token, userUpdateDto);
//    }


//    @PostMapping("/createUser")
//    public ResponseDto updateUser(@RequestParam("token") String token, @RequestBody UserCreateDto userCreateDto)
//            throws CustomException, AuthenticationFailException {
//        authenticationService.authenticate(token);
//        return userService.createUser(token, userCreateDto);
//    }
}
