package dev.joey.nmz.tasks;

import dev.joey.nmz.services.PrayerService;
import dev.joey.nmz.services.SleepService;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Prayer;
import org.rspeer.runetek.api.component.tab.Prayers;
import org.rspeer.script.task.Task;

import java.util.function.Predicate;

public class ActivatePrayers extends Task {

    @Override
    public boolean validate() {
        return !Prayers.isActive(Prayer.PROTECT_FROM_MELEE) && PrayerService.hasPotion();
    }

    @Override
    public int execute() {
        Prayers.toggle(true, Prayer.PROTECT_FROM_MELEE);
        SleepService.sleepRandom(Random.nextDouble(0.2, 0.4));
        return 600;
    }
}
