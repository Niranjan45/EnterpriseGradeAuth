package com.auth.security;


import com.auth.entity.User;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.stream.Collectors;



@Getter
public class CustomUserDetails implements UserDetails {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final User user;


    public CustomUserDetails(User user){

        this.user = user;
    }



    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities(){


        return user.getRoles()

                .stream()

                .flatMap(role -> {


                    var roles =
                            java.util.stream.Stream.of(
                                    new SimpleGrantedAuthority(
                                    role.getRoleName()
                                    ));


                    var permissions =
                            role.getPermissions()
                            .stream()
                            .map(permission ->
                            new SimpleGrantedAuthority(
                            permission.getPermissionName()
                            ));


                    return java.util.stream.Stream.concat(
                            roles,
                            permissions
                    );


                })

                .collect(Collectors.toSet());

    }



    @Override
    public String getPassword(){

        return user.getPassword();

    }



    @Override
    public String getUsername(){

        return user.getUsername();

    }



    @Override
    public boolean isEnabled(){

        return user.isEnabled();

    }



    @Override
    public boolean isAccountNonExpired(){

        return true;

    }



    @Override
    public boolean isAccountNonLocked(){

        return true;

    }



    @Override
    public boolean isCredentialsNonExpired(){

        return true;

    }

}