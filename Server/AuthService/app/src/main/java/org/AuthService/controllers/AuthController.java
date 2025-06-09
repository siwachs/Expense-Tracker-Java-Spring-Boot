package org.AuthService.controllers;

import org.AuthService.entities.RefreshToken;
import org.AuthService.models.UserInfoDto;
import org.AuthService.response.JwtResponseDto;
import org.AuthService.services.JwtService;
import org.AuthService.services.RefreshTokenService;
import org.AuthService.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserInfoDto userInfoDto) {
        try {
            Boolean isSignUpSuccess = userDetailsService.signUpUser(userInfoDto);
            if (Boolean.FALSE.equals(isSignUpSuccess)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already Exists");
            }

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.generateToken(userInfoDto.getUsername());

            return ResponseEntity.status(HttpStatus.OK).body(JwtResponseDto
                    .builder()
                    .accessToken(jwtToken)
                    .token(refreshToken.getToken())
                    .build());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception in User Service");
        }
    }
}





























