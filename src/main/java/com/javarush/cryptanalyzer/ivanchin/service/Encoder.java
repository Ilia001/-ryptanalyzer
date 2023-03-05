package com.javarush.cryptanalyzer.ivanchin.service;

import com.javarush.cryptanalyzer.ivanchin.controllers.DataInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static com.javarush.cryptanalyzer.ivanchin.constants.CryptoAlphabet.ALPHABET;

public class Encoder {
    private static char findChar(char symbol, int key){
        for (int i = 0; i <ALPHABET.length() ; i++) {
            if (symbol==ALPHABET.charAt(i)) {
                if (key>=0 && (i+key)<ALPHABET.length()){
                    return symbol= ALPHABET.charAt(i+key);
                } else if (key>=0 && (i+key)>=ALPHABET.length()){
                    return symbol= ALPHABET.charAt(((i+key) % ALPHABET.length()));
                }else if (key<0 && (key+i)<0){
                    return symbol= ALPHABET.charAt((ALPHABET.length() + (i + key) % ALPHABET.length()) % ALPHABET.length());
                }else if (key<0 && (key+i)>=0){
                    return symbol= ALPHABET.charAt(i+key);
                }
            }
        }
        return symbol;
    }

    public static void encrypt(Path inFile, Path outFile ){
        try (FileReader reader = new FileReader(String.valueOf(inFile));
             FileWriter writer = new FileWriter(String.valueOf(outFile))) {
            StringBuilder result = new StringBuilder();
            int key= DataInput.enterKey();
            while (reader.ready()) {
                writer.append(findChar((char) reader.read(), key));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void encryptBruteForce(Path inFile){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(inFile)))) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < ALPHABET.length() ; i++){
                bufferedReader.mark(1024);
                while (bufferedReader.ready()){
                    result.append(findChar((char) bufferedReader.read(), i));
                }
                bufferedReader.reset();
                System.out.println(i +"-"+ result);
                result.delete(0,result.length());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
