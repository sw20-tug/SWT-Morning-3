package com.tugraz.backend;

import com.tugraz.backend.api.NotesRestController;
import com.tugraz.backend.db.NotesDatabase;
import com.tugraz.backend.models.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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

}
