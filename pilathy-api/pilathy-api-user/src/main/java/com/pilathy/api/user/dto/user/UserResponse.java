package com.pilathy.api.user.dto.user;

import com.pilathy.api.user.config.validator.Phone;
import com.pilathy.domain.rds.domain.account.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ToString
@Getter
@AllArgsConstructor
public class UserResponse {
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String name;

    private LocalDate birthDate;

    @Phone
    private String phone;

    public static UserResponse of(User user) {
        return new UserResponse(user.getEmail(), user.getName(), user.getBirthDate(), user.getPhone());
    }
}
