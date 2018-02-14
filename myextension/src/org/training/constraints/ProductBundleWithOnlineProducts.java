package org.training.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductBundleWithOnlineProductsValidator.class)
@Documented
public @interface ProductBundleWithOnlineProducts {
    String message() default "{org.training.constraints.ProductBundleWithOnlineProductsValidator.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}