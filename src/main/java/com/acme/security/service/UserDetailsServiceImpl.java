package com.acme.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acme.model.user.User;
import com.acme.security.UserDetailsAdapter;
import com.acme.services.UserService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        UserDetails userDetails = null;
        User userEntity = userService.getUserByUsername(username);

        if (userEntity == null) {
          throw new UsernameNotFoundException("user not found");
        }
        userDetails = new UserDetailsAdapter(userEntity);

        return userDetails;
    }

}
