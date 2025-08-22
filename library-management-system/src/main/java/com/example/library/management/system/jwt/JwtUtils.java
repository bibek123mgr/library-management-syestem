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
    private static final long REFRESH_TOKEN_EXPIRATION_TIME=1000*60*60*24*7;


    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateTokenAccessToken(String username,String role){
        Map<String,Object> claims=new HashMap<>();
        claims.put("role",role);
        return createToken(claims,username,ACCESS_TOKEN_EXPIRATION_TIME);
    }


    public String generateRefreshToken(String username,String role){
        Map<String,Object> claims=new HashMap<>();
        claims.put("role",role);
        return createToken(claims,username, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    private String createToken(Map<String,Object> claims,String username,long EXPIRATION_TIME){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
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

    private Date getExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username= extractUsername(token);
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }






}
