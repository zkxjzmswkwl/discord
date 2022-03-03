package gg.therealm.discord;

import java.net.URI;
import java.net.URISyntaxException;

// "Globals are bad" - That's too bad.
public class Yoink {
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
