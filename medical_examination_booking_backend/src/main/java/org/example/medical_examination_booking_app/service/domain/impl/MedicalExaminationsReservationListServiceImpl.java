package org.example.medical_examination_booking_app.service.domain.impl;

import org.example.medical_examination_booking_app.model.domain.*;
import org.example.medical_examination_booking_app.model.enumerations.ReservationListStatus;
import org.example.medical_examination_booking_app.model.exceptions.*;
import org.example.medical_examination_booking_app.repository.DoctorRepository;
import org.example.medical_examination_booking_app.repository.PatientRepository;
import org.example.medical_examination_booking_app.service.domain.UserService;
import org.springframework.stereotype.Service;
import org.example.medical_examination_booking_app.repository.MedicalExaminationReservationRepository;
import org.example.medical_examination_booking_app.repository.MedicalExaminationsReservationListRepository;
import org.example.medical_examination_booking_app.service.domain.MedicalExaminationReservationService;
import org.example.medical_examination_booking_app.service.domain.MedicalExaminationsReservationListService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MedicalExaminationsReservationListServiceImpl implements MedicalExaminationsReservationListService {

    private final DoctorRepository doctorRepository;
    private final MedicalExaminationsReservationListRepository medicalExaminationsReservationListRepository;
    private final MedicalExaminationReservationService medicalExaminationReservationService;
    private final UserService userService;
    private final PatientRepository patientRepository;


    public MedicalExaminationsReservationListServiceImpl(MedicalExaminationsReservationListRepository medicalExaminationsReservationListRepository, MedicalExaminationReservationRepository medicalExaminationReservationRepository, DoctorRepository doctorRepository, MedicalExaminationReservationService medicalExaminationReservationService, UserService userService, PatientRepository patientRepository) {
        this.medicalExaminationsReservationListRepository = medicalExaminationsReservationListRepository;
        this.doctorRepository = doctorRepository;
        this.medicalExaminationReservationService = medicalExaminationReservationService;
        this.userService = userService;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<MedicalExaminationReservation> listAllMedicalExaminationsInReservationList(Long reservationListId) {
        if (medicalExaminationsReservationListRepository.findById(reservationListId).isEmpty())
            throw new ReservationListNotFoundException(reservationListId);
        return medicalExaminationsReservationListRepository.findById(reservationListId).get().getMedicalExaminationReservations();
    }

    @Override
    public Optional<MedicalExaminationsReservationList> getActiveReservationList(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(medicalExaminationsReservationListRepository.findByUserAndStatus(
                user,
                ReservationListStatus.UNPAID
        ).orElseGet(() -> medicalExaminationsReservationListRepository.save(new MedicalExaminationsReservationList(user))));
    }

    @Override
    public Optional<MedicalExaminationsReservationList> addMedicalExaminationToReservationList(String username, Long medicalExaminationId) {

        if (getActiveReservationList(username).isPresent()) {
            System.out.println(getActiveReservationList(username).isPresent());
            MedicalExaminationsReservationList medicalExaminationsReservationList = getActiveReservationList(username).get();

            MedicalExaminationReservation medicalExaminationReservation = medicalExaminationReservationService.findById(medicalExaminationId)
                    .orElseThrow(() -> new MedicalExaminationReservationNotFoundException(medicalExaminationId));

            boolean conflictExists = medicalExaminationsReservationList.getMedicalExaminationReservations()
                    .stream()
                    .anyMatch(i ->
                            i.getDoctor().getId().equals(medicalExaminationReservation.getDoctor().getId()) &&
                                    i.getWhenDate().equals(medicalExaminationReservation.getWhenDate()) &&
                                    i.getWhenTime().equals(medicalExaminationReservation.getWhenTime())
                    );
            if(conflictExists){
                medicalExaminationReservationService.deleteById(medicalExaminationId);
                throw new TheDoctorIsNotFreeException();
            }

            conflictExists = medicalExaminationsReservationList.getMedicalExaminationReservations()
                    .stream()
                    .anyMatch(i ->
                            i.getPatient() != null &&
                            i.getPatient().getId().equals(medicalExaminationReservation.getPatient().getId()) &&
                                    i.getWhenDate().equals(medicalExaminationReservation.getWhenDate()) &&
                                    i.getWhenTime().equals(medicalExaminationReservation.getWhenTime())
                    );
            if(conflictExists){
                medicalExaminationReservationService.deleteById(medicalExaminationId);
                throw new ThePatientIsNotFreeException();
            }



            medicalExaminationsReservationList.getMedicalExaminationReservations().add(medicalExaminationReservation);

            System.out.println("This medical examination doctorId is: ");
            System.out.println(medicalExaminationReservation.getDoctor().getId());
            Doctor doctor = doctorRepository.findById(medicalExaminationReservation.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));

            List<MedicalExaminationReservation> terms = doctor.getReservations();
            Iterator<MedicalExaminationReservation> iterator = terms.iterator();

            while (iterator.hasNext()) {
                MedicalExaminationReservation m = iterator.next();
                if (m.getWhenTime().equals(medicalExaminationReservation.getWhenTime()) &&
                        m.getWhenDate().equals(medicalExaminationReservation.getWhenDate())) {

                    iterator.remove(); // ✅ safe removal
                    doctor.setReservations(terms);
                    doctorRepository.save(doctor);
                    break;
                }
            }

            System.out.println(medicalExaminationsReservationList.getMedicalExaminationReservations().size());
            return Optional.of(medicalExaminationsReservationListRepository.save(medicalExaminationsReservationList));
        }
        return Optional.empty();
    }

    @Override
    public Optional<MedicalExaminationsReservationList> createAndAddReservation(String username, Long doctorId, LocalDate date, LocalTime time) {
        User user = userService.findByUsername(username);
        Patient patient = patientRepository.findByUsername(username).orElseThrow(()-> new PatientWithNameNotFoundException(username));


        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId));


        MedicalExaminationReservation reservation = new MedicalExaminationReservation();
        reservation.setPatient(patient);
        reservation.setDoctor(doctor);
        reservation.setWhenDate(date);
        reservation.setWhenTime(time);

        reservation = medicalExaminationReservationService.save(reservation).get();


        return addMedicalExaminationToReservationList(username, reservation.getId());
    }

    @Override
    public Optional<MedicalExaminationsReservationList> save(String username) {

        MedicalExaminationsReservationList reservationList=getActiveReservationList(username).orElseThrow(()->new ActiveReservationListNotFound());

        reservationList.setStatus(ReservationListStatus.PAID);
        reservationList.getMedicalExaminationReservations().clear();

        return Optional.of(medicalExaminationsReservationListRepository.save(reservationList));
    }


}
