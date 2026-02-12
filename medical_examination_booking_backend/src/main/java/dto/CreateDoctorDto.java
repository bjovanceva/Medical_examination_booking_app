package dto;

import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.enumerations.TypesOfDoctors;

public record CreateDoctorDto(String name, String surname, String username, Double examination_price, TypesOfDoctors type, String photoUrl) {

    public Doctor toDoctor() {
        return new Doctor(name, surname, username, examination_price, type, photoUrl);
    }
}
