package com.pilathy.api.user.dto.account.user.response;

import com.pilathy.api.user.config.validator.Phone;
import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.service.common.dto.response.AuditingTimeResponse;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse extends AuditingTimeResponse {

    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String name;

    private LocalDate birthDate;

    @Phone
    private String phone;

    @Builder
    private UserResponse(String email, String name, LocalDate birthDate, String phone) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public static UserResponse of(User user) {
        UserResponse response = UserResponse.builder()
                .email(user.getEmail().getEmail())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .phone(user.getPhone())
                .build();
        response.setAuditingTimeByEntity(user);
        return response;
    }
}
