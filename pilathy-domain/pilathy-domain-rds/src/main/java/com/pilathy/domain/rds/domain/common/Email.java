package com.pilathy.domain.rds.domain.common;

import com.pilathy.common.exception.ErrorCode;
import com.pilathy.common.exception.model.InvalidException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z\\d._%+-]+@[A-Z\\d.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Column(nullable = false, length = 50)
    private String email;

    private Email(String email) {
        validateFormat(email);
        this.email = email;
    }

    public static Email of(String email) {
        return new Email(email);
    }

    private void validateFormat(String email) {
        if (!EMAIL_REGEX.matcher(email).matches()) {
            throw new InvalidException(String.format("(%s)은 이메일 형식에 어긋납니다", email), ErrorCode.E400_INVALID_EMAIL_FORMAT);
        }
    }
}
