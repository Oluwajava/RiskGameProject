package com.soen.riskgame.module.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.soen.riskgame.module.core.interfaces.ContinentAction;
import com.soen.riskgame.module.core.interfaces.CountryAction;
import com.soen.riskgame.module.core.utils.MapDataUtil;
import lombok.Data;

@Data
public class MapData implements ContinentAction, CountryAction {

	private String fileName;

	private HashMap<String, Country> countries;

	private HashMap<String, Continent> continents;

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


	@Override
	public void addContinent(String name, int controlValue) {
		Continent continent = new Continent(name, controlValue, null);
		continents.put(String.valueOf(continents.size() + 1), continent);
	}

	@Override
	public void removeContinent(String name) {
		continents.forEach((key, continent) -> {
			if (continent.getName().equalsIgnoreCase(name)) {
				continents.remove(continent);
			}
		});
	}

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

	@Override
	public void removeCountry(String countryName) {
		Country country = MapDataUtil.findCountryByName(countryName, countries);
		countries.remove(countryName);
		Continent continent = country.getContinent();
		continent.removeCountry(countryName);
		continents.put(String.valueOf(continent.getId()), continent);
	}

	@Override
	public void addNeighbour(String countryName, String neighborCountryName) {

	}

	@Override
	public void removeNeighbour(String countryName, String neighbourCountryName) {

	}


	public String toFile() {
		return null;
	}
}
