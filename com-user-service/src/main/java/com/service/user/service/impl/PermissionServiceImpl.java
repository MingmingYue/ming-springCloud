package com.service.user.service.impl;

import com.core.commons.utils.StringHelper;
import com.core.commons.utils.WebUtils;
import com.service.common.cache.AdminCacheKey;
import com.service.common.jpa.JPAFactoryImpl;
import com.service.user.entity.*;
import com.service.user.repository.RoleMenuPermissionRepository;
import com.service.user.repository.RoleMenuRepository;
import com.service.user.service.PermissionService;
import com.service.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Service(value = "permissionService")
public class PermissionServiceImpl extends JPAFactoryImpl implements PermissionService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @Autowired
    private RoleMenuPermissionRepository roleMenuPermissionRepository;

    @Override
    @Cacheable(cacheNames = AdminCacheKey.PERMISSION_INFO, key = "'permission_' + #roleId")
    public List<RoleMenuPermission> findMenuPermissionByRoleId(Integer roleId) {
        if (null == roleId) return null;
        // 查询菜单
        QRoleMenu qRoleMenu = QRoleMenu.roleMenu;
        QRoleMenuPermission qRoleMenuPermission = QRoleMenuPermission.roleMenuPermission;
        return this.queryFactory.select(qRoleMenuPermission).from(
                qRoleMenuPermission, qRoleMenu).where(qRoleMenu.roleId.eq(roleId)).where(
                qRoleMenuPermission.roleMenuId.eq(qRoleMenu.id)).fetch();
    }

    @Override
    public Set<String> findMenuPermissions(String roleCode) {
        Set<String> permissions = new HashSet<>();
        // 查询Role
        Role role = roleService.findRoleByCode(roleCode.trim());
        if (null == role) return permissions;
        // 查询菜单
        List<RoleMenuPermission> rList = this.findMenuPermissionByRoleId(role.getRoleId());
        if (null == rList || rList.size() == 0) return permissions;
        rList.forEach(r -> permissions.add(r.getPermission()));
        return permissions;
    }

    @Override
    @CacheEvict(value = {AdminCacheKey.PERMISSION_INFO, AdminCacheKey.MENU_INFO,
            AdminCacheKey.MODULE_INFO, AdminCacheKey.ROLE_INFO}, allEntries = true)
    @Transactional
    public boolean updateRoleMenuPermissions(String roleCode, String... permissions) {
        Role role = roleService.findRoleByCode(roleCode.trim());
        if (null == role) return false;

        // 菜单集合
        Map<Integer, List<String>> roleMenuIdList = new HashMap<Integer, List<String>>();
        for (String permission : permissions) {
            if (!permission.contains("|")) continue;
            String[] menuPermissions = permission.split("\\|");
            Integer menuId = WebUtils.parseStrToInteger(menuPermissions[0], null);
            if (null == menuId || StringHelper.isBlank(menuPermissions[1])) continue;
            if (!roleMenuIdList.containsKey(menuId)) {
                roleMenuIdList.put(menuId, new ArrayList<String>());
            }
            roleMenuIdList.get(menuId).add(menuPermissions[1].trim());
        }

        // 删除RoleMenu和RoleMenuPermission
        this.delRoleMenuPermission(role.getRoleId());

        if (roleMenuIdList.size() > 0) {
            roleMenuIdList.forEach((menuId, menuPermissions) -> {
                RoleMenu menuRole = new RoleMenu();
                menuRole.setMenuId(menuId);
                menuRole.setRoleId(role.getRoleId());
                menuRole = roleMenuRepository.saveAndFlush(menuRole);
                Integer menuRoleId = menuRole.getId();

                menuPermissions.forEach(p -> {
                    String permission = p.trim();
                    if (permission.contains("_")) {
                        RoleMenuPermission roleMenuPermission = new RoleMenuPermission();
                        roleMenuPermission.setRoleMenuId(menuRoleId);
                        roleMenuPermission.setPermission(permission);
                        roleMenuPermissionRepository.saveAndFlush(roleMenuPermission);
                    }
                });
            });
        }

        return true;
    }

    private boolean delRoleMenuPermission(Integer roleId) {
        QRoleMenu qRoleMenu = QRoleMenu.roleMenu;
        List<RoleMenu> roleMeunList = this.queryFactory.selectFrom(qRoleMenu).where(
                qRoleMenu.roleId.eq(roleId)).fetch();

        Set<Integer> roleMenuIdList = new HashSet<>();
        roleMeunList.forEach(r -> {
            roleMenuIdList.add(r.getId());
            roleMenuRepository.deleteById(r.getId());
        });

        Integer[] roleMenuIdArray = new Integer[roleMenuIdList.size()];
        return this.delRoleMenuPermissionByRoleMenuId(roleMenuIdList.toArray(roleMenuIdArray));
    }

    private boolean delRoleMenuPermissionByRoleMenuId(Integer... roleMenuArray) {
        QRoleMenuPermission qRoleMenuPermission = QRoleMenuPermission.roleMenuPermission;
        long num = this.queryFactory.delete(qRoleMenuPermission).where(
                qRoleMenuPermission.roleMenuId.in(roleMenuArray)).execute();
        return num > 0;
    }
}
