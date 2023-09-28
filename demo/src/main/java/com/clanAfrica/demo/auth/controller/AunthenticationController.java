package com.clanAfrica.demo.auth.controller;
import com.clanAfrica.demo.Exception.AlreadyExistException;
import com.clanAfrica.demo.Exception.NotFoundException;
import com.clanAfrica.demo.auth.dto.response.AuthResponse;
import com.clanAfrica.demo.auth.dto.response.RegisterUserResponse;
import com.clanAfrica.demo.auth.service.AuthenticationService;
import com.clanAfrica.demo.auth.dto.request.RegisterRequest;
import com.clanAfrica.demo.auth.data.models.User;
import com.clanAfrica.demo.auth.dto.request.AuthenticationRequest;
import com.clanAfrica.demo.auth.dto.response.AuthenticationResponse;
import com.clanAfrica.demo.flight.dto.RegisterFlightResponse;
import com.clanAfrica.demo.flight.dto.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AunthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = service.register(request);
            return new ResponseEntity<>(
                    new RegisterUserResponse(true, HttpStatus.CREATED.value(), "successful",
                            LocalDateTime.now(), user)
                    ,HttpStatus.CREATED);
        }
        catch (AlreadyExistException e){
            return new ResponseEntity<>(
                    new BaseResponse(false, HttpStatus.BAD_REQUEST.value(), e.getMessage(),
                            LocalDateTime.now())
                    ,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = service.authenticate(request);
            return new ResponseEntity<>(
                    new AuthResponse(true, HttpStatus.OK.value(), "successful",
                            LocalDateTime.now(), response)
                    ,HttpStatus.OK);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(
                    new BaseResponse(false, HttpStatus.BAD_REQUEST.value(), e.getMessage(),
                            LocalDateTime.now())
                    ,HttpStatus.BAD_REQUEST);
        }



    }
}
