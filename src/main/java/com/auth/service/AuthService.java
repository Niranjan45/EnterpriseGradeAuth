package com.auth.service;


import com.auth.dto.*;
import com.auth.entity.*;
import com.auth.repository.UserRepository;
import com.auth.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;



@Service
@RequiredArgsConstructor
public class AuthService {



    private final AuthenticationManager authenticationManager;


    private final UserRepository userRepository;


    private final JwtService jwtService;


    private final RefreshTokenService refreshTokenService;


    private final AuditService auditService;





    public AuthResponse login(
            LoginRequest request){



        Authentication authentication =

                authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getUsername(),

                        request.getPassword()

                ));




        User user =

                userRepository
                .findByUsername(
                        request.getUsername()
                )

                .orElseThrow();




        Map<String,Object> claims =
                new HashMap<>();



        claims.put(
                "tenant_id",
                user.getTenantId()
        );



        claims.put(
                "roles",

                user.getRoles()

                .stream()

                .map(Role::getRoleName)

                .toList()
        );



        claims.put(
                "permissions",

                user.getRoles()

                .stream()

                .flatMap(role ->
                    role.getPermissions()
                    .stream()
                )

                .map(Permission::getPermissionName)

                .toList()

        );



        String accessToken =

                jwtService.generateToken(

                        user.getUsername(),

                        claims

                );



        RefreshToken refreshToken =

                refreshTokenService
                .createToken(user);



        auditService.saveLog(

                user.getUsername(),

                "LOGIN",

                "LOCAL"

        );



        return new AuthResponse(

                accessToken,

                refreshToken.getToken()

        );

    }







    public AuthResponse refresh(

            RefreshRequest request){



        RefreshToken token =

        refreshTokenService
        .findByToken(
                request.getRefreshToken()
        );



        User user =
                token.getUser();



        String accessToken =

                jwtService.generateToken(

                        user.getUsername(),

                        Map.of(

                        "tenant_id",
                        user.getTenantId()

                        )

                );



        return new AuthResponse(

                accessToken,

                token.getToken()

        );

    }



}