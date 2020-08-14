package com.service.user.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Setter
@Getter
public class UserForm {

    private String	username;

    private String	password;

    private String	newPassword;

    private Integer	status	= 0;

    private Integer	roleId;

    private Integer	userId;

    private Integer	deptId;

    private String	mobile;
}