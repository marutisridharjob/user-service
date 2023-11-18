package com.example.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable("userId") String userId) {

        User userOne = new User(userId, "User Name" + userId, "XXXXX" + userId);

        Posts posts = restTemplate.getForObject("http://post-service/post/1", Posts.class);  // "http://localhost:8081/post/1"

        userOne.setPosts(posts);

        Notifications notifications = restTemplate.getForObject("http://notification-service/notification/1", Notifications.class); // "http://localhost:8080/notification/1"

        userOne.setNotifications(notifications);

        return userOne;
    }
}
