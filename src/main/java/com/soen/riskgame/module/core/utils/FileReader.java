package com.soen.riskgame.module.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is used for reading the file
 * @author Sibil
 */
public class FileReader {
    /**
     * file location
     */
    String fileLocation;

    /**
     * constructor of the class
     * @param fileLocation is passed from the called function
     */
    public FileReader(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * This file is used to read the data from a file
     *
     * @return fileData as String
     * @throws IOException
     */
    public String readData() throws IOException {
        String fileData = new String(Files.readAllBytes(Paths.get(fileLocation)));
        return fileData;
    }
}
