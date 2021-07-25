package com.dell.ecloud.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String date;

    @Getter
    @Setter
    private String university;

    @Getter
    @Setter
    private String category;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private long userId;

    @Getter
    @Setter
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
