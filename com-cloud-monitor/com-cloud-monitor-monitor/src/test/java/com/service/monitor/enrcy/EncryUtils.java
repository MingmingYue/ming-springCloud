package com.service.monitor.enrcy;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author xiaoMing
 * Create on 2020-08-14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryUtils {

    @Resource
    StringEncryptor stringEncryptor;

    @Test
    public void encryptPwd() {
        System.out.println(stringEncryptor.encrypt("user-service"));
        System.out.println(stringEncryptor.encrypt("jwt:token:ming"));
        System.out.println(stringEncryptor.decrypt("H+Ujylg8J3e7QymVchGmDBIf+NiKciFl"));
    }
}