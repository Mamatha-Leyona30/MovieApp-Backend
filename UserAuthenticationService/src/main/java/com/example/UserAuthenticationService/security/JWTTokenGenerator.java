package com.example.UserAuthenticationService.security;

import com.example.UserAuthenticationService.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTTokenGenerator implements SecurityTokenGeneratorI {
    @Override
    public String createToken(User user) {
        Map<String,Object> userData=new HashMap<>();
        userData.put("email",user.getEmail());
        System.out.println(userData);
        return generateToken(userData,user.getEmail());
    }

    private String generateToken(Map<String, Object> userData, String email) {
    String jwtToken= Jwts.builder()
            .setClaims(userData)
            .setSubject(email)
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256,"MySecretKeyForUserAuthenticationServiceOfMovieApp")
            .compact();
    return jwtToken;
    }

}