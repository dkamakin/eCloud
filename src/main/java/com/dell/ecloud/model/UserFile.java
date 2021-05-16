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
    private String nickname;

    protected UserFile() {}

    public UserFile(String name, String date, String university, String category, String nickname) {
        this.nickname = nickname;
        this.name = name;
        this.date = date;
        this.university = university;
        this.category = category;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    @Override
    public String toString() {
        return String.format(
                "UserFile[id %d, name %s, user nickname %s]",
                id, name, nickname);
    }

}

