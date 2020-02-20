package com.debug.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.debug.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Lyb
 * @since 2020-01-19
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
