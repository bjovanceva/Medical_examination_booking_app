package dto;

import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.Patient;

import java.util.List;
import java.util.stream.Collectors;

public record CreatePatientDto(String name, String surname, String photoUrl, String username, String address, Integer age, Long doctorId) {


    public static CreatePatientDto from(Patient patient) {
        return new CreatePatientDto(
                patient.getName(),
                patient.getSurname(),
                patient.getPhotoUrl(),
                patient.getUsername(),
                patient.getAddress(),
                patient.getAge(),
                patient.getDoctor().getId()
        );
    }

    public static List<CreatePatientDto> from(List<Patient> patients) {
        return patients.stream().map(CreatePatientDto::from).collect(Collectors.toList());
    }

    public Patient toPatient(Doctor doctor) {
        return new Patient(name, surname, photoUrl, username, address, age, doctor);
    }
}
