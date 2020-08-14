package com.service.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 方法上标注需要的权限 {@link Functional }
 *
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Setter
@Getter
@Entity
@Table(name = Module.TABLE_NAME)
public class Module implements Serializable {

    private static final long serialVersionUID = 2657654159968503284L;

    public static final String TABLE_NAME = "t_sys_module";

    /**
     * 部门id
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    /**
     * 编码 {@link Functional }
     */
    @Column(name = "code")
    private String code;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

}
