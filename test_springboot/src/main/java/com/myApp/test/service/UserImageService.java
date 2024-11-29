package com.myApp.test.service;

import com.myApp.test.model.UserImage;
import com.myApp.test.model.User;
import com.myApp.test.repository.UserImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class UserImageService {
    @Autowired
    private UserImageRepository userImageRepository;

    public UserImage saveUserImage(User user, MultipartFile imageFile) throws IOException {
        UserImage existingUserImage = userImageRepository.findByUser(user);
        if (existingUserImage != null) {
            existingUserImage.setImage(imageFile.getBytes());
            return userImageRepository.save(existingUserImage);
        } else {
            UserImage newUserImage = new UserImage();
            newUserImage.setUser(user);
            newUserImage.setImage(imageFile.getBytes());
            return userImageRepository.save(newUserImage);
        }
    }

    public UserImage getUserImage(User user) {
        return userImageRepository.findByUser(user);
    }
}