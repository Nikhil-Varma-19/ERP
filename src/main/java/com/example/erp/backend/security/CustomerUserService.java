package com.example.erp.backend.security;

import com.example.erp.backend.entities.User;
import com.example.erp.backend.exceptions.DataNotFound;
import com.example.erp.backend.repositories.UserRep;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerUserService  implements UserDetailsService {

    private final UserRep userRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRep.findByEmailAndIsActiveTrue(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("");
        return user.get();
    }

    public User getUserById(Long id){
        Optional<User> user=userRep.findByIdAndIsActiveTrue(id);
        if(user.isEmpty()) throw new DataNotFound("User Not Found");
        return user.get();
    }
}
