package com.service.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Setter
@Getter
@Entity
@Table(name = RoleMenu.TABLE_NAME)
public class RoleMenu implements Serializable {

    private static final long	serialVersionUID	= 8409879328945905867L;

    public static final String	TABLE_NAME			= "t_sys_role_menu";

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer				id;

    /**
     * 角色Id
     */
    @Column(name = "role_id")
    private Integer				roleId;

    /**
     * 菜单id
     */
    @Column(name = "menu_id")
    private Integer				menuId;
}
