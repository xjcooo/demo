package org.xjc.demo.common.util;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 公私钥生成工具
 *
 * @author xujc
 * @date 2020/1/6 15:08
 */
public class RsaKeyPairGenerator {

    public static final String KEY_ALGORITHM = "RSA";
//    private static final String PUBLIC_KEY = "RSAPublicKey";
//    private static final String PRIVATE_KEY = "RSAPrivateKey";
//    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";


    public static Keys initKey() throws Exception {
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        //通过对象 KeyPairGenerator 获取对象KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();

        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //公私钥对象存入map中
        Keys keys = new Keys();
        keys.setPrivateKey(encryptBASE64(privateKey.getEncoded()));
        keys.setPublicKey(encryptBASE64(publicKey.getEncoded()));
        return keys;
    }

    /**
     * 解码返回byte
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.decodeBase64(key);
//        return new BASE64Decoder().decodeBuffer(key);
    }

    /**
     * 编码返回字符串
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return new String(Base64.encodeBase64(key));
//        return new BASE64Encoder().encodeBuffer(key).replaceAll("\r\n", "");
    }

    @Setter
    @Getter
    static class Keys {
        protected String privateKey;
        protected String publicKey;

        @Override
        public String toString() {
            return "---RSA start---\r\nprivateKey:\r\n" + privateKey + "\r\n" +
                    "publicKey:\r\n" + publicKey + "\r\n---RSA end---";
        }
    }

    public static void main(String[] args) throws Exception {
        Keys keys = RsaKeyPairGenerator.initKey();
        System.out.println(keys);
        byte[] bytes = decryptBASE64(keys.getPrivateKey());
        System.out.println(keys.getPrivateKey());
        System.out.println(encryptBASE64(bytes));

    }
}
