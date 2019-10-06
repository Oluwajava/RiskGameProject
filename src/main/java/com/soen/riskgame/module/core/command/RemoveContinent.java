package com.soen.riskgame.module.core.command;

public class RemoveContinent {
    String continentName; //to add modifier

    public RemoveContinent() {
        super();
        // TODO Auto-generated constructor stub
    }

    public RemoveContinent(String continentName) {
        super();
        this.continentName = continentName;
    }

    public boolean canEqual(Object object) {
        // TODO Confirm
        return true;
    }

    @Override
    public int hashCode() {
        // TODO add code
        return 0; //to modify
    }

    @Override
    public boolean equals(Object obj) {
        // TODO add code
        return false; //to modify
    }

    @Override
    public String toString() {
        return "RemoveContinent [continentName=" + continentName + "]";
    }

}
