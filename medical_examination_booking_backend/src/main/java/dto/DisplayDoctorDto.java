package dto;

import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;
import org.example.medical_examination_booking_app.model.enumerations.TypesOfDoctors;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayDoctorDto(Long id, String name, String surname, String username, Double examination_price, TypesOfDoctors type, String photoUrl, List<MedicalExaminationReservation> reservations) {

    public static DisplayDoctorDto from(Doctor doctor) {
        return new DisplayDoctorDto(doctor.getId(), doctor.getName(), doctor.getSurname(), doctor.getUsername(), doctor.getExamination_price(), doctor.getType(), doctor.getPhotoUrl(), doctor.getReservations());
    }

    public static List<DisplayDoctorDto> from(List<Doctor> doctors) {
        return doctors.stream().map(DisplayDoctorDto::from).collect(Collectors.toList());
    }

}
