package dev.joey.nmz.services;

import dev.joey.nmz.util.PingData;
import dev.joey.nmz.util.ScriptStatus;
import dev.joey.nmz.util.StopMessage;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.Script;
import org.rspeer.ui.Log;

import java.time.Instant;

public class SessionService {

    private static Instant lastUpdated;
    private static String sessionId = null;
    private static int xpGained;
    private static int baseXp;
    private static ScriptStatus scriptStatus = ScriptStatus.RUNNING;

    public static void init() {
        baseXp = CombatService.getCombatXp();
    }

    public static boolean shouldUpdate() {
        return lastUpdated.plusSeconds(600).isBefore(Instant.now());
    }

    public static void update() {
        if (!shouldUpdate()) return;
        PingData data = new PingData(getXpGained(), null, null, null);
        HttpService.updateSession(data);
        lastUpdated = Instant.now();
    }

    public static void stopScript(StopMessage message) {
        Log.severe(message.toString());
        scriptStatus = ScriptStatus.STOPPED;
//        HttpService.stopSession();
    }

    public static void setSessionId(String id) {
        if (id == null)
            SessionService.stopScript(StopMessage.INVALID_SESSION);

        Log.info("Welcome to odusNMZ! Session ID: " + id);
        sessionId = id;
    }

    public static String getSessionId() {
        return sessionId;
    }

    public static int getXpGained() {
        xpGained = CombatService.getCombatXp() - baseXp;
        return xpGained;
    }

    public static ScriptStatus getScriptStatus() {
        return scriptStatus;
    }

    public static boolean isRunning() {
        return scriptStatus == ScriptStatus.RUNNING;
    }
}
