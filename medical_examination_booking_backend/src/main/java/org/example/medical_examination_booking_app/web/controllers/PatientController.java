package org.example.medical_examination_booking_app.web.controllers;

import dto.CreatePatientDto;
import dto.DisplayPatientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.medical_examination_booking_app.model.domain.Patient;
import org.example.medical_examination_booking_app.service.application.PatientApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.medical_examination_booking_app.service.domain.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patient API", description = "Endpoints for managing Patient")
public class PatientController {

    private final PatientApplicationService patientApplicationService;

    public PatientController(PatientApplicationService patientApplicationService) {
        this.patientApplicationService = patientApplicationService;
    }


    @Operation(
            summary = "Get all patients", description = "Retrieves a list of all available patients."
    )
    @GetMapping
    public List<DisplayPatientDto> findAll() {
        return patientApplicationService.findAll();
    }

    @Operation(summary = "Get patientId by ID", description = "Finds a patientId by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayPatientDto> findById(@PathVariable Long id) {
        return patientApplicationService.findById(id)
                .map(patient -> ResponseEntity.ok().body(patient))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayPatientDto> save(@RequestBody CreatePatientDto createPatientDto) {
        return patientApplicationService.save(createPatientDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing patientId", description = "Updates a patientId by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayPatientDto> update(
            @PathVariable Long id,
            @RequestBody CreatePatientDto createPatientDto
    ) {
        return patientApplicationService.update(id, createPatientDto)
                .map(patient1 -> ResponseEntity.ok().body(patient1))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a patientId", description = "Deletes a patientId by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (patientApplicationService.findById(id).isPresent()) {
            patientApplicationService.deleteById(id); return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
