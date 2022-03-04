package gg.therealm.discord;

import java.net.URI;
import java.net.URISyntaxException;

// "Globals are bad" - That's too bad.
public class Yoink {
    public static final String PUBLIC_KEY = "e6617b9412a9dcb3edf97a9325f8e3ec3e824c70b29567df8e1fe2e03ac29601";
    public static final String BASE_AUTH_URL = "https://discord.com/api/oauth2/authorize";
    public static final String TOKEN_URL = "https://discord.com/api/oauth2/token";
    public static final String APPLICATION_ID = "949045476865507419";
    public static final String COMMAND_URL = "https://discord.com/api/v8/applications/" + APPLICATION_ID + "/commands";

    private static Yoink yoink;
    private WebSocket webSocket;

    static {
        yoink = new Yoink();
    }

    public static Yoink gimme() {
        return yoink;
    }

    public WebSocket theWebSocket() {
        if (webSocket != null) {
            return webSocket;
        }

        try {
            webSocket = new WebSocket(new URI("wss://gateway.discord.gg/?v=9&encoding=json"));
            webSocket.connect();
            return webSocket;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
