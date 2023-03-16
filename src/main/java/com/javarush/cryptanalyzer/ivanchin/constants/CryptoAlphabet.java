package com.javarush.cryptanalyzer.ivanchin.constants;

public class CryptoAlphabet {
    private static final String letterUpperCase="ÀÁÂÃÄÅ¨ÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞß";
    public static final String letterLowerCase="àáâãäå¸æçèéêëìíîïğñòóôõö÷øùúûüışÿ";
    private static final String numbers="0123456789";
    private static final String symbols=" .,\":!?";
    public static final String ALPHABET=letterUpperCase+letterLowerCase+numbers+symbols;

}
