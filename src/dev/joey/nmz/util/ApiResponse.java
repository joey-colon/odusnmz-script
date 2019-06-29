package dev.joey.nmz.util;

public enum ApiResponse {
    SUCCESS("success"),
    ERROR("error");

    private String statusMessage;
    ApiResponse(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return statusMessage;
    }

}
