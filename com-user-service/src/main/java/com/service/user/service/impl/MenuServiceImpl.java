package com.service.user.service.impl;

import com.core.commons.constants.CommonConstant;
import com.core.commons.tree.TreeUtil;
import com.core.commons.utils.StringHelper;
import com.service.common.bean.AuthMenu;
import com.service.common.cache.AdminCacheKey;
import com.service.common.jpa.JPAFactoryImpl;
import com.service.common.tree.MenuTree;
import com.service.user.entity.*;
import com.service.user.repository.MenuRepository;
import com.service.user.service.MenuService;
import com.service.user.service.ModuleService;
import com.service.user.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Service(value = "menuService")
@CacheConfig(cacheNames = AdminCacheKey.MENU_INFO)
public class MenuServiceImpl extends JPAFactoryImpl implements MenuService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModuleService moduleService;

    @Override
    public Set<AuthMenu> findMenuByRole(String roleCode) {
        if (StringHelper.isBlank(roleCode)) {
            return null;
        }
        Role role = roleService.findRoleByCode(roleCode.trim());
        if (null == role) {
            return null;
        }
        List<Menu> rList = this.findMenuByRoleId(role.getRoleId());
        if (null == rList || rList.size() == 0) {
            return null;
        }
        Set<AuthMenu> mList = new HashSet<>();
        for (Menu m : rList) {
            AuthMenu authMenu = new AuthMenu();
            BeanUtils.copyProperties(m, authMenu);
            mList.add(authMenu);
        }
        return mList;
    }

    @Override
    public List<MenuTree> findRoleMenuTree(String roleCode) {
        Set<AuthMenu> menuList = findMenuByRole(roleCode);
        List<MenuTree> menuTreeList = new ArrayList<>();
        menuList.forEach(menu -> {
            menuTreeList.add(new MenuTree(menu));
        });
        return TreeUtil.build(menuTreeList, 0);
    }

    // 目前只支持二级菜单，若有三级，则改递归即可
    @Override
    public List<MenuTree> findAllMenuTree() {
        List<Menu> rList = this.findMenuList();

        List<Module> moduleList = this.moduleService.getAllList();
        List<MenuTree> menuTreeList = new ArrayList<>();
        rList.forEach(menu -> {
            if (menu.getPid() != 0 && null != moduleList && moduleList.size() > 0) {
                moduleList.forEach(m -> {
                    String id = menu.getPath() + "_" + m.getCode();
                    menuTreeList.add(new MenuTree(id, menu.getMenuId() + "", m.getName()));
                });
            }
            menuTreeList.add(new MenuTree(menu.getMenuId() + "", menu.getPid() + "", menu.getMenuName()));
        });

        return TreeUtil.build(menuTreeList, "0");
    }

    @Override
    public List<MenuTree> findAllMenuTreeList() {
        List<Menu> rList = this.findMenuList();

        List<MenuTree> menuTreeList = new ArrayList<MenuTree>();
        rList.forEach(menu -> {
            AuthMenu authMenu = new AuthMenu();
            BeanUtils.copyProperties(menu, authMenu);
            menuTreeList.add(new MenuTree(authMenu));
        });

        return TreeUtil.build(menuTreeList, "0");
    }

    @Override
    @Cacheable(key = "'menu_list'")
    public List<Menu> findMenuList() {
        QMenu qMenu = QMenu.menu;
        return this.queryFactory.selectFrom(qMenu).fetch();
    }

    @Override
    @Cacheable(key = "'menu_' + #roleId")
    public List<Menu> findMenuByRoleId(Integer roleId) {
        if (null == roleId) return null;
        QRoleMenu qRoleMenu = QRoleMenu.roleMenu;
        QMenu qMenu = QMenu.menu;
        return this.queryFactory.select(qMenu).from(qRoleMenu, qMenu).where(
                qRoleMenu.roleId.eq(roleId)).where(qRoleMenu.menuId.eq(qMenu.menuId)).fetch();
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public boolean delById(Integer menuId) {
        QMenu qMenu = QMenu.menu;
        long num = this.queryFactory.delete(qMenu).where(qMenu.menuId.eq(menuId)).execute();
        return num > 0;
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public Menu saveOrUpdate(Menu menu) {
        if (null == menu) return null;

        return menuRepository.saveAndFlush(menu);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public Boolean deleteMenu(Integer menuId, String roleCode) {
        // 删除当前节点 -- 假删除
        QMenu qMenu = QMenu.menu;
        this.queryFactory.update(qMenu).set(qMenu.statu, CommonConstant.STATUS_DEL).where(qMenu.menuId.eq(menuId)).execute();
        // 删除父节点为当前节点的节点 -- 假删除
        this.queryFactory.update(qMenu).set(qMenu.statu, CommonConstant.STATUS_DEL).where(qMenu.pid.eq(menuId)).execute();
        return true;
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public Boolean updateMenuById(Menu menu, String roleCode) {
        if (null == menu || null == menu.getMenuId()) {
            return null;
        }
        menuRepository.saveAndFlush(menu);
        return true;
    }
}
