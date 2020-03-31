package com.debug.demo.controller;

import com.debug.demo.entity.User;
import com.debug.demo.model.req.AddUserParam;
import com.debug.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Lyb
 * @since 2020-01-20
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json")
    public Mono<Void> add(@Valid @RequestBody AddUserParam param) {
        User user = new User();
        BeanUtils.copyProperties(param, user);
        return userService.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Mono<User> add(@PathVariable Long id) {
        return userService.query(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public Flux<User> list() {
        return userService.list();
    }
}
