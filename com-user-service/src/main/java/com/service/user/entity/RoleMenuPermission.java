package com.service.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 系统菜单功能权限
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Setter
@Getter
@Entity
@Table(name = RoleMenuPermission.TABLE_NAME)
public class RoleMenuPermission implements Serializable {

    private static final long	serialVersionUID	= -5477582001754524659L;

    public static final String	TABLE_NAME			= "t_sys_role_menu_permission";

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer				id;

    /**
     * 角色菜单Id
     */
    @Column(name = "role_menu_id")
    private Integer				roleMenuId;

    /**
     * 权限编码
     */
    @Column(name = "permission")
    private String				permission;

}

