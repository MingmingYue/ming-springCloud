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
@Table(name = UserRole.TABLE_NAME)
public class UserRole implements Serializable {

    private static final long	serialVersionUID	= 8409879328945905867L;

    public static final String	TABLE_NAME			= "t_sys_user_role";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer				id;

    @Column(name = "user_id")
    private Integer				userId;

    @Column(name = "role_id")
    private Integer				roleId;

}