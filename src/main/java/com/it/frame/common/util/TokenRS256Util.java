package com.it.frame.common.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * TODO
 *
 * @author chenshaoqi
 * @since 2020/5/19
 */
@Slf4j
public class TokenRS256Util {
    /**
     * 过期时间3600秒
     */
    private static final long EXPIRE_TIME = 1000 * 3600;
    private static RSAKey rsaKey;
    private static RSAKey publicRsaKey;

    static {
        try {
            // 生成公钥，公钥是提供出去，让使用者校验token的签名
            rsaKey = getKey();
            publicRsaKey = rsaKey.toPublicJWK();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
    }


    public static String buildToken(String account) {
        try {
            // 1. 生成秘钥,秘钥是token的签名方持有，不可对外泄漏
            RSASSASigner rsassaSigner = new RSASSASigner(rsaKey);

            // 2. 建立payload 载体
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject("fram")
                    .issuer("http://www.fram.com")
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .claim("ACCOUNT", account)
                    .build();

            // 3. 建立签名
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);
            signedJWT.sign(rsassaSigner);

            // 4. 生成token
            return signedJWT.serialize();

        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String volidToken(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            //添加私密钥匙 进行解密
            RSASSAVerifier rsassaVerifier = new RSASSAVerifier(publicRsaKey);

            //校验是否有效
            if (!jwt.verify(rsassaVerifier)) {
                log.error("Token 无效");
                return "";
            }
            //校验超时
            if (new Date().after(jwt.getJWTClaimsSet().getExpirationTime())) {
                log.error("Token 已过期");
                return "";
            }
            //获取载体中的数据
            Object account = jwt.getJWTClaimsSet().getClaim("ACCOUNT");
            //是否有openUid
            if (Objects.isNull(account)) {
                log.error("Token 账号为空");
                return "";
            }
            return account.toString();
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 创建加密key
     */
    private static RSAKey getKey() throws JOSEException {
        RSAKeyGenerator rsaKeyGenerator = new RSAKeyGenerator(2048);
        return rsaKeyGenerator.generate();
    }

    public static void main(String[] args) {
        System.out.println(TokenRS256Util.buildToken("W9004028"));
    }

}
