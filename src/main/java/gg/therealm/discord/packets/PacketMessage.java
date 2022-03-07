package gg.therealm.discord.packets;

import java.util.List;

public class PacketMessage extends Packet {
    private int type;
    private boolean tts;
    private String timestamp;
    private boolean pinned;
    private String nonce;
    private List<Object> mentions;
    private List<Object> mention_roles;
    private boolean mention_everyone;
    // TODO: Create a data structure for this..?
    private Object member;
    private String id;
    private int flags;
    private List<Object> embeds;
    private String content;
    private List<Object> components;
    private String channel_id;
    // TODO: Create a data structure for this..?
    private Object author;
    private List<Object> attachments;
    private String guild_id;

    public PacketMessage() {
        super(0);
    }

    public String getContent() {
        if (this.content.startsWith("\"")) {
            this.content = this.content.replace("\"", "");
        }
        return this.content;
    }

    public String getChannelId() {
        return this.channel_id;
    }
}
