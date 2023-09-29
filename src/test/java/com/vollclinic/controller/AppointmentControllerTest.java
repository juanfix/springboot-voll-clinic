package com.vollclinic.controller;

import com.vollclinic.dto.DataAppointment;
import com.vollclinic.dto.DataScheduleAppointment;
import com.vollclinic.enums.Specialty;
import com.vollclinic.models.Doctor;
import com.vollclinic.models.Patient;
import com.vollclinic.services.IAppointmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@SuppressWarnings("all")
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DataScheduleAppointment> dataScheduleAppointmentJacksonTester;
    @Autowired
    private JacksonTester<DataAppointment> dataAppointmentJacksonTester;
    @MockBean
    private IAppointmentService appointmentService;

    @Test
    @DisplayName("It must be returns 400 error when the input data is incorrect.")
    @WithMockUser
    void scheduleAppointmentScene1() throws Exception {
        //given //when
        var response = mvc.perform(MockMvcRequestBuilders.post("/api/appointment").accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("It must be returns 200 status when the input data is correct.")
    @WithMockUser
    void agendarEscenario2() throws Exception {
        //given
        LocalDateTime date = LocalDateTime.now().plusHours(1);
        Specialty specialty = Specialty.CARDIOLOGY;
        DataAppointment dataAppointment = new DataAppointment(null,new Patient(),new Doctor(),date);

        // when
        when(appointmentService.createAppointment(any(), any())).thenReturn(ResponseEntity.ok(dataAppointment));

        var response = mvc.perform(MockMvcRequestBuilders.post("/api/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataScheduleAppointmentJacksonTester.write(new DataScheduleAppointment(2l,3l,date, specialty)).getJson())
        ).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonWanted = dataAppointmentJacksonTester.write(dataAppointment).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonWanted);

    }

}