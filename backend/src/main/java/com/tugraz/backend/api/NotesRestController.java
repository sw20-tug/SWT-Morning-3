package com.tugraz.backend.api;

import com.sun.deploy.net.HttpResponse;
import com.tugraz.backend.db.NotesDatabase;
import com.tugraz.backend.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class NotesRestController {

    @Autowired
    private NotesDatabase notesDb;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        List<Note> mockNotesList = new ArrayList<>();
        mockNotesList.add(new Note("test", "blablalblalba", new Date().getTime()));
        mockNotesList.add(new Note("test2", "asdasdsadassda", new Date().getTime()));
        mockNotesList.add(new Note("test3", "ldfkldsflsdlfkds", new Date().getTime()));
        return mockNotesList;
    }

    @PostMapping("/notes")
    public ResponseEntity<Object> createNote(@RequestBody Note note) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(notesDb.save(note).getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
