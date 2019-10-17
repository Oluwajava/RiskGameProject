package com.soen.riskgame.module.core.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Continent {

    private Long id;

    private String name;

    private int controlValue;

    private String color;

    private List<Country> countries;

    public Continent(String name, int controlValue, String color) {
        this.name = name;
        this.controlValue = controlValue;
        this.color = color;
        countries = new ArrayList<>();
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

    public void removeCountry(String name) {
        this.countries = countries.stream().filter(v -> !(v.getName().equalsIgnoreCase(name))).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Continent{" +
                "name='" + name + '\'' +
                ", controlValue=" + controlValue +
                ", color='" + color + '\'' +
                '}';
    }


}
