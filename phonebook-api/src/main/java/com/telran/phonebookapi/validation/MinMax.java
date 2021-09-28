package com.telran.phonebookapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinMaxValidator.class)
public @interface MinMax {

    String message() default "{validation.age.default}";

    int minAge() default Integer.MIN_VALUE;

    int maxAge() default Integer.MAX_VALUE;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
