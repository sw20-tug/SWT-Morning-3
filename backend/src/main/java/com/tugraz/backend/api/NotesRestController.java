package com.tugraz.backend.api;

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
import java.util.Date;
import java.util.List;

@RestController
public class NotesRestController {

    @Autowired
    private NotesDatabase notesDb;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return notesDb.findAll();
    }

    @PostMapping("/notes")
    public ResponseEntity<Object> createNote(@RequestBody Note note) {
        note.setDateCreated(new Date().getTime());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(notesDb.save(note).getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
