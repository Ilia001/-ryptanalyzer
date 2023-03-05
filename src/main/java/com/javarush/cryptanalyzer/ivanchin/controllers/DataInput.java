package com.javarush.cryptanalyzer.ivanchin.controllers;

import java.nio.file.Path;
import java.util.Scanner;

public class DataInput {
    public static Path enterInFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Requests.IN_FILE_MASSAGE);
        Path inFile;
        do {
            inFile = Path.of(scanner.nextLine());
        } while (!Checker.checkInFiles(inFile));
        return inFile;
    }
    public static Path enterOutFile(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Requests.OUT_FILE_MASSAGE);
        Path outFile;
        do {
            outFile = Path.of(scanner.nextLine());
        } while (!Checker.checkOutFiles(outFile));
        return outFile;
    }
    public static int enterKey(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Requests.KEY_MASSAGE);
        String key;
        do {
            key = scanner.nextLine();
        } while (!key.matches("[-+]?\\d+"));
        return Integer.parseInt(key);
    }
}
