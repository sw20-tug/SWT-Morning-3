package com.tugraz.backend.api;

import com.tugraz.backend.models.Note;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotesRestController {

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        List<Note> mockNotesList = new ArrayList<>();
        mockNotesList.add(new Note("test", "blablalblalba"));
        mockNotesList.add(new Note("test2", "asdasdsadassda"));
        mockNotesList.add(new Note("test3", "ldfkldsflsdlfkds"));
        return mockNotesList;
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody Note note) {
        // TODO store into databse
        return note;
    }
}
