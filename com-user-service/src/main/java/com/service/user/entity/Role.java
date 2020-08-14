package com.service.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Setter
@Getter
@Entity
@Table(name = Role.TABLE_NAME)
public class Role implements Serializable {

    private static final long	serialVersionUID	= -5794622871292709802L;

    public static final String	TABLE_NAME			= "t_sys_role";

    /**
     * 角色ID
     */
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Integer				roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String				roleName;

    /**
     * 角色编码，唯一
     */
    @Column(name = "role_code")
    private String				roleCode;

    /**
     * 描述
     */
    @Column(name = "role_desc")
    private String				roleDesc;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date				updateTime;

    /**
     * 0-正常，1-删除
     */
    @Column(name = "statu")
    private Integer				statu				= 0;

    @Transient
    private Integer				deptId;
}
