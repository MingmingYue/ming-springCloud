package com.service.common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@Setter
@Getter
@ToString
public class AuthLog implements Serializable {

    private static final long	serialVersionUID	= -7612739305546935933L;

    private Log					log;
}
