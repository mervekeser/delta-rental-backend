package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.services.JwtService;
import com.delta.rental.deltarental.entities.User;
import com.delta.rental.deltarental.repositories.UserRepository;
import com.delta.rental.deltarental.services.abstracts.AuthService;
import com.delta.rental.deltarental.services.dtos.requests.authentication.AddAuthenticationRequest;
import com.delta.rental.deltarental.services.dtos.requests.user.AddUserRequest;
import com.delta.rental.deltarental.services.dtos.requests.user.UpdateUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.authentication.GetAuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public GetAuthenticationResponse updateUserInformation(UpdateUserRequest request) {
        var user = User.builder()
                .id(request.getId())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .gsm(request.getGsm())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(request.getRoles())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return GetAuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public GetAuthenticationResponse register(AddUserRequest request) {
        var user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .gsm(request.getGsm())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(request.getRoles())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return GetAuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public GetAuthenticationResponse authenticate(AddAuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Bilgiler hatalı."));
        var jwtToken = jwtService.generateToken(user);
        return GetAuthenticationResponse.builder().token(jwtToken).build();
    }


}
