package gg.therealm.discord;

public class Vroom implements Runnable {

    @Override
    public void run() {
        System.out.println("Vroom thread started ^_^");
        for (;;)
        {
            long nowTime = System.currentTimeMillis();
            long difference = nowTime - Yoink.gimme().theChatWebSocket().getLastHeartbeat();

            if (difference >= Yoink.gimme().theChatWebSocket().getHeartbeatInterval()) {
                System.out.println("[!] Sending heartbeat packet to Discord.");
                Yoink.gimme().theChatWebSocket().send(Packet.buildHeartbeatPacket());
            }

            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
