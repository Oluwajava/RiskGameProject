package com.soen.riskgame.module.core.model;

import java.util.List;

public class Continent {
	
String name;

long id;

String color;

int controlValue;

List<Country> countries; 

public Continent(String str1, int n, String str2)
{
	
}

public void addCountry(Country country)
{
	
}

public void removeCountry(String str1)
{
	
}

public boolean canEqual()
{
	return true;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((color == null) ? 0 : color.hashCode());
	result = prime * result + controlValue;
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	Continent other = (Continent) obj;
	if (color == null) {
		if (other.color != null)
			return false;
	} else if (!color.equals(other.color))
		return false;
	if (controlValue != other.controlValue)
		return false;
	if (id != other.id)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}

@Override
public String toString() {
	return "Continent [name=" + name + ", id=" + id + ", color=" + color + ", controlValue=" + controlValue + "]";
}





}
