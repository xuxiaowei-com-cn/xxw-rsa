package cn.ac.xxw.rsa;

import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;

/**
 * RSA 工具类 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
class RsaRawEncryptorTests {

    @Test
    void rsaRawEncryptor() {
        RsaRawEncryptor rsaRawEncryptor = new RsaRawEncryptor();

        String publicKey = rsaRawEncryptor.getPublicKey();
        String privateKey = rsaRawEncryptor.getPrivateKey();

        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

        String text = "今天中午吃啥呢？？？";

        String encrypt = rsaRawEncryptor.encrypt(text);
        String decrypt = rsaRawEncryptor.decrypt(encrypt);

        System.out.println("原文：" + text);
        System.out.println("加密后：" + encrypt);
        System.out.println("解密后：" + decrypt);
    }

    @Test
    void key() throws InvalidKeyException {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCD5XD8TEL7d6EwN6WohUhLMPbx7LumqTY4hD0wHQDVB8QcOtyKiHTJEL+KnmY662gkDJnxiaeMUqX5c2AneXf3wLCYi6I8JmFqSNhOdxNdo/YvklPcmBAmxpW2grZdO4J2MWVykHrAMD07YJOKXDZcwe4HQgpoIH7hKvalcc4QnQIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIPlcPxMQvt3oTA3paiFSEsw9vHsu6apNjiEPTAdANUHxBw63IqIdMkQv4qeZjrraCQMmfGJp4xSpflzYCd5d/fAsJiLojwmYWpI2E53E12j9i+SU9yYECbGlbaCtl07gnYxZXKQesAwPTtgk4pcNlzB7gdCCmggfuEq9qVxzhCdAgMBAAECgYBYoWqOL5TnNFlddFdeacnNtSaMJR9n+9cSnVIcrbCsdl6C9c7TTKTlo9qChLR/rUa6yrj7xRuQwM0FVlFr1UUWeoiYK4A7KKSMwbe5HZkj5hZM1O9T/nqXrdI+qztqUcPMoThE1W3pSJ1SuFH2NSoWyXuyYXRdoAyTwoVSVZ4WBQJBAOeqB4PlrNCVgCveabrc/WTTSuUvu42NKG7n7huaQtrDH7uy0GIumq8MwuZl+R6ZUtyxv2CA9MK0dPyPKSkDbCcCQQCRwG2g2XPgCDXyy0Sl+GOEnkf2JWKVhuGrlCf41gwJP77JPMekSrNi0Yw27c0YwdFmrtm/GKAGhL4vYtvxo+ObAkEAqkRl0aN1KLk4wwVtYFIcS4agfWJfzuH43crJTrBKgs72+9WpIwBt4ErY1M4OE1dNd7eMmTkurAxGD3qJHgPN8QJAbIQSm0GLjm9Oi1hf4hpPLfwSo+ctwRpNhsul/xSOnYxCZd3E3kNnz9koRfVDUH1thMAGCsswyemnF+zIyN42pQJAI6ZJSx+bUacrS6yhPLKJXDRrZtp7xCGLesc6kMK6Yyaa2FcQWs+XZncPEdlFxsFgOgAsBd9s+PpCv6DRFIR9Lg==";

        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

        RsaRawEncryptor rsaRawEncryptor = new RsaRawEncryptor(publicKey, privateKey);

        String text = "今天中午吃啥呢？？？";

        String encrypt = rsaRawEncryptor.encrypt(text);
        String decrypt = rsaRawEncryptor.decrypt(encrypt);

        System.out.println("原文：" + text);
        System.out.println("加密后：" + encrypt);
        System.out.println("解密后：" + decrypt);
    }

}
