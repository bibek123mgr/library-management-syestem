package com.example.library.management.system.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {

    private static final String SECRET="librarymanagementSystem";
    private static final long ACCESS_TOKEN_EXPIRATION_TIME=1000*60*60;
    private static final long REFRESH_TOKEN_EXPIRATION_TIME=1000*60*60;


    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateTokenAccessToken(Integer user_id,String role){
        Map<String,Object> claims=new HashMap<>();
        claims.put("role",role);
        return createToken(claims,user_id,ACCESS_TOKEN_EXPIRATION_TIME);
    }


    public String generateRefreshToken(Integer user_id,String role){
        Map<String,Object> claims=new HashMap<>();
        claims.put("role",role);
        return createToken(claims,user_id, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    private String createToken(Map<String,Object> claims,Integer user_id,long EXPIRATION_TIME){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(String.valueOf(user_id))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getKey())
                .compact();
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsTFunction){
        final Claims claims=extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }


    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String extractUsername(String token){
        return extractClaims(token,Claims :: getSubject);
    }

    public Date getExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String userId= extractUsername(token);
        return userDetails.getUsername().equals(userId) && !isTokenExpired(token);
    }






}
