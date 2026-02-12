package org.example.medical_examination_booking_app.service.application;

import dto.CreateMedicalExaminationReservationDto;
import dto.DisplayMedicalExaminationReservationDto;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;

import java.util.List;
import java.util.Optional;

public interface MedicalExaminationReservationApplicationService {

    List<DisplayMedicalExaminationReservationDto> findAll();

    Optional<DisplayMedicalExaminationReservationDto> findById(Long id);

    Optional<DisplayMedicalExaminationReservationDto> update(Long id, CreateMedicalExaminationReservationDto createMedicalExaminationReservationDto);

    Optional<DisplayMedicalExaminationReservationDto> save(CreateMedicalExaminationReservationDto createMedicalExaminationReservationDto);

    void deleteById(Long id);
}
