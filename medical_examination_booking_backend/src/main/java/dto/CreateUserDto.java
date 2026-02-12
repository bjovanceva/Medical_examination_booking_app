package dto;


import org.example.medical_examination_booking_app.model.domain.User;
import org.example.medical_examination_booking_app.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}
