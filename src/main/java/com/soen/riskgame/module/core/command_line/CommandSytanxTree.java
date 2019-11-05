package com.soen.riskgame.module.core.command_line;

import com.soen.riskgame.module.core.command.*;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.interfaces.PlayerCommandListener;
import com.soen.riskgame.module.core.model.MapData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommandSytanxTree {

    private List<Token> tokens;

    private List<Command> commands;

    private MapData mapData;

    private ShowMapCommand.ShowMapListener showMapListener;

    private ValidateCommand.ValidateMapListener validateMapListener;

    private LoadMapCommand.LoadMapListener loadMapListener;

    private EditMapCommand.EditMapListener editMapListener;

    private PlayerCommandListener playerCommandListener;

    public CommandSytanxTree(MapData mapData, List<Token> tokens) {
        this.tokens = tokens;
        this.mapData = mapData;
        commands = new ArrayList<>();
    }

    public void setShowMapListener(ShowMapCommand.ShowMapListener showMapListener) {
        this.showMapListener = showMapListener;
    }

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
                } else if (currentCommand == TokenType.DEFEND) {
                    int num = Integer.parseInt(tokens.get(i).getContent());
                    DefendCommand defendCommand = new DefendCommand(mapData, num);
                    commands.add(defendCommand);
                } else if (currentCommand == TokenType.ATTACK_MOVE) {
                    int num = Integer.parseInt(tokens.get(i).getContent());
                    AttackMoveCommand attackMoveCommand = new AttackMoveCommand(mapData, num);
                    commands.add(attackMoveCommand);
                } else if (currentCommand == TokenType.ATTACK) {
                    AttackCommand processAttackCommmand = processAttackCommmand(i);
                    commands.add(processAttackCommmand);
                    i = tokens.size();
                }
            }

            if (tokens.size() - 1 == i) {
                checkNext = false;
            }
            i++;
        }
        execute();
    }

    private void execute() {
        commands.forEach(Command::execute);
    }

    private AttackCommand processAttackCommmand(int i) {
        String countryFromName = tokens.get(i).getContent();
        String countryToName = tokens.get(i+1).getContent();
        int numOfDice = Integer.parseInt(tokens.get(i+2).getContent());
        String extendedAction = null;
        AttackCommand attackCommand = new AttackCommand(mapData, countryFromName, countryToName, numOfDice);
        if (tokens.size() > i+3) {
            extendedAction = tokens.get(i+3).getContent();
            attackCommand.setExtendedAction(extendedAction);
        }
        return attackCommand;
    }

    private FortifyCommand processTokenFortifyCommand(int i) {
        String fromCountry = tokens.get(i).getContent();
        String toCountry = tokens.get(i + 1).getContent();
        int num = Integer.parseInt(tokens.get(i + 2).getContent());
        return new FortifyCommand(mapData, fromCountry, toCountry, num);
    }

    private ReinforceCountryCommand processTokenReinforceCountryCommand(int i) {
        String countryName = tokens.get(i).getContent();
        int num = Integer.parseInt(tokens.get(i + 1).getContent());
        ReinforceCountryCommand reinforceCountryCommand = new ReinforceCountryCommand(mapData, countryName, num);
        return reinforceCountryCommand;
    }

    private PlaceArmyCommand processTokenPlaceArmyCommand(int i) {
        String countryName = tokens.get(i).getContent();
        PlaceArmyCommand placeArmyCommand = new PlaceArmyCommand(mapData, countryName);
        return placeArmyCommand;
    }


    private RemovePlayerCommand processTokenRemovePlayerCommand(int i) {
        String playerName = tokens.get(i + 1).getContent();
        RemovePlayerCommand removePlayerCommand = new RemovePlayerCommand(playerCommandListener, playerName);
        return removePlayerCommand;
    }

    private AddPlayerCommand processTokenAddPlayerCommand(int i) {
        String playerName = tokens.get(i + 1).getContent();
        AddPlayerCommand addPlayerCommand = new AddPlayerCommand(playerCommandListener, playerName);
        return addPlayerCommand;
    }

    private LoadMapCommand processTokenLoadMapCommand(int i) {
        String fileName = tokens.get(i).getContent();
        LoadMapCommand loadMapCommand = new LoadMapCommand(fileName, loadMapListener);
        return loadMapCommand;
    }

    private EditMapCommand processTokenEditMapCommand(int i) {
        String fileName = tokens.get(i).getContent();
        EditMapCommand editMapCommand = new EditMapCommand(editMapListener, fileName);
        return editMapCommand;
    }

    private SaveFileCommand processTokenToSaveFileCommand(int i) {
        String fileName = tokens.get(i).getContent();
        SaveFileCommand saveFileCommand = new SaveFileCommand(mapData, fileName);
        return saveFileCommand;
    }

    private AddContinentCommand processTokenToAddContinentCommand(int i) {
        String continentName = tokens.get(i + 1).getContent();
        String continentControlValue = tokens.get(i + 2).getContent();
        return new AddContinentCommand(mapData, continentName, Integer.parseInt(continentControlValue));
    }

    private RemoveContinentCommand processTokenToRemoveContinentCommand(int i) {
        String continentName = tokens.get(i + 1).getContent();
        return new RemoveContinentCommand(mapData, continentName);
    }

    private AddCountryCommand processTokenToAddCountryCommand(int i) {
        String countryName = tokens.get(i + 1).getContent();
        String continentName = tokens.get(i + 2).getContent();
        return new AddCountryCommand(mapData, countryName, continentName);
    }

    private RemoveCountryCommand processTokenToRemoveCountryCommand(int i) {
        String countryName = tokens.get(i + 1).getContent();
        return new RemoveCountryCommand(mapData, countryName);
    }

    private AddNeighbourCommand processTokenToAddNeigbourCommand(int i) {
        String countryName = tokens.get(i + 1).getContent();
        String countryNeigbourName = tokens.get(i + 2).getContent();
        return new AddNeighbourCommand(mapData, countryName, countryNeigbourName);
    }

    private RemoveNeigbourCommand processTokenToRemoveNeigbourCommand(int i) {
        String countryName = tokens.get(i + 1).getContent();
        String countryNeigbourName = tokens.get(i + 2).getContent();
        return new RemoveNeigbourCommand(mapData, countryName, countryNeigbourName);
    }


}
