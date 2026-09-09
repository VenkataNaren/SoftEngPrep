package com.nz.prep.controller;

import com.nz.prep.dto.AuthRequest;
import com.nz.prep.dto.AuthResponse;
import com.nz.prep.entity.User;
import com.nz.prep.security.JwtUtil;
import com.nz.prep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();

        return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), user.getName(), user.getRole().name()));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        if (userService.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User(request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
        User savedUser = userService.saveUser(user);

        String token = jwtUtil.generateToken(savedUser);

        return ResponseEntity.ok(new AuthResponse(token, savedUser.getEmail(), savedUser.getName(), savedUser.getRole().name()));
    }
}