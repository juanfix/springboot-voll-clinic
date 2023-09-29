package com.vollclinic.controller;

import com.vollclinic.dto.DataJWTToken;
import com.vollclinic.dto.DataUser;
import com.vollclinic.dto.DataUserAuthentication;
import com.vollclinic.dto.DataUserRegister;
import com.vollclinic.models.User;
import com.vollclinic.services.IAuthService;
import com.vollclinic.services.IDoctorService;
import com.vollclinic.services.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/auth")
@Tag(name = "User (Authentication)", description = "Obtain the token necessary to access all endpoints")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@RequestBody @Valid DataUserAuthentication dataUserAuthentication) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dataUserAuthentication.username(),
                dataUserAuthentication.password());
        Authentication authenticatedUser = authenticationManager.authenticate(authToken);
        String jwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new DataJWTToken(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<DataUser> createUser(@RequestBody @Valid DataUserRegister dataUserRegister, UriComponentsBuilder uriComponentsBuilder) {
        return authService.createUser(dataUserRegister, uriComponentsBuilder);
    }

}
