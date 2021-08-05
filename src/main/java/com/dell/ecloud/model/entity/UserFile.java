package com.dell.ecloud.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "files", schema = "public")
public class UserFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String name;
    private String date;
    private String university;
    private String category;
    private String description;
    private long userid;
    private String filename;

    protected UserFile() {
    }

    public UserFile(String name, String date, String university, String category, String description,
                    long userId, String fileName) {
        this.userid = userId;
        this.name = name;
        this.date = date;
        this.university = university;
        this.description = description;
        this.category = category;
        this.filename = fileName;
    }

}
