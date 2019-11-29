package com.soen.riskgame.module.save_game;

import java.util.HashMap;
import java.util.List;

import com.soen.riskgame.module.core.model.Continent;
import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.GameCounter;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;
import com.soen.riskgame.module.core.utils.RoundRobin;

/**
 * Class uses command pattern to abstract SaveGame Command
 *  *  for the Map This class can be called by either the GUI or the command line to perform
 *  *  @see com.soen.riskgame.module.core.interfaces.Command
 *  *  @see <a href="https://refactoring.guru/design-patterns/command"> Command Pattern Tutorial</a>
 *  * @author Hitansh
 */
public class SaveGameBuilder {
	
	/**
     * map file name
     */
    private String fileName;
    /**
     * round robin implmentation of players
     */
    private RoundRobin<Player> players;
    /**
     * variable to save list of countries
     */
    private HashMap<String, Country> countries;
    /**
     * variable to save  list of continents
     */
    private HashMap<String, Continent> continents;
	/**
	 * variable to save  attack from country
	 */
	private Country attackFromCountry;
	/**
	 * variable to save attack to country
	 */
	private Country attackToCountry;
	/**
	 * variable for storing game started
	 */
	private boolean gameStarted;
	/**
	 * variable to save attack Num of Dice
	 */
	private int attackNumOfDice;
	/**
	 * variable to save  defend Number of Dice
	 */
	private int defendNumDice;
	/**
	 * variable to save attack Log
	 */
	private String attackLog;
	/**
	 * variable to save  save conquered country
	 */
	private boolean conqueredCountry;
	/**
	 * variable to save a tournament mode
	 */
	private boolean isTournamentMode;
	/**
	 * variable to save List of Map files
	 */
	private List<String> listOfMapFiles;
	/**
	 *variable to  game counters
	 */
	private List<GameCounter> gameCounters;
	/**
	 * variable to save List of Player strategies
	 */
	private List<String> listOfPlayersStrategies;
	/**
	 * variable to save number of Games
	 */
	private int numberOfGames;
	/**
	 * variable to save  maximum number of turns
	 */
	private int maxNumberOfTurns;
	/**
	 * variable to save  game counter
	 */
	private int gameCounter;
	/**
	 * variable to save turn counter
	 */
	private int turnCounter;
	/**
	 * variable to save new phase
	 */
	private boolean newPhase;
	/**
	 *variable to save  first player name
	 */
	private String firstPlayerName;
	/**
	 * variable to save isRisk
	 */
	private boolean isRisk;
	/**
	 * variable to save End of tournament
	 */
	private boolean isTournamentEnd;
	/**
	 * variable to save Total game count
	 */
	private int totalGameCount;
	/**
	 *variable to save  game over
	 */
	private boolean gameOver;

	/**
	 * function to set fileName
	 * @param fileName
	 * @return saveGameBuilder
	 */
	public SaveGameBuilder setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	/**
	 * function to setPlayers
	 * @param players
	 * @return saveGameBuilder
	 */
	public SaveGameBuilder setPlayers(RoundRobin<Player> players) {
		this.players = players;
		return this;
	}

	/**
	 * function to setCountries
	 * @param countries
	 * @return setCountries
	 */
	public SaveGameBuilder setCountries(HashMap<String, Country> countries) {
		this.countries = countries;
		return this;
	}

	/**
	 * function to setContinent
	 * @param continents
	 * @return setGameBuilder
	 */
	public SaveGameBuilder setContinents(HashMap<String, Continent> continents) {
		this.continents = continents;
		return this;
	}

	/**
	 * set getMapData
	 * @return MapData
	 */
	public MapData getMapData() {

		return new MapData(gameStarted, fileName, players, countries, continents, attackFromCountry, attackToCountry, attackNumOfDice, defendNumDice, attackLog, conqueredCountry, isTournamentMode, listOfMapFiles, gameCounters, listOfPlayersStrategies, numberOfGames, maxNumberOfTurns, gameCounter, turnCounter, newPhase, firstPlayerName, isRisk, isTournamentEnd, totalGameCount, gameOver);
	}

	/**
	 * function used to setAttackfromCountry
	 * @param attackFromCountry
	 * @return saveGameBuilder
	 */
	public SaveGameBuilder setAttackFromCountry(Country attackFromCountry) {
		this.attackFromCountry = attackFromCountry;
		return this;
	}

	/**
	 * function used to setAttack country
	 * @param attackToCountry
	 * @return saveGameBuilder
	 */
	public SaveGameBuilder setAttackToCountry(Country attackToCountry) {
		this.attackToCountry = attackToCountry;
		return this;
	}

	/**
	 * function used to set the start of the game
	 * @param gameStarted
	 * @return setGameStarted
	 */
	public SaveGameBuilder setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
		return this;
	}

	/**
	 * function used to set Attack number of dice
	 * @param attackNumOfDice
	 * @return setAttackNumOfDice
	 */
	public SaveGameBuilder setAttackNumOfDice(int attackNumOfDice) {
		this.attackNumOfDice = attackNumOfDice;
		return this;
	}

	/**
	 * function used to set defend number of dice
	 * @param defendNumDice
	 * @return setDefendNumDice
	 */
	public SaveGameBuilder setDefendNumDice(int defendNumDice) {
		this.defendNumDice = defendNumDice;
		return this;
	}

	/**
	 * function used to set setAttackLog
	 * @param attackLog
	 * @return setAttackLog
	 */
	public SaveGameBuilder setAttackLog(String attackLog) {
		this.attackLog = attackLog;
		return this;
	}

	/**
	 * function used to set conquered country
	 * @param conqueredCountry
	 * @return setconqueredCountry
	 */
	public SaveGameBuilder setConqueredCountry(boolean conqueredCountry) {
		this.conqueredCountry = conqueredCountry;
		return this;
	}

	/**
	 * function used to set tournament Mode
	 * @param isTournamentMode
	 * @return setTournamentMode
	 */
	public SaveGameBuilder setTournamentMode(boolean isTournamentMode) {
		this.isTournamentMode = isTournamentMode;
		return this;
	}

	/**
	 * function used to set list of Map files
	 * @param listOfMapFiles
	 * @return setListOfMapFiles
	 */
	public SaveGameBuilder setListOfMapFiles(List<String> listOfMapFiles) {
		this.listOfMapFiles = listOfMapFiles;
		return this;
	}

	/**
	 * function used to setGameCounters
	 * @param gameCounters
	 * @return setGameCounters
	 */
	public SaveGameBuilder setGameCounters(List<GameCounter> gameCounters) {
		this.gameCounters = gameCounters;
		return this;
	}

	/**
	 * funtion used to set list of players strategies
	 * @param listOfPlayersStrategies
	 * @return setListOfPlayersStrategies
	 */
	public SaveGameBuilder setListOfPlayersStrategies(List<String> listOfPlayersStrategies) {
		this.listOfPlayersStrategies = listOfPlayersStrategies;
		return this;
	}

	/**
	 *function used to save game builder
	 * @param numberOfGames
	 * @return setNumberOfGames
	 */
	public SaveGameBuilder setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
		return this;
	}

	/**
	 * function used to set maximum number of turns
	 * @param maxNumberOfTurns
	 * @return setMaxNumberOfTurns
	 */

	public SaveGameBuilder setMaxNumberOfTurns(int maxNumberOfTurns) {
		this.maxNumberOfTurns = maxNumberOfTurns;
		return this;
	}

	/**
	 * function used to setGameCounter
	 * @param gameCounter
	 * @return setGameCounter
	 */
	public SaveGameBuilder setGameCounter(int gameCounter) {
		this.gameCounter = gameCounter;
		return this;
	}

	/**
	 * fucntion used to set turn counter
	 * @param turnCounter
	 * @return setTurnCounter
	 */
	public SaveGameBuilder setTurnCounter(int turnCounter) {
		this.turnCounter = turnCounter;
		return this;
	}

	/**
	 * function used to set new phase
	 * @param newPhase
	 * @return newPhase
	 */
	public SaveGameBuilder setNewPhase(boolean newPhase) {
		this.newPhase = newPhase;
		return this;
	}

	/**
	 * function used to set first player name
	 * @param firstPlayerName
	 * @return setFirstPlayerName
	 */
	public SaveGameBuilder setFirstPlayerName(String firstPlayerName) {
		this.firstPlayerName = firstPlayerName;
		return this;
	}

	/**
	 * fumction used to set the setRisk
	 * @param isRisk
	 * @return setRisk
	 */
	public SaveGameBuilder setRisk(boolean isRisk) {
		this.isRisk = isRisk;
		return this;
	}
    
}
