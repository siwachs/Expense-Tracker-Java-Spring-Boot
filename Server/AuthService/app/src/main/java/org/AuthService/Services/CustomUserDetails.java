package org.AuthService.Services;

import org.AuthService.entities.UserInfo;
import org.AuthService.entities.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserInfo implements UserDetails {
    private final String username;
    private final String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserInfo byUsername){
        this.username = byUsername.getUsername();
        this.password = byUsername.getPassword();

        List<GrantedAuthority> auths = new ArrayList<>();

        for(UserRole role: byUsername.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getName().toLowerCase()));
        }

        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
