package com.javarush.cryptanalyzer.ivanchin.service;

import com.javarush.cryptanalyzer.ivanchin.constants.CryptoAlphabet;
import com.javarush.cryptanalyzer.ivanchin.controllers.DataInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.javarush.cryptanalyzer.ivanchin.constants.CryptoAlphabet.ALPHABET;
import static com.javarush.cryptanalyzer.ivanchin.constants.Probability.probabilityOfOccurrence;

public class Encoder {

    private static char findChar(char symbol, int key){
        for (int i = 0; i <ALPHABET.length() ; i++) {
            if (symbol==ALPHABET.charAt(i)) {
                if (key>=0 && (i+key)<ALPHABET.length()){
                    return ALPHABET.charAt(i+key);
                } else if (key>=0 && (i+key)>=ALPHABET.length()){
                    return ALPHABET.charAt(((i+key) % ALPHABET.length()));
                }else if (key<0 && (key+i)<0){
                    return ALPHABET.charAt((ALPHABET.length() + (i + key) % ALPHABET.length()) % ALPHABET.length());
                }else if (key<0 && (key+i)>=0){
                    return ALPHABET.charAt(i+key);
                }
            }
        }
        return symbol;
    }

    public static void encrypt(Path inFile, Path outFile ){
        try (FileReader reader = new FileReader(String.valueOf(inFile));
             FileWriter writer = new FileWriter(String.valueOf(outFile))) {
            int key= DataInput.enterKey();
            while (reader.ready()) {
                writer.append(findChar((char) reader.read(), key));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void encryptBruteForce(Path inFile){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(inFile)));
             FileWriter writer=new FileWriter("src/main/resources/output.txt")) {
            StringBuilder result = new StringBuilder();
            double[] keys= new double[probabilityOfOccurrence.size()];
            for (int i = 0; i < CryptoAlphabet.letterLowerCase.length() ; i++){
                bufferedReader.mark(65636);
                while (bufferedReader.ready()){
                    result.append(findChar((char) bufferedReader.read(), i));
                }
                keys[i]=chiSquare(entryCalculation(String.valueOf(result)));
                bufferedReader.reset();
            }
            while (bufferedReader.ready()) {
                writer.write(findChar((char) bufferedReader.read(), maxIndex(keys)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static HashMap<Character, Double> entryCalculation(String encryptedString){
        HashMap<Character, Double> calculationMap= new HashMap<>(){{
            put('î', 0.0);
            put('å', 0.0);
            put('à', 0.0);
            put('è', 0.0);
            put('í', 0.0);
            put('ò', 0.0);
            put('ñ', 0.0);
            put('ð', 0.0);
            put('â', 0.0);
            put('ë', 0.0);
            put('ê', 0.0);
            put('ì', 0.0);
            put('ä', 0.0);
            put('ï', 0.0);
            put('ó', 0.0);
            put('ÿ', 0.0);
            put('û', 0.0);
            put('ü', 0.0);
            put('ã', 0.0);
            put('ç', 0.0);
            put('á', 0.0);
            put('÷', 0.0);
            put('é', 0.0);
            put('õ', 0.0);
            put('æ', 0.0);
            put('ø', 0.0);
            put('þ', 0.0);
            put('ö', 0.0);
            put('ù', 0.0);
            put('ý', 0.0);
            put('ô', 0.0);
            put('ú', 0.0);
            put('¸', 0.0);
        }};
        calculationMap.replaceAll((s, v) -> (double) countOccurrences(encryptedString, s));
        double sum = calculationMap.values().stream().mapToDouble(Double::doubleValue).sum();
        calculationMap.replaceAll((s, v) -> calculationMap.get(s) / sum);
        return calculationMap;
    }


    private static double chiSquare(HashMap<Character, Double> entryCalculation){
        double sumChi=0;
        for (char symbol: probabilityOfOccurrence.keySet()){
            sumChi+=(probabilityOfOccurrence.get(symbol)*entryCalculation.get(symbol));
        }
        return sumChi;
    }

    private static int countOccurrences(String encryptedString, char symbol) {
        Matcher matcher = Pattern.compile(String.valueOf(symbol)).matcher(encryptedString.toLowerCase());
        int counter = 0;
        while (matcher.find()) {
            counter++;
        }
        return counter;
    }
    private static int maxIndex(double[] array){
        double max = array[0];
        int indexForMax = 0;
        for (int i = 1; i < array.length; i++) {
            double imax=array[i];
            if (imax > max) {
                max = imax;
                indexForMax=i;
            }
        }
        return indexForMax;
    }


}
