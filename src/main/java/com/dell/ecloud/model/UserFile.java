package com.dell.ecloud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String date;
    private String university;
    private String category;
    private String description;
    private long userId;
    private String fileName;

    protected UserFile() {
    }

    public UserFile(String name, String date, String university, String category, String description,
                    long userId, String fileName) {
        this.userId = userId;
        this.name = name;
        this.date = date;
        this.university = university;
        this.description = description;
        this.category = category;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getUserId() {
        return userId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public long getId() {
        return id;
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

}
