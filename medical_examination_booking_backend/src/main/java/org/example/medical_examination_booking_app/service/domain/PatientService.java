package org.example.medical_examination_booking_app.service.domain;

import org.example.medical_examination_booking_app.model.domain.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    List<Patient> findAll();

    Optional<Patient> findById(Long id);

    Optional<Patient> update(Long id, Patient patient);

    void deleteById(Long id);

    Optional<Patient> save(Patient patient);

    Optional<Patient> findByUsername(String username);
}
