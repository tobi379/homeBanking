package com.mindhub.homebanking.ultilties;

public final class CardUtils {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
