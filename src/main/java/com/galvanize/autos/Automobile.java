package com.galvanize.autos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "Automobiles") // renames table from "automobile" to "automobiles". Equivalent annotation also available for renaming columns
@JsonInclude(JsonInclude.Include.NON_NULL) // excludes all null fields
public class Automobile {

    // column annotations to keep in mind: @Column to rename, @JSONFormat to enforce patterns in e.g. date
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key; auto-increment
    private Long id;
    private String color;
    private String make;
    private String model;
    private String owner;
    private String vin;
    private int year;

    public Automobile(){}

    public Automobile(String color, String make, int year, String model, String owner, String vin){
        this.color = color;
        this.make = make;
        this.model = model;
        this.owner = owner;
        this.vin = vin;
        this.year = year;
    }

    public Automobile(String make, int year, String model, String owner, String vin){
        this.make = make;
        this.model = model;
        this.owner = owner;
        this.vin = vin;
        this.year = year;
    }

    public Automobile(String make, int year, String model, String vin){
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.year = year;
    }

    public Automobile(String color, String make, int year, String model, String vin){
        this.color = color;
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.year = year;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
