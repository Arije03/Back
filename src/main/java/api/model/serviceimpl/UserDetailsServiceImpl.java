/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.serviceimpl;

import api.model.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import api.model.iservice.IUsersService;

/**
 *
 * @author Makhlouf Helali
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsersService userService;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = userService.findByUsername(userName).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with number: " + userName)
        );
        return UserDetailsImpl.build(user);
    }
    
}
