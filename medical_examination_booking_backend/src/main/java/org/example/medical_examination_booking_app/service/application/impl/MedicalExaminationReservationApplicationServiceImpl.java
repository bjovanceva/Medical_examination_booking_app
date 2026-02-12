package org.example.medical_examination_booking_app.service.application.impl;

import dto.CreateMedicalExaminationReservationDto;
import dto.DisplayMedicalExaminationReservationDto;
import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.Patient;
import org.example.medical_examination_booking_app.model.exceptions.DoctorNotFoundException;
import org.example.medical_examination_booking_app.model.exceptions.PatientNotFoundException;
import org.example.medical_examination_booking_app.service.application.MedicalExaminationReservationApplicationService;
import org.example.medical_examination_booking_app.service.domain.DoctorService;
import org.example.medical_examination_booking_app.service.domain.MedicalExaminationReservationService;
import org.example.medical_examination_booking_app.service.domain.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalExaminationReservationApplicationServiceImpl implements MedicalExaminationReservationApplicationService {

    private final MedicalExaminationReservationService medicalExaminationReservationService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public MedicalExaminationReservationApplicationServiceImpl(MedicalExaminationReservationService medicalExaminationReservationService, DoctorService doctorService, PatientService patientService) {
        this.medicalExaminationReservationService = medicalExaminationReservationService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @Override
    public List<DisplayMedicalExaminationReservationDto> findAll() {
        return DisplayMedicalExaminationReservationDto.from(medicalExaminationReservationService.findAll());
    }

    @Override
    public Optional<DisplayMedicalExaminationReservationDto> findById(Long id) {
        return medicalExaminationReservationService.findById(id).map(DisplayMedicalExaminationReservationDto::from);
    }

    @Override
    public Optional<DisplayMedicalExaminationReservationDto> update(Long id, CreateMedicalExaminationReservationDto createMedicalExaminationReservationDto) {
        Doctor doctor=doctorService.findById(createMedicalExaminationReservationDto.doctorId()).orElseThrow(()->new DoctorNotFoundException(createMedicalExaminationReservationDto.doctorId()));
        Patient patient=patientService.findById(createMedicalExaminationReservationDto.patientId()).orElseThrow(()->new PatientNotFoundException(createMedicalExaminationReservationDto.patientId()));

        return medicalExaminationReservationService.update(id, createMedicalExaminationReservationDto.toMedicalExaminationReservation(doctor)).map(DisplayMedicalExaminationReservationDto::from);
    }

    @Override
    public Optional<DisplayMedicalExaminationReservationDto> save(CreateMedicalExaminationReservationDto createMedicalExaminationReservationDto) {
        Doctor doctor=doctorService.findById(createMedicalExaminationReservationDto.doctorId()).orElseThrow(()->new DoctorNotFoundException(createMedicalExaminationReservationDto.doctorId()));
        Patient patient=patientService.findById(createMedicalExaminationReservationDto.patientId()).orElseThrow(()->new PatientNotFoundException(createMedicalExaminationReservationDto.patientId()));

        return medicalExaminationReservationService.save(createMedicalExaminationReservationDto.toMedicalExaminationReservation(doctor)).map(DisplayMedicalExaminationReservationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        medicalExaminationReservationService.deleteById(id);
    }
}
