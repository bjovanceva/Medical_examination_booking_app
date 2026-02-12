package org.example.medical_examination_booking_app.service.application;

import dto.CreateDoctorDto;
import dto.DisplayDoctorDto;
import dto.DisplayMedicalExaminationReservationDto;
import dto.DisplayPatientDto;
import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface DoctorApplicationService {

    List<DisplayDoctorDto> findAll();

    Optional<DisplayDoctorDto> findById(Long id);

    Optional<DisplayDoctorDto> update(Long id, CreateDoctorDto createDoctorDto);

    void deleteById(Long id);

    Optional<DisplayDoctorDto> save(CreateDoctorDto createDoctorDto);

    Optional<DisplayDoctorDto> findByUsername(String username);

    List<DisplayMedicalExaminationReservationDto> addNewTerm(String username, LocalDate date, LocalTime time);

}
