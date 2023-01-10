package io.turntabl.leaderboard.service;

import io.turntabl.leaderboard.model.SecurityUser;
import io.turntabl.leaderboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomeUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  userRepository.findByUsername(username);
        return user.map(SecurityUser::new).orElseThrow(
                ()-> new UsernameNotFoundException("The Username Given Does Not Exist!")
        );
    }
}
