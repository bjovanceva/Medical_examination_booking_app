package org.example.medical_examination_booking_app.repository;

import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalExaminationReservationRepository extends JpaRepository<MedicalExaminationReservation, Long> {
}
