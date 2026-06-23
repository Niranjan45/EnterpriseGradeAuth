package com.auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserRequest {

    private String username;

    private String password;

    private String tenantId;

    private Set<Long> roleIds;
}