package com.myApp.test.controller;

import com.myApp.test.model.UserImage;
import com.myApp.test.model.User;
import com.myApp.test.service.UserService;
import com.myApp.test.service.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/user-image")
public class UserImageController {
    @Autowired
    private UserImageService userImageService;
    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadUserImage(@RequestParam("userId") int userId, @RequestParam("image") MultipartFile imageFile) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }
            UserImage savedImage = userImageService.saveUserImage(user, imageFile);
            return ResponseEntity.ok(savedImage);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error processing the image");
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUserImage(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserImage userImage = userImageService.getUserImage(user);

        if (userImage != null) {
            byte[] decodedImage = Base64.getDecoder().decode(userImage.getUserImageBase64());
            userImage.setImage(decodedImage);
            return ResponseEntity.ok(userImage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}