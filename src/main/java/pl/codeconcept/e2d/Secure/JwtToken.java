package pl.codeconcept.e2d.Secure;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.codeconcept.e2d.Model.Users;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtToken implements Serializable {

    @Value("${pl.codeconcept.e2d.jwtSecret}")
    private String jwtSecret;

    @Value("${pl.codeconcept.e2d.jwtExpiration}")
    private int jwtExpiration;


    public String generateJwtToken(Authentication authentication) {
        Users users = (Users) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(users.getUsername())
                .claim("role","user")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public Jws<Claims> getUsernameFromJwtToken1(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token.replace("Bearer ",""));
    }



}
