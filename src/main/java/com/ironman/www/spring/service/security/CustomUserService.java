package com.ironman.www.spring.service.security;

import com.ironman.www.spring.service.UserService;
import com.ironman.www.spring.service.dao.UserDAO;
import com.ironman.www.spring.service.entity.Role;
import com.ironman.www.spring.service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by superuser on 10/25/17.
 */
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByName(username);
        if(user == null) {
            throw new UsernameNotFoundException("the user is not exist");
        }
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        //add the permission for this user
        for (Role role : user.getRoles()) {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorityList);
    }
}
