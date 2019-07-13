package dev.joey.nmz.tasks;

import dev.joey.nmz.services.PrayerService;
import dev.joey.nmz.services.SleepService;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class DrinkPrayerPotion extends Task {

    @Override
    public boolean validate() {
        return PrayerService.shouldDrink() && PrayerService.hasPotion();
    }

    @Override
    public int execute() {
        Item pot = Inventory.getFirst(PrayerService.POTION_PREDICATE);
        if (pot == null) return 600;
        SleepService.sleepRandom(1.2);

        pot.click();
        SleepService.sleepRandom(Random.nextDouble(0.5, 0.8));

        PrayerService.generatePrayerThreshold();

        return 600;
    }
}
