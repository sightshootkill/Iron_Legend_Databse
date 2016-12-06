package com.example.userdaniel.ironkingdoms.Models;

/**
 * Created by danielabraham on 12/2/16.
 */
public class MeleeWeapons  {

    private String name;
    private String cost;
    private String skill;
    private String attackmod;
    private String pow;
    private String description;
    private String specialnotes;

    public MeleeWeapons(String name, String cost, String skill, String attackmod, String pow, String description, String specialnotes) {
        this.name = name;
        this.cost = cost;
        this.skill = skill;
        this.attackmod = attackmod;
        this.pow = pow;
        this.description = description;
        this.specialnotes = specialnotes;
    }

    public String getName() {
        return this.name;
    }

    public String getCost() {
        return this.cost;
    }

    public String getSkill() {
        return this.skill;
    }

    public String getAttackMod() {
        return this.attackmod;
    }

    public String getPow() {
        return this.pow;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSpecialnotes() {
        return this.specialnotes;
    }

}


