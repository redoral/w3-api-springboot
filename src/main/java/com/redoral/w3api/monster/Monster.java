package com.redoral.w3api.monster;

import javax.persistence.*;

@Entity
@Table
public class Monster {
    // Sequence
    @Id
    @SequenceGenerator(
            name = "monster_sequence",
            sequenceName = "monster_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "monster_sequence"
    )

    // Variables
    public Long id;
    public String img;
    public String name;
    public String type;
    public String[] susceptibility;
    public String[] loot;

    // Constructors
    public Monster(){ }

    public Monster(Long id, String img, String name, String type, String[] susceptibility, String[] loot){
        this.id = id;
        this.img = img;
        this.name = name;
        this.type = type;
        this.susceptibility = susceptibility;
        this.loot = loot;
    }

    public Monster(String img, String name, String type, String[] susceptibility, String[] loot){
        this.img = img;
        this.name = name;
        this.type = type;
        this.susceptibility = susceptibility;
        this.loot = loot;
    }


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getSusceptibility() {
        return susceptibility;
    }

    public void setSusceptibility(String[] susceptibility) {
        this.susceptibility = susceptibility;
    }

    public String[] getLoot() {
        return loot;
    }

    public void setLoot(String[] loot) {
        this.loot = loot;
    }
}
