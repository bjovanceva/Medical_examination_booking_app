package org.example.medical_examination_booking_app.web.controllers;

import dto.ReservationListDto;
import dto.ReserveRequestDto;
import dto.TermRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.medical_examination_booking_app.model.exceptions.TheDoctorIsNotFreeException;
import org.example.medical_examination_booking_app.model.exceptions.ThePatientIsNotFreeException;
import org.example.medical_examination_booking_app.service.application.MedicalExaminationsReservationListApplicationService;
import org.example.medical_examination_booking_app.service.domain.MedicalExaminationsReservationListService;
import org.example.medical_examination_booking_app.service.domain.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/examinationList")
@Tag(name = "Medical Examination Reservation List API", description = "Endpoints for managing Medical Examination Reservation List")
public class MedicalExaminationsReservationListController {

    private final MedicalExaminationsReservationListApplicationService medicalExaminationsReservationListApplicationService;
    private final UserService userService;

    public MedicalExaminationsReservationListController(MedicalExaminationsReservationListService medicalExaminationsReservationListService, MedicalExaminationsReservationListApplicationService medicalExaminationsReservationListApplicationService, UserService userService) {
        this.medicalExaminationsReservationListApplicationService = medicalExaminationsReservationListApplicationService;
        this.userService = userService;
    }

    @ExceptionHandler(TheDoctorIsNotFreeException.class)
    public ResponseEntity<Map<String, String>> handleDoctorNotFree(TheDoctorIsNotFreeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "The doctor is not available at the selected time"));
    }

    @ExceptionHandler(ThePatientIsNotFreeException.class)
    public ResponseEntity<Map<String, String>> handlePatientNotFree(ThePatientIsNotFreeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "The patient is not available at the selected time"));
    }



    @Operation(
            summary = "Get active examinations list",
            description = "Retrieves the active list for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Examinations list retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "Examinations list not found")}
    )
    @GetMapping
    public ResponseEntity<ReservationListDto> getActiveExaminationsList(@AuthenticationPrincipal Jwt jwt) {

        String username = jwt.getClaim("preferred_username");
        System.out.println(medicalExaminationsReservationListApplicationService.getActiveReservationList(username).get().medicalExaminationReservations().size());
        return medicalExaminationsReservationListApplicationService.getActiveReservationList(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add product to shopping cart",
            description = "Adds a product to the shopping cart for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Product added to shopping cart successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(responseCode = "404", description = "Product not found")}
    )
    @PostMapping("/reserve/{id}")
    public ResponseEntity<ReservationListDto> makeReservation(
            @PathVariable Long id, // doctor's ID
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody ReserveRequestDto request
    ) {
        String username = jwt.getClaim("preferred_username");


        return medicalExaminationsReservationListApplicationService
                .createAndAddReservation(
                        username,
                        id,                  // doctor ID from path
                        request.date(),
                        request.time()
                )
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
