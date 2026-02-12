package org.example.medical_examination_booking_app.config.init;

import jakarta.annotation.PostConstruct;
import org.example.medical_examination_booking_app.model.domain.Doctor;
import org.example.medical_examination_booking_app.model.domain.Patient;
import org.example.medical_examination_booking_app.model.domain.User;
import org.example.medical_examination_booking_app.model.enumerations.Role;
import org.example.medical_examination_booking_app.model.enumerations.TypesOfDoctors;
import org.example.medical_examination_booking_app.model.exceptions.UserNotFoundException;
import org.example.medical_examination_booking_app.repository.DoctorRepository;
import org.example.medical_examination_booking_app.repository.PatientRepository;
import org.example.medical_examination_booking_app.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    public DataInitializer(
            DoctorRepository doctorRepository,
            UserRepository userRepository, PatientRepository patientRepository
    ) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @PostConstruct
    public void init() {
        doctorRepository.save(new Doctor("Jovan", "Petrov", "jpetrov", 2000.00, TypesOfDoctors.DERMATOLOGIST, "https://hips.hearstapps.com/hmg-prod/images/portrait-of-a-happy-young-doctor-in-his-clinic-royalty-free-image-1661432441.jpg?crop=0.66698xw:1xh;center,top&resize=640:*"));
        doctorRepository.save(new Doctor("Ivana", "Janeva", "ijaneva", 1500.00, TypesOfDoctors.CARDIOLOGIST, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQY8H3qHLImNgx1ebYAADPaJ2uUy164ywK11A&s"));
        doctorRepository.save(new Doctor("Milan", "Stojanov", "milanstojanov", 850.00, TypesOfDoctors.NEUROLOGIST, "https://static.vecteezy.com/system/resources/thumbnails/026/375/249/small_2x/ai-generative-portrait-of-confident-male-doctor-in-white-coat-and-stethoscope-standing-with-arms-crossed-and-looking-at-camera-photo.jpg"));
        doctorRepository.save(new Doctor("Angel", "Petkov", "apetkov", 1000.00, TypesOfDoctors.ORTHOPAEDIST, "https://i.pinimg.com/736x/9d/85/ef/9d85ef63db3691882dee8b0d2dd08a4c.jpg"));
        doctorRepository.save(new Doctor("Stojan", "Janev", "sjanev", 1500.00, TypesOfDoctors.CARDIOLOGIST, "https://img.freepik.com/premium-photo/doctor-with-white-background-high-quality-ultra-hd_889056-81208.jpg"));
        doctorRepository.save(new Doctor("Bisera", "Stojkova", "bstojkova", 1000.00, TypesOfDoctors.ORTHOPAEDIST, "https://media.istockphoto.com/id/1373258973/photo/successful-senior-doctor-smiling.jpg?s=612x612&w=0&k=20&c=FkX5gYDuYyucTPbyHzoEPtl5Ugr-rjuBIqYVFJNv7uo="));
        doctorRepository.save(new Doctor("Ljupcho", "Angelovski", "langelovski", 2000.00, TypesOfDoctors.PSYCHIATRIST, "https://st3.depositphotos.com/12531762/15013/i/450/depositphotos_150132126-stock-photo-doctor-in-white-coat.jpg"));

        userRepository.save(new User(
                "bjovanceva",
                "bj",
                "Bojana",
                "Jovancheva",
                Role.ROLE_PATIENT
        ));
        userRepository.save(new User(
                "aangelova",
                "aa",
                "Angela",
                "Angelova",
                Role.ROLE_PATIENT
        ));
        userRepository.save(new User(
                "jjaneva",
                "jj",
                "Jana",
                "Janeva",
                Role.ROLE_PATIENT
        ));

        patientRepository.save(new Patient(
                "Bojana",
                "Jovancheva",
                "https://thumbs.dreamstime.com/b/beauty-woman-portrait-girl-beautiful-face-smiling-closeup-happy-perfect-smile-white-teeth-camera-attractive-healthy-76138194.jpg",
                "bjovanceva",
                "Kochani",
                21,
                doctorRepository.findAll().getFirst()
        ));

        //patientRepository.findAll().getFirst().setUser(userRepository.findByUsername("bjovanceva").orElseThrow(()->new UserNotFoundException("bjovanceva")));

        patientRepository.save(new Patient(
                "Jana",
                "Janeva",
                "https://img.freepik.com/premium-photo/woman-portrait-close-up_172134-1092.jpg",
                "jjaneva",
                "Kochani",
                25,
                doctorRepository.findAll().getFirst()
        ));

        patientRepository.save(new Patient(
                "Angela",
                "Angelova",
                "https://cdn.vanderbilt.edu/vu-news/files/20190417210712/Amanda-Clayton.jpg",
                "aangelova",
                "Kochani",
                17,
                doctorRepository.findAll().getFirst()
        ));

//
//        userRepository.save(new User(
//                "patient",
//                passwordEncoder.encode("patient"),
//                "patient",
//                "patient",
//                Role.ROLE_PATIENT
//        ));
    }
}
