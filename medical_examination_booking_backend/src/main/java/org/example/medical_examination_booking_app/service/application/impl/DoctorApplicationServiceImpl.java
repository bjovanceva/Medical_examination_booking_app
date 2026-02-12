package org.example.medical_examination_booking_app.service.application.impl;

import dto.CreateDoctorDto;
import dto.DisplayDoctorDto;
import dto.DisplayMedicalExaminationReservationDto;
import org.example.medical_examination_booking_app.service.application.DoctorApplicationService;
import org.example.medical_examination_booking_app.service.domain.DoctorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorApplicationServiceImpl implements DoctorApplicationService {

    private final DoctorService doctorService;

    public DoctorApplicationServiceImpl(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public List<DisplayDoctorDto> findAll() {
        return DisplayDoctorDto.from(doctorService.findAll());
    }

    @Override
    public Optional<DisplayDoctorDto> findById(Long id) {
        return doctorService.findById(id).map(DisplayDoctorDto::from);
    }

    @Override
    public Optional<DisplayDoctorDto> update(Long id, CreateDoctorDto createDoctorDto) {
        return doctorService.update(id, createDoctorDto.toDoctor()).map(DisplayDoctorDto::from);
    }

    @Override
    public void deleteById(Long id) {
        doctorService.deleteById(id);
    }

    @Override
    public Optional<DisplayDoctorDto> save(CreateDoctorDto createDoctorDto) {
        return doctorService.save(createDoctorDto.toDoctor()).map(DisplayDoctorDto::from);
    }

    @Override
    public Optional<DisplayDoctorDto> findByUsername(String username) {
        return doctorService.findByUsername(username).map(DisplayDoctorDto::from);
    }

    @Override
    public List<DisplayMedicalExaminationReservationDto> addNewTerm(String username, LocalDate date, LocalTime time) {
        return DisplayMedicalExaminationReservationDto.from(doctorService.addNewTerm(username, date, time));
    }
}
