package com.soen.riskgame.module.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is used for reading the file
 * @author sibil
 */
public class FileReader {
    String fileLocation;

    /**
     * @param fileLocation is passed from the called function
     */
    public FileReader(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * This function is used to read the file and pass the data as String
     *
     * @return fileData as String
     * @throws IOException
     */
    public String readData() throws IOException {
        String fileData = new String(Files.readAllBytes(Paths.get(fileLocation)));
        return fileData;
    }
}
