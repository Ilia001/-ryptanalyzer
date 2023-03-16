package com.javarush.cryptanalyzer.ivanchin;

import com.javarush.cryptanalyzer.ivanchin.controllers.DataInput;
import com.javarush.cryptanalyzer.ivanchin.controllers.Requests;
import com.javarush.cryptanalyzer.ivanchin.service.Encoder;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Requests.REQUEST);
        int choice = scanner.nextInt();
        switch (choice){
            case 1->Encoder.encrypt(DataInput.enterInFile(), DataInput.enterOutFile());
            case 2->Encoder.encrypt(Path.of("src/main/resources/input.txt"), Path.of("src/main/resources/encode.txt"));
            case 3->Encoder.encryptBruteForce(DataInput.enterInFile());
            case 4->Encoder.encryptBruteForce(Path.of("src/main/resources/encode.txt"));
            case 0->System.exit(0);
        }

        scanner.close();
    }
}