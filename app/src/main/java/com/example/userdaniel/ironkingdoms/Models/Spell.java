package com.example.userdaniel.ironkingdoms.Models;

/**
 * Created by UserDaniel on 5/17/16.
 */
public class Spell  {

    private String name;
    private String cost;
    private String rng;
    private String aoe;
    private String pow;
    private String upkeep;
    private String offensive;
    private String description;

    public Spell(String name, String cost, String rng, String aoe, String pow, String upkeep, String offensive, String description) {
        this.name = name;
        this.cost = cost;
        this.rng = rng;
        this.aoe = aoe;
        this.pow = pow;
        this.upkeep = upkeep;
        this.offensive = offensive;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getCost() {
        return this.cost;
    }

    public String getRng() {
        return this.rng;
    }

      public String getAoe() {
        return this.aoe;
    }

    public String getPow() {
        return this.pow;
    }

    public String getUpkeep() {
        return this.upkeep;
    }

      public String getOffensive() {
        return this.offensive;
    }

    public String getDescription() {
        return this.description;
    }

}
