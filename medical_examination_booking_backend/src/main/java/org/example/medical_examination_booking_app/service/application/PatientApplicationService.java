package org.example.medical_examination_booking_app.service.application;

import dto.CreatePatientDto;
import dto.DisplayPatientDto;
import org.example.medical_examination_booking_app.model.domain.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientApplicationService {

    List<DisplayPatientDto> findAll();

    Optional<DisplayPatientDto> findById(Long id);

    Optional<DisplayPatientDto> update(Long id, CreatePatientDto createPatientDto);

    void deleteById(Long id);

    Optional<DisplayPatientDto> save(CreatePatientDto createPatientDto);

    Optional<DisplayPatientDto> findByUsername(String username);
}
