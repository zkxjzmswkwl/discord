package gg.therealm.discord;

import java.io.IOException;
import java.net.URISyntaxException;


public class App 
{
    public static void main( String[] args ) throws IOException, URISyntaxException
    {
        WebSocket ws = Yoink.gimme().theChatWebSocket();
    }
}
