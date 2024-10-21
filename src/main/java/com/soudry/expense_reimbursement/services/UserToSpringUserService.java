package com.soudry.expense_reimbursement.services;

import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soudry.expense_reimbursement.entities.User;
import com.soudry.expense_reimbursement.repository.UserRepository;

@Service
public class UserToSpringUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserToSpringUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Determine the role based on the manager field
        String role = user.getRole() ? "ROLE_MANAGER" : "ROLE_USER";

        // Create authorities list
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            authorities  // Use the appropriate role (user or manager)
        );
    }
}
