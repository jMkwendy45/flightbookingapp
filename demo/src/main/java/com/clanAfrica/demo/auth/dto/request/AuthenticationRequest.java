package com.clanAfrica.demo.auth.dto.request;

import lombok.Builder;
import lombok.Data;
    @Data
    @Builder
    public class AuthenticationRequest {

        private String email;
        private  String password;
    }
