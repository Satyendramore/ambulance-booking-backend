package com.Adily.Project.demo.Security;




import com.Adily.Project.demo.Entity.UserInfo;
import com.Adily.Project.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Userdetailserviceimpl implements UserDetailsService {
    @Autowired
    private UserRepo user;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserInfo u = user.findByName(name).orElse(null);
        if (u == null) {
            throw new UsernameNotFoundException(name);
        }
        return new Userprincipal(u);
    }
}

