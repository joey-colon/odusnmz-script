package dev.joey.nmz.services;

import dev.joey.nmz.util.PingData;
import dev.joey.nmz.util.PingResponse;
import dev.joey.nmz.util.ScriptStatus;
import dev.joey.nmz.util.StopMessage;
import org.rspeer.ui.Log;

import java.time.Instant;

public class SessionService {

    private static Instant lastUpdated;
    private static String sessionId = null;
    private static int xpGained;
    private static int baseXp;
    private static ScriptStatus scriptStatus = ScriptStatus.RUNNING;
    private static Instant startTime;

    public static void init() {
        lastUpdated = Instant.now();
        startTime = Instant.now();
        baseXp = CombatService.getCombatXp();
    }

    public static boolean shouldUpdate() {
        return lastUpdated.plusSeconds(600).isBefore(Instant.now());
    }

    public static PingResponse update() {
        Log.fine("Sending NMZ update");
        PingData data = new PingData(getXpGained(), null, getScriptStatus().toString(), String.valueOf(getRunTime()));
        PingResponse response = HttpService.updateSession(data);
        if (response == PingResponse.INSUFFICIENT_ACCESS) {
            stopScript(StopMessage.INSUFFICENT_ACCESS);
        } else if (response == PingResponse.ERROR) {
            stopScript(StopMessage.GENERAL_ERROR);
        }

        lastUpdated = Instant.now();
        return response;
    }

    public static void stopScript(StopMessage message) {
        if (scriptStatus == ScriptStatus.STOPPED) return;
        Log.severe(message.toString());
        scriptStatus = ScriptStatus.STOPPED;
        HttpService.stopSession();
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

    public static long getRunTime() {
        return Instant.now().toEpochMilli() - startTime.toEpochMilli();
    }
}
