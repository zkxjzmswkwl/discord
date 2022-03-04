package gg.therealm.discord;

public class Vroom implements Runnable {
    private long lastHeartbeat;

    @Override
    public void run() {
        System.out.println("Vroom thread started ^_^");
        for (;;)
        {
            long nowTime = System.currentTimeMillis();

            if (nowTime - Yoink.gimme().theWebSocket().getLastHeartbeat() >= Yoink.gimme().theWebSocket().getHeartbeatInterval()) {
                System.out.println("[!] Sending heartbeat packet to Discord.");
                Yoink.gimme().theWebSocket().send(Packet.buildHeartbeatPacket());
            }

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
