package com.myApp.test.controller;

import com.myApp.test.model.User;
import com.myApp.test.response.ResponseBody;
import com.myApp.test.response.ResponseCode;
import com.myApp.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    public com.myApp.test.response.ResponseBody registerUser(@RequestBody User user) {
        if (service.exists(user)) {
            User u = service.findByNameAndPassword(user);
            return new com.myApp.test.response.ResponseBody(u != null ? ResponseCode.SIGN_IN_SUCCESS : ResponseCode.SIGN_IN_FAILED, u != null ? u.getId() : "");
        }
        return new com.myApp.test.response.ResponseBody(service.insert(user) ? ResponseCode.SIGN_UP_SUCCESS : ResponseCode.SIGN_UP_FAILED, "");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User existingUser = service.findByName(user.getName());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户不存在");
        }
        if (service.checkPassword(existingUser, user.getPassword())) {
            return ResponseEntity.ok().body(new ResponseBody(ResponseCode.SIGN_IN_SUCCESS, existingUser));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码错误");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseBody> updateUser(@RequestBody User user) {
        ResponseBody response = new ResponseBody(service.update(user) ? ResponseCode.UPDATE_SUCCESS : ResponseCode.UPDATE_FAILED, "");
        return ResponseEntity.ok(response);
    }
}