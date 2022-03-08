package gg.therealm.discord.packets;

public abstract class Packet {
    private int opcode;

    public Packet(int opcode) {
        this.opcode = opcode;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public String build() { return null; };
}
