package gg.therealm.discord;

import java.net.URI;
import java.net.URISyntaxException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import gg.therealm.discord.packets.PacketMessage;
import gg.therealm.discord.packets.PacketVoiceIdentify;
import gg.therealm.discord.packets.PacketVoiceServerUpdate;
import gg.therealm.discord.packets.PacketVoiceStateUpdate;

public class WebSocket extends WebSocketClient {
    private String lastMessage;
    private int heartbeatInterval;
    private long lastHeartbeat;
    private JsonParser jsonParser;
    private Gson gson;
    Web webClient;

    public WebSocket(URI serverUri) {
        super(serverUri);
        webClient = new Web();
        jsonParser = new JsonParser();
        gson = new Gson();
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        System.out.println("Websocket onClose\t" + arg1);
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
    public void onMessage(String lastMessage) {
        JsonObject packetBuffer = jsonParser.parse(lastMessage).getAsJsonObject();
        System.out.println(lastMessage);
        int opcode = packetBuffer.get("op").getAsInt();

        switch (opcode) {
            case 0 -> {
                // PacketMessage messagePacket = gson.fromJson(packetBuffer.get("d"), PacketMessage.class);
                // if (messagePacket.getContent().equalsIgnoreCase("ass")) {
                //     // Build response
                //     JsonObject data = new JsonObject();
                //     data.addProperty("tts", false);
                //     data.addProperty("content", "titties\ntitties\ntitties");
                //     data.addProperty("embeds", ""); 

                //     webClient.postReq("https://discord.com/api/v9/channels/" + messagePacket.getChannelId() + "/messages", data.toString());
                // }
                if (lastMessage.contains("VOICE_SERVER_UPDATE")) {
                    PacketVoiceServerUpdate voiceUpdate = gson.fromJson(packetBuffer.get("d"), PacketVoiceServerUpdate.class);
                    System.out.println("Endpoint:\t" + voiceUpdate.getEndpoint());
                    System.out.println("Token...:\t" + voiceUpdate.getToken());
                    System.out.println("Guild...:\t" + voiceUpdate.getGuildId());
                    try {
                        VoiceSocket voiceSocket = new VoiceSocket(new URI("wss://" + voiceUpdate.getEndpoint()));
                        voiceSocket.connect();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            break;
            }
            
            case 10 -> {
                this.heartbeatInterval = Integer.parseInt(lastMessage.split("_interval\":")[1].split(",")[0].strip());
                System.out.println("Heartbeat Interval -> " + heartbeatInterval);
                Vroom vroom = new Vroom();
                Thread vroomThread = new Thread(vroom);
                vroomThread.start();


                // PacketVoiceStateUpdate voiceState = new PacketVoiceStateUpdate();
                // voiceState
                //     .setGuildId("")
                //     .setChannelId("");
                
                // this.send(voiceState.build());
                this.send(Packet.buildIdentityPacket());
            break;
            }

            case 11 -> {
                this.lastHeartbeat = System.currentTimeMillis();
                System.out.println("Heartbeat ACK");
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
