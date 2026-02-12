package dto;

import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayMedicalExaminationReservationDto(Long id, Long doctorId, Long patientId, LocalDate whenDate, LocalTime whenTime) {

    public static DisplayMedicalExaminationReservationDto from(MedicalExaminationReservation medicalExaminationReservation) {
        return new DisplayMedicalExaminationReservationDto(
                medicalExaminationReservation.getId(),
                medicalExaminationReservation.getDoctor().getId(),
                medicalExaminationReservation.getPatient() != null ? medicalExaminationReservation.getPatient().getId() : null,
                medicalExaminationReservation.getWhenDate(),
                medicalExaminationReservation.getWhenTime());
    }

    public static List<DisplayMedicalExaminationReservationDto> from(List<MedicalExaminationReservation> medicalExaminationReservations) {
        return medicalExaminationReservations.stream().map(DisplayMedicalExaminationReservationDto::from).collect(Collectors.toList());
    }
}
