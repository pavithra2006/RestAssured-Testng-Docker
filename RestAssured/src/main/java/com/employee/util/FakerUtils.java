package com.employee.util;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FakerUtils {

    //service layer
    private static final Faker faker= new Faker();

    static int getNumber(int startValue, int endValue){
       return faker.number().numberBetween(startValue, endValue);
    }

    static String getString(){
        return faker.name().fullName();
    }
}
