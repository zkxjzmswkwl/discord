package gg.therealm.discord;

import java.net.URI;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocket extends WebSocketClient {
    private String lastMessage;
    private int heartbeatInterval;
    private long lastHeartbeat;
    private JsonParser jsonParser;
    Web webClient;

    public WebSocket(URI serverUri) {
        super(serverUri);
        webClient = new Web();
        jsonParser = new JsonParser();
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

    /*
        {
        "op": 10,
        "d": {
            "heartbeat_interval": 45000
        }
        }
    */
    @Override
    public void onMessage(String arg0) {
        System.out.println("Websocket onMessage\t" + arg0);
        lastMessage = arg0;
        JsonObject packetBuffer = jsonParser.parse(lastMessage).getAsJsonObject();

        if (lastMessage.toLowerCase().contains("\"op\":10")) {
            this.heartbeatInterval = Integer.parseInt(lastMessage.split("_interval\":")[1].split(",")[0].strip());
            System.out.println("Heartbeat Interval -> " + heartbeatInterval);
            this.send(Packet.buildIdentityPacket());
            
            this.lastHeartbeat = System.currentTimeMillis();
        }

        if (lastMessage.toLowerCase().contains("\"op\":0")) {
            JsonObject bufferData = packetBuffer.get("d").getAsJsonObject();
            if (bufferData.get("content").toString().equalsIgnoreCase("\"fuck u\"")) {
                // Need the id of the channel the message was sent so that we can respond in the same channel.
                String channelId = bufferData.get("channel_id").getAsString();

                // Build response
                JsonObject data = new JsonObject();
                data.addProperty("tts", false);
                data.addProperty("content", "No, fuck you.");
                data.addProperty("embeds", ""); 

                webClient.postReq("https://discord.com/api/v9/channels/949045799365525585/messages", data.toString());
            }


        }
    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        System.out.println("Websocket onOpen");
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public long getLastHeartbeat() {
        return lastHeartbeat;
    }

    public int getHeartbeatInterval() {
        return heartbeatInterval;
    }
    
}
