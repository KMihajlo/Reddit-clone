package com.mkraguje.redditclone.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordsMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordsMatch {

    String message() default "Password & Confirm Password do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
