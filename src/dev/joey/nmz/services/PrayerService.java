package dev.joey.nmz.services;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.ui.Log;

import java.util.Random;
import java.util.function.Predicate;

public class PrayerService {

    private static int prayerThreshold = Integer.MIN_VALUE;
    public static Predicate<Item> POTION_PREDICATE = (item) -> item.getName().startsWith("Prayer potion") || item.getName().startsWith("Super restore");

    public static boolean shouldDrink() {
        if (prayerThreshold == Integer.MIN_VALUE)
            generatePrayerThreshold();

        return Skills.getCurrentLevel(Skill.PRAYER) <= prayerThreshold;
    }

    public static boolean hasPotion() {
        return Inventory.contains(POTION_PREDICATE);
    }

    public static void generatePrayerThreshold() {
        Random random = new Random();
        int first = random.nextInt(100);
        int prayerLevel = Skills.getLevel(Skill.PRAYER);
        int high, low;
        if (first > 25 && first < 50) {
            low = 13;
            high = (int) (prayerLevel * .5);
        } else if (first > 50 && first < 75){
            low = 7;
            high = (int) (prayerLevel * .7);
        } else {
            low = 12;
            high = (int) (prayerLevel * .6);
        }

        prayerThreshold = random.nextInt(high - low) + low;
        Log.info("Drinking prayer potion at " + prayerThreshold + " prayer...");
    }

}
