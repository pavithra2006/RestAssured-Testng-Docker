package com.employee.annontations;

import com.employee.enums.CategoryType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FrameworkAnnotation {
    public String[] AuthorName() default "Karthick";
    public CategoryType[] Category() default CategoryType.REGRESSION;
}
