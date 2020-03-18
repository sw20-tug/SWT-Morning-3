package com.tugraz.backend.models;

import lombok.Data;

@Data
public class Note {
    private String id;
    private String title;
    private String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
