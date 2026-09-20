package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 * 基于 jjwt 实现，签名算法 HS256，密钥固定为 "5L2g5aW9Cg=="
 * 过期时间统一为 2 小时
 */
public class JwtUtils {

    // 固定密钥（与测试类一致）
    private static final String SECRET_KEY = "5L2g5aW9Cg==";

    // 过期时间：2 小时（单位毫秒）
    private static final long EXPIRATION = 2 * 3600 * 1000L;

    /**
     * 生成 JWT 令牌
     * @param claims 要存入令牌的自定义数据（如 id, username 等）
     * @return 紧凑格式的 JWT 字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .compact();
    }

    /**
     * 解析 JWT 令牌，并返回 Claims（包含所有声明）
     * @param token JWT 字符串
     * @return Claims 对象，可从中获取 id、username 等自定义数据
     * @throws io.jsonwebtoken.JwtException 如果令牌无效、过期或签名错误
     */
    public static Claims parseJwt(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}