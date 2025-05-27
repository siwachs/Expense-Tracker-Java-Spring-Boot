package org.AuthService.controllers;

import org.AuthService.entities.RefreshToken;
import org.AuthService.request.AuthRequestDto;
import org.AuthService.request.RefreshTokenRequestDto;
import org.AuthService.response.JwtResponseDto;
import org.AuthService.services.JwtService;
import org.AuthService.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TokenController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("auth/v1/login")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));

        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDto.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(JwtResponseDto
                    .builder()
                    .accessToken(jwtService.generateToken(authRequestDto.getUsername()))
                    .token(refreshToken.getToken())
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception in User Service");
        }
    }

    @PostMapping("/auth/v1/refreshToken")
    public JwtResponseDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        return refreshTokenService.findByToken(refreshTokenRequestDto.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getUsername());
                    return JwtResponseDto
                            .builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDto.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
    }
}


































