package com.tugraz.backend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Note {
    @Id
    private String id;

    private String title;
    private String description;
    private Long dateCreated;
    private Boolean pinned = false;
    private List<String> tags;
    private Boolean completed = false;
    private Long dateCompleted;

    public Note(){

    }

    public Note(String title, String description, Long dateCreated, Boolean pinned, List<String> tags) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.pinned = pinned;
        this.tags = tags;
    }

    public Note(String id, String title, String description, Long dateCreated, Boolean pinned, List<String> tags) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.pinned = pinned;
        this.tags = tags;
    }

    public Note(String id, String title, String description, Long dateCreated, Boolean pinned, List<String> tags, Boolean completed, Long dateCompleted) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.pinned = pinned;
        this.tags = tags;
        this.completed = completed;
        this.dateCompleted = dateCompleted;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Long dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
