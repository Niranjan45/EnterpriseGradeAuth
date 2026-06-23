package com.auth.security;


import com.auth.entity.User;
import com.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {



    private final UserRepository repository;




    @Override
    public UserDetails loadUserByUsername(
            String username)
            throws UsernameNotFoundException {



        User user =
                repository.findByUsername(username)

                .orElseThrow(
                        () ->
                        new UsernameNotFoundException(
                        "User not found"
                        ));



        return new CustomUserDetails(user);

    }

}