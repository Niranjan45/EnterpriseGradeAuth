package com.auth.service;


import com.auth.entity.RefreshToken;
import com.auth.entity.User;
import com.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class RefreshTokenService {


    private final RefreshTokenRepository repository;



    public RefreshToken createToken(
            User user){


        RefreshToken token =
                RefreshToken.builder()

                .token(
                    UUID.randomUUID().toString()
                )

                .user(user)

                .expiryDate(
                    LocalDateTime.now()
                    .plusDays(7)
                )

                .build();


        return repository.save(token);

    }




    public RefreshToken findByToken(
            String token){


        return repository
                .findByToken(token)
                .orElseThrow(
                () ->
                new RuntimeException(
                "Invalid refresh token"
                ));

    }


}