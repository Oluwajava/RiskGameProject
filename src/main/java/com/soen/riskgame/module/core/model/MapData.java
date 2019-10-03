package com.soen.riskgame.module.core.model;

import java.util.HashMap;

import com.soen.riskgame.module.core.interfaces.ContinentAction;
import com.soen.riskgame.module.core.interfaces.CountryAction;

public class MapData implements ContinentAction,CountryAction {
	
	
	String fileName;
	
	HashMap<String, Continent> continents=new HashMap<String, Continent>();
	
	HashMap<String, Country> countries=new HashMap<String, Country>();
	
	

	public MapData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void addCountrytoContinent(Country country)
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
	
	public boolean equals(Object obj)
	{
		return true;
	}
	
	public boolean canEqual(Object obj)
	{
		return true;
	}
	

	public int hashCode(Object obj)
	{
		return 0;
	}

	@Override
	public String toString() {
		return "MapData []";
	}
	
	
	

	
}
