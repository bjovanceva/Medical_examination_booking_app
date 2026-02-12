package org.example.medical_examination_booking_app.service.application;

import dto.DisplayMedicalExaminationReservationDto;
import dto.ReservationListDto;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationsReservationList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface MedicalExaminationsReservationListApplicationService {
    List<DisplayMedicalExaminationReservationDto> listAllMedicalExaminationsInReservationList(Long reservationListId);

    Optional<ReservationListDto> getActiveReservationList(String username);

    Optional<ReservationListDto> addMedicalExaminationToReservationList(String username, Long medicalExaminationId);

    Optional<ReservationListDto> createAndAddReservation(
            String username,
            Long doctorId,
            LocalDate date,
            LocalTime time);

    public Optional<ReservationListDto> save(String username);

}
