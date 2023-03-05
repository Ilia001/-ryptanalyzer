package com.javarush.cryptanalyzer.ivanchin;

import com.javarush.cryptanalyzer.ivanchin.controllers.DataInput;
import com.javarush.cryptanalyzer.ivanchin.controllers.Requests;
import com.javarush.cryptanalyzer.ivanchin.service.Encoder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Requests.REQUEST);
        int choice = scanner.nextInt();
        if (choice==1){
            Encoder.encrypt(DataInput.enterInFile(), DataInput.enterOutFile());
        } else if (choice==2) {
            Encoder.encryptBruteForce(DataInput.enterInFile());
        }else if (choice==0) {
            System.exit(0);
        }
        scanner.close();
    }
}