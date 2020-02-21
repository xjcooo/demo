package org.xjc.demo.common.util;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Rsa对称加密
 *
 * @author xujc
 * @date 2020/2/19 15:48
 */
public class RsaUtil {

    /**
     * 加密最大明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * 解密块大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 字符串加密
     *
     * @param data         待加密数据
     * @param publicKeyStr 公钥
     * @return 加密后数据
     */
    public static String encrypt(String data, String publicKeyStr) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, parsePublicKey(publicKeyStr));
        return Base64.encodeBase64String(dealLongText(data.getBytes(CharEncoding.UTF_8), MAX_ENCRYPT_BLOCK, cipher));
    }

    /**
     * 加密串解密
     *
     * @param data          待解密数据
     * @param privateKeyStr 私钥
     * @return 解密后数据
     */
    public static String decrypt(String data, String privateKeyStr) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, parsePrivateKey(privateKeyStr));
        return new String(dealLongText(Base64.decodeBase64(data.getBytes(CharEncoding.UTF_8)), MAX_DECRYPT_BLOCK, cipher));
    }

    /**
     * 签名算法
     *
     * @param data          代签名数据
     * @param privateKeyStr 私钥字符串
     * @return 签名结果
     * @throws Exception
     */
    public static String sign(String data, String privateKeyStr) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(parsePrivateKey(privateKeyStr).getEncoded()));
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes(CharEncoding.UTF_8));
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验证签名
     *
     * @param sourceData   签名数据
     * @param publicKeyStr 公钥字符串
     * @param signStr      待验证的签名
     * @return 签名校验结果
     * @throws Exception
     */
    public static boolean verify(String sourceData, String publicKeyStr, String signStr) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(parsePublicKey(publicKeyStr).getEncoded()));
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        signature.update(sourceData.getBytes(CharEncoding.UTF_8));
        return signature.verify(Base64.decodeBase64(signStr.getBytes()));
    }

    public static RSAPublicKey parsePublicKey(String publicKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr)));
    }

    public static RSAPrivateKey parsePrivateKey(String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr)));
    }

    /**
     * 长字符串分段处理
     *
     * @param data      待处理文本
     * @param blockSize 单次处理块大小
     * @param cipher    处理器
     * @return
     */
    private static byte[] dealLongText(byte[] data, int blockSize, Cipher cipher) throws BadPaddingException, IllegalBlockSizeException, IOException {
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        while (inputLen > offset) {
            cache = cipher.doFinal(data, offset, (inputLen - offset > blockSize) ? blockSize : inputLen - offset);
            out.write(cache, 0, cache.length);
            offset = ++i * blockSize;
        }
        byte[] rs = out.toByteArray();
        out.close();
        return rs;
    }

}
