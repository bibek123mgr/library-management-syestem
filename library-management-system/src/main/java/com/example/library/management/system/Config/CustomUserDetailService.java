package com.example.library.management.system.Config;

import com.example.library.management.system.entity.User;
import com.example.library.management.system.entity.UserPrincipal;
import com.example.library.management.system.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           try{
               User user=userRepo.findByEmail(username);
               if (user != null){
                   return new UserPrincipal(user);
               }else{
                   throw new UsernameNotFoundException("User Not Found");
               }
           } catch (Exception e) {
               throw new RuntimeException(e.getMessage());
           }
    }
}
