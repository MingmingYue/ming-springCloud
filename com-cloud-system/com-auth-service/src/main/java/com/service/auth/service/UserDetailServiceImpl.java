package com.service.auth.service;

import com.core.commons.utils.StringHelper;
import com.service.api.UserFeignApi;
import com.service.api.model.AuthUser;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author xiaoMing
 * Create on 2020-08-17.
 */
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService, Serializable {

    private static final long serialVersionUID = 5181442448895412779L;

    @Autowired
    private UserFeignApi userFeignApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringHelper.isBlank(username)) {
            throw new UsernameNotFoundException("用户不存在:" + username);
        }
        AuthUser user = userFeignApi.findUserByUsername(username);
        if (null == user)
            throw new UsernameNotFoundException("用户不存在:" + username);

        return new UserDetailsImpl(user);
    }
}
