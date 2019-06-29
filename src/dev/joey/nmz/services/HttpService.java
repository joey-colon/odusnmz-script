package dev.joey.nmz.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dev.joey.nmz.util.ApiResponse;
import dev.joey.nmz.util.PingData;
import org.json.JSONObject;
import org.rspeer.script.Script;

public class HttpService {

    private static String API_URL = "http://localhost:3000/api/";
    private Unirest client;

    public static void init() {
        SessionService.setSessionId(startSession());
    }

    public static String startSession() {
        try {
            HttpResponse<JsonNode> response = Unirest
                    .get(API_URL + "session/create/?rspeer_user={rspeer_user}")
                    .header("Accept", "application/json")
                    .routeParam("rspeer_user", Script.getRSPeerUser().getUsername())
                    .asJson();
            JSONObject obj = response.getBody().getObject();
            String status = obj.getString("status");
            if (!status.equals(ApiResponse.SUCCESS.toString())) return null;
            JSONObject sessionResponse = new JSONObject(obj.get("response").toString());
            return sessionResponse.get("session_id").toString();
        } catch (UnirestException _e) {
            return null;
        }
    }


    public static boolean stopSession() {
        try {
            HttpResponse<JsonNode> response = Unirest
                    .get(API_URL + " session/?session_id={session_id}")
                    .header("Accept", "application/json")
                    .routeParam("session_id", SessionService.getSessionId())
                    .asJson();
            int statusCode = response.getStatus();
            return statusCode == 200;
        } catch (UnirestException _e) {
            return false;
        }
    }

    public static boolean updateSession(PingData data) {
        try {
            HttpResponse<JsonNode> response = Unirest
                    .get(API_URL + " ping/?session_id={session_id}&status={status}&screenshot={screenshot}")
                    .routeParam("session_id", SessionService.getSessionId())
                    .routeParam("status", data.getStatus())
                    .routeParam("screenshot", data.getScreenshot())
                    .asJson();
            int statusCode = response.getStatus();
            return statusCode == 200;
        } catch (UnirestException _e) {
            return false;
        }
    }

}
