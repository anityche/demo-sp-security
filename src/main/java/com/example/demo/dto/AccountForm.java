package com.example.demo.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.domain.Account;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountForm {

	private Long id;
    private String username;
    private String password;
    private String email;
    private String age;
    private String role;

    @Builder
    public AccountForm(Long id, String username, String password, String email, String age, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public Account toEntity(){
        return Account.builder()
                .id(id)
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .age(age)
                .role(role)
                .build();
    }
}
