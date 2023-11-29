package com.phonecommerce.phonestore.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGeneratorUtil {
    public static long generateRandomLongId() {
        long leftLimit = 1L;
        long rightLimit = Long.MAX_VALUE;

        return leftLimit + (long) (ThreadLocalRandom.current().nextDouble() * (rightLimit - leftLimit));
    }
}