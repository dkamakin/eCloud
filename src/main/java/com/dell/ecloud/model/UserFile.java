package com.dell.ecloud.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class UserFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter (AccessLevel.NONE)
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

}
