package com.openclassrooms.historyapi.repository;

import com.openclassrooms.historyapi.model.Note;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface History repository.
 */
@Repository
public interface HistoryRepository extends MongoRepository<Note, String> {

    /**
     * Find notes by patient id list.
     *
     * @param patientId the patient id
     * @return the list
     */
    List<Note> findNotesByPatientId(Integer patientId);

    /**
     * Find note by id note.
     *
     * @param id the id
     * @return the note
     */
    Note findNoteById(String id);

    /**
     * Exists by note and patient id and date boolean.
     *
     * @param note      the note
     * @param patientId the patient id
     * @param date      the date
     * @return the boolean
     */
    boolean existsByNoteAndPatientIdAndDate(String note, Integer patientId, LocalDate date);

}
