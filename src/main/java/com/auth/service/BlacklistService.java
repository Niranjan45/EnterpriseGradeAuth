package com.auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class BlacklistService {


    private final RedisTemplate<String,String>
            redisTemplate;



    public void blacklist(
            String token){


        redisTemplate.opsForValue()

                .set(
                        token,
                        "BLACKLISTED"
                );
    }




    public boolean isBlacklisted(
            String token){


        return Boolean.TRUE.equals(

                redisTemplate.hasKey(token)

        );

    }

}