package com.tugraz.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tugraz.backend.api.NotesRestController;
import com.tugraz.backend.models.Note;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

@SpringBootTest
@AutoConfigureMockMvc
//@ContextConfiguration(classes=BackendApplication.class)
//@DataMongoTest
class NotesRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NotesRestController controller;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void getAllNotes_returns200 () throws Exception {
        mockMvc.perform(get("/notes"))
            .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void getAllNotes_returnesNotesListJson () throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/notes"))
            .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        objectMapper.readValue(contentAsString, Note[].class);
    }

    @Test
    @Order(3)
    void whenNoteExists_getNoteById_returns200 () throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/notes"))
            .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Note[] notes = objectMapper.readValue(contentAsString, Note[].class);

        // select random note
        Random random = new Random();
        String randomNoteId = notes[random.nextInt(notes.length)].getId();

        mockMvc.perform(get("/notes/" + randomNoteId))
            .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void whenNoteExists_getNoteById_returnsNote () throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/notes"))
            .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Note[] notes = objectMapper.readValue(contentAsString, Note[].class);

        // select random note
        Random random = new Random();
        String randomNoteId = notes[random.nextInt(notes.length)].getId();

        ResultActions retrievalResultAction = mockMvc.perform(get("/notes/" + randomNoteId))
            .andExpect(status().isOk());
        MvcResult retrievalResult = retrievalResultAction.andReturn();
        String retrievalContent = retrievalResult.getResponse().getContentAsString();

        objectMapper.readValue(retrievalContent, Note.class);
    }

    @Test
    @Order(5)
    void whenNoteDoesNotExist_getNoteById_returns400 () throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/notes"))
            .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Note[] notes = objectMapper.readValue(contentAsString, Note[].class);
        
        String randomUUID = "";
        while (true) {
            String uuid = UUID.randomUUID().toString();

            boolean doesUUIDExist = Arrays.stream(notes).anyMatch(n -> n.getId().equals(uuid));

            if (!doesUUIDExist) {
                randomUUID = uuid;
                break;
            }
        }

        mockMvc.perform(get("/notes/" + randomUUID))
            .andExpect(status().isBadRequest());
    }

    @Test
    @Order(6)
    void whenNoteIsNull_createNote_returns400 () throws Exception {
        mockMvc.perform(post("/notes").contentType("application/json")
                .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(7)
    void createNote_returns201 () throws Exception {
        Note note = new Note();
        note.setTitle("createNoteTitle");
        note.setDescription("createNoteDescription");
        mockMvc.perform(post("/notes").contentType("application/json")
                .content(objectMapper.writeValueAsString(note)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(8)
    void whenNoteDoesNotExist_updateNote_returns404 () throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/notes"))
            .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Note[] notes = objectMapper.readValue(contentAsString, Note[].class);
        
        String randomUUID = "";
        while (true) {
            String uuid = UUID.randomUUID().toString();

            boolean doesUUIDExist = Arrays.stream(notes).anyMatch(n -> n.getId().equals(uuid));

            if (!doesUUIDExist) {
                randomUUID = uuid;
                break;
            }
        }

        Note inexistentNote = new Note();
        inexistentNote.setId(randomUUID);

        mockMvc.perform(put("/notes/" + randomUUID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inexistentNote)))
            .andExpect(status().isNotFound());
    }

    @Test
    @Order(9)
    void whenNoteExists_updateNote_returns202 () throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/notes"))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Note[] notes = objectMapper.readValue(contentAsString, Note[].class);
        if (notes.length != 0) {
            // get created note
            Note note = Arrays.stream(notes).filter(n -> n.getTitle().equals("createNoteTitle")).findFirst().orElse(null);
            // change note
            if (note != null) {
                note.setTitle("updateNoteTitle");
                note.setDescription("updateNoteDescription");
                note.setCompleted(!note.getCompleted());
                note.setPinned(!note.getPinned());
                List<String> tags = note.getTags();
                tags.add("updateNoteTag");
                note.setTags(tags);
                mockMvc.perform(put("/notes/" + note.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(note)))
                        .andExpect(status().isAccepted());
            }
        }
    }

    @Test
    @Order(10)
    void whenNoteDoesNotExist_deleteNote_returns200 () throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/notes"))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Note[] notes = objectMapper.readValue(contentAsString, Note[].class);

        String randomUUID = "";
        while (true) {
            String uuid = UUID.randomUUID().toString();

            boolean doesUUIDExist = Arrays.stream(notes).anyMatch(n -> n.getId().equals(uuid));

            if (!doesUUIDExist) {
                randomUUID = uuid;
                break;
            }
        }

        mockMvc.perform(delete("/notes/" + randomUUID))
                .andExpect(status().isOk());
    }

    @Test
    @Order(11)
    void deleteNote_returns200 () throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/notes"))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Note[] notes = objectMapper.readValue(contentAsString, Note[].class);
        Note note = Arrays.stream(notes).filter(n -> n.getTitle().equals("updateNoteTitle")).findFirst().orElse(null);
        if (note != null)
            mockMvc.perform(delete("/notes/" + note.getId()))
                    .andExpect(status().isOk());
    }

    @Test
    @Order(12)
    void importNotes_returns200 () throws Exception {
        Note note = new Note();
        note.setId("5bf142459b72e12b2b1b2cc");
        note.setTitle("importNoteTitle");
        note.setDescription("importNoteDescription");
        mockMvc.perform(post("/notes/import").contentType("application/json")
                .content(objectMapper.writeValueAsString(Arrays.asList(note))))
                .andExpect(status().isOk());
    }

    @Test
    @Order(13)
    void importNotes_returns400 () throws Exception {
        Note note = new Note();
        note.setId("5bf142459b72e12b2b1b2cc");
        note.setTitle("importNoteFailTitle");
        note.setDescription("importNoteFailDescription");
        mockMvc.perform(post("/notes/import").contentType("application/json")
                .content(objectMapper.writeValueAsString(Arrays.asList(note))))
                .andExpect(status().isBadRequest());
    }
}
