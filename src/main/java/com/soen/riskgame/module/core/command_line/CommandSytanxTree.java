package com.soen.riskgame.module.core.command_line;

import com.soen.riskgame.module.core.command.*;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.interfaces.PlayerCommandListener;
import com.soen.riskgame.module.core.model.MapData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class CommandSyntaxTree is a class which provides the functionality of processing various commands.
 * @author hitansh
 *
 */
@Data
public class CommandSytanxTree {

	/**
	 * list of tokens
	 */
    private List<Token> tokens;

    /**
     * List of commands
     */
    private List<Command> commands;

    /**
     * object of MapData
     */
    private MapData mapData;

    /**
     * Listener for ShowMapCommand
     */
    private ShowMapCommand.ShowMapListener showMapListener;

    /**
     * Listener for validate Command
     */
    private ValidateCommand.ValidateMapListener validateMapListener;

    /**
     * listener for load map command
     */
    private LoadMapCommand.LoadMapListener loadMapListener;

    /**
     * listener for edit map commmand
     */
    private EditMapCommand.EditMapListener editMapListener;

    /**
     * Listener for player command
     */
    private PlayerCommandListener playerCommandListener;

    /**
     * parameterized constructor
     * @param mapData mapdata
     * @param tokens list of tokens
     */
    public CommandSytanxTree(MapData mapData, List<Token> tokens) {
        this.tokens = tokens;
        this.mapData = mapData;
        commands = new ArrayList<>();
    }

    /**
     * Setter for ShowMapListener
     * @param showMapListener showMapListener
     */
    public void setShowMapListener(ShowMapCommand.ShowMapListener showMapListener) {
        this.showMapListener = showMapListener;
    }

    /**
     * Method to process various commands
     */
    public void processCommand() {
        TokenType currentCommand = null;
        boolean checkNext = true;
        int i = 0;
        while (checkNext && i <= tokens.size()) {
            if (tokens.get(i).tokenType.getCommandType() == CommandType.COMMAND) {
                currentCommand = tokens.get(i).tokenType;
                i++;
                if (currentCommand == TokenType.SHOWMAP) {
                    ShowMapCommand showMapCommand = new ShowMapCommand(mapData, showMapListener);
                    commands.add(showMapCommand);
                    break;
                } else if (currentCommand == TokenType.VALIDATEMAP) {
                    ValidateCommand validateCommand = new ValidateCommand(mapData, validateMapListener);
                    commands.add(validateCommand);
                    break;
                } else if (currentCommand == TokenType.POPULATE_COUNTRIES) {
                    PopulateCountriesCommand populateCountriesCommand = new PopulateCountriesCommand(mapData);
                    commands.add(populateCountriesCommand);
                    break;
                } else if (currentCommand == TokenType.PLACE_ALL) {
                    PlaceAllArmyCommand placeAllArmyCommand = new PlaceAllArmyCommand(mapData);
                    commands.add(placeAllArmyCommand);
                    break;
                }
            }
            if (currentCommand != null && i > 0) {
                TokenType currentTokenType = tokens.get(i).tokenType;
                if (currentCommand == TokenType.EDIT_CONTINENT) {
                    if (currentTokenType == TokenType.ADD) {
                        AddContinentCommand addCountryCommand = processTokenToAddContinentCommand(i);
                        commands.add(addCountryCommand);
                        i += 2;
                    } else if (currentTokenType == TokenType.REMOVE) {
                        RemoveContinentCommand removeContinentCommand = processTokenToRemoveContinentCommand(i);
                        commands.add(removeContinentCommand);
                        i += 1;
                    }
                } else if (currentCommand == TokenType.EDIT_COUNTRY) {
                    if (currentTokenType == TokenType.ADD) {
                        AddCountryCommand addCountryCommand = processTokenToAddCountryCommand(i);
                        commands.add(addCountryCommand);
                        i += 2;
                    } else if (currentTokenType == TokenType.REMOVE) {
                        RemoveCountryCommand removeCountryCommand = processTokenToRemoveCountryCommand(i);
                        commands.add(removeCountryCommand);
                        i += 1;
                    }
                } else if (currentCommand == TokenType.EDIT_NEIGHBOUR) {
                    if (currentTokenType == TokenType.ADD) {
                        AddNeighbourCommand addNeighbourCommand = processTokenToAddNeigbourCommand(i);
                        commands.add(addNeighbourCommand);
                        i += 2;
                    } else if (currentTokenType == TokenType.REMOVE) {
                        RemoveNeigbourCommand removeNeigbourCommand = processTokenToRemoveNeigbourCommand(i);
                        commands.add(removeNeigbourCommand);
                        i += 2;
                    }
                } else if (currentCommand == TokenType.SAVE_MAP) {
                    SaveFileCommand saveFileCommand = processTokenToSaveFileCommand(i);
                    commands.add(saveFileCommand);
                    i++;
                } else if (currentCommand == TokenType.EDIT_MAP) {
                    EditMapCommand saveFileCommand = processTokenEditMapCommand(i);
                    commands.add(saveFileCommand);
                    i++;
                } else if (currentCommand == TokenType.LOAD_MAP) {
                    LoadMapCommand loadMapCommand = processTokenLoadMapCommand(i);
                    commands.add(loadMapCommand);
                    i++;
                } else if (currentCommand == TokenType.GAME_PLAYER) {
                    if (currentTokenType == TokenType.ADD) {
                        AddPlayerCommand addPlayerCommand = processTokenAddPlayerCommand(i);
                        commands.add(addPlayerCommand);
                        i += 1;
                    } else if (currentTokenType == TokenType.REMOVE) {
                        RemovePlayerCommand removePlayerCommand = processTokenRemovePlayerCommand(i);
                        commands.add(removePlayerCommand);
                        i += 2;
                    }
                } else if (currentCommand == TokenType.PLACE_ARMY) {
                    PlaceArmyCommand placeArmyCommand = processTokenPlaceArmyCommand(i);
                    commands.add(placeArmyCommand);
                    i++;
                } else if (currentCommand == TokenType.REINFORCE) {
                    ReinforceCountryCommand reinforceCountryCommand = processTokenReinforceCountryCommand(i);
                    commands.add(reinforceCountryCommand);
                    i++;
                } else if (currentCommand == TokenType.FORTIFY) {
                    if (currentTokenType == TokenType.NONE) {
                        FortifyNoneCommand fortifyNoneCommand = new FortifyNoneCommand(mapData);
                        commands.add(fortifyNoneCommand);
                        i++;
                    } else {
                        FortifyCommand fortifyNoneCommand = processTokenFortifyCommand(i);
                        commands.add(fortifyNoneCommand);
                        i += 2;
                    }
                }
            }

            if (tokens.size() - 1 == i) {
                checkNext = false;
            }
            i++;
        }
        execute();
    }

    /**
     * method to execute various commands
     */
    private void execute() {
        commands.forEach(Command::execute);
    }

    /**
     * method for token fortify command
     * @param i integer value 
     * @return object of the class FortifyCommand
     */
    private FortifyCommand processTokenFortifyCommand(int i) {
        String fromCountry = tokens.get(i).getContent();
        String toCountry = tokens.get(i + 1).getContent();
        int num = Integer.parseInt(tokens.get(i + 2).getContent());
        return new FortifyCommand(mapData, fromCountry, toCountry, num);
    }

    /**
     * Method for processing token reinforce country command
     * @param i integer value
     * @return object of ReinforceCountryCommand
     */
    private ReinforceCountryCommand processTokenReinforceCountryCommand(int i) {
        String countryName = tokens.get(i).getContent();
        int num = Integer.parseInt(tokens.get(i + 1).getContent());
        ReinforceCountryCommand reinforceCountryCommand = new ReinforceCountryCommand(mapData, countryName, num);
        return reinforceCountryCommand;
    }

    /**
     * method to process place army command
     * @param i integer value
     * @return object of PlaceArmyCommmand
     */
    private PlaceArmyCommand processTokenPlaceArmyCommand(int i) {
        String countryName = tokens.get(i).getContent();
        PlaceArmyCommand placeArmyCommand = new PlaceArmyCommand(mapData, countryName);
        return placeArmyCommand;
    }


    /**
     * Method to process remove player command
     * @param i an integer value
     * @return object of RemovePlayerCommand
     */
    private RemovePlayerCommand processTokenRemovePlayerCommand(int i) {
        String playerName = tokens.get(i + 1).getContent();
        RemovePlayerCommand removePlayerCommand = new RemovePlayerCommand(playerCommandListener, playerName);
        return removePlayerCommand;
    }

    /**
     * method to process add player command
     * @param i an integer value
     * @return object of AddPlayerCommand
     */
    private AddPlayerCommand processTokenAddPlayerCommand(int i) {
        String playerName = tokens.get(i + 1).getContent();
        AddPlayerCommand addPlayerCommand = new AddPlayerCommand(playerCommandListener, playerName);
        return addPlayerCommand;
    }

    /**
     * method to process LoadMap Command
     * @param i an integer value
     * @return object of LoadMapCommand
     */
    private LoadMapCommand processTokenLoadMapCommand(int i) {
        String fileName = tokens.get(i).getContent();
        LoadMapCommand loadMapCommand = new LoadMapCommand(fileName, loadMapListener);
        return loadMapCommand;
    }

    /**
     * Method to process EditMap Command
     * @param i an integer Value
     * @return object of EditMapCommand
     */
    private EditMapCommand processTokenEditMapCommand(int i) {
        String fileName = tokens.get(i).getContent();
        EditMapCommand editMapCommand = new EditMapCommand(editMapListener, fileName);
        return editMapCommand;
    }

    /**
     * method to process Save File Command
     * @param i an integer value
     * @return object of SaveFileCommand
     */
    private SaveFileCommand processTokenToSaveFileCommand(int i) {
        String fileName = tokens.get(i).getContent();
        SaveFileCommand saveFileCommand = new SaveFileCommand(mapData, fileName);
        return saveFileCommand;
    }

    /**
     * method to process add Continent Command
     * @param i an integer value
     * @return AddContinentCommand Object
     */
    private AddContinentCommand processTokenToAddContinentCommand(int i) {
        String continentName = tokens.get(i + 1).getContent();
        String continentControlValue = tokens.get(i + 2).getContent();
        return new AddContinentCommand(mapData, continentName, Integer.parseInt(continentControlValue));
    }

    /**
     * method to process remove Continent command
     * @param i an integer value
     * @return object of RemoveContinentCommand
     */
    private RemoveContinentCommand processTokenToRemoveContinentCommand(int i) {
        String continentName = tokens.get(i + 1).getContent();
        return new RemoveContinentCommand(mapData, continentName);
    }

    /**
     * Method to process add country command
     * @param i an integer value
     * @return object of AddCountryCommand
     */
    private AddCountryCommand processTokenToAddCountryCommand(int i) {
        String countryName = tokens.get(i + 1).getContent();
        String continentName = tokens.get(i + 2).getContent();
        return new AddCountryCommand(mapData, countryName, continentName);
    }

    /**
     * Method to process RemoveCountry command
     * @param i an integer value
     * @return object of RemoveCountryCommand
     */
    private RemoveCountryCommand processTokenToRemoveCountryCommand(int i) {
        String countryName = tokens.get(i + 1).getContent();
        return new RemoveCountryCommand(mapData, countryName);
    }

    /**
     * Method to process add neighbor command
     * @param i an integer value
     * @return object of AddNeighborCommand
     */
    private AddNeighbourCommand processTokenToAddNeigbourCommand(int i) {
        String countryName = tokens.get(i + 1).getContent();
        String countryNeigbourName = tokens.get(i + 1).getContent();
        return new AddNeighbourCommand(mapData, countryName, countryNeigbourName);
    }

    /**
     * Method to process Remove Neighbor command
     * @param i an integer value
     * @return object of RemoveNeigbourCommand
     */
    private RemoveNeigbourCommand processTokenToRemoveNeigbourCommand(int i) {
        String countryName = tokens.get(i + 1).getContent();
        String countryNeigbourName = tokens.get(i + 1).getContent();
        return new RemoveNeigbourCommand(mapData, countryName, countryNeigbourName);
    }


}
