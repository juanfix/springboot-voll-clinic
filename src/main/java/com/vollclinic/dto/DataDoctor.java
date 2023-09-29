package com.vollclinic.dto;

public record DataDoctor(Long id, String name, String email, String phone, String dni,
                         DataAddress address) {
}
