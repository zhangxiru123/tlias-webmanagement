package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

public class JwtTest {
    @Test
    public void testGenerateJwt(){
        HashMap<String,Object> dataMap=new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "5L2g5aW9Cg==")
                .addClaims(dataMap)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        Claims body = Jwts.parser().
                setSigningKey("5L2g5aW9Cg==")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc4Njg3MTczNH0.MaAn_OSUVJert5jivqVyL0_8mQk1x4W-vTqQX7TCE9c")
                .getBody();
        System.out.println(body);

    }
}
