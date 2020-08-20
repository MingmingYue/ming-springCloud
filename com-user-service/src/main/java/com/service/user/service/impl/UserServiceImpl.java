package com.service.user.service.impl;

import com.core.commons.utils.StringHelper;
import com.service.api.model.AuthRole;
import com.service.api.model.AuthUser;
import com.service.common.cache.AdminCacheKey;
import com.service.common.jpa.JPAFactoryImpl;
import com.service.user.bean.UserBean;
import com.service.user.entity.QRole;
import com.service.user.entity.QUserRole;
import com.service.user.entity.Role;
import com.service.user.entity.User;
import com.service.user.repository.UserRepository;
import com.service.user.repository.UserRoleRepository;
import com.service.user.service.PermissionService;
import com.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Component
@CacheConfig(cacheNames = AdminCacheKey.USER_INFO)
public class UserServiceImpl extends JPAFactoryImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    @Cacheable(key = "'user_name_' + #username")
    public AuthUser findUserByUsername(String username) {
        User user = findUserByUsername(username, true);

        return buildAuthUserByUser(user);
    }

    @Override
    public User findUserByUsername(String username, boolean isLoadRole) {

        if (StringHelper.isBlank(username)) return null;

        User user = userRepository.findUserByUsername(username.trim());
        if (null == user) return null;

        if (isLoadRole) user.setRoleList(findRoleListByUserId(user.getUserId()));

        return user;
    }

    @Override
    @Cacheable(key = "'user_mobile_' + #mobile")
    public AuthUser findUserByMobile(String mobile) {
        User user = userRepository.findUserByMobile(mobile.trim());
        if (null == user) return null;

        user.setRoleList(findRoleListByUserId(user.getUserId()));

        return buildAuthUserByUser(user);
    }

    public List<Role> findRoleListByUserId(Integer userId) {
        if (null == userId) return null;

        // load role
        QUserRole qUserRole = QUserRole.userRole;
        QRole qRole = QRole.role;
        List<Role> rList = this.queryFactory.select(qRole).from(qUserRole, qRole).where(
                qUserRole.userId.eq(userId)).where(qUserRole.roleId.eq(qRole.roleId)).fetch();

        return rList;
    }

    @Override
    public UserBean findUserInfo(AuthUser user) {
        User dbUser = findUserByUsername(user.getUsername(), false);

        UserBean userInfo = new UserBean();
        // 过滤关键信息
        dbUser.setPassword("");
        dbUser.setCreateTime(null);
        dbUser.setUpdateTime(null);
        userInfo.setUser(dbUser);

        // 设置角色列表
        List<AuthRole> roleList = user.getRoleList();
        List<String> roleCodes = new ArrayList<>();

        roleList.stream().forEach(r -> {
            roleCodes.add(r.getRoleCode());
        });

        String[] roles = roleCodes.toArray(new String[roleCodes.size()]);

        userInfo.setRoles(roles);

        // 设置权限列表（menu.permission）
        Set<String> permissions = new HashSet<String>();
        for (String roleCode : roles) {
            permissions.addAll(permissionService.findMenuPermissions(roleCode));
        }

        userInfo.setPermissions(permissions.toArray(new String[permissions.size()]));

        return userInfo;
    }

    private AuthUser buildAuthUserByUser(User user) {
        if (null == user) return null;

        AuthUser authUser = new AuthUser();
        authUser.setPicUrl(user.getPicUrl());
        authUser.setStatu(user.getStatus());
        authUser.setPassword(user.getPassword());
        authUser.setUserId(user.getUserId());
        authUser.setUsername(user.getUsername());

        if (null == user.getRoleList() || user.getRoleList().size() == 0) return authUser;
        List<AuthRole> rList = new ArrayList<AuthRole>();
        for (Role r : user.getRoleList()) {
            AuthRole aRole = new AuthRole();
            aRole.setStatu(r.getStatu());
            aRole.setRoleCode(r.getRoleCode());
            aRole.setRoleDesc(r.getRoleDesc());
            aRole.setRoleId(r.getRoleId());
            aRole.setRoleName(r.getRoleName());
            rList.add(aRole);
        }
        authUser.setRoleList(rList);

        return authUser;
    }
}
