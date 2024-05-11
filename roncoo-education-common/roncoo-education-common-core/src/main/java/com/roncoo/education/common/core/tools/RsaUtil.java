package com.roncoo.education.common.core.tools;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.security.KeyPair;

@Slf4j
public final class RsaUtil {
    private RsaUtil() {
    }

    public static void init() {
        KeyPair keyPair = SecureUtil.generateKeyPair("RSA");
        //获得私钥
        String privateKeyStr = Base64.encode(keyPair.getPrivate().getEncoded());
        log.info("私钥：" + privateKeyStr);
        //获得公钥
        String publicKeyStr = Base64.encode(keyPair.getPublic().getEncoded());
        log.info("公钥：" + publicKeyStr);
    }

    /**
     * 加密
     *
     * @param data
     * @param publicKey
     * @return
     */
    public static String encrypt(String data, String publicKey) {
        return Base64.encode(SecureUtil.rsa(null, publicKey).encrypt(data, KeyType.PublicKey));
    }

    /**
     * 解密
     *
     * @param data
     * @param privateKey
     * @return
     */
    public static String decrypt(String data, String privateKey) {
        if (StringUtils.hasText(data)) {
            try {
                return SecureUtil.rsa(privateKey, null).decryptStr(data, KeyType.PrivateKey);
            } catch (Exception e) {
                log.error("解密异常", e);
                Thread.currentThread().interrupt();
            }
        }
        return null;
    }

}
