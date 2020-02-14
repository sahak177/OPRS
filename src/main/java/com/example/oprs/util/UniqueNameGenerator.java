package com.example.oprs.util;

public final class UniqueNameGenerator {

    public static String generateName(String signatureKey) {

        long nowDate = System.currentTimeMillis();

        return signatureKey + nowDate;
    }
}