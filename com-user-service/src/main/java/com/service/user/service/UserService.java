package com.service.user.service;

import com.service.api.model.AuthUser;
import com.service.user.bean.UserBean;
import com.service.user.entity.User;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
public interface UserService {

    AuthUser findUserByUsername(String username);

    User findUserByUsername(String username, boolean isLoadRole);

    UserBean findUserInfo(AuthUser user);

    AuthUser findUserByMobile(String mobile);
}
