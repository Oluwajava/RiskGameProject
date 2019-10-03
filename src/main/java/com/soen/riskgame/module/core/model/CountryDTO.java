package com.soen.riskgame.module.core.model;

public class CountryDTO {
    protected String name;
    protected long id;
    String XCoordinate;
    String YCoordinate;
    //ContinentDTO continentDTO;
    String continentId;

    public CountryDTO() {

    }

    public CountryDTO(long id, String name, String continentId, ContinentDTO, String XCoordinate, String YCoordinate) {
        super();
        this.name = name;
        this.id = id;

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
        return super.equals(obj);
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
        //return super.hashCode();
    }

    @Override
    public String toString() {
        return "CountryDTO [name=" + name + ", id=" + id + ", color=" + color + ", controlValue=" + controlValue
                + "]";
       // return super.toString();
    }

    private boolean canEqual(Object obj) {

    }

    public boolean canEqual(Object other) {
        return true;
    }
}