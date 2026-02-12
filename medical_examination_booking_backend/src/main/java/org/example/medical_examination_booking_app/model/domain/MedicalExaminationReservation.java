package org.example.medical_examination_booking_app.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
public class MedicalExaminationReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    private LocalDate whenDate;

    private LocalTime whenTime;

    public MedicalExaminationReservation() {}

    public MedicalExaminationReservation(Doctor doctor, LocalDate whenDate, LocalTime whenTime) {
        this.patient = null;
        this.doctor = doctor;
        this.whenDate = whenDate;
        this.whenTime = whenTime;
    }

    public Long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDate getWhenDate() {
        return whenDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalTime getWhenTime() {
        return whenTime;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setWhenDate(LocalDate whenDate) {
        this.whenDate = whenDate;
    }

    public void setWhenTime(LocalTime whenTime) {
        this.whenTime = whenTime;
    }

    @Override
    public String toString() {
        return "Doctor: " + doctor.getName() +
                "- Time: " + whenDate + " " + whenTime;
    }
}
