package com.clanAfrica.demo.auth.service;
import com.clanAfrica.demo.Exception.AlreadyExistException;
import com.clanAfrica.demo.Exception.NotFoundException;
import com.clanAfrica.demo.auth.dto.request.AuthenticationRequest;
import com.clanAfrica.demo.auth.dto.request.RegisterRequest;
import com.clanAfrica.demo.auth.dto.response.AuthenticationResponse;
import com.clanAfrica.demo.security.config.JwtService;
import com.clanAfrica.demo.auth.data.models.User;
import com.clanAfrica.demo.auth.data.repositories.UserRepository;
import com.clanAfrica.demo.auth.data.enums.Role;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private  final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public User register(RegisterRequest request){
       Optional<User> founderUser = userRepository.findByEmail(request.getEmail());
       if (founderUser.isPresent()){
           throw new AlreadyExistException("Email already exist");
       }
       else {
           var user = User.builder()
                   .firstName(request.getFirstname())
                   .lastName(request.getLastname())
                   .password(passwordEncoder.encode(request.getPassword()))
                   .email(request.getEmail())
                   .role(Role.USER)
                   .build();
           return  userRepository.save(user);
       }

//    var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
}

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()){
            throw new NotFoundException("Email or password is not correct");
        }  else {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
            var jwtToken = jwtService.generateToken((UserDetails) user.get());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

    }
}
