package com.openclassrooms.historyapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.historyapi.dto.NoteDTO;
import com.openclassrooms.historyapi.exception.NoteAlreadyExistsException;
import com.openclassrooms.historyapi.exception.NoteNotFoundException;
import com.openclassrooms.historyapi.model.Note;
import com.openclassrooms.historyapi.repository.HistoryRepository;
import com.openclassrooms.historyapi.service.HistoryServiceImp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type History controller test.
 */
@WebMvcTest(HistoryController.class)
class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryServiceImp historyServiceImp;

    @MockBean
    private HistoryRepository historyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    private static NoteDTO noteDTO1;
    private static NoteDTO noteDTO2;
    private static List<NoteDTO> noteDTOList;
    private static Note note1;
    private static Note note2;
    private static List<Note> noteList;

    /**
     * Sets up.
     */
    @BeforeAll
    static void setUp() {
        noteDTO1 = new NoteDTO(1, LocalDate.now(), "note1");
        noteDTO2 = new NoteDTO(2, LocalDate.now(), "note2");
        noteDTOList = Arrays.asList(noteDTO1, noteDTO2);
        note1 = new Note(1, LocalDate.now(), "note1");
        note2 = new Note(2, LocalDate.now(), "note2");
        noteList = Arrays.asList(note1, note2);
    }

    /**
     * Sets up before each.
     */
    @BeforeEach
    public void setUpBeforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * Test get note by id.
     *
     * @throws NoteNotFoundException the note not found exception
     * @throws Exception             the exception
     */
    @Test
    void test_getNoteById() throws NoteNotFoundException, Exception {

        when(historyServiceImp.readById(anyString())).thenReturn(noteDTO1);

        MvcResult mvcResult = mockMvc.perform(get("/history/anyString"))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertThat(content).contains("note1");
        verify(historyServiceImp).readById("anyString");

    }

    /**
     * Test get all.
     *
     * @throws Exception the exception
     */
    @Test
    void test_getAll() throws Exception {

        when(historyServiceImp.readAllByPatientId(anyInt())).thenReturn(noteDTOList);

        MvcResult mvcResult = mockMvc.perform(get("/history/list/1"))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        verify(historyServiceImp).readAllByPatientId(1);

    }

    /**
     * Test delete.
     *
     * @throws NoteNotFoundException the note not found exception
     * @throws Exception             the exception
     */
    @Test
    void test_delete() throws NoteNotFoundException, Exception {

        MvcResult mvcResult = mockMvc.perform(get("/history/delete/string"))
                .andExpect(status().isOk())
                .andReturn();

        verify(historyServiceImp).deleteById("string");

    }

    /**
     * Test validate.
     *
     * @throws NoteAlreadyExistsException the note already exists exception
     * @throws Exception                  the exception
     */
    @Test
    void test_validate() throws NoteAlreadyExistsException, Exception {
        NoteDTO noteDTO = new NoteDTO(10, LocalDate.now(), "note");

        MvcResult mvcResult = mockMvc.perform(post("/history/validate/")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(noteDTO)))
                                    .andExpect(status().isCreated())
                                    .andReturn();

    }

    /**
     * Test update.
     *
     * @throws Exception                  the exception
     * @throws NoteAlreadyExistsException the note already exists exception
     */
    @Test
    void test_update() throws Exception, NoteAlreadyExistsException {

        NoteDTO noteDTO = new NoteDTO(10, LocalDate.now(), "note");
        historyServiceImp.create(noteDTO);
        noteDTO.setNote("note updated");

        MvcResult mvcResult = mockMvc.perform(post("/history/update/" + noteDTO.getId())
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .content(objectMapper.writeValueAsString(noteDTO)))
                                     .andExpect(status().isCreated())
                                     .andReturn();
    }

    /**
     * Test get patient id.
     *
     * @throws Exception             the exception
     * @throws NoteNotFoundException the note not found exception
     */
    @Test
    void test_getPatientId() throws Exception, NoteNotFoundException {

        when(historyServiceImp.findPatientIdByNoteId(anyString())).thenReturn(anyInt());

        MvcResult mvcResult = mockMvc.perform(get("/history/patient/anyString"))
                .andExpect(status().isOk())
                .andReturn();

        verify(historyServiceImp).findPatientIdByNoteId("anyString");

    }

}
