package org.example.medical_examination_booking_app.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.example.medical_examination_booking_app.model.enumerations.TypesOfDoctors;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;
    private String username;

    private Double examination_price;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MedicalExaminationReservation> reservations;

    @Enumerated(EnumType.STRING)
    private TypesOfDoctors type;

    private String photoUrl;

    public Doctor() {
    }

    public Doctor(String name, String surname, String username, Double examination_price, TypesOfDoctors type, String photoUrl) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.examination_price = examination_price;
        this.type = type;
        this.photoUrl = photoUrl;
        reservations=new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<MedicalExaminationReservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<MedicalExaminationReservation> reservations) {
        this.reservations = reservations;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getExamination_price() {
        return examination_price;
    }

    public String getSurname() {
        return surname;
    }

    public TypesOfDoctors getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setExamination_price(Double examination_price) {
        this.examination_price = examination_price;
    }

    public void setType(TypesOfDoctors type) {
        this.type = type;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
