package com.admin.config.aspect;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    public String title() default "";

    public boolean isSaveRequestData() default true;

    public BusinessType businessType() default BusinessType.OTHER;
}
