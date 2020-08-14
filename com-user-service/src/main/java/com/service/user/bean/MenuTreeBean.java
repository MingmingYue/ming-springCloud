package com.service.user.bean;

import com.service.common.tree.MenuTree;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 用于redis缓存的必须实现Serializable 接口
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Setter
@Getter
public class MenuTreeBean implements Serializable {

    private static final long	serialVersionUID	= 2707121320504244801L;

    private List<MenuTree> menuList;

    private String[]			permissions;

}
