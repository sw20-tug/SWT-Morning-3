package com.tugraz.backend.api;

import com.tugraz.backend.models.Note;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class NotesRestController {

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        List<Note> mockNotesList = new ArrayList<>();
        mockNotesList.add(new Note("test", "blablalblalba", new Date().getTime()));
        mockNotesList.add(new Note("test2", "asdasdsadassda", new Date().getTime()));
        mockNotesList.add(new Note("test3", "ldfkldsflsdlfkds", new Date().getTime()));
        return mockNotesList;
    }




}
