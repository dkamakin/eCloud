package com.dell.ecloud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String date;
    private String university;
    private String category;

    protected StudentFile() {}

    public StudentFile(String name, String date, String university, String category) {
        this.name = name;
        this.date = date;
        this.university = university;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getUniversity() {
        return university;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format(
                "StudentFile[id=%d, name = %s]",
                id, name);
    }

}
