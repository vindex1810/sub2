package com.example.pokmon.domain;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class pokemon {

    private String ID;
    private String name;
    private int pokedexNumber;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    @Override
    public String toString(){
        return name.toString();
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("pokedexNumber", pokedexNumber);

        return result;
    }

    public pokemon(){}

}
