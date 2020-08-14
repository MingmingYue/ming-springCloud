package com.service.auth.service;

import com.core.commons.constants.CommonConstant;
import com.service.api.model.AuthRole;
import com.service.api.model.AuthUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@Setter
@Getter
public class UserDetailsImpl implements UserDetails {

    private static final long	serialVersionUID	= 4996108569522332042L;

    private String				username;

    private String				password;

    private Integer				status;

    private List<AuthRole>      roleList;

    public UserDetailsImpl(AuthUser authUser) {
        this.username = authUser.getUsername();
        this.password = authUser.getPassword();
        this.status = authUser.getStatu();
        this.roleList = authUser.getRoleList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (AuthRole role : roleList) {
            authorityList.add(new SimpleGrantedAuthority(role.getRoleCode()));
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return CommonConstant.STATUS_LOCK.intValue() != status.intValue();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return CommonConstant.STATUS_NORMAL.intValue() == status.intValue();
    }
}