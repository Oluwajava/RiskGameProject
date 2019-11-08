package com.soen.riskgame.module.core.command_line;

import com.soen.riskgame.module.core.command.*;
import com.soen.riskgame.module.core.enums.Phase;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.interfaces.CommandSytanxProcessor;
import com.soen.riskgame.module.core.interfaces.PlayerCommandListener;
import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;
import com.soen.riskgame.module.core.utils.GraphUtil;
import com.soen.riskgame.module.core.utils.MapDataUtil;
import com.sun.tools.javac.util.GraphUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
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

    private CommandSytanxProcessor commandSytanxProcessor;

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
                    DefendCommand defendCommand = processDefendCommand(i);
                    commands.add(defendCommand);
                } else if (currentCommand == TokenType.ATTACK_MOVE) {
                    AttackMoveCommand attackMoveCommand = processAttackMove(i);
                    commands.add(attackMoveCommand);
                } else if (currentCommand == TokenType.ATTACK) {
                    AttackCommand processAttackCommmand = processAttackCommmand(i);
                    commands.add(processAttackCommmand);
                    i = tokens.size() - 1;
                } else if (currentCommand == TokenType.EXCHANGE_CARDS) {
                    ExchangeCardCommand exchangeCardCommand = processExchanceCardCommand(i);
                    commands.add(exchangeCardCommand);
                    i = tokens.size() - 1;
                }
            }

            if (tokens.size() - 1 == i) {
                checkNext = false;
            }
            i++;
        }
        execute();
    }

    private AttackMoveCommand processAttackMove(int i) {
        int num = Integer.parseInt(tokens.get(i).getContent());
        if (num < mapData.getAttackFromCountry().getNoOfArmies() && num >= 1) {
            return new AttackMoveCommand(mapData, num);
        } else {
            commandSytanxProcessor.onError("Invalid number of army");
        }
        return null;
    }

    private DefendCommand processDefendCommand(int i) {
        int num = Integer.parseInt(tokens.get(i).getContent());
        if (num >= 1 && num <= 2) {
            return new DefendCommand(mapData, num);
        } else {
            commandSytanxProcessor.onError("Invalid number of dice");
        }
        return null;
    }

    private void execute() {
        commands.forEach(v -> {
            if (v != null) v.execute();
        });
    }

    private AttackCommand processAttackCommmand(int i) {
        Player player = mapData.getPlayers().last();
        if (player.getPhase() == Phase.ATTACK) {
            String countryFromName = tokens.get(i).getContent();
            Country countryFrom = MapDataUtil.findCountryByName(countryFromName, mapData.getCountries());
            String countryToName = tokens.get(i + 1).getContent();
            int numOfDice = Integer.parseInt(tokens.get(i + 2).getContent());
            if (numOfDice >= 1 && numOfDice <= 3) {
                if (countryFrom.getNoOfArmies() >= numOfDice && countryFrom.getNoOfArmies() > 1) {
                    String extendedAction = null;
                    AttackCommand attackCommand = new AttackCommand(mapData, countryFromName, countryToName, numOfDice);
                    if (tokens.size() > i + 3) {
                        extendedAction = tokens.get(i + 3).getContent();
                        attackCommand.setExtendedAction(extendedAction);
                    }
                    return attackCommand;
                } else {
                    commandSytanxProcessor.onError("You don't have enough army to attack country");

                }
            } else {
                commandSytanxProcessor.onError("Invalid dice number");
            }
        } else {
            commandSytanxProcessor.onError("You're not in attack Phase");
        }

        return null;
    }


    private ExchangeCardCommand processExchanceCardCommand(int i) {
        Player player = mapData.getPlayers().last();
        if (player.getPhase() == Phase.REINFORCEMENT) {
            if (player.getCards().getNumberOfCards() >= 3) {
                int num1 = Integer.parseInt(tokens.get(i).getContent());
                int num2 = Integer.parseInt(tokens.get(i + 1).getContent());
                int num3 = Integer.parseInt(tokens.get(i + 2).getContent());
                if (num1 < player.getCards().getNumberOfCards() && num2 < player.getCards().getNumberOfCards() &&
                        num3 < player.getCards().getNumberOfCards()) {
                    String extendedAction = null;
                    ExchangeCardCommand exchangeCardCommand = new ExchangeCardCommand(mapData, num1, num2, num3);
                    if (tokens.size() > i + 3) {
                        extendedAction = tokens.get(i + 3).getContent();
                        exchangeCardCommand.setExtendedAction(extendedAction);
                    }
                    return exchangeCardCommand;
                } else {
                    commandSytanxProcessor.onError("Invalid Card for exchange");
                }
            } else {
                commandSytanxProcessor.onError("You do not have enough cards to exchange");
            }
        } else {
            commandSytanxProcessor.onError("You cannot exchange cards in this phase");
        }
        return null;

    }


    private FortifyCommand processTokenFortifyCommand(int i) {
        Player player = mapData.getPlayers().last();
        if (player.getPhase() == Phase.FORTIFICATION) {
            String fromCountry = tokens.get(i).getContent();
            String toCountry = tokens.get(i + 1).getContent();
            Country countryFromm = MapDataUtil.findCountryByName(fromCountry, mapData.getCountries());
            if (countryFromm.isCountryAdjacent(toCountry)) {
                int num = Integer.parseInt(tokens.get(i + 2).getContent());
                return new FortifyCommand(mapData, fromCountry, toCountry, num);
            } else {
                commandSytanxProcessor.onError("Countries aren't adjacent");
            }
        } else {
            commandSytanxProcessor.onError("You're not in Fortification Phase");
        }
        return null;
    }

    private ReinforceCountryCommand processTokenReinforceCountryCommand(int i) {
        Player player = mapData.getPlayers().last();
        if (player.getPhase() == Phase.REINFORCEMENT) {
            String countryName = tokens.get(i).getContent();
            int num = Integer.parseInt(tokens.get(i + 1).getContent());
            ReinforceCountryCommand reinforceCountryCommand = new ReinforceCountryCommand(mapData, countryName, num);
            return reinforceCountryCommand;
        } else {
            commandSytanxProcessor.onError("You're not in Reinforcement Phase");
        }

        return null;
    }

    private PlaceArmyCommand processTokenPlaceArmyCommand(int i) {
        Player player = mapData.getPlayers().last();
        String countryName = tokens.get(i).getContent();
        if (player.doesCountryBelongToPlayer(countryName)) {
            PlaceArmyCommand placeArmyCommand = new PlaceArmyCommand(mapData, countryName);
            return placeArmyCommand;
        } else {
            commandSytanxProcessor.onError("Country doesn't belong to you!!!");
        }
        return null;
    }


    private RemovePlayerCommand processTokenRemovePlayerCommand(int i) {
        String playerName = tokens.get(i + 1).getContent();
        RemovePlayerCommand removePlayerCommand = new RemovePlayerCommand(playerCommandListener, playerName);
        return removePlayerCommand;
    }

    private AddPlayerCommand processTokenAddPlayerCommand(int i) {
        if (!(mapData.getPlayers().size() >= mapData.getCountries().size())) {
            String playerName = tokens.get(i + 1).getContent();
            AddPlayerCommand addPlayerCommand = new AddPlayerCommand(playerCommandListener, playerName);
            return addPlayerCommand;
        } else {
            commandSytanxProcessor.onError("You can no longer add players. Number of players can't be greater than countries");
        }
        return null;
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
        boolean connected = new GraphUtil(new HashSet<>(mapData.getCountries().values())).isConnected();
        if (connected) {
            String fileName = tokens.get(i).getContent();
            SaveFileCommand saveFileCommand = new SaveFileCommand(mapData, fileName);
            return saveFileCommand;
        } else {
            commandSytanxProcessor.onError("Map isn't valid. Countries aren't connected");
        }
        return null;
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
