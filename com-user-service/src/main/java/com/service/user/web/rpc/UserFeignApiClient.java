package com.service.user.web.rpc;

import com.core.commons.utils.StringHelper;
import com.service.api.UserFeignApi;
import com.service.api.model.AuthPermission;
import com.service.api.model.AuthUser;
import com.service.common.bean.AuthMenu;
import com.service.common.permission.Module;
import com.service.common.web.BaseController;
import com.service.common.web.aop.PrePermissions;
import com.service.user.service.MenuService;
import com.service.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Slf4j
@RestController
public class UserFeignApiClient extends BaseController implements UserFeignApi {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    /**
     * 通过用户名查询用户及其角色信息
     */
    @Override
    public AuthUser findUserByUsername(@PathVariable("username") String username) {
        if (StringHelper.isBlank(username)) {
            return null;
        }
        return this.userService.findUserByUsername(username);
    }

    /**
     * 通过手机号码查询用户及其角色信息
     */
    @Override
    public AuthUser findUserByMobile(@PathVariable("mobile") String mobile) {
        if (StringHelper.isBlank(mobile)) {
            return null;
        }
        return this.userService.findUserByMobile(mobile);
    }

    @Override
    public Set<AuthPermission> findMenuByRole(@PathVariable("roleCode") String roleCode) {
        Set<AuthPermission> permissions = new HashSet<>();
        if (StringHelper.isBlank(roleCode)) return permissions;
        Set<AuthMenu> menus = menuService.findMenuByRole(roleCode);
        if (null == menus || menus.size() == 0) {
            return permissions;
        }
        menus.stream().forEach(r -> permissions.add(new AuthPermission(r.getUrl())));
        return permissions;
    }

}
