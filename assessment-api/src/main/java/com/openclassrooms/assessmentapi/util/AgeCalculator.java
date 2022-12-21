package com.openclassrooms.assessmentapi.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

/**
 * The type Age calculator.
 */
@Slf4j
@Component
public class AgeCalculator {

    /**
     * Gets age from birthdate.
     *
     * @param birthDate the birthdate
     * @return the age from birthdate
     */
    public int getAgeFromBirthDate(LocalDate birthDate) {

        int age = Period.between(birthDate, LocalDate.now()).getYears();

        if(birthDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("ERROR: Birthdate can't after today's date");

        return age;
    }

}
