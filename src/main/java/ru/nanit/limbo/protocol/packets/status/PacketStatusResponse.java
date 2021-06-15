package ru.nanit.limbo.protocol.packets.status;

import ru.nanit.limbo.LimboConstants;
import ru.nanit.limbo.protocol.*;
import ru.nanit.limbo.protocol.registry.Version;
import ru.nanit.limbo.server.LimboServer;

public class PacketStatusResponse implements PacketOut {

    private static final String REPLY = "{ \"version\": { \"name\": \"NanoLimbo\", \"protocol\": " + Version.getCurrentSupported().getProtocolNumber() + " }, \"players\": { \"max\": 1, \"online\": 0, \"sample\": [] }, \"description\": \"NanoLimboLite\" }";

    public PacketStatusResponse() {}

    @Override
    public void encode(ByteMessage msg) {
        msg.writeString(REPLY);
    }
}
