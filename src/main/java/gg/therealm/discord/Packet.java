package gg.therealm.discord;

import com.google.gson.JsonObject;

public abstract class Packet {
        /*
      https://discord.com/developers/docs/topics/gateway#identifying
    {
        "op": 2,
        "d": {
                "token": "my_token",
                "intents": 513,
                "properties": {
                "$os": "linux",
                "$browser": "my_library",
                "$device": "my_library"
            }
        }
    }
    */
    public static String buildIdentityPacket() {
        JsonObject object = new JsonObject();
        object.addProperty("op", 2);

        JsonObject d = new JsonObject();
        d.addProperty("token", Yoink.TOKEN);
        d.addProperty("intents", 513);

        JsonObject properties = new JsonObject();
        properties.addProperty("$os", "windows");
        properties.addProperty("$browser", "Realm");
        properties.addProperty("$device", "Realm");

        d.add("properties", properties);
        object.add("d", d);
        return object.toString();
    }

    // "op" for opcode. 11 is the packet opcode for Discord's heartbeat.
    // This tells Discord that we are online ^_^
    public static String buildHeartbeatPacket() {
        JsonObject object = new JsonObject();
        object.addProperty("op", 1);
        return object.toString();
    }
}
