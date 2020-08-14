package com.service.api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@Getter
@Setter
public class AuthUser implements Serializable {

    private static final long	serialVersionUID	= 5350461830095965990L;

    /**
     * 用户名Id
     */
    private Integer				userId;

    /**
     * 用户名
     */
    private String				username;

    /**
     * 密码
     */
    private String				password;

    /**
     * 0-正常，1-删除
     */
    private Integer				statu				= 0;

    /**
     * 头像
     */
    private String				picUrl;

    /**
     * 角色列表
     */
    private List<AuthRole> roleList			= new ArrayList<>();
}