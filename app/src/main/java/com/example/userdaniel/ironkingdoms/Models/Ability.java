package com.example.userdaniel.ironkingdoms.Models;

/**
 * Created by UserDaniel on 5/15/16.
 */
public class Ability  {

    private String name;
    private String prereq;
    private String description;

    public Ability(String name, String prereq, String description) {
        this.name = name;
        this.prereq = prereq;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getPrereq() {
        return this.prereq;
    }

    public String getDescription() {
        return this.description;
    }

}
