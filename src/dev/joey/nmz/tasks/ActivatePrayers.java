package dev.joey.nmz.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Prayer;
import org.rspeer.runetek.api.component.tab.Prayers;
import org.rspeer.script.task.Task;

import java.util.function.Predicate;

public class ActivatePrayers extends Task {
    public static Predicate<Item> POTION = (item) -> item.getName().startsWith("Prayer potion") || item.getName().startsWith("Super restore");

    @Override
    public boolean validate() {
        return !Prayers.isActive(Prayer.PROTECT_FROM_MELEE) && Inventory.contains(POTION);
    }

    @Override
    public int execute() {
        Prayers.toggle(true, Prayer.PROTECT_FROM_MELEE);
        return 600;
    }
}
