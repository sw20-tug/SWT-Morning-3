package com.tugraz.backend;

import com.tugraz.backend.api.NotesRestController;
import com.tugraz.backend.db.NotesDatabase;
import com.tugraz.backend.models.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private NotesDatabase notesDb;

    @Test
    void contextLoads() {
    }

    @Test
    void createDeleteNote() {
        Note testNote = new Note();
        testNote.setId("test");
        assert "test".equals(notesDb.save(testNote).getId());

        notesDb.deleteById("test");
        assert !notesDb.findById("test").isPresent();

    }

    @Test
    void updateNote() {
        // store initial note
        Note note = notesDb.save(new Note("testTitle", "...", Long.valueOf("123"), false, new ArrayList<>()));
        // set new variables
        note.setTitle("testTitle2");
        note.setDescription(".");
        note.setDateCreated(Long.valueOf("1"));
        note.setPinned(true);
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        note.setTags(tags);
        // update note
        Note updatedNote = notesDb.save(note);

        assert updatedNote.getId().equals(note.getId());
        assert updatedNote.getPinned().equals(true);
        assert updatedNote.getDateCreated().equals(Long.valueOf("1"));
        assert updatedNote.getTags().equals(tags);
        assert updatedNote.getTitle().equals("testTitle2");
        assert updatedNote.getDescription().equals(".");

        // delete after test
        notesDb.delete(updatedNote);

    }

    @Test
    void markAsCompletedDB() {
        // store initial note
        Note note = notesDb.save(new Note("testTitle", "...", Long.valueOf("123"), false, new ArrayList<>()));
        assert note.getCompleted().equals(false);

        // updated completed property
        note.setCompleted(true);
        Note updatedNote = notesDb.save(note);
        assert updatedNote.getCompleted().equals(true);

        // delete after test
        notesDb.delete(updatedNote);
    }

    @Test
    void markAsCompleted() {
        // store initial note
        Note note = new Note("testTitle", "...", Long.valueOf("123"), false, new ArrayList<>());
        note.setCompleted(true);
        assert note.getCompleted().equals(true);
    }

}
