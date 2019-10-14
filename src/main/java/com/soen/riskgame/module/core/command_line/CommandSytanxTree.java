package com.soen.riskgame.module.core.command_line;

import com.soen.riskgame.module.core.command.*;
import com.soen.riskgame.module.core.interfaces.Command;
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
        while (checkNext) {
            if (tokens.get(i).tokenType.getCommandType() == CommandType.COMMAND) {
                currentCommand = tokens.get(i).tokenType;
                i++;
                if (currentCommand == TokenType.SHOWMAP) {
                    ShowMapCommand showMapCommand = new ShowMapCommand(mapData, showMapListener);
                    commands.add(showMapCommand);
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
        commands.forEach(command -> {
            command.execute();
        });
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
        String continentName = tokens.get(i + 1).getContent();
        return new AddCountryCommand(mapData, countryName, continentName);
    }

    private RemoveCountryCommand processTokenToRemoveCountryCommand(int i) {
        String countryName = tokens.get(i + 1).getContent();
        return new RemoveCountryCommand(mapData, countryName);
    }

    private AddNeighbourCommand processTokenToAddNeigbourCommand(int i) {
        String countryName = tokens.get(i + 1).getContent();
        String countryNeigbourName = tokens.get(i + 1).getContent();
        return new AddNeighbourCommand(mapData, countryName, countryNeigbourName);
    }

    private RemoveNeigbourCommand processTokenToRemoveNeigbourCommand(int i) {
        String countryName = tokens.get(i + 1).getContent();
        String countryNeigbourName = tokens.get(i + 1).getContent();
        return new RemoveNeigbourCommand(mapData, countryName, countryNeigbourName);
    }


}
