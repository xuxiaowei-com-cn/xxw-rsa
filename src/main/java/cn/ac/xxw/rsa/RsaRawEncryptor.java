package cn.ac.xxw.rsa;

import org.apache.commons.codec.binary.Base64;
import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * RSA 工具类
 *
 * @author xuxiaowei
 * @author Dave Syer
 * @author Luke Taylor
 */
public class RsaRawEncryptor {

    /**
     * 默认编码
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 算法
     */
    private static final String ALGORITHM = "RSA";

    private static final int MAX_LENGTH = 117;

    /**
     * 秘钥大小
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 编码
     */
    private final Charset charset;

    /**
     * 公钥
     */
    private final PublicKey publicKey;

    /**
     * 私钥
     */
    private final PrivateKey privateKey;

    /**
     * 默认编码
     */
    private final Charset defaultCharset;

    /**
     * 默认构造器
     */
    public RsaRawEncryptor() {
        this(generateKeyPair());
    }

    /**
     * 使用 公钥、私钥 构造器
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @throws InvalidKeyException 秘钥不合法
     */
    public RsaRawEncryptor(String publicKey, String privateKey) throws InvalidKeyException {
        RSAPublicKey rsaPublicKey = RSAPublicKeyImpl.newKey(Base64.decodeBase64(publicKey));
        RSAPrivateKey rsaPrivateKey = RSAPrivateCrtKeyImpl.newKey(Base64.decodeBase64(privateKey));
        this.charset = Charset.forName(DEFAULT_ENCODING);
        this.privateKey = rsaPrivateKey;
        this.publicKey = rsaPublicKey;
        this.defaultCharset = Charset.forName(DEFAULT_ENCODING);
    }

    /**
     * 使用 密钥对 构造器
     *
     * @param keyPair 密钥对
     */
    public RsaRawEncryptor(KeyPair keyPair) {
        this(DEFAULT_ENCODING, keyPair.getPublic(), keyPair.getPrivate());
    }

    /**
     * 使用 编码、公钥、私钥 构造器
     *
     * @param encoding   编码
     * @param publicKey  公钥
     * @param privateKey 私钥
     */
    public RsaRawEncryptor(String encoding, PublicKey publicKey, PrivateKey privateKey) {
        this.charset = Charset.forName(encoding);
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.defaultCharset = Charset.forName(DEFAULT_ENCODING);
    }

    /**
     * 使用 编码、公钥、私钥 构造器
     *
     * @param encoding   编码
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @throws InvalidKeyException 秘钥不合法
     */
    public RsaRawEncryptor(String encoding, String publicKey, String privateKey) throws InvalidKeyException {
        RSAPublicKey rsaPublicKey = RSAPublicKeyImpl.newKey(Base64.decodeBase64(publicKey));
        RSAPrivateKey rsaPrivateKey = RSAPrivateCrtKeyImpl.newKey(Base64.decodeBase64(privateKey));
        this.charset = Charset.forName(encoding);
        this.publicKey = rsaPublicKey;
        this.privateKey = rsaPrivateKey;
        this.defaultCharset = Charset.forName(DEFAULT_ENCODING);
    }

    /**
     * 获取 密钥对
     *
     * @return 返回 密钥对
     */
    public static KeyPair generateKeyPair() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(KEY_SIZE);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取 公钥
     *
     * @return 返回 公钥
     */
    public String getPublicKey() {
        return Base64.encodeBase64String(publicKey.getEncoded());
    }

    /**
     * 获取 私钥
     *
     * @return 返回 私钥
     */
    public String getPrivateKey() {
        return Base64.encodeBase64String(privateKey.getEncoded());
    }

    /**
     * 加密 字符串
     *
     * @param text 字符串
     * @return 返回 加密结果
     */
    public String encrypt(String text) {
        byte[] bytes = text.getBytes(this.charset);
        byte[] encrypt = encrypt(bytes);
        byte[] encodeBase64 = Base64.encodeBase64(encrypt);
        return new String(encodeBase64, this.defaultCharset);
    }

    /**
     * 加密 字节
     *
     * @param byteArray 字节
     * @return 返回 加密结果
     */
    public byte[] encrypt(byte[] byteArray) {
        return encrypt(byteArray, this.publicKey);
    }

    /**
     * 使用 公钥 加密 字节
     *
     * @param text 字节
     * @return 返回 加密结果
     */
    public static byte[] encrypt(byte[] text, PublicKey key) {
        ByteArrayOutputStream output = new ByteArrayOutputStream(text.length);
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            int limit = Math.min(text.length, MAX_LENGTH);
            int pos = 0;
            while (pos < text.length) {
                cipher.init(Cipher.ENCRYPT_MODE, key);
                cipher.update(text, pos, limit);
                pos += limit;
                limit = Math.min(text.length - pos, MAX_LENGTH);
                byte[] buffer = cipher.doFinal();
                output.write(buffer, 0, buffer.length);
            }
            return output.toByteArray();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Cannot encrypt", e);
        }
    }

    /**
     * 解密 数据
     *
     * @param encryptedText 加密文本
     * @return 返回 解密后的数据
     */
    public String decrypt(String encryptedText) {
        if (this.privateKey == null) {
            throw new IllegalStateException("Private key must be provided for decryption");
        }
        byte[] bytes = encryptedText.getBytes(this.defaultCharset);
        byte[] decodeBase64 = Base64.decodeBase64(bytes);
        return new String(decrypt(decodeBase64), this.charset);
    }

    /**
     * 解密 数据
     *
     * @param encryptedByteArray 加密文本
     * @return 返回 解密后的数据
     */
    public byte[] decrypt(byte[] encryptedByteArray) {
        return decrypt(encryptedByteArray, this.privateKey);
    }

    /**
     * 使用 私钥 解密 数据
     *
     * @param text 加密文本
     * @param key  私钥
     * @return 返回 解密后的数据
     */
    private static byte[] decrypt(byte[] text, PrivateKey key) {
        ByteArrayOutputStream output = new ByteArrayOutputStream(text.length);
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            int limit = Math.min(text.length, 128);
            int pos = 0;
            while (pos < text.length) {
                cipher.init(Cipher.DECRYPT_MODE, key);
                cipher.update(text, pos, limit);
                pos += limit;
                limit = Math.min(text.length - pos, 128);
                byte[] buffer = cipher.doFinal();
                output.write(buffer, 0, buffer.length);
            }
            return output.toByteArray();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Cannot decrypt", e);
        }
    }

}
