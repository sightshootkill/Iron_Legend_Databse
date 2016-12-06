package com.example.userdaniel.ironkingdoms.Models;

/**
 * Created by UserDaniel on 5/17/16.
 */
public class Archetype  {

    private String name;
    private String description;

    public Archetype(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

}
