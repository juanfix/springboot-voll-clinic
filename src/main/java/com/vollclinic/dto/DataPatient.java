package com.vollclinic.dto;

public record DataPatient(Long id, String name, String email, String phone, String dni,
                          DataAddress address) {
}
