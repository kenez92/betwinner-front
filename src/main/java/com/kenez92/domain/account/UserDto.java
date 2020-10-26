package com.kenez92.domain.account;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String role;
    private String email;
    private String userStrategy;
    private Boolean subscription;
    private String money;
}
