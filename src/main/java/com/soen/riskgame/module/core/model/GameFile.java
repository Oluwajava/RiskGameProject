package com.soen.riskgame.module.core.model;

public class GameFile {
    private String preview;
    private String map;
    private String pic;
    private String card;

    public GameFile(String preview, String map, String pic, String card) {
        this.preview = preview;
        this.map = map;
        this.pic = pic;
        this.card = card;
    }

    public GameFile(){
       //TODO
    }

    public boolean equals(Object object) {
        //TODO
        return false; //to modify
    }

    public boolean canEqual(Object object) {
        //TODO
        return false; //to modify
    }

    public int hashCode(){
        //TODO
        return 0; //to modify
    }

    public String toString() {
        //TODO
        return null; //to modify
    }
}