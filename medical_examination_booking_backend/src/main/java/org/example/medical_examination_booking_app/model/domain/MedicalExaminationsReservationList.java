package org.example.medical_examination_booking_app.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.example.medical_examination_booking_app.model.enumerations.ReservationListStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MedicalExaminationsReservationList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalExaminationReservation> medicalExaminationReservations;

    @Enumerated(EnumType.STRING)
    private ReservationListStatus status;

    public MedicalExaminationsReservationList() {
    }

    public MedicalExaminationsReservationList(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.medicalExaminationReservations = new ArrayList<>();
        this.status = ReservationListStatus.UNPAID;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }

    public List<MedicalExaminationReservation> getMedicalExaminationReservations() {
        return medicalExaminationReservations;
    }

    public ReservationListStatus getStatus() {
        return status;
    }

    public void setMedicalExaminationReservations(List<MedicalExaminationReservation> medicalExaminationReservations) {
        this.medicalExaminationReservations = medicalExaminationReservations;
    }

    public void setStatus(ReservationListStatus status) {
        this.status = status;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
