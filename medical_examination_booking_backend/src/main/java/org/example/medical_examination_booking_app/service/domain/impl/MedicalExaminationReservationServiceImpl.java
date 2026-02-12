package org.example.medical_examination_booking_app.service.domain.impl;

import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;
import org.springframework.stereotype.Service;
import org.example.medical_examination_booking_app.repository.MedicalExaminationReservationRepository;
import org.example.medical_examination_booking_app.service.domain.DoctorService;
import org.example.medical_examination_booking_app.service.domain.MedicalExaminationReservationService;
import org.example.medical_examination_booking_app.service.domain.PatientService;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalExaminationReservationServiceImpl implements MedicalExaminationReservationService {

    private final MedicalExaminationReservationRepository medicalExaminationReservationRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public MedicalExaminationReservationServiceImpl(MedicalExaminationReservationRepository medicalExaminationReservationRepository, DoctorService doctorService, PatientService patientService) {
        this.medicalExaminationReservationRepository = medicalExaminationReservationRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @Override
    public List<MedicalExaminationReservation> findAll() {
        return medicalExaminationReservationRepository.findAll();
    }

    @Override
    public Optional<MedicalExaminationReservation> findById(Long id) {
        return medicalExaminationReservationRepository.findById(id);
    }

    @Override
    public Optional<MedicalExaminationReservation> update(Long id, MedicalExaminationReservation medicalExaminationReservation) {
        return medicalExaminationReservationRepository.findById(id).map(existingMedicalExaminationReservation -> {
            if (medicalExaminationReservation.getWhenDate() != null) {
                existingMedicalExaminationReservation.setWhenDate(medicalExaminationReservation.getWhenDate());
            }
            if (medicalExaminationReservation.getWhenTime() != null) {
                existingMedicalExaminationReservation.setWhenTime(medicalExaminationReservation.getWhenTime());
            }
            if (medicalExaminationReservation.getDoctor() != null && doctorService.findById(medicalExaminationReservation.getDoctor().getId()).isPresent()) {
                existingMedicalExaminationReservation.setDoctor(doctorService.findById(medicalExaminationReservation.getDoctor().getId()).get());
            }
            if (medicalExaminationReservation.getPatient() != null && patientService.findById(medicalExaminationReservation.getPatient().getId()).isPresent()) {
                existingMedicalExaminationReservation.setPatient(patientService.findById(medicalExaminationReservation.getPatient().getId()).get());
            }

            MedicalExaminationReservation updatedMedicalExaminationReservation = medicalExaminationReservationRepository.save(existingMedicalExaminationReservation);

//            this.refreshMaterializedView();
            //            this.applicationEventPublisher.publishEvent(new ProductCreatedEvent(product));

            return updatedMedicalExaminationReservation;
        });
    }

    @Override
    public Optional<MedicalExaminationReservation> save(MedicalExaminationReservation medicalExaminationReservation) {
        Optional<MedicalExaminationReservation> savedMedicalExaminationReservation = Optional.empty();
        if (medicalExaminationReservation.getDoctor() != null && doctorService.findById(medicalExaminationReservation.getDoctor().getId())
                .isPresent()) {
            savedMedicalExaminationReservation = Optional.of(medicalExaminationReservationRepository.save(new MedicalExaminationReservation(
                    doctorService.findById(medicalExaminationReservation.getDoctor().getId()).get(),
                    medicalExaminationReservation.getWhenDate(),
                    medicalExaminationReservation.getWhenTime()
            )));
//            this.refreshMaterializedView();
            //        this.applicationEventPublisher.publishEvent(new ProductCreatedEvent(product));
        }
        return savedMedicalExaminationReservation;
    }

    @Override
    public void deleteById(Long id) {
         medicalExaminationReservationRepository.deleteById(id);
    }
}
