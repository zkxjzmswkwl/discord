package gg.therealm.discord.packets;

public class PacketVoiceServerUpdate extends Packet {
    private String token;
    private String guild_id;
    private String endpoint;

    public PacketVoiceServerUpdate() {
        super(2);
    }

    public String getToken()    {   return this.token;      }
    public String getGuildId()  {   return this.guild_id;   }
    public String getEndpoint() {   return this.endpoint;   }
}
