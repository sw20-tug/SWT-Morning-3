package com.tugraz.backend.db;

import com.tugraz.backend.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotesDatabase extends MongoRepository<Note, String> {

}
