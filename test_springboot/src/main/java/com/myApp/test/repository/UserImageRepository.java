package com.myApp.test.repository;

import com.myApp.test.model.User;
import com.myApp.test.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
    UserImage findByUser(User user);
}