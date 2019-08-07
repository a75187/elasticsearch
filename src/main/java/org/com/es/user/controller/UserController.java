package org.com.es.user.controller;

import org.com.es.user.model.User;
import org.com.es.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/8/6 15:29
 * @Version: 1.0
 */

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping(value = "/add")
    private String add(User user) throws IOException {
        userService.addDocument(user);
        return Boolean.TRUE.toString();
    }

    @GetMapping(value = "/del")
    private String del(User user) throws IOException {
        userService.del(user);
        return Boolean.TRUE.toString();
    }

    @GetMapping(value = "/update")
    private String update(User user) throws IOException {
        userService.update(user);
        return Boolean.TRUE.toString();
    }

    @GetMapping(value = "/search")
    public List<User> search(User user) throws IOException {
        List<User> search = userService.search(user);
        return search;
    }
}