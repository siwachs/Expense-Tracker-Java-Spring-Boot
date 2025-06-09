package org.AuthService.services;

import org.AuthService.entities.UserInfo;
import org.AuthService.models.UserInfoDto;
import org.AuthService.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfUserAlreadyExist(UserInfoDto userInfoDto) {
        return userRepository.findByUsername(userInfoDto.getUsername());
    }

    public Boolean signUpUser(UserInfoDto userInfoDto) {
        if (checkIfUserAlreadyExist(userInfoDto) != null) {
            return false;
        }
        
        String userId = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(userInfoDto.getPassword());

        UserInfo newUser = UserInfo.builder()
                .userId(userId)
                .username(userInfoDto.getUsername())
                .password(encodedPassword)
                .roles(new HashSet<>())
                .build();

        userRepository.save(newUser);
        return true;
    }
}
