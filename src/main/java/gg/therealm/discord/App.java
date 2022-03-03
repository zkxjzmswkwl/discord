package gg.therealm.discord;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class App 
{
    public static final String PUBLIC_KEY = "e6617b9412a9dcb3edf97a9325f8e3ec3e824c70b29567df8e1fe2e03ac29601";
    public static final String TOKEN = "OTQ5MDQ1NDc2ODY1NTA3NDE5.YiEpeg.B7FPPyXiRIGW1YvwwtP2cYRJN_c";
    public static final String BASE_AUTH_URL = "https://discord.com/api/oauth2/authorize";
    public static final String TOKEN_URL = "https://discord.com/api/oauth2/token";
    public static final String APPLICATION_ID = "949045476865507419";
    public static final String COMMAND_URL = "https://discord.com/api/v8/applications/" + APPLICATION_ID + "/commands";
    public static Gson gson;



    public static void main( String[] args ) throws IOException, URISyntaxException
    {
        gson = new Gson();
        Web web = new Web();
        Path file = Path.of("cmd.json");
        String json = Files.readString(file);

        System.out.println(json);
        web.setAuthorization(TOKEN);

        WebSocket ws = Yoink.gimme().theWebSocket();
        // String a = web.postReq(COMMAND_URL, json);
        // System.out.println(a);
    }
}
