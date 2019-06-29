package dev.joey.nmz.tasks;

import dev.joey.nmz.services.SessionService;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class StopScript extends Task {
    @Override
    public boolean validate() {
        return !SessionService.isRunning();
    }

    @Override
    public int execute() {
        Log.info("Stopping odusNMZ");
        return -1;
    }
}
