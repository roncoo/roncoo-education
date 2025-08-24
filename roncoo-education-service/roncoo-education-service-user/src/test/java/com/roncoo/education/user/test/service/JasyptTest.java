package com.roncoo.education.user.test.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;

@Slf4j
public class JasyptTest extends BaseTest {

    @Resource
    private StringEncryptor encryptor;

    @Test
    public void test() {
        String password = "RonCoo.123";
        log.info("加密前：{}", password);
        String encrypt = encryptor.encrypt(password);
        log.info("加密后：{}", encrypt);
        String decrypt = encryptor.decrypt(encrypt);
        log.info("解密后：{}", decrypt);
    }

}

