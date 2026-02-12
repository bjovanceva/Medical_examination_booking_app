package org.example.medical_examination_booking_app.service.domain;

import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;

import java.util.List;
import java.util.Optional;

public interface MedicalExaminationReservationService {

    List<MedicalExaminationReservation> findAll();

    Optional<MedicalExaminationReservation> findById(Long id);

    Optional<MedicalExaminationReservation> update(Long id, MedicalExaminationReservation medicalExaminationReservation);

    Optional<MedicalExaminationReservation> save(MedicalExaminationReservation medicalExaminationReservation);

    void deleteById(Long id);

//    void refreshMaterializedView();
}
