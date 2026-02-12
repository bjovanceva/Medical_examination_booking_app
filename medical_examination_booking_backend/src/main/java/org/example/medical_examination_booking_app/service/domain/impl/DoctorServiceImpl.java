package org.example.medical_examination_booking_app.service.domain.impl;

import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationReservation;
import org.example.medical_examination_booking_app.model.domain.Patient;
import org.example.medical_examination_booking_app.model.exceptions.DoctorNotFoundException;
import org.springframework.stereotype.Service;
import org.example.medical_examination_booking_app.repository.DoctorRepository;
import org.example.medical_examination_booking_app.service.domain.DoctorService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Doctor ID must not be null");
        }
        return doctorRepository.findById(id);
    }

    @Override
    public Optional<Doctor> update(Long id, Doctor doctor) {
        return doctorRepository.findById(id).map(existingDoctor -> {
            if (doctor.getName() != null) {
                existingDoctor.setName(doctor.getName());
            }
            if (doctor.getSurname() != null) {
                existingDoctor.setSurname(doctor.getSurname());
            }
            if (doctor.getUsername() != null) {
                existingDoctor.setUsername(doctor.getUsername());
            }
            if (doctor.getType() != null) {
                existingDoctor.setType(doctor.getType());
            }
            if (doctor.getExamination_price() != null) {
                existingDoctor.setExamination_price(doctor.getExamination_price());
            }
            return doctorRepository.save(existingDoctor);
        });
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Optional<Doctor> save(Doctor doctor) {

        return Optional.of(doctorRepository.save(new Doctor(
                doctor.getName(),
                doctor.getSurname(),
                doctor.getUsername(),
                doctor.getExamination_price(),
                doctor.getType(),
                doctor.getPhotoUrl())));
    }

    @Override
    public Optional<Doctor> findByUsername(String username) {
        return doctorRepository.findByUsername(username);
    }

    @Override
    public List<MedicalExaminationReservation> addNewTerm(String username, LocalDate date, LocalTime time) {
        Doctor doctor = doctorRepository.findByUsername(username).orElseThrow(()->new DoctorNotFoundException(null));

        List<MedicalExaminationReservation> terms = doctor.getReservations();
        MedicalExaminationReservation newTerm = new MedicalExaminationReservation(doctor, date, time);

        terms.add(newTerm);
        doctor.setReservations(terms);
        doctorRepository.save(doctor);

        return terms;
    }
}
