package org.example.medical_examination_booking_app.service.application.impl;

import dto.CreatePatientDto;
import dto.DisplayPatientDto;
import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.exceptions.DoctorNotFoundException;
import org.example.medical_examination_booking_app.service.application.PatientApplicationService;
import org.example.medical_examination_booking_app.service.domain.DoctorService;
import org.example.medical_examination_booking_app.service.domain.PatientService;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
public class PatientApplicationServiceImpl implements PatientApplicationService {

    private final PatientService patientService;
    private final DoctorService doctorService;

    public PatientApplicationServiceImpl(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @Override
    public List<DisplayPatientDto> findAll() {
        return DisplayPatientDto.from(patientService.findAll());
    }

    @Override
    public Optional<DisplayPatientDto> findById(Long id) {
        return patientService.findById(id).map(DisplayPatientDto::from);
    }

    @Override
    public Optional<DisplayPatientDto> update(Long id, CreatePatientDto createPatientDto) {
        Doctor doctor=doctorService.findById(createPatientDto.doctorId()).orElseThrow(()->new DoctorNotFoundException(createPatientDto.doctorId()));
        return patientService.update(id, createPatientDto.toPatient(doctor)).map(DisplayPatientDto::from);
    }

    @Override
    public void deleteById(Long id) {
       patientService.deleteById(id);
    }

    @Override
    public Optional<DisplayPatientDto> save(CreatePatientDto createPatientDto) {
        Doctor doctor=doctorService.findById(createPatientDto.doctorId()).orElseThrow(()->new DoctorNotFoundException(createPatientDto.doctorId()));
        return patientService.save(createPatientDto.toPatient(doctor)).map(DisplayPatientDto::from);
    }

    @Override
    public Optional<DisplayPatientDto> findByUsername(String username) {
        return patientService.findByUsername(username).map(DisplayPatientDto::from);
    }
}
