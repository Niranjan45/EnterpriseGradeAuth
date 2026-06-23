package com.auth.config;


import com.auth.entity.Role;
import com.auth.entity.User;
import com.auth.repository.RoleRepository;
import com.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@RequiredArgsConstructor
public class DataInitializer 
implements CommandLineRunner {



private final UserRepository userRepository;

private final RoleRepository roleRepository;

private final PasswordEncoder encoder;



@Override
public void run(String... args){



Role adminRole =

roleRepository
.findByRoleName("ROLE_ADMIN")
.orElseGet(() ->

roleRepository.save(

Role.builder()

.roleName("ROLE_ADMIN")

.build()

));





if(userRepository
.findByUsername("admin")
.isEmpty()){



User admin =

User.builder()

.username("admin")

.password(
encoder.encode("admin123")
)

.tenantId("TENANT_1")

.enabled(true)

.roles(
Set.of(adminRole)
)

.build();

userRepository.save(admin);
}}
}