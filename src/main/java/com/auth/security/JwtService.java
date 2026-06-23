package com.auth.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;


@Service
public class JwtService {


    private final String SECRET =
            "MyVeryStrongSecretKeyForJwtAuthenticationSystem123456789";


    private SecretKey getKey(){

        return Keys.hmacShaKeyFor(
                SECRET.getBytes()
        );
    }



    public String generateToken(
            String username,
            Map<String,Object> claims){


        return Jwts.builder()

                .claims(claims)

                .subject(username)

                .issuedAt(
                        new Date()
                )

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                +900000
                        )
                )

                .signWith(getKey())

                .compact();
    }



    public String extractUsername(
            String token){


        return Jwts.parser()

                .verifyWith(getKey())

                .build()

                .parseSignedClaims(token)

                .getPayload()

                .getSubject();

    }



    public boolean validateToken(
            String token){


        try {

            Jwts.parser()

                    .verifyWith(getKey())

                    .build()

                    .parseSignedClaims(token);


            return true;


        }catch(Exception e){

            return false;

        }
    }

}