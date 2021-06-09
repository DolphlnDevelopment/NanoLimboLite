package ru.nanit.limbo.protocol.packets.play;

import ru.nanit.limbo.protocol.ByteMessage;
import ru.nanit.limbo.protocol.PacketOut;

import java.util.concurrent.ThreadLocalRandom;

public class PacketPlayerPositionAndLook implements PacketOut {

    private byte flags = 0x08;

    @Override
    public void encode(ByteMessage msg) {
        msg.writeDouble(0);
        msg.writeDouble(64);
        msg.writeDouble(0);
        msg.writeFloat(0);
        msg.writeFloat(0);
        msg.writeByte(flags);
        msg.writeVarInt(ThreadLocalRandom.current().nextInt());
    }

}
