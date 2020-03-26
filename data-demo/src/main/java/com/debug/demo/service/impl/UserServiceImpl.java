package com.debug.demo.service.impl;

import com.debug.demo.entity.User;
import com.debug.demo.mapper.UserMapper;
import com.debug.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLException;

/**
 * @author Lyb
 * @since 2020-01-19
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Mono<Void> save(User user) {
        return Mono.justOrEmpty(userMapper.insert(user))
                .switchIfEmpty(Mono.error(new SQLException("用户信息保存失败")))
                .then();
    }

    @Override
    public Mono<User> query(Long id) {
        return Mono.justOrEmpty(userMapper.selectById(id))
                .switchIfEmpty(Mono.error(new SQLException("用户不存在")));
    }

    @Override
    public Flux<User> list() {
        return Flux.fromIterable(userMapper.selectList(null));
    }
}
