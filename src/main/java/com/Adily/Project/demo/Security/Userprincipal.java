package com.Adily.Project.demo.Security;


import com.Adily.Project.demo.Entity.UserInfo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Userprincipal implements UserDetails {

    private final UserInfo userinfo;




    public Userprincipal(UserInfo userinfo) {
        this.userinfo = userinfo;
        System.out.println("Username: " + userinfo.getName());
        System.out.println("Password: " + userinfo.getPassword());

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userinfo.getRole().stream()
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .distinct()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }


    @Override
    public String getPassword() {
        return userinfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userinfo.getName();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}

