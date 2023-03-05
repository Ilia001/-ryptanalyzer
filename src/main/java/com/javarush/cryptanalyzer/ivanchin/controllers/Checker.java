package com.javarush.cryptanalyzer.ivanchin.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Checker {
    public static boolean checkInFiles( Path inFile){
        if (!(Files.exists(inFile))){
            System.out.println(Requests.FILE_NOT_EXIST_MASSAGE);
        }
        return Files.exists(inFile);
    }
    public static boolean checkOutFiles( Path outFile){
        if (Files.exists(outFile)){
            try {
                Files.delete(outFile);
                Files.createFile(Path.of(String.valueOf(outFile)));
                System.out.println(Requests.FILE_REPLACED_MASSAGE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Files.createFile(Path.of(String.valueOf(outFile)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Files.exists(outFile);
    }

}
