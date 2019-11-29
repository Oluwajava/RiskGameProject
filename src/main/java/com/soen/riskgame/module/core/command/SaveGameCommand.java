package com.soen.riskgame.module.core.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.MapDataUtil;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * * Class uses command pattern to abstract SaveGame Command
 *  for the Map This class can be called by either the GUI or the command line to perform
 *  @see com.soen.riskgame.module.core.interfaces.Command
 *  @see <a href="https://refactoring.guru/design-patterns/command"> Command Pattern Tutorial</a>
 * @author Mayokun
 */
public class SaveGameCommand implements Command {
    /**
     * object of map data
     */
    private MapData mapData;
    /**
     * name of file
     */
    private String fileName;

    /**
     * Save command method
     * @param data data
     * @param fileName fileName
     */
    public SaveGameCommand(MapData data, String fileName)
    {
        this.mapData=data;
        this.fileName = fileName;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute()  {
        try {
            FileOutputStream fileOutputStream=new FileOutputStream("C:/Users/Adekunle/RiskGameProject/src/main/resources/savedGames/"+fileName);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(mapData);
            objectOutputStream.close();
            fileOutputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }



    }


}
