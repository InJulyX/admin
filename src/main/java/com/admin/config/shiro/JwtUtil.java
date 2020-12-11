package com.admin.config.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public class JwtUtil {
    public static final String ACCOUNT = "username";
    public final static String CURRENT_TIME_MILLIS = "currentTimeMillis";
        public static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;
//    public static final long REFRESH_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L;

    public static final String SECRET_KEY = "123456789";

    /**
     * token验证
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        String secret = getClaim(token, ACCOUNT) + SECRET_KEY;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        verifier.verify(token);
        return true;
    }

    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String sign(String account, String currentTimeMillis) {
        String secret = account + SECRET_KEY;
        Date date = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim(ACCOUNT, account)
                .withClaim(CURRENT_TIME_MILLIS, currentTimeMillis)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static void main(String[] args) {
        String token = JwtUtil.sign("admin", String.valueOf(System.currentTimeMillis()));
        System.out.println(token);
        System.out.println(JwtUtil.verify(token));
        System.out.println(JwtUtil.getClaim(token, ACCOUNT));
    }
}
