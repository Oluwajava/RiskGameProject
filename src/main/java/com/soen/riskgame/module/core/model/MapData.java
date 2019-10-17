package com.soen.riskgame.module.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.soen.riskgame.module.core.interfaces.ContinentAction;
import com.soen.riskgame.module.core.interfaces.CountryAction;
import com.soen.riskgame.module.core.utils.MapDataUtil;
import lombok.Data;
/**
 * 
 * Class MapData provides the implementation of the methods related to entity Country and Continent
 * implements ContinentAction and CountryAction
 *
 */
@Data
public class MapData implements ContinentAction, CountryAction {

	/**
	 * name of the file
	 */
	private String fileName;

	/**
	 * Map of Countries
	 */
	private HashMap<String, Country> countries;

	/**
	 * Map of the Countries
	 */
	private HashMap<String, Continent> continents;

	/**
	 * Method addCountryToContinent provides the functionality of adding a particular Country to a Continent
	 * @param country object of the entity Country to be added
	 */
	public void addCountryToContinent(Country country) {
		Continent continent = continents.get(country.getContinentId());
		List<Country> countries = continent.getCountries();
		if (countries == null) {
			countries = new ArrayList<>();
		}
		countries.add(country);
		continent.setCountries(countries);
		continents.put(continent.getName(), continent);
	}

	/**
	 * Method addContinent to add a new Continent
	 * @param name name of the continent
	 * @param controlValue control value of the continent
	 */
	@Override
	public void addContinent(String name, int controlValue) {
		Continent continent = new Continent(name, controlValue, null);
		continents.put(String.valueOf(continents.size() + 1), continent);
	}

	/**
	 * Method removeContinent to remove a particular continent
	 * @param name name of the continent
	 */
	@Override
	public void removeContinent(String name) {
		continents.forEach((key, continent) -> {
			if (continent.getName().equalsIgnoreCase(name)) {
				continents.remove(continent);
			}
		});
	}

	/**
	 * Method editContinent provides the functionality of editing a particular continent
	 * @param name name of the continent
	 * @param controlValue control value of the continent
	 */
	@Override
	public void editContinent(String name, int controlValue) {
		continents.forEach((key, continent) -> {
			if (continent.getName().equalsIgnoreCase(name)) {
				continent.setName(name);
				continent.setControlValue(controlValue);
				continents.put(key, continent);
			}
		});
	}

	/**
	 * Method addCountry is to add a Country
	 * @param countryName name of the Country to be added
	 * @param continentName name of the continent in which the country would be added.
	 */
	@Override
	public void addCountry(String countryName, String continentName) {
		Country country = new Country();
		country.setId(Long.valueOf(countries.size()+1));
		country.setName(countryName);
		Continent continent = MapDataUtil.findContinentByName(continentName, continents);
		country.setContinent(continent);
		country.setContinentId(String.valueOf(continent.getId()));
		continent.addCountry(country);
		countries.put(String.valueOf(country.getId()), country);
		continents.put(String.valueOf(continent.getId()), continent);
	}

	/**
     * Method removeCountry is to remove a particular country from a Map
     * @param country name of the country
     */
	@Override
	public void removeCountry(String countryName) {
		Country country = MapDataUtil.findCountryByName(countryName, countries);
		countries.remove(countryName);
		Continent continent = country.getContinent();
		continent.removeCountry(countryName);
		continents.put(String.valueOf(continent.getId()), continent);
	}

	/**
     * Method addNeighbour provides the functionality of adding neighbor of a particular country
     * @param countryName name of the country
     * @param neighborCountryName name of the neighbor country
     */
	@Override
	public void addNeighbour(String countryName, String neighborCountryName) {

	}

	/**
     * Method removeCountry provides the functionality of removing a country
     * @param countryName name of the country
     * @param neighbourCountryName name of the neighbor country
     */
	@Override
	public void removeNeighbour(String countryName, String neighbourCountryName) {

	}


	/**
	 * Method toFile: 
	 * @return string
	 */
	public String toFile() {
		return null;
	}
}
