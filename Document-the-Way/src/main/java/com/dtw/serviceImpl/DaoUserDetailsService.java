package com.dtw.serviceImpl;

import com.dtw.entity.User;
import com.dtw.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class DaoUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Attempting to load user: " + username);
        try {
            User foundUser = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            System.out.println("User found: " + foundUser.getUsername());
            System.out.println("Password hash: " + foundUser.getPassword());

            UserPrincipal principal = new UserPrincipal(foundUser);
            System.out.println("UserPrincipal created successfully");

            return principal;
        } catch (Exception e) {
            System.out.println("Error loading user: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}


