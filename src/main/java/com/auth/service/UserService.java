package com.auth.service;


import com.auth.dto.UserRequest;
import com.auth.entity.Role;
import com.auth.entity.User;

import com.auth.repository.RoleRepository;
import com.auth.repository.UserRepository;


import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.List;



@Service
@RequiredArgsConstructor
public class UserService {



    private final UserRepository userRepository;


    private final RoleRepository roleRepository;


    private final PasswordEncoder passwordEncoder;




    public User createUser(
            UserRequest request){



        User user =
                User.builder()

                .username(
                    request.getUsername()
                )

                .password(
                    passwordEncoder
                    .encode(
                    request.getPassword()
                    )
                )

                .tenantId(
                    request.getTenantId()
                )

                .enabled(true)

                .build();




        if(request.getRoleIds()!=null){


            List<Role> roles =

            roleRepository
            .findAllById(
                request.getRoleIds()
            );


            user.setRoles(
                    Set.copyOf(roles)
            );

        }



        return userRepository.save(user);

    }






    public List<User> getAll(){

        return userRepository.findAll();

    }





    public User getById(Long id){

        return userRepository
                .findById(id)
                .orElseThrow();

    }






    public void delete(Long id){

        userRepository.deleteById(id);

    }


}