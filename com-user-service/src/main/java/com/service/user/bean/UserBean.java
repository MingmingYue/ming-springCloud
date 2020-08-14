package com.service.user.bean;

import com.service.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Setter
@Getter
public class UserBean implements Serializable {

    private static final long	serialVersionUID	= 4100476652382025202L;

    /**
     * 用户基本信息
     */
    private User user;

    /**
     * 权限标识集合
     */
    private String[]			permissions;

    /**
     * 角色集合
     */
    private String[]			roles;
}