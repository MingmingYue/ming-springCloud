package com.service.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xiaoMing
 * Create on 2020-08-04.
 */
@Getter
@Setter
@ToString
public class UserModel implements Serializable {
    private static final long serialVersionUID = -4638828612409567132L;

    private Integer userId;

    private String username;

    @Builder(toBuilder = true)
    public UserModel(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
