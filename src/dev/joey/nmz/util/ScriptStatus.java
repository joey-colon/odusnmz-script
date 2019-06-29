package dev.joey.nmz.util;

public enum ScriptStatus {
    RUNNING("Running"),
    STOPPED("Stopped");

    private String status;
    ScriptStatus(String status) {
        this.status = status;
    }

}
