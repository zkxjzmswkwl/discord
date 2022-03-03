package gg.therealm.discord;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocket extends WebSocketClient {
    private String lastMessage;

    public WebSocket(URI serverUri) {
        super(serverUri);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        System.out.println("Websocket onClose");
    }

    @Override
    public void onError(Exception arg0) {
        System.out.println("Websocket onError");
        arg0.printStackTrace();
    }

    @Override
    public void onMessage(String arg0) {
        System.out.println("Websocket onMessage\t" + arg0);
        lastMessage = arg0;
    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        System.out.println("Websocket onOpen");
    }

    public String getLastMessage() {
        return lastMessage;
    }
    
}
