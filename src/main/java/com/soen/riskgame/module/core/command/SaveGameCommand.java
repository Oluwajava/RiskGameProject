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


public class SaveGameCommand implements Command {
    private MapData mapData;

    private String fileName;

    public SaveGameCommand(MapData data, String fileName)
    {
        this.mapData=data;
        this.fileName = fileName;
    }

    @Override
    public void execute()  {
        try {
//            FileWriter writer = new FileWriter("C:/Users/Adekunle/RiskGameProject/src/main/resources/savedGames/"+fileName);
//            Gson gson = new GsonBuilder().create();
//            String s = gson.toJson(mapData);
//            writer.write(s);
//            writer.flush();
//            writer.close();

            FileOutputStream fileOutputStream=new FileOutputStream("C:/Users/Adekunle/RiskGameProject/src/main/resources/savedGames/"+fileName);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
           // String d = new Gson().toJson(mapData);
            objectOutputStream.writeObject(mapData);
            objectOutputStream.close();
            fileOutputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }



    }


}
