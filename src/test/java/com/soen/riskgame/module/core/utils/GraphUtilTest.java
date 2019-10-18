package com.soen.riskgame.module.core.utils;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.soen.riskgame.module.core.model.Country;

import org.junit.*;


public class GraphUtilTest {
	
	private GraphUtil graphUtil;
	
	@Before
	public void setUp()
	{
		graphUtil=new GraphUtil();
	}

	@Test
	public void testIsGraphConnected()
	{
		Set<Country> countries =new HashSet<Country>();
		Country country=new Country();
		List<Country> adjacentCountries=new ArrayList<Country>();
		Country country1=new Country();
		adjacentCountries.add(country1);
		country.setAdjacentCountries(adjacentCountries);
		countries.add(country);
		graphUtil.isGraphConnected(countries);
		
		
	}
	
	@Test
	public void testIsGraphConnected2()
	{
		Set<Country> countries =new HashSet<Country>();
		Country country=new Country();
		Country country2=new  Country();
		Country country1=new Country();
		List<Country> adjacentCountries=new ArrayList<Country>();
		adjacentCountries.add(country1);
		List<Country> adjacentCountries2=new ArrayList<Country>();
		adjacentCountries.add(country);
		country.setAdjacentCountries(adjacentCountries);
		country.setAdjacentCountries(adjacentCountries2);
		countries.add(country);
		countries.add(country1);
		countries.add(country2);
		graphUtil.isGraphConnected(countries);
		
		
	}
	
	
	

}
