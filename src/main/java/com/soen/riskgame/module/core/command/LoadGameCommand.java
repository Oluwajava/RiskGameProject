package com.soen.riskgame.module.core.command;

import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.interfaces.Command;
import com.soen.riskgame.module.core.model.Map;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.FileReader;
import com.soen.riskgame.module.core.utils.MapDataUtil;
import com.soen.riskgame.module.core.utils.MapParser;
import com.soen.riskgame.module.core.utils.MapValidator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *Class uses command pattern to abstract LoadGame Command
 *for the Map This class can be called by either the GUI or the command line to perform
 *@see Command
 *@see <a href="https://refactoring.guru/design-patterns/command">Command Pattern Tutorial</a>
 * @author John
 */
public class LoadGameCommand implements Command {
    /**
     * Name to the map file
     */
    private String fileName;
    /**
     * object of the interface implemented
     */
    private LoadGameListener loadGameListener;

    /**
     * Constructor for the LoadGame class.
     * @param fileName Name to the map file
     * @param loadGameListener object of the interface implemented
     */
    public LoadGameCommand(String fileName, LoadGameListener loadGameListener) {
        this.fileName = fileName;
        this.loadGameListener = loadGameListener;
    }
    /**
     * Method is inherited from the @see Command Interface
     * which is used to abstract the execution of
     * the Command
     */
    @Override
    public void execute() {
        System.out.println("in execute function");
        MapData data = null;
        try {
            System.out.println("in command");
            FileInputStream file  = new FileInputStream("C:/Users/Adekunle/RiskGameProject/src/main/resources/savedGames/"+fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            data = (MapData)in.readObject();
            System.out.println(data);
            in.close();
            file.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }


        loadGameListener.loadGame(data);
    }

    /**
     * Interface representation for the class
     */
    public interface LoadGameListener {

        void loadGame(MapData ma);

    }
}
