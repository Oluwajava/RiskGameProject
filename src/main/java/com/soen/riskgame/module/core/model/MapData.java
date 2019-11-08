package com.soen.riskgame.module.core.model;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.enums.Phase;
import com.soen.riskgame.module.core.interfaces.*;
import com.soen.riskgame.module.core.utils.Dice;
import com.soen.riskgame.module.core.utils.MapDataUtil;
import com.soen.riskgame.module.core.utils.RoundRobin;
import lombok.Data;

import java.util.*;

/**
 * Map data class contains details of countries,players,army,reinforce and continent
 * @author Sai Sukruth
 */
@Data
public class MapData extends Observable implements ContinentAction, CountryAction, PlayerAction, ArmyAction, ReinforceAction, FortificationAction, AttackAction, CardAction {
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

    private Country attackFromCountry;

    private Country attackToCountry;

    private int attackNumOfDice;

    private int defendNumDice;

    private String attackLog;

    /**
     * Constructor for the class
     */
    public MapData() {
        countries = new HashMap<>();
        continents = new HashMap<>();
    }
    /**
     * method to addCountries to Continents
     * @param country
     */
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
     * @param name name of the country
     * @param controlValue value of the country
     */
    @Override
    public void addContinent(String name, int controlValue) {
        Continent continent = new Continent(name, controlValue, null);
        Long continentId = Long.valueOf(continents.size() + 1);
        continent.setId(continentId);
        continents.put(String.valueOf(continentId), continent);
        updateView();
    }
    /**
     * method to remove continent
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
     * @param name name of the country
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
     * @param countryName name of the Country to be added
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
     * @param country name of the Country to be added
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
     * @param countryName name of the country
     * @param continentName name of the continent
     * @param xCoordinate coordinate of x plane
     * @param yCoordinate coordinate of y plane
     */
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
     * @param countryName name of the country
     * @param neighborCountryName name of the neighbour country
     */
    @Override
    public void addNeighbour(String countryName, String neighborCountryName) {
        Country country = MapDataUtil.findCountryByName(countryName, countries);
        Country neighbourCountry = MapDataUtil.findCountryByName(neighborCountryName, countries);
        country.addNeighbour(neighbourCountry); //adds both ways
        countries.put(String.valueOf(country.getId()), country);
        countries.put(String.valueOf(neighbourCountry.getId()), neighbourCountry);
        updateView();
    }

    /**
     * method to remove neighbour
     * @param countryName name of the country
     * @param neighbourCountryName name of the neighbor country
     */
    @Override
    public void removeNeighbour(String countryName, String neighbourCountryName) {
        updateView();
    }

    /**
     * method to update the Map file
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

    @Override
    public void removePlayer(String playerName) {
        Player player = new Player(playerName);
        players.deleteNode(player);
    }

    @Override
    public void populateCountries() {
        List<Country> newCountries = new ArrayList<>(countries.values());
        Stack<Country> countryStack = new Stack<>();
        countryStack.addAll(newCountries);
        Player temp = players.last();
        do {
            Country country = countryStack.pop();
            if (!temp.isCountriesPopulated()) {
                temp.addCountry(country);
                temp.setPlaceArmiesNo(reinforcementArmy(players.size()));
                country.setPlayer(temp);
                country.addArmy();
                countries.put(String.valueOf(country.getId()), country);
            }
            players.rotate();
            temp = players.getTail().getElement();
        } while (!countryStack.isEmpty());
        for (int i = 0; i < players.size(); i++) {
            Player p = players.last();
            if (!p.isCountriesPopulated()) {
                p.decreasePlaceArmiesNo(p.getCountries().size());
                p.setCountriesPopulated(true);
                players.setElement(p);
            }
            players.rotate();
        }
        updateView();
    }

    private int reinforcementArmy(int size) {
        if (size == 2) {
            return 40;
        } else if (size == 3) {
            return 35;
        } else if (size == 4) {
            return 30;
        } else if (size == 5) {
            return 25;
        } else if (size == 20) {
            return 20;
        } else {
            return 20;
        }

    }

    @Override
    public void placeArmy(String countryName) {
        Player player = players.last();
        if (player.doesCountryBelongToPlayer(countryName)) {
            if (player.getPlaceArmiesNo() > 0) {
                Country country = MapDataUtil.findCountryByName(countryName, countries);
                country.addArmy();
                player.decreasePlaceArmmiesNo();
                players.setElement(player);
                countries.put(String.valueOf(country.getId()), country);
            } else {
                player.setPhase(Phase.REINFORCEMENT);
                players.setElement(player);
            }
            players.rotate();
        }
        updateView();
    }

    @Override
    public void placeAll() {
        Player firstPlayer = players.last();
        Player temp = firstPlayer;
        do {
            int noOfArimes = temp.getPlaceArmiesNo();
            for (int i = 0; i < noOfArimes; i++) {
                if (temp.getPlaceArmiesNo() > 0) {
                    int randomCountry = new Random().nextInt(temp.getCountries().size());
                    Country country = temp.getCountries().get(randomCountry);
                    country.addArmy();
                    temp.decreasePlaceArmmiesNo();
                    countries.put(String.valueOf(country.getId()), country);
                    players.setElement(temp);
                }
            }
            players.rotate();
            temp = players.last();
        } while (temp != firstPlayer);
        for (int i = 0; i < players.size(); i++) {
            Player p = players.last();
            p.setPhase(Phase.REINFORCEMENT);
            players.setElement(p);
            players.rotate();
        }
        updateView();
    }
    /**
     * method to list the map
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
     * @param fromCountry name of Country
     * @param toCountry name of New Country
     * @param num number of armies
     */
    @Override
    public void fortifyCountry(String fromCountry, String toCountry, int num) {
        if (isInPhase(Phase.FORTIFICATION)) {
        Country country = MapDataUtil.findCountryByName(fromCountry, countries);
        Country countryTo = MapDataUtil.findCountryByName(toCountry, countries);
        Player player = players.last();
        if (player.doesCountryBelongToPlayer(fromCountry)&&player.doesCountryBelongToPlayer(toCountry)&&num>0) {
            if (country.isCountryAdjacent(toCountry) && (country.getNoOfArmies() - num >= 1)) {
                country.removeArmy(num);
                countryTo.addArmy(num);
            }
        }
        updateView();
        }
    }
    /**
     * method to fortify none of teh player countries
     */
    @Override
    public void fortifyNone() {
        if (isInPhase(Phase.FORTIFICATION)) {
            Player temp = players.last();
            temp.setPhase(Phase.REINFORCEMENT);
            players.rotate();
            updateView();
        }
    }

    /**
     * method to reinforce country
     * @param countryName name of the country
     * @param number number of armies
     */
    @Override
    public void reinforceCountry(String countryName, int number) {
        if (isInPhase(Phase.REINFORCEMENT)) {
        Player player = players.last();

        if (player.doesCountryBelongToPlayer(countryName)&&number>0&&number<=player.getNumOfArmies()) {
            if (player.getNumOfArmies() > 0) {
                player.decreaseNumOfArmies(number);
                Country country = MapDataUtil.findCountryByName(countryName, countries);
                country.addArmy(number);
                if (player.getNumOfArmies() <= 0) {
                    player.setPhase(Phase.ATTACK);
                    players.setElement(player);
                    players.rotate();
                }
            } else {
                players.rotate();
            }
        }
        updateView();
        }
    }

    @Override
    public void attack(String fromCountry, String toCountry, int numOfDice) {
        updateView();
        attackLog = null;
        if (isInPhase(Phase.ATTACK)) {
            Country country = MapDataUtil.findCountryByName(fromCountry, countries);
            Country countryTo = MapDataUtil.findCountryByName(toCountry, countries);
            if (country.isCountryAdjacent(toCountry) && (country.getNoOfArmies() > numOfDice)) {
                this.attackFromCountry = country;
                this.attackToCountry = countryTo;
                this.attackNumOfDice = numOfDice;
            }
        }
    }

    private boolean isInPhase(Phase phase) {
        Player player = players.last();
        return player.getPhase() == phase;
    }

    @Override
    public void attackMove(int num) {
        if (isInPhase(Phase.ATTACK)) {
            if (attackFromCountry.isCountryAdjacent(attackToCountry.getName()) && (attackFromCountry.getNoOfArmies() - num) > num) {
                attackFromCountry.removeArmy(num);
                attackToCountry.addArmy(num);
            }
            updateView();
        }
    }

    @Override
    public void attackNone() {
        if (isInPhase(Phase.ATTACK)) {
            Player player = players.last();
            player.setPhase(Phase.REINFORCEMENT);
            players.setElement(player);
            players.rotate();
            updateView();
        }
    }

    @Override
    public void defend(int num) {
        this.defendNumDice = num;
        simulateAttack();
    }

    private void simulateAttack() {
        StringBuilder sim = new StringBuilder();
        List<Integer> attackDice = new ArrayList<>();
        List<Integer> defendDice = new ArrayList<>();
        for (int i = 0; i < attackNumOfDice; i++) {
            int no = Dice.roll();
            attackDice.add(no);
            sim.append(attackFromCountry.getPlayer().getPlayerName() + " rolled: " + no + "\n");
        }

        for (int i = 0; i < defendNumDice; i++) {
            int no = Dice.roll();
            defendDice.add(no);
            sim.append(attackToCountry.getPlayer().getPlayerName() + " rolled: " + no + "\n");
        }


        Collections.sort(attackDice, Collections.reverseOrder());
        Collections.sort(defendDice, Collections.reverseOrder());

        attackResult(sim, attackDice, defendDice, 0);

        if (defendDice.size() > 1 & attackDice.size() > 1) {
            attackResult(sim, attackDice, defendDice, 1);
        }

        attackLog = sim.toString();
        updateView();

    }

    private void attackResult(StringBuilder sim, List<Integer> attackDice, List<Integer> defendDice, int index) {
        if (attackDice.get(index) <= defendDice.get(index)) {
            attackFromCountry.removeArmy(1);
            sim.append(attackFromCountry.getPlayer().getPlayerName() + " lost an army" + "\n");
        } else if (attackDice.get(index) > defendDice.get(index)) {
            attackToCountry.removeArmy(1);
            sim.append(attackToCountry.getPlayer().getPlayerName() + " lost an army" + "\n");
            if (attackToCountry.getNoOfArmies() == 0) {
                Player player = players.last();
                player.getCards().getCard();
                attackToCountry.setPlayer(player);
                countries.put(String.valueOf(attackToCountry.getId()), attackToCountry);
            }
        }

    }

    @Override
    public void exchange(int num1, int num2, int num3) {

    }

    @Override
    public void exchangeNone() {

    }
}
