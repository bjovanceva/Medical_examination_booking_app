package org.example.medical_examination_booking_app.repository;

import org.example.medical_examination_booking_app.model.domain.MedicalExaminationsReservationList;
import org.example.medical_examination_booking_app.model.domain.User;
import org.example.medical_examination_booking_app.model.enumerations.ReservationListStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalExaminationsReservationListRepository extends JpaRepository<MedicalExaminationsReservationList, Long> {

    Optional<MedicalExaminationsReservationList> findByUserAndStatus(User user, ReservationListStatus status);
}
