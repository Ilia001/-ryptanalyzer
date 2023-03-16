package com.javarush.cryptanalyzer.ivanchin.controllers;

public class Requests {
    public static final String REQUEST="What do you want to do? \n 1- Encrypt/Decrypt (Key is known);\n 2- Encrypt/Decrypt default file (Key is known);\n 3- Decrypt (brute force);\n 4- Decrypt default file (brute force) \n Press 0 to close";
    protected static final String IN_FILE_MASSAGE ="Enter the path to the incoming TXT file";
    protected static final String OUT_FILE_MASSAGE ="Enter the path to the outgoing TXT file";
    protected static final String KEY_MASSAGE="Enter key value";
    protected static final String FILE_NOT_EXIST_MASSAGE ="File does not exist, try again";
    protected static final String FILE_REPLACED_MASSAGE ="File replaced";
}
