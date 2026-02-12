package org.example.medical_examination_booking_app.repository;

import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByUsername(String username);

}
