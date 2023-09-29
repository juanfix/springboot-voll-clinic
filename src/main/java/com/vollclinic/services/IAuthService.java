package com.vollclinic.services;

import com.vollclinic.dto.DataUser;
import com.vollclinic.dto.DataUserRegister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface IAuthService {
    public ResponseEntity<DataUser> createUser(DataUserRegister dataUserRegister, UriComponentsBuilder uriComponentsBuilder);

}
