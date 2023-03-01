package com.pilathy.api.user.dto.account.user.request;

import com.pilathy.api.user.config.validator.Password;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateUserPasswordRequest {

    @Password
    private String password;

    @Builder
    private UpdateUserPasswordRequest(String password) {
        this.password = password;
    }

}
