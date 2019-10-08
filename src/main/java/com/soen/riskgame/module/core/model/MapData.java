package com.soen.riskgame.module.core.model;

import java.util.HashMap;

import com.soen.riskgame.module.core.interfaces.ContinentAction;
import com.soen.riskgame.module.core.interfaces.CountryAction;
import lombok.Data;

@Data
public class MapData implements ContinentAction,CountryAction {
	
	String fileName;
	
	HashMap<String, Continent> continents=new HashMap<String, Continent>();
	
	HashMap<String, Country> countries=new HashMap<String, Country>();

	public MapData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void addCountryToContinent(Country country)
	{
		
	}

	@Override
	public void addContinent(String str1, int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeContinent(String str1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editContinent(String str1, int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCountry(String str1, String str2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCountry(String str1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNeighbour(String str1, String str2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNeighbour(String str1, String str2) {
		// TODO Auto-generated method stub
		
	}
	
	public String toFile()
	{
		return null;
	}
	
	
	
	public boolean canEqual(Object obj)
	{
		return true;
	}
	

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((continents == null) ? 0 : continents.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapData other = (MapData) obj;
		if (continents == null) {
			if (other.continents != null)
				return false;
		} else if (!continents.equals(other.continents))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MapData []";
	}
	
	
	

	
}
