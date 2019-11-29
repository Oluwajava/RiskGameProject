package com.soen.riskgame.module.save_game;

import java.util.HashMap;
import java.util.List;

import com.soen.riskgame.module.core.model.Continent;
import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.GameCounter;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;
import com.soen.riskgame.module.core.utils.RoundRobin;

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
     * list of countries
     */
    private HashMap<String, Country> countries;
    /**
     * list of continents
     */
    private HashMap<String, Continent> continents;
	private Country attackFromCountry;
	private Country attackToCountry;
	private boolean gameStarted;
	private int attackNumOfDice;
	private int defendNumDice;
	private String attackLog;
	private boolean conqueredCountry;
	private boolean isTournamentMode;
	private List<String> listOfMapFiles;
	private List<GameCounter> gameCounters;
	private List<String> listOfPlayersStrategies;
	private int numberOfGames;
	private int maxNumberOfTurns;
	private int gameCounter;
	private int turnCounter;
	private boolean newPhase;
	private String firstPlayerName;
	private boolean isRisk;
	private boolean isTournamentEnd;
	private int totalGameCount;
	private boolean gameOver;
    
	public SaveGameBuilder setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
	public SaveGameBuilder setPlayers(RoundRobin<Player> players) {
		this.players = players;
		return this;
	}
	public SaveGameBuilder setCountries(HashMap<String, Country> countries) {
		this.countries = countries;
		return this;
	}
	public SaveGameBuilder setContinents(HashMap<String, Continent> continents) {
		this.continents = continents;
		return this;
	}
	
	public MapData getMapData() {
		
		return new MapData(gameStarted, fileName, players, countries, continents, attackFromCountry, attackToCountry, attackNumOfDice, defendNumDice, attackLog, conqueredCountry, isTournamentMode, listOfMapFiles, gameCounters, listOfPlayersStrategies, numberOfGames, maxNumberOfTurns, gameCounter, turnCounter, newPhase, firstPlayerName, isRisk, isTournamentEnd, totalGameCount, gameOver);
	}
	public SaveGameBuilder setAttackFromCountry(Country attackFromCountry) {
		this.attackFromCountry = attackFromCountry;
		return this;
	}
	public SaveGameBuilder setAttackToCountry(Country attackToCountry) {
		this.attackToCountry = attackToCountry;
		return this;
	}
	public SaveGameBuilder setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
		return this;
	}
	public SaveGameBuilder setAttackNumOfDice(int attackNumOfDice) {
		this.attackNumOfDice = attackNumOfDice;
		return this;
	}
	public SaveGameBuilder setDefendNumDice(int defendNumDice) {
		this.defendNumDice = defendNumDice;
		return this;
	}
	public SaveGameBuilder setAttackLog(String attackLog) {
		this.attackLog = attackLog;
		return this;
	}
	public SaveGameBuilder setConqueredCountry(boolean conqueredCountry) {
		this.conqueredCountry = conqueredCountry;
		return this;
	}
	public SaveGameBuilder setTournamentMode(boolean isTournamentMode) {
		this.isTournamentMode = isTournamentMode;
		return this;
	}
	public SaveGameBuilder setListOfMapFiles(List<String> listOfMapFiles) {
		this.listOfMapFiles = listOfMapFiles;
		return this;
	}
	public SaveGameBuilder setGameCounters(List<GameCounter> gameCounters) {
		this.gameCounters = gameCounters;
		return this;
	}
	public SaveGameBuilder setListOfPlayersStrategies(List<String> listOfPlayersStrategies) {
		this.listOfPlayersStrategies = listOfPlayersStrategies;
		return this;
	}
	public SaveGameBuilder setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
		return this;
	}
	public SaveGameBuilder setMaxNumberOfTurns(int maxNumberOfTurns) {
		this.maxNumberOfTurns = maxNumberOfTurns;
		return this;
	}
	public SaveGameBuilder setGameCounter(int gameCounter) {
		this.gameCounter = gameCounter;
		return this;
	}
	public SaveGameBuilder setTurnCounter(int turnCounter) {
		this.turnCounter = turnCounter;
		return this;
	}
	public SaveGameBuilder setNewPhase(boolean newPhase) {
		this.newPhase = newPhase;
		return this;
	}
	public SaveGameBuilder setFirstPlayerName(String firstPlayerName) {
		this.firstPlayerName = firstPlayerName;
		return this;
	}
	public SaveGameBuilder setRisk(boolean isRisk) {
		this.isRisk = isRisk;
		return this;
	}

    
    
}
