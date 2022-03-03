package gg.therealm.discord;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Web {
    private OkHttpClient client;
    private String authorizationToken;

    public Web() {
        client = new OkHttpClient();
    }

    public void setAuthorization(String token) {
        this.authorizationToken = token;
    }

    public String getReq(String url) {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String postReq(String url, String payload) {
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), payload);
        Request request = new Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bot " + this.authorizationToken)
            .addHeader("Content-Type", "application/json")
            .post(body)
            .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OkHttpClient getClient() {
        return this.client;
    }
}