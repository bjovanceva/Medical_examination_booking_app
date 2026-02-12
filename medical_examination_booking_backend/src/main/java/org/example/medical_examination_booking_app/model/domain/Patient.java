package org.example.medical_examination_booking_app.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String address;

    private Integer age;

    private String photoUrl;

    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private User user;


    public Patient() {
    }

    public Patient(String name, String surname, String photoUrl, String username, String address, Integer age, Doctor doctor) {
        this.name = name;
        this.surname = surname;
        this.photoUrl = photoUrl;
        this.username = username;
        this.address = address;
        this.age = age;
        this.doctor = doctor;
    }

    public String getPhotoUrl() {
        return photoUrl;
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

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public Integer getAge() {
        return age;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
