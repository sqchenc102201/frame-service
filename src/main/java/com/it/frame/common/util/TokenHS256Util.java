package com.it.frame.common.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * Token 生成工具类
 *
 * @author chenshaoqi
 * @since 2020/5/19
 */
@Slf4j
public class TokenHS256Util {
    /**
     * 创建秘钥
     */
    private static final byte[] SECRET = "db05cc7a9fcb410386c4f0b73f3fdf3b".getBytes();

    /**
     * 过期时间60分钟
     */
    private static final long EXPIRE_TIME = 1000 * 3600;


    /**
     * 生成Token
     *
     * @param account 工号
     * @return token
     */
    public static String makeJWTToken(String account) {
        try {
            // 1.创建一个32-byte的密匙
            MACSigner macSigner = new MACSigner(SECRET);

            // 2. 建立payload 载体
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject("fram")
                    .issuer("http://www.fram.com")
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .claim("ACCOUNT", account)
                    .build();

            // 3. 建立签名
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(macSigner);

            // 4. 生成token
            return signedJWT.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 校验token
     *
     * @param token token
     * @return 工号
     */
    private static String vaildToken(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET);
            //校验是否有效
            if (!jwt.verify(verifier)) {
                log.error("Token 已失效");
                return null;
            }
            //校验超时
            Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)) {
                log.error("Token 已过期");
                return null;
            }

            //获取载体中的数据
            Object account = jwt.getJWTClaimsSet().getClaim("ACCOUNT");
            //是否有openUid
            if (Objects.isNull(account)) {
                log.error("Token 账号为空");
                return null;
            }
            return account.toString();
        } catch (ParseException | JOSEException e) {
            log.error("vaild Token Exception", e);
        }
        return null;
    }

    public static void main(String[] args) {
        String token = TokenHS256Util.makeJWTToken("W9004028");
        System.out.println(token);
        String account = TokenHS256Util.vaildToken(token);
        System.out.println(account);
    }
}
