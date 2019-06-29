package dev.joey.nmz;

import dev.joey.nmz.services.HttpService;
import dev.joey.nmz.services.SessionService;
import dev.joey.nmz.tasks.*;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;

@ScriptMeta(name="odusNMZ", desc="Nightmarezo2ne", developer = "joey", version = 1.0, category = ScriptCategory.COMBAT)
public class Main extends TaskScript {

    private final Task[] TASKS = { new StopScript(), new IsInDream(), new ActivatePrayers(), new DrinkPrayerPotion(), new DrinkOverload() };

    @Override
    public void onStart() {
        SessionService.init();
        HttpService.init();
        submit(TASKS);
    }

    @Override
    public void onStop() {
        Log.fine("Thanks for using odusNMZ.");
    }
}