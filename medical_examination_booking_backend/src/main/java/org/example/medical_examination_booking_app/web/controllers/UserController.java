package org.example.medical_examination_booking_app.web.controllers;

import dto.DisplayDoctorDto;
import dto.DisplayPatientDto;
import org.example.medical_examination_booking_app.model.domain.Patient;
import org.example.medical_examination_booking_app.service.application.DoctorApplicationService;
import org.example.medical_examination_booking_app.service.application.PatientApplicationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final PatientApplicationService patientApplicationService;
    private final DoctorApplicationService doctorApplicationService;

    public UserController(PatientApplicationService patientApplicationService, DoctorApplicationService doctorApplicationService) {
        this.patientApplicationService = patientApplicationService;
        this.doctorApplicationService = doctorApplicationService;
    }


    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaim("preferred_username");

        Map<String, Object> realmAccess = jwt.getClaim("realm_access"); // cast to Map
        List<String> roles = (List<String>) realmAccess.get("roles");
        boolean isPatient = roles.contains("PATIENT");
        boolean isDoctor=roles.contains("DOCTOR");

        DisplayPatientDto patient=null;
        if(isPatient){
            if(patientApplicationService.findByUsername(username).isPresent()){
                patient=patientApplicationService.findByUsername(username).get();
                System.out.println("This is patient");
            }
        }

        DisplayDoctorDto doctor=null;
        if(isDoctor){
            if(doctorApplicationService.findByUsername(username).isPresent()){
                doctor=doctorApplicationService.findByUsername(username).get();
                System.out.println("This is doctor");
            }
        }


//        System.out.println(Map.of(
//                "username", jwt.getClaim("preferred_username"),
//                "email", jwt.getClaim("email"),
//                "roles", roles,
//                "photoUrl", patient != null ? patient.photoUrl() : null,
//                "firstName", patient.name(),
//                "lastName", patient.surname(),
//                "age", patient.age()
//        ));
        if(isPatient) {
            return Map.of(
                    "username", username,
                    "email", jwt.getClaim("email"),
                    "roles", roles,
                    "photoUrl", patient.photoUrl(),
                    "firstName", patient.name(),
                    "lastName", patient.surname(),
                    "age", patient.age()
            );
        }
        else {
            return Map.of(
                    "username", username,
                    "email", jwt.getClaim("email"),
                    "roles", roles,
                    "photoUrl", doctor.photoUrl(),
                    "firstName", doctor.name(),
                    "lastName", doctor.surname(),
                    "price", doctor.examination_price()
            );
        }
    }
}
