package dev.joey.nmz.util;

public enum StopMessage {
    NOT_LOGGED_IN("Your account is either not logged into RuneScape or in a NMZ dream."),
    INSUFFICENT_ACCESS("You do not have the required access to run this script."),
    INSTANCE_LIMIT("Your account has hit an instance limit. Purchase more instances of odusNMZ to remove this restriction."),
    INVALID_SESSION("No session detected. Try rerunning the script.");

    private String message;
    StopMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
