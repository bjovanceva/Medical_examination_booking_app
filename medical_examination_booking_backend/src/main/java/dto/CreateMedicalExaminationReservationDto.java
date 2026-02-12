package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;
import org.example.medical_examination_booking_app.model.domain.Patient;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateMedicalExaminationReservationDto(
        Long doctorId,
        Long patientId,

        LocalDate whenDate,

//        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime whenTime) {

    public MedicalExaminationReservation toMedicalExaminationReservation(Doctor doctor) {
        return new MedicalExaminationReservation(doctor, whenDate, whenTime);
    }
}
