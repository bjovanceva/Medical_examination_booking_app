package dto;

import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationsReservationList;
import org.example.medical_examination_booking_app.model.domain.Patient;
import org.example.medical_examination_booking_app.model.enumerations.ReservationListStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationListDto(Long id,
                                 LocalDateTime dateCreated,
                                 DisplayUserDto user,
                                 List<DisplayMedicalExaminationReservationDto> medicalExaminationReservations,
                                 ReservationListStatus status) {

    public static ReservationListDto from(MedicalExaminationsReservationList reservationList) {
        return new ReservationListDto(
                reservationList.getId(),
                reservationList.getDateCreated(),
                DisplayUserDto.from(reservationList.getUser()),
                DisplayMedicalExaminationReservationDto.from(reservationList.getMedicalExaminationReservations()),
                reservationList.getStatus()
        );
    }

    public MedicalExaminationsReservationList toReservationList() {
        return new MedicalExaminationsReservationList(user.toUser());
    }

}
