package com.CHRESTAPI.todolist.config.jwtconfig;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Builder
public class JwtService {


    private static final String  SECRET_key = "2B4D6251655468576D5A7134743777217A25432A462D4A404E635266556A586E";
    public String extractUsername(String token) {
        return extractClaim(token , Claims::getSubject);

    }

    public  <T>  T extractClaim(String token, Function<Claims , T> claimsResolver ){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInLey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInLey() {
        byte[] keybytes = Decoders.BASE64.decode(SECRET_key);
       return Keys.hmacShaKeyFor(keybytes);
    }

    //generate token without extracting claims only from userDetails
    public String generateToken (UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }


    // generate token with extracting claims
    public  String generateToken(Map<String , Object> extraClaims , UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInLey(), SignatureAlgorithm.HS256)
                .compact();

    }

    //validating token
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username =  extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token , Claims::getExpiration);
    }
}
