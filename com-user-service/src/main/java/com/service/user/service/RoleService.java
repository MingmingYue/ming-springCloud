package com.service.user.service;

import com.core.commons.page.PageBean;
import com.core.commons.page.PageParams;
import com.service.user.bean.RoleDeptBean;
import com.service.user.entity.Role;

import java.util.List;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
public interface RoleService {

    List<Role> getRoleList();

    PageBean<RoleDeptBean> findAll(PageParams pageParams, Role role);

    boolean delById(Integer roleId);

    Role findRoleByCode(String roleCode);
}
