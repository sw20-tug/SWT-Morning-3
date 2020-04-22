package com.tugraz.backend.api;

import com.tugraz.backend.db.NotesDatabase;
import com.tugraz.backend.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@Valid @RequestBody Note note, @PathVariable String id) {
        // check if note with given id exists in database
        if (!notesDb.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Note currentNote = notesDb.findById(id).get();
        if(note.getDescription() != null) {
            currentNote.setDescription(note.getDescription());
        }
        if(note.getTitle() != null) {
            currentNote.setTitle(note.getTitle());
        }
        // store in database, if id exists then it updates current
        notesDb.save(currentNote);
        return new ResponseEntity<>(currentNote, HttpStatus.ACCEPTED);
    }
}
