package gg.therealm.discord;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class VoiceSocket extends WebSocketClient {

    public VoiceSocket(URI serverUri) {
        super(serverUri);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("onOpen\t" + handshakedata.toString());
    }

    @Override
    public void onMessage(String message) {
        System.out.println("onMessage\t" + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("onClose\t" + reason);
    }

    @Override
    public void onError(Exception ex) {
    }
    
}
