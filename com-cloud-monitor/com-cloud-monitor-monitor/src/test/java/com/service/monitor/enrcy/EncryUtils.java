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
        System.out.println(stringEncryptor.encrypt("com.github.liuweijw"));
        System.out.println(stringEncryptor.decrypt("H+Ujylg8J3e7QymVchGmDBIf+NiKciFl"));
        System.out.println(stringEncryptor.decrypt("sKbpJGHz9JZmdydv1WOyAYjyXm2irxc0xYqvQ6VpF31uEw/FVNxYeA=="));
        System.out.println(stringEncryptor.decrypt("fmVD9Jt6YXP9Q+KBPU8/1cWgKn5WhlGuD10SzFADNz4="));
        System.out.println(stringEncryptor.decrypt("rC/X/8UBBH2bn9Tgfuu7aw=="));
    }
}