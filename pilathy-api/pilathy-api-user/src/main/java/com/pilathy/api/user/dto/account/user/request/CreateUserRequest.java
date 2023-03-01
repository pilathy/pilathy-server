package com.pilathy.api.user.dto.account.user.request;

import com.pilathy.api.user.config.validator.Password;
import com.pilathy.api.user.config.validator.Phone;
import com.pilathy.domain.rds.domain.account.user.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserRequest {

    @Email
    private String email;

    @Password
    private String password;

    @NotBlank
    @Size(max = 20)
    private String name;

    private LocalDate birthDate;

    @Phone
    private String phone;

    @Builder
    private CreateUserRequest(String email, String password, String name, LocalDate birthDate, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public User toEntity() {
        return User.of(email, password, name, birthDate, phone);
    }

    public void encodePassword(PasswordEncoder encoder, String rawPassword) {
        this.password = encoder.encode(rawPassword);
    }
}
