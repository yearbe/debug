package com.debug.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Lyb
 * @since 2020-01-19
 */
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    private Integer age;
    private Integer sex;
    private String idCard;
    private String address;
}
