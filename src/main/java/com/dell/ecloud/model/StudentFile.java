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

    public StudentFile(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "StudentFile[id=%d, name = %s]",
                id, name);
    }

}
