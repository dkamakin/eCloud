package com.dell.ecloud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String password;
    private String nickname;
    private String university;
    private long karma;

    protected Student() {}


    public Student(String email, String password, String nickname, String university, long karma) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.university = university;
        this.karma = karma;
    }

    public String getUniversity() {
        return university;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setKarma(long karma) {
        this.karma = karma;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
