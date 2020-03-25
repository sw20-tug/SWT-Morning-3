package com.tugraz.backend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Note {
    @Id
    private String id;

    private String title;
    private String description;
    private Long dateCreated;

    public Note(String title, String description, Long dateCreated) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
    }
}
