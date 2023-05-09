package com.CHRESTAPI.todolist.auth;


import com.CHRESTAPI.todolist.config.jwtconfig.JwtService;
import com.CHRESTAPI.todolist.entities.User;
import com.CHRESTAPI.todolist.repositories.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class AuthenticationService {

    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = new User();
                user.setFirstName(request.getFirstName());
                user.setLastName(request.getLastName());
                user.setEmail(request.getEmail());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                userRepository.save(user);
              //  builder().firstName(request.getFirstName()).lastName(request.getLastName()).build();
        var jwtToken = jwtService.generateToken(user);
        return null;
            //    AuthenticationResponse.builder().build().se*

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
