package com.soen.riskgame.module.core.command;

public class AddContinent {
	
	String continentName;
	
	int controlValue;

	
	
	public AddContinent() {
		super();
		// TODO Auto-generated constructor stub
	}



	public AddContinent(String continentName, int controlValue) {
		super();
		this.continentName = continentName;
		this.controlValue = controlValue;
	}
	
	public boolean canEqual(Object obj)
	{
		return true;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((continentName == null) ? 0 : continentName.hashCode());
		result = prime * result + controlValue;
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
		AddContinent other = (AddContinent) obj;
		if (continentName == null) {
			if (other.continentName != null)
				return false;
		} else if (!continentName.equals(other.continentName))
			return false;
		if (controlValue != other.controlValue)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "AddContinent [continentName=" + continentName + ", controlValue=" + controlValue + "]";
	}
	
	
	

}
