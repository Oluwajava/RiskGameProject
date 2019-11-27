package com.soen.riskgame.module.core.model;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.interfaces.*;
import com.soen.riskgame.module.core.utils.MapDataUtil;
import com.soen.riskgame.module.core.utils.RoundRobin;
import lombok.Data;

import java.util.*;
/**
 * Map data class contains details of countries,players,army,reinforce and continent
 *
 * @author Sai Sukruth
 */
@Data
public class MapData extends Observable implements ContinentAction, CountryAction, PlayerAction, ArmyAction, ReinforceAction, FortificationAction {
    /**
     * to check of the game has started
     */
    private boolean gameStarted;
    /**
     * map file name
     */
    private String fileName;
    /**
     * round robin implmentation of players
     */
    private RoundRobin<Player> players;
    /**
     * list of countries
     */
    private HashMap<String, Country> countries;
    /**
     * list of continents
     */
    private HashMap<String, Continent> continents;

    public MapData() {
        countries = new HashMap<>();
        continents = new HashMap<>();
    }

    public void addCountryToContinent(Country country) {
        Continent continent = continents.get(country.getContinentId());
        List<Country> countries = continent.getCountries();
        if (countries == null) {
            countries = new ArrayList<>();
        }
        countries.add(country);
        continent.setCountries(countries);
        continents.put(String.valueOf(continent.getId()), continent);
        updateView();
    }
    /**
     * method to update the map view
     */
    private void updateView() {
        setChanged();
        notifyObservers(this);
    }
    /**
     * method to add continent
     *
     * @param name         name of the country
     * @param controlValue value of the country
     */
    @Override
    public void addContinent(String name, int controlValue) {
        Continent continent = new Continent(name, controlValue, null);
        continents.put(String.valueOf(continents.size() + 1), continent);
        updateView();
    }
    /**
     * method to remove continent
     *
     * @param name name of the country
     */
    @Override
    public void removeContinent(String name) {
        HashMap<String, Continent> newData = new HashMap<>(continents);
        continents.forEach((key, continent) -> {
            if (continent.getName().equalsIgnoreCase(name)) {
                newData.remove(key);
            }
        });
        continents = newData;
        updateView();
    }
    /**
     * method to edit continent
     *
     * @param name         name of the country
     * @param controlValue value of the country
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
        updateView();
    }
    /**
     * method to add country
     *
     * @param countryName   name of the Country to be added
     * @param continentName name of the continent in which the country would be added.
     */
    @Override
    public void addCountry(String countryName, String continentName) {
        Country country = new Country();
        country.setId(Long.valueOf(countries.size() + 1));
        country.setName(countryName);
        Continent continent = MapDataUtil.findContinentByName(continentName, continents);
        addCountry(country, continent);

    }

    /**
     * method to add country to continent
     *
     * @param country   name of the Country to be added
     * @param continent name of the continent in which the country would be added.
     */
    private void addCountry(Country country, Continent continent) {
        if (continent != null) {
            country.setContinent(continent);
            country.setContinentId(String.valueOf(continent.getId()));
            continent.addCountry(country);
            countries.put(String.valueOf(country.getId()), country);
            continents.put(String.valueOf(continent.getId()), continent);
            updateView();
        }
    }
    /**
     * method to add country to continent with coordinates on map
     *
     * @param countryName   name of the country
     * @param continentName name of the continent
     * @param xCoordinate   coordinate of x plane
     * @param yCoordinate   coordinate of y plane
     */
    @Over
    @Override
    public void addCountry(String countryName, String continentName, String xCoordinate, String yCoordinate) {
        Country country = new Country();
        country.setId(Long.valueOf(countries.size() + 1));
        country.setName(countryName);
        country.setXCoordinate(xCoordinate);
        country.setYCoordinate(yCoordinate);
        Continent continent = MapDataUtil.findContinentByName(continentName, continents);
        addCountry(country, continent);
    }
    /**
     * method to remove country
     *
     * @param countryName name of the country
     */
    @Override
    public void removeCountry(String countryName) {
        Country country = MapDataUtil.findCountryByName(countryName, countries);
        if (country != null) {
            countries.remove(String.valueOf(country.getId()));
            Continent continent = country.getContinent();
            continent.removeCountry(countryName);
            continents.put(String.valueOf(continent.getId()), continent);
            updateView();
        }
    }
    /**
     * method to add neighbour
     *
     * @param countryName         name of the country
     * @param neighborCountryName name of the neighbour country
     */
    @Override
    public void addNeighbour(String countryName, String neighborCountryName) {
        Country country = MapDataUtil.findCountryByName(countryName, countries);
        Country neighbourCountry = MapDataUtil.findCountryByName(neighborCountryName, countries);
        country.addNeighbour(neighbourCountry);
        neighbourCountry.addNeighbour(country);
        countries.put(String.valueOf(country.getId()), country);
        countries.put(String.valueOf(neighbourCountry.getId()), neighbourCountry);
        updateView();
    }
    /**
     * method to remove neighbour
     *
     * @param countryName          name of the country
     * @param neighbourCountryName name of the neighbor country
     */
    @Override
    public void removeNeighbour(String countryName, String neighbourCountryName) {
        updateView();
    }

    /**
     * method to update the Map file
     *
     * @return String value mapFileBuilder
     */
    public String toFile() {
        StringBuilder mapFileBuilder = new StringBuilder();
        mapFileBuilder.append("name " + fileName + MapDelimiters.NEXT_LINE_DELIMETER + MapDelimiters.NEXT_LINE_DELIMETER);
        mapFileBuilder.append(MapDelimiters.FILE_DELIMITER + MapDelimiters.NEXT_LINE_DELIMETER);
        //MAP INFOR
        mapFileBuilder.append(MapDelimiters.NEXT_LINE_DELIMETER);
        mapFileBuilder.append(MapDelimiters.CONTINENT_DELIMETER + MapDelimiters.NEXT_LINE_DELIMETER);
        continents.forEach((s, continent) -> {
            mapFileBuilder.append(String.format("%s %d %s", continent.getName(), continent.getControlValue(), continent.getColor()));
            mapFileBuilder.append(MapDelimiters.NEXT_LINE_DELIMETER);
        });
        mapFileBuilder.append(MapDelimiters.NEXT_LINE_DELIMETER);

        mapFileBuilder.append(MapDelimiters.CONTINENT_DELIMETER + MapDelimiters.NEXT_LINE_DELIMETER);
        countries.forEach((s, country) -> {
            mapFileBuilder.append(String.format("%d %s %s %s %s", country.getId(), country.getName(),
                    country.getContinentId(), country.getXCoordinate(), country.getYCoordinate()));
            mapFileBuilder.append(MapDelimiters.NEXT_LINE_DELIMETER);
        });
        mapFileBuilder.append(MapDelimiters.NEXT_LINE_DELIMETER);

        mapFileBuilder.append(MapDelimiters.BORDER_DELIMETER + MapDelimiters.NEXT_LINE_DELIMETER);
        countries.forEach((s, country) -> {
            StringBuilder adjacentCountries = new StringBuilder();
            country.getAdjacentCountries().forEach(c -> adjacentCountries.append(c.getId() + " "));
            mapFileBuilder.append(String.format("%d %s", country.getId(), adjacentCountries));
            mapFileBuilder.append(MapDelimiters.NEXT_LINE_DELIMETER);
        });
        mapFileBuilder.append(MapDelimiters.NEXT_LINE_DELIMETER);


        return mapFileBuilder.toString();
    }

    @Override
    public String toString() {
        return "MapData{" +
                "fileName='" + fileName + '\'' +
                ", countries=" + countries +
                ", continents=" + continents +
                '}';
    }
    /**
     * method to build String of data
     * @return String value of stringBuilder object
     */
    public String buildStringData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==============================\n");
        stringBuilder.append("        Continents            \n");
        stringBuilder.append("==============================\n");
        continents.entrySet().forEach(entry -> {
            stringBuilder.append(entry.getValue().toString() + '\n');
            stringBuilder.append("Countries in Continent\n");
            entry.getValue().getCountries().forEach(country -> {
                stringBuilder.append(country.toString() + '\n');
            });
            stringBuilder.append("\n");
        });


        stringBuilder.append("\n\n");
        stringBuilder.append("==============================");
        stringBuilder.append("        Countries             ");
        stringBuilder.append("==============================");
        countries.entrySet().forEach(entry -> {
            if (gameStarted) {
                stringBuilder.append("Player: " + entry.getValue().getPlayer().getPlayerName() + "\n");
                stringBuilder.append("Number of Armies: " + entry.getValue().getNoOfArmies() + "\n");
            }
            stringBuilder.append(entry.getValue().toString() + '\n');
            stringBuilder.append("Adjacent Countries" + '\n');
            entry.getValue().getAdjacentCountries().forEach(country -> stringBuilder.append(country.toString() + '\n'));
            System.out.println("\n");
        });
        return stringBuilder.toString();
    }
    /**
     * method to add player
     * @param playerName name of the player
     * @return player added
     */
    @Override
    public void addPlayer(String playerName) {
        if (players == null) {
            players = new RoundRobin<>();
        }
        Player player = new Player(playerName);
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        Player.PlayerColor playerColor = new Player.PlayerColor();
        playerColor.setRed(r);
        playerColor.setGreen(g);
        playerColor.setBlue(b);
        player.setPlayerColor(playerColor);
        players.addFirst(player);
    }
    /**
     * method to remove player
     * @param playerName name of the player to be removed
     */
    @Override
    public void removePlayer(String playerName) {
        Player player = new Player(playerName);
        players.deleteNode(player);
    }
    /**
     * method to populate countries using command
     */
    @Override
    public void populateCountries() {
        List<Country> newCountries = new ArrayList<>(countries.values());
        Stack<Country> countryStack = new Stack<>();
        countryStack.addAll(newCountries);
        Player firstPlayer = players.first();
        Player temp = firstPlayer;
        do {
            Country country = countryStack.pop();
            temp.addCountry(country);
            country.setPlayer(temp);
            countries.put(String.valueOf(country.getId()), country);
            players.rotate();
            temp = players.getTail().getElement();
        } while (!countryStack.isEmpty());
        updateView();
    }
    /**
     * method to place army using command for each player
     * @param countryName name of the country
     */
    @Override
    public void placeArmy(String countryName) {
        Player player = players.last();
        if (player.doesCountryBelongToPlayer(countryName)) {
            if (player.getNumOfArmies() > 0) {
                Country country = MapDataUtil.findCountryByName(countryName, countries);
                country.addArmy();
                countries.put(String.valueOf(country.getId()), country);
            }
            players.rotate();
        }
        updateView();
    }
    /**
     * method to place all armies
     */
    @Override
    public void placeAll() {
        Player firstPlayer = players.last();
        Player temp = firstPlayer;
        do {
            for (int i = 0; i < temp.getNumOfArmies(); i++) {
                if (temp.getNumOfArmies() > 0) {
                    int randomCountry = new Random().nextInt(temp.getCountries().size());
                    Country country = temp.getCountries().get(randomCountry);
                    country.addArmy();
                    countries.put(String.valueOf(country.getId()), country);
                }
            }
            players.rotate();
            temp = players.last();
        } while (temp != firstPlayer);
        updateView();
    }
    /**
     * method to list the map
     *
     * @return list of players
     */
    public List<Player> toList() {
        List<Player> playersList = new ArrayList<>();
        Player firstPlayer = players.last();
        Player temp = firstPlayer;
        do {
            playersList.add(temp);
            players.rotate();
            temp = players.last();
        } while (temp != firstPlayer);
        return playersList;
    }
    /**
     * method to fortify country for ech player
     *
     * @param fromCountry name of Country
     * @param toCountry   name of New Country
     * @param num         number of armies
     */
    @Override
    public void fortifyCountry(String fromCountry, String toCountry, int num) {
        Country country = MapDataUtil.findCountryByName(fromCountry, countries);
        Country countryTo = MapDataUtil.findCountryByName(toCountry, countries);
        if (country.isCountryAdjacent(toCountry) && (country.getNoOfArmies() - num >= 1)) {
            country.removeArmy(num);
            countryTo.addArmy(num);
        }
        updateView();
    }

    /**
     * method to fortify none of teh player countries
     */
    @Override
    public void fortifyNone() {
        players.rotate();
        updateView();
    }
    /**
     * method to reinforce country
     *
     * @param countryName name of the country
     * @param number      number of armies
     */
    @Override
    public void reinforceCountry(String countryName, int number) {
        Player player = players.last();
        if (player.getNumOfArmies() > 0) {
            player.decreaseNumOfArmies(number);
            Country country = MapDataUtil.findCountryByName(countryName, countries);
            country.addArmy(number);
            if (player.getNumOfArmies() <= 0) {
                players.rotate();
            }
        } else {
            players.rotate();
        }

        updateView();
    }
}
