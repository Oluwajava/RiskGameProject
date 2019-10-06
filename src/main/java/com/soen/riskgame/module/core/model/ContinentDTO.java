package com.soen.riskgame.module.core.model;

public class ContinentDTO {
	
	String name;
	long id;
	String color;
	Integer controlValue;
	public ContinentDTO(String name, long id, String color, Integer controlValue) {
		super();
		this.name = name;
		this.id = id;
		this.color = color;
		this.controlValue = controlValue;
	}
	public ContinentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ContinentDTO [name=" + name + ", id=" + id + ", color=" + color + ", controlValue=" + controlValue
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((controlValue == null) ? 0 : controlValue.hashCode());
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
		ContinentDTO other = (ContinentDTO) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (controlValue == null) {
			if (other.controlValue != null)
				return false;
		} else if (!controlValue.equals(other.controlValue))
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
	
	 
	public boolean canEqual(Object other) {
        return true;
    }

}
