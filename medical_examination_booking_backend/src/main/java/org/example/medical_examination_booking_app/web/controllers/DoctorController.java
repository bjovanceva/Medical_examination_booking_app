package org.example.medical_examination_booking_app.web.controllers;

import dto.CreateDoctorDto;
import dto.DisplayDoctorDto;
import dto.DisplayMedicalExaminationReservationDto;
import dto.TermRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.exceptions.DoctorNotFoundException;
import org.example.medical_examination_booking_app.service.application.DoctorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
@Tag(name = "Doctor API", description = "Endpoints for managing Doctor")
public class DoctorController {

    private final DoctorApplicationService doctorApplicationService;

    public DoctorController(DoctorApplicationService doctorApplicationService) {
        this.doctorApplicationService = doctorApplicationService;
    }


    @Operation(
            summary = "Get all doctors", description = "Retrieves a list of all available doctors."
    )
    @GetMapping
    public List<DisplayDoctorDto> findAll() {
        return doctorApplicationService.findAll();
    }

    @Operation(summary = "Get doctorId by ID", description = "Finds a doctorId by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayDoctorDto> findById(@PathVariable Long id) {
        return doctorApplicationService.findById(id)
                .map(doctor -> ResponseEntity.ok().body(doctor))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayDoctorDto> save(@RequestBody CreateDoctorDto createDoctorDto) {
        return doctorApplicationService.save(createDoctorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing doctorId", description = "Updates a doctorId by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayDoctorDto> update(
            @PathVariable Long id,
            @RequestBody CreateDoctorDto createDoctorDto
    ) {
        return doctorApplicationService.update(id, createDoctorDto)
                .map(doctor1 -> ResponseEntity.ok().body(doctor1))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a doctorId", description = "Deletes a doctorId by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (doctorApplicationService.findById(id).isPresent()) {
            doctorApplicationService.deleteById(id); return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add new term", description = "Adding new term")
    @PostMapping("/addNewTerm")
    public ResponseEntity<Void> addNewTerm(
                                            @AuthenticationPrincipal Jwt jwt,
                                            @RequestBody TermRequestDto request){

        DisplayDoctorDto doctor = doctorApplicationService.findByUsername(request.doctorUsername()).orElseThrow(()->new DoctorNotFoundException(null));

        System.out.println(doctor.reservations());

        doctorApplicationService.addNewTerm(request.doctorUsername(), request.date(), request.time());

        System.out.println(doctor.reservations());

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/terms/{id}")
    public List<DisplayMedicalExaminationReservationDto> getDoctorTerms(@PathVariable Long id) {
        DisplayDoctorDto doctor = doctorApplicationService.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(null));

        return DisplayMedicalExaminationReservationDto.from(doctor.reservations())
                .stream()
                .filter(term -> term.patientId() == null)
                .collect(Collectors.toList());
    }
}
