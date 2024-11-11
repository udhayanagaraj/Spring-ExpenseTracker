package com.udhaya.expenseTracker.Security;

import com.udhaya.expenseTracker.Models.User;
import com.udhaya.expenseTracker.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<User> userRes = userRepo.findByEmail(email);
        if(userRes.isEmpty()){
            throw new UsernameNotFoundException("Could not find user with email: "+email);
        }

        User user = userRes.get();

        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
