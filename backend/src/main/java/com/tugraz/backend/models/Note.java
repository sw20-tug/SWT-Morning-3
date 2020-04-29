package com.tugraz.backend.models;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Note {
    @Id
    private String id;

    private String title;
    private String description;
    private Long dateCreated;
    private Boolean pinned;

    public Note(){

    }

    public Note(String title, String description, Long dateCreated, Boolean pinned) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.pinned = pinned;
    }

    public Note(String id, String title, String description, Long dateCreated, Boolean pinned) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.pinned = pinned;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }
}
