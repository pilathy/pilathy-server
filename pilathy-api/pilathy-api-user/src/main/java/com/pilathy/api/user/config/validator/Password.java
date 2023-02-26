package com.pilathy.api.user.config.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User 비밀번호 규칙
 * - 최소 8자
 * - 하나 이상 문자와 하나 이상 숫자
 */
@Constraint(validatedBy = {PasswordValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "비밀번호 형식을 확인해주세요.";

    String pattern() default "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
