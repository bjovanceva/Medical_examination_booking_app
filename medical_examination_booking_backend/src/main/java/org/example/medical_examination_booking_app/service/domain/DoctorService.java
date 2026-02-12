package org.example.medical_examination_booking_app.service.domain;

import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;
import org.example.medical_examination_booking_app.model.domain.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface DoctorService {

    List<Doctor> findAll();

    Optional<Doctor> findById(Long id);

    Optional<Doctor> update(Long id, Doctor doctor);

    void deleteById(Long id);

    Optional<Doctor> save(Doctor doctor);

    Optional<Doctor> findByUsername(String username);

    List<MedicalExaminationReservation> addNewTerm(String username, LocalDate date, LocalTime time);
}

