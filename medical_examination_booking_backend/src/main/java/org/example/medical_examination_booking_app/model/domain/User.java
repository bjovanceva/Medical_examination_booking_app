package org.example.medical_examination_booking_app.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.example.medical_examination_booking_app.model.enumerations.Role;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "medical_users")
public class User {

    @Id
    private String username;

    @JsonIgnore
    private String password;

    private String name;

    private String surname;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<MedicalExaminationsReservationList> medicalExaminationsReservationLists;

    public User() {
    }

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

//    public User(UserDetails userDetails) {
//        this.username = userDetails.getUsername();
//        this.password = userDetails.getPassword();
//    }

    public User(String username, String name, String surname, Role role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.role = role;
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

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Role getRole() {
        return role;
    }

    //    public User(String username, String password, String name, String surname) {
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.surname = surname;
//        this.role = Role.ROLE_USER;
//    }

}

