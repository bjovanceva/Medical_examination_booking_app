package org.example.medical_examination_booking_app.service.application.impl;

import dto.DisplayMedicalExaminationReservationDto;
import dto.ReservationListDto;
import org.example.medical_examination_booking_app.service.application.MedicalExaminationsReservationListApplicationService;
import org.example.medical_examination_booking_app.service.domain.MedicalExaminationsReservationListService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalExaminationsReservationListApplicationServiceImpl implements MedicalExaminationsReservationListApplicationService {
    private final MedicalExaminationsReservationListService medicalExaminationsReservationListService;

    public MedicalExaminationsReservationListApplicationServiceImpl(MedicalExaminationsReservationListService medicalExaminationsReservationListService) {
        this.medicalExaminationsReservationListService = medicalExaminationsReservationListService;
    }


    @Override
    public List<DisplayMedicalExaminationReservationDto> listAllMedicalExaminationsInReservationList(Long reservationListId) {
        return DisplayMedicalExaminationReservationDto.from(medicalExaminationsReservationListService.listAllMedicalExaminationsInReservationList(reservationListId));
    }

    @Override
    public Optional<ReservationListDto> getActiveReservationList(String username) {
        return medicalExaminationsReservationListService.getActiveReservationList(username).map(ReservationListDto::from);
    }

    @Override
    public Optional<ReservationListDto> addMedicalExaminationToReservationList(String username, Long medicalExaminationId) {
        return medicalExaminationsReservationListService.addMedicalExaminationToReservationList(username, medicalExaminationId)
                .map(ReservationListDto::from);
    }

    @Override
    public Optional<ReservationListDto> createAndAddReservation(String username, Long doctorId, LocalDate date, LocalTime time) {
        System.out.println(">>> ApplicationService: createAndAddReservation called for doctorId=" + doctorId);
        return medicalExaminationsReservationListService.createAndAddReservation(username, doctorId, date, time).map(ReservationListDto::from);
    }

    @Override
    public Optional<ReservationListDto> save(String username) {
        return Optional.of(ReservationListDto.from(medicalExaminationsReservationListService.save(username).orElseThrow()));
    }
}
