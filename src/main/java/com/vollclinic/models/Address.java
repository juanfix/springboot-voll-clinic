package com.vollclinic.models;

import com.vollclinic.dto.DataAddress;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String address;
    private String county;
    private String zipcode;
    private String complement;
    private String city;

    public Address(DataAddress address) {
        this.address = address.address();
        this.county = address.county();
        this.zipcode = address.zipcode();
        this.complement = address.complement();
        this.city = address.city();

    }

    public Address updateData(DataAddress address) {
        this.address = address.address();
        this.county = address.county();
        this.zipcode = address.zipcode();
        this.complement = address.complement();
        this.city = address.city();
        return this;
    }

}
