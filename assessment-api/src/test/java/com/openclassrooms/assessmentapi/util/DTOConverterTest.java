package com.openclassrooms.assessmentapi.util;

import com.openclassrooms.assessmentapi.dto.AssessmentDTO;
import com.openclassrooms.assessmentapi.dto.PatientDTO;
import com.openclassrooms.assessmentapi.model.Assessment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Dto converter test.
 */
@SpringBootTest
class DTOConverterTest {

    @Autowired
    private DTOConverter dtoConverter;

    /**
     * Test assessment to assessment dto.
     */
    @Test
    void test_AssessmentToAssessmentDTO() {

        Assessment assessment = new Assessment(new PatientDTO("Test","test", LocalDate.now(),"M"),25,"None");

        AssessmentDTO assessmentDTO = dtoConverter.AssessmentToAssessmentDTO(assessment);

        assertEquals("Test",assessmentDTO.getPatientDTO().getLastName());
        assertEquals("None",assessmentDTO.getDiabetesRiskLevel());

    }
}
