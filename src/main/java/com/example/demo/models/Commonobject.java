package com.example.demo.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Commonobject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String desciption;
    private String details;


    public Commonobject(int id, String desciption, String details) {
        this.id = id;
        this.desciption = desciption;
        this.details = details;
    }

    public Commonobject() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesciption() {
        return this.desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    @Override
    public String toString(){
        return "Commonobject(id,desciption,details)";

    }

}
