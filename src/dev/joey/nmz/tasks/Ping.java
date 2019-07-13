package dev.joey.nmz.tasks;

import dev.joey.nmz.services.SessionService;
import dev.joey.nmz.util.PingResponse;
import org.rspeer.script.task.Task;

public class Ping extends Task {
    @Override
    public boolean validate() {
        return SessionService.shouldUpdate();
    }

    @Override
    public int execute() {
        PingResponse response = SessionService.update();
        return (response == PingResponse.SUCCESS) ? 600 : -1;
    }
}
