package com.clanAfrica.demo.auth.dto.request;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
