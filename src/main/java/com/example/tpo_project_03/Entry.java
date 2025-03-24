package com.example.tpo_project_03;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String polish;
    private String german;
    private String english;

    public Entry() {
    }

    public Entry(String polish, String german, String english) {
        this.id = id;
        this.polish = polish;
        this.german = german;
        this.english = english;
    }

    public String getEnglish() {
        return english;
    }

    public String getGerman() {
        return german;
    }

    public String getPolish() {
        return polish;
    }

    public Long getId() {
        return id;
    }

    public void setPolish(String polish) {
        this.polish = polish;
    }

    public void setGerman(String german) {
        this.german = german;
    }

    public void setEnglish(String english) {
        this.english = english;
    }


    @Override
    public String toString() {
        return "[Polish: " + polish + ", German:" + german + ", English: " + english + "]";
    }
}
