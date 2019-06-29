package dev.joey.nmz.util;

public class PingData {

    private int xp_gained;
    private String screenshot;
    private String status;
    private String runtime;

    public PingData(int xp_gained, String screenshot, String status, String runtime) {
        this.xp_gained = xp_gained;
        this.screenshot = screenshot;
        this.status = status;
        this.runtime = runtime;
    }

    public int getXp_gained() {
        return xp_gained;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public String getStatus() {
        return status;
    }

    public String getRuntime() {
        return runtime;
    }



}
