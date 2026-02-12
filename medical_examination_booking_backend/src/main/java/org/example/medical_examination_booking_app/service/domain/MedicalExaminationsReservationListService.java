package org.example.medical_examination_booking_app.service.domain;

import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationsReservationList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface MedicalExaminationsReservationListService {

    List<MedicalExaminationReservation> listAllMedicalExaminationsInReservationList(Long reservationListId);

    Optional<MedicalExaminationsReservationList> getActiveReservationList(String username);

    Optional<MedicalExaminationsReservationList> addMedicalExaminationToReservationList(String username, Long medicalExaminationId);

    Optional<MedicalExaminationsReservationList> createAndAddReservation(
            String username,
            Long doctorId,
            LocalDate date,
            LocalTime time);

    public Optional<MedicalExaminationsReservationList> save(String username);
}
