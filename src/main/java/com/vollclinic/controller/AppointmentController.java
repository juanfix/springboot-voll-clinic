package com.vollclinic.controller;

import com.vollclinic.dto.DataAppointment;
import com.vollclinic.dto.DataCancelAppointment;
import com.vollclinic.dto.DataScheduleAppointment;
import com.vollclinic.models.Appointment;
import com.vollclinic.services.IAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@ResponseBody
@RequestMapping("api/appointment")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    IAppointmentService appointmentService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Get the appointment data with it's ID",
            description = "")
    public ResponseEntity<Appointment> getOne(@PathVariable Long id) {
        return appointmentService.getById(id);
    }

    @PostMapping
    @Transactional
    @Operation(
            summary = "Schedule one appointment with a doctor or a specialty",
            description = "")
    public ResponseEntity<DataAppointment> scheduleAppointment(@RequestBody @Valid DataScheduleAppointment dataScheduleAppointment, UriComponentsBuilder uriComponentsBuilder){
        return appointmentService.createAppointment(dataScheduleAppointment, uriComponentsBuilder);
    }

    @DeleteMapping
    @Transactional
    @Operation(
            summary = "Cancel one appointment with a reason",
            description = "")
    public ResponseEntity scheduleAppointment(@RequestBody @Valid DataCancelAppointment dataCancelAppointment){
        return appointmentService.cancelAppointment(dataCancelAppointment);
    }
}
