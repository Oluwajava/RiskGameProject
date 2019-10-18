package com.soen.riskgame.module.core.utils;



import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.*;

import com.soen.riskgame.module.core.model.Continent;
import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;

public class MapValidatorTest {
	
	private MapValidator mapValidator,mapValidator2,mapValidator3,mapValidator4,mapValidator5,mapValidator6;

	@Before
	public void setUp() throws Exception {
		mapValidator=new MapValidator("[borders] [continents] [countries] [files] ");
		mapValidator2=new MapValidator("[continents] [countries] [files]");
		mapValidator3=new MapValidator("[borders] [countries] [files] ");
		mapValidator4=new MapValidator("[borders] [continents]  [files] ");
		mapValidator5=new MapValidator("[borders] [continents] [countries]  ");
		

	
	}
	@Test 
	public void testValidate() throws Exception
	{
		
		mapValidator.validate();
	}
	
	@Test
	public void testValidateNoBorder() throws Exception
	{
		try {
		mapValidator2.validate();
		}
		catch(Exception e)
		{
			
		}
	}
	@Test
	public void testValidateNoContinent() throws Exception
	{
		try {
		mapValidator3.validate();
		}
		catch(Exception e)
		{
			
		}
	}
	@Test
	public void testValidateNoCountry() throws Exception
	{
		try {
		mapValidator4.validate();
		}
		catch(Exception e)
		{
			
		}
	}
	@Test
	public void testValidateNoFile() throws Exception
	{
		try {
		mapValidator5.validate();
		}
		catch(Exception e)
		{
			
		}
	}

	@Test
	public void emptyFile()
	{
		try
		{
			mapValidator6=new MapValidator("");

		}
		catch(Exception e)
		{
			
		}
	}
	

	@Test
	public void mapDataNull()
	{
		try
		{
			mapValidator6=new MapValidator(null);

		}
		catch(Exception e)
		{
			
		}
	}
	 
	@Test
	public void testIsGraphConnected()
	{
		MapData data=new MapData();
		HashMap<String, Country> countries=new HashMap<String, Country>();
		Country country=new Country();
		Country country2=new Country();
		List<Country> adjacentCountries=new ArrayList<Country>();
		adjacentCountries.add(country2);
		List<Country> adjacentCountries2=new ArrayList<Country>();
		adjacentCountries2.add(country);
		country2.setAdjacentCountries(adjacentCountries2);
		country.setAdjacentCountries(adjacentCountries);
		countries.put("Country1", country);
		countries.put("Country 2", country2);
		data.setCountries(countries);
		HashMap<String, Continent> continents=new HashMap<String, Continent>();
		Continent continent=new Continent("Asia", 1, "blue");
		List<Country> countriesList=new ArrayList<Country>();
		countriesList.add(country2);
		countriesList.add(country);
		continent.setCountries(countriesList);
		continents.put("Continent 1", continent);
		data.setContinents(continents);
		assertTrue(mapValidator.isGraphConnected(data));
	}
	
	 
	@Test
	public void testIsGraphConnectedFalse()
	{
		MapData data=new MapData();
		HashMap<String, Country> countries=new HashMap<String, Country>();
		Country country=new Country();
		Country country2=new  Country();
		Country country1=new Country();
		List<Country> adjacentCountries=new ArrayList<Country>();
		adjacentCountries.add(country1);
		List<Country> adjacentCountries2=new ArrayList<Country>();
		adjacentCountries2.add(country);
		country.setAdjacentCountries(adjacentCountries2);
		countries.put("Country1", country);
		countries.put("Country 2", country1);
		countries.put("Country 3",country2);
		data.setCountries(countries);
		HashMap<String, Continent> continents=new HashMap<String, Continent>();
		Continent continent=new Continent("Asia", 1, "blue");
		List<Country> countriesList=new ArrayList<Country>();
		countriesList.add(country);
		countriesList.add(country1);
		countriesList.add(country2);
		continent.setCountries(countriesList);
		continents.put("Continent 1", continent);
		data.setContinents(continents);
		mapValidator.isGraphConnected(data);
	}
	
	
	
 

}
