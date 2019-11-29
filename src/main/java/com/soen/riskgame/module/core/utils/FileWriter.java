package com.soen.riskgame.module.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * This class is used to write the data to a file
 * @author Sibil
 */
public class FileWriter {
    /**
     * location of file
     */
    private String fileLocation;
    /**
     * data of the file
     */
    private String data;

    /**
     * Consturctor- Intializing the data and fileLocation variables
     * @param data file data
     * @param fileLocation fiel location
     */
    public FileWriter(String data, String fileLocation) {
        this.data = data;
        this.fileLocation = fileLocation;
    }

    /**
     * This function is used to write the data to the file
     * @throws FileNotFoundException FileNotFoundException
     */
    public void save() throws FileNotFoundException {
        File file = new File(fileLocation);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println(data);
        printWriter.close();
    }
}
