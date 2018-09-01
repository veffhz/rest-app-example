package io.hexlet.java.links;

import java.util.Random;

public class LinkHelper {
    public static String getRandomId() {
        String possibleCharacters = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
        StringBuilder idBuilder = new StringBuilder();
        Random rnd = new Random();
        while (idBuilder.length() < 5) {
            int index = (int) (rnd.nextFloat() * possibleCharacters.length());
            idBuilder.append(possibleCharacters.charAt(index));
        }
        return idBuilder.toString();
    }
}
