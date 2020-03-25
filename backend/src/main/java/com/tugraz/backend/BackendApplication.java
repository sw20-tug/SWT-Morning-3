package com.tugraz.backend;

import com.tugraz.backend.db.NotesDatabase;
import com.tugraz.backend.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private NotesDatabase notesDb;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        notesDb.deleteAll();

        // save mock data
        notesDb.save(new Note("test", "asdsadasd", new Date().getTime()));
        notesDb.save(new Note("test2", "34343434", new Date().getTime()));
        notesDb.save(new Note("test3", "asdsa314123dasd", new Date().getTime()));


        // fetch all notes
        System.out.println("Notes found with findAll():");
        System.out.println("-------------------------------");
        for (Note n : notesDb.findAll()) {
            System.out.println(n);
        }
        System.out.println();
    }
}
