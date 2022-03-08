package gg.therealm.discord.packets;

public class PacketVoiceIdentify extends Packet {
    private String server_id;
    private String user_id;
    private String session_id;
    private String token;
    
    public PacketVoiceIdentify() {
        super(0);
    }

    public String getServerId()     {   return this.server_id;  }
    public String getUserId()       {   return this.user_id;    }
    public String getSessionId()    {   return this.session_id; }
    public String getToken()        {   return this.token;      }
    
}
