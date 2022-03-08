package gg.therealm.discord.packets;

import com.google.gson.JsonObject;

public class PacketVoiceStateUpdate extends Packet {

    private String guild_id;
    private String channel_id;
    private boolean self_mute = false;
    private boolean self_deaf = false;

    public PacketVoiceStateUpdate() {
        super(4);
    }

    public String getGuildId() {
        return this.guild_id;
    }

    public String getChannelId() {
        return this.channel_id;
    }

    public PacketVoiceStateUpdate setGuildId(String guildId) {
        this.guild_id = guildId;
        return this;
    }

    public PacketVoiceStateUpdate setChannelId(String channelId) {
        this.channel_id = channelId;
        return this;
    }

    @Override
    public String build() {
        JsonObject buffer = new JsonObject();
        buffer.addProperty("op", super.getOpcode());

        JsonObject d = new JsonObject();
        d.addProperty("guild_id", this.guild_id);
        d.addProperty("channel_id", this.channel_id);
        d.addProperty("self_mute", false);
        d.addProperty("self_deaf", false);

        buffer.add("d", d);
        return buffer.toString();
    }
}
