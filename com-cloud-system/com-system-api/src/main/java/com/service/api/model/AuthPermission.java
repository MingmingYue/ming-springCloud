package com.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthPermission implements Serializable {

    private static final long	serialVersionUID	= 4566420419542436770L;

    /**
     * 请求URL
     */
    private String				url;
}
