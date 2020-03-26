package com.debug.demo.model.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Lyb
 * @since 2020-01-20
 */
@Data
public class AddUserParam {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    private Integer age;
    private Integer sex;
    private String idCard;
    private String address;
}
