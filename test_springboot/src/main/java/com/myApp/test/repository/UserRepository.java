package com.myApp.test.repository;

import com.myApp.test.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    boolean existsByName(String name);
    User findByNameAndPassword(String name, String password);
    User findByName(String name);
}