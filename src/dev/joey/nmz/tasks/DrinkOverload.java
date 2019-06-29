package dev.joey.nmz.tasks;

import dev.joey.nmz.services.SleepService;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.Predicate;

public class DrinkOverload extends Task {
    private static final Predicate<Item> OVERLOAD = (item) -> item.getName().startsWith("Overload");

    @Override
    public boolean validate() {
        return Skills.getCurrentLevel(Skill.HITPOINTS) == Skills.getLevel(Skill.HITPOINTS)
                && Inventory.contains(OVERLOAD);
    }

    @Override
    public int execute() {
        Item pot = Inventory.getFirst(OVERLOAD);
        if (pot == null) return 600;
        SleepService.sleepRandom(Random.nextDouble(0.2, 0.4));
        pot.click();
        SleepService.sleepRandom(Random.nextDouble(1.2, 1.8));
        return 600;
    }
}
