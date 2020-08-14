package com.service.api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@Getter
@Setter
public class AuthRole implements Serializable {

    private static final long	serialVersionUID	= -213874145064828983L;

    /**
     * 角色ID
     */
    private Integer				roleId;

    /**
     * 角色名称
     */
    private String				roleName;

    /**
     * 角色编码，唯一
     */
    private String				roleCode;

    /**
     * 描述
     */
    private String				roleDesc;

    /**
     * 0-正常，1-删除
     */
    private Integer				statu;
}
