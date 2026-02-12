package org.example.medical_examination_booking_app.service.domain.impl;

import org.example.medical_examination_booking_app.model.domain.Patient;
import org.springframework.stereotype.Service;
import org.example.medical_examination_booking_app.repository.PatientRepository;
import org.example.medical_examination_booking_app.service.domain.DoctorService;
import org.example.medical_examination_booking_app.service.domain.PatientService;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final DoctorService doctorService;

    public PatientServiceImpl(PatientRepository patientRepository, DoctorService doctorService) {
        this.patientRepository = patientRepository;
        this.doctorService = doctorService;
    }


    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Optional<Patient> update(Long id, Patient patient) {
        return patientRepository.findById(id).map(existingPatient -> {
            if (patient.getName() != null) {
                existingPatient.setName(patient.getName());
            }
            if (patient.getSurname() != null) {
                existingPatient.setSurname(patient.getSurname());
            }
            if (patient.getUsername() != null) {
                existingPatient.setUsername(patient.getUsername());
            }
            if (patient.getAddress() != null) {
                existingPatient.setAddress(patient.getAddress());
            }
            if (patient.getAge() != null) {
                existingPatient.setAge(patient.getAge());
            }
            if(patient.getDoctor() != null && doctorService.findById(patient.getDoctor().getId()).isPresent()){
                existingPatient.setDoctor(doctorService.findById(patient.getDoctor().getId()).get());
            }
            return patientRepository.save(existingPatient);
        });
    }

    @Override
    public void deleteById(Long id) {
       patientRepository.deleteById(id);
    }

    @Override
    public Optional<Patient> save(Patient patient) {
        Optional<Patient> savedPatient = Optional.empty();
        if (patient.getDoctor() != null && doctorService.findById(patient.getDoctor().getId())
                .isPresent()) {
            savedPatient = Optional.of(patientRepository.save(new Patient(
                    patient.getName(),
                    patient.getSurname(),
                    patient.getPhotoUrl(),
                    patient.getUsername(),
                    patient.getAddress(),
                    patient.getAge(),
                    doctorService.findById(patient.getDoctor().getId()).get()
            )));
//            this.refreshMaterializedView();
            //        this.applicationEventPublisher.publishEvent(new ProductCreatedEvent(product));
        }
        return savedPatient;
    }

    @Override
    public Optional<Patient> findByUsername(String username) {
        return patientRepository.findByUsername(username);
    }
}
