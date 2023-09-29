package com.vollclinic.services;

import com.vollclinic.dto.DataUser;
import com.vollclinic.dto.DataUserRegister;
import com.vollclinic.models.User;
import com.vollclinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class AuthService implements UserDetailsService, IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public ResponseEntity<DataUser> createUser(DataUserRegister dataUserRegister, UriComponentsBuilder uriComponentsBuilder) {
        User user = userRepository.save(new User(dataUserRegister.username(), passwordEncoder.encode(dataUserRegister.password())));
        DataUser dataUser = new DataUser(user.getId(), user.getUsername(), user.getPassword());

        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(dataUser);
    }
}
