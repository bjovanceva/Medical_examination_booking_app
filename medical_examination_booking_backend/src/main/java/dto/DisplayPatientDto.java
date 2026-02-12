package dto;

import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.Patient;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayPatientDto(Long id, String name, String surname, String username, String address, Integer age, Long doctorId, String photoUrl) {

    public static DisplayPatientDto from(Patient patient) {
        return new DisplayPatientDto(patient.getId(), patient.getName(), patient.getSurname(), patient.getUsername(), patient.getAddress(), patient.getAge(), patient.getDoctor().getId(), patient.getPhotoUrl());
    }

    public static List<DisplayPatientDto> from(List<Patient> patients) {
        return patients.stream().map(DisplayPatientDto::from).collect(Collectors.toList());
    }
}
