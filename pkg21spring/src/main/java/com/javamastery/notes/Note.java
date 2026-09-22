package com.javamastery.notes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Note {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String body;

    public Note() {}

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}
