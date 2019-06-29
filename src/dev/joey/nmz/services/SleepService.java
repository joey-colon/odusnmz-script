package dev.joey.nmz.services;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;

public class SleepService {
    public static void sleepRandom(double p) {
        double sleep1 = Random.nextInt(3000) * p;
        double sleep2 = sleep1 + Random.nextInt(6000) * p;
        Time.sleep((int) sleep1, (int) sleep2);
    }
}
