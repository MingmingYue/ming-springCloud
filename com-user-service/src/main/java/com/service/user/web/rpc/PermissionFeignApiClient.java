package com.service.user.web.rpc;

import com.core.commons.utils.StringHelper;
import com.service.api.PermissionFeignApi;
import com.service.api.model.AuthPermission;
import com.service.common.bean.AuthMenu;
import com.service.common.web.BaseController;
import com.service.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaoMing
 * Create on 2020-08-17.
 */
@RestController
public class PermissionFeignApiClient extends BaseController implements PermissionFeignApi {

    @Autowired
    private MenuService menuService;

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