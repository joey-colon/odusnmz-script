package dev.joey.nmz.tasks;

import dev.joey.nmz.services.SessionService;
import dev.joey.nmz.util.StopMessage;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class IsInDream extends Task {
    @Override
    public boolean validate() {
        return !Game.isLoggedIn() || Players.getLocal().getFloorLevel() != 3;
    }

    @Override
    public int execute() {
        SessionService.stopScript(StopMessage.NOT_LOGGED_IN);
        return -1;
    }
}
