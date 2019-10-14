package com.soen.riskgame.module.core.model;

import lombok.Data;

@Data
public class CountryDTO {
    /**
     * contains country name
     */
    private String name;

    /**
     * contains country id value
     */
    private long id;

    /**
     * contains country X coordinate data while using the GUI
     */
    private String XCoordinate;

    /**
     * contains country Y coordinate data while using the GUI
     */
    private String YCoordinate;

    /**
     * contains the continent data the country is in
     */
    private ContinentDTO continentDTO;

    /**
     * contains the continent Id data the country is in
     */
    private String continentId;

    /**
     * default constructor of the CountryDTO class
     */
    public CountryDTO() {

    }

    /**
     * Parameterized constructor of the CountryDTO class
     *
     * @param id           id is country id reference
     * @param name         name refers to the name if the country
     * @param continentId  continent id refers to the continent id of the continent, the country is present in
     * @param ContinentDTO continentDTO refers to the continentDTo class of the continent, the country is present in
     * @param XCoordinate  X coordinate contains country X coordinate data while using the GUI
     * @param YCoordinate  Y coordinate contains country Y coordinate data while using the GUI
     */
    public CountryDTO(long id, String name, String continentId, ContinentDTO ContinentDTO, String XCoordinate, String YCoordinate) {
        super();
        this.name = name;
        this.id = id;
        this.continentId = continentId;


    }
    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}