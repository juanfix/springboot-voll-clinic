package com.vollclinic.services;

import com.vollclinic.dto.DataAppointment;
import com.vollclinic.dto.DataCancelAppointment;
import com.vollclinic.dto.DataScheduleAppointment;
import com.vollclinic.models.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface IAppointmentService {
    public ResponseEntity<Appointment> getById(Long id);
    public ResponseEntity<DataAppointment> createAppointment(DataScheduleAppointment dataScheduleAppointment, UriComponentsBuilder uriComponentsBuilder);
    public ResponseEntity cancelAppointment(DataCancelAppointment dataCancelAppointment);

}
