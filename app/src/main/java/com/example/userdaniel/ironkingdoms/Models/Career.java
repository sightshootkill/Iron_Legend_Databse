package com.example.userdaniel.ironkingdoms.Models;

/**
 * Created by UserDaniel on 6/6/16.
 */
public class Career  {

    private String name;
    private String prereq;
    private String startingability;
    private String startingconnection;
    private String startingmilitary;
    private String startingoccupational;
    private String startingspells;
    private String startingspecial;
    private String startingassets;
    private String potentialability;
    private String potentialconnection;
    private String potentialmilitary;
    private String potentialoccupational;

    public Career(String name, String prereq, String startingability, String startingconnection, String startingmilitary, String startingoccupational
    , String startingspells, String startingspecial, String startingassets, String potentialability, String potentialconnection, String potentialmilitary, String potentialoccupational) {
        this.name = name;
        this.prereq = prereq;
        this.startingability = startingability;
        this.startingconnection = startingconnection;
        this.startingmilitary = startingmilitary;
        this.startingoccupational = startingoccupational;
        this.startingspells = startingspells;
        this.startingspecial = startingspecial;
        this.startingassets = startingassets;
        this.potentialability = potentialability;
        this.potentialconnection = potentialconnection;
        this.potentialmilitary = potentialmilitary;
        this.potentialoccupational = potentialoccupational;
    }

    public String getName() {
        return this.name;
    }

    public String getPrereq() {
        return this.prereq;
    }

    public String getStartingability() {
        return this.startingability;
    }

    public String getStartingconnection() {
        return this.startingconnection;
    }

    public String getStartingmilitary() {
        return this.startingmilitary;
    }

    public String getStartingoccupational() {
        return this.startingoccupational;
    }

    public String getStartingspells() {
        return this.startingspells;
    }

    public String getStartingspecial() {
        return this.startingspecial;
    }

    public String getStartingassets() {
        return this.startingassets;
    }

    public String getPotentialability() {
        return this.potentialability;
    }

    public String getPotentialconnection() {
        return this.potentialconnection;
    }

    public String getPotentialmilitary() {
        return this.potentialmilitary;
    }

    public String getPotentialoccupational() {
        return this.potentialoccupational;
    }

}

