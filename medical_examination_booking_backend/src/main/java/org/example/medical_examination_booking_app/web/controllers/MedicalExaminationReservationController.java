package org.example.medical_examination_booking_app.web.controllers;

import dto.CreateMedicalExaminationReservationDto;
import dto.DisplayMedicalExaminationReservationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;
import org.example.medical_examination_booking_app.model.domain.Patient;
import org.example.medical_examination_booking_app.service.application.MedicalExaminationReservationApplicationService;
import org.example.medical_examination_booking_app.service.domain.MedicalExaminationReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examination")
@Tag(name = "Medical Examination Reservation API", description = "Endpoints for managing Medical Examination Reservation")
public class MedicalExaminationReservationController {

    private final MedicalExaminationReservationApplicationService medicalExaminationReservationApplicationService;

    public MedicalExaminationReservationController(MedicalExaminationReservationApplicationService medicalExaminationReservationApplicationService) {
        this.medicalExaminationReservationApplicationService = medicalExaminationReservationApplicationService;
    }


    @Operation(
            summary = "Get all Medical Examination Reservations", description = "Retrieves a list of all available Medical Examination Reservations."
    )
    @GetMapping
    public List<DisplayMedicalExaminationReservationDto> findAll() {
        return medicalExaminationReservationApplicationService.findAll();
    }

    @Operation(summary = "Get examinationId by ID", description = "Finds a examinationId by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayMedicalExaminationReservationDto> findById(@PathVariable Long id) {
        return medicalExaminationReservationApplicationService.findById(id)
                .map(medicalExaminationReservation -> ResponseEntity.ok().body(medicalExaminationReservation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayMedicalExaminationReservationDto> save(@RequestBody CreateMedicalExaminationReservationDto createMedicalExaminationReservationDto) {
        return medicalExaminationReservationApplicationService.save(createMedicalExaminationReservationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing examinationId", description = "Updates a examinationId by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayMedicalExaminationReservationDto> update(
            @PathVariable Long id,
            @RequestBody CreateMedicalExaminationReservationDto createMedicalExaminationReservationDto
    ) {
        return medicalExaminationReservationApplicationService.update(id, createMedicalExaminationReservationDto)
                .map(medicalExaminationReservation1 -> ResponseEntity.ok().body(medicalExaminationReservation1))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a examinationId", description = "Deletes a examinationId by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (medicalExaminationReservationApplicationService.findById(id).isPresent()) {
            medicalExaminationReservationApplicationService.deleteById(id); return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
