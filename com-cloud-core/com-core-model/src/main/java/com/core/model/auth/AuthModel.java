package com.core.model.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xiaoMing
 * Create on 2020-08-07.
 */
@Getter
@Setter
@ToString
public class AuthModel implements Serializable {
    private static final long serialVersionUID = -3183156069581988826L;

    private Integer authId;

    private String authName;

    @Builder(toBuilder = true)
    public AuthModel(Integer authId, String authName) {
        this.authId = authId;
        this.authName = authName;
    }
}
