package com.pilathy.api.user.dto.account.user.request;

import com.pilathy.api.user.config.validator.Phone;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateUserInfoRequest {

    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String name;

    private LocalDate birthDate;

    @Phone
    private String phone;

    @Builder
    private UpdateUserInfoRequest(String email, String name, LocalDate birthDate, String phone) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
    }

}
