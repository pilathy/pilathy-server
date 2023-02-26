package com.pilathy.api.user.config.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PhoneValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "휴대폰번호 형식을 확인해주세요.";

    String pattern() default "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
