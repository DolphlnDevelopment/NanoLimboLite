package ru.nanit.limbo.protocol.packets.status;

import ru.nanit.limbo.LimboConstants;
import ru.nanit.limbo.protocol.*;
import ru.nanit.limbo.protocol.registry.Version;
import ru.nanit.limbo.server.LimboServer;

public class PacketStatusResponse implements PacketOut {

    private static final String TEMPLATE = "{ \"version\": { \"name\": \"%s\", \"protocol\": %d }, \"players\": { \"max\": %d, \"online\": %d, \"sample\": [] }, \"description\": %s }";

    private LimboServer server;

    public PacketStatusResponse(){ }

    public PacketStatusResponse(LimboServer server){
        this.server = server;
    }

    @Override
    public void encode(ByteMessage msg) {
        String ver = LimboConstants.version;
        String desc = LimboConstants.motd;
        String json = getResponseJson(ver, Version.getCurrentSupported().getProtocolNumber(),
                LimboConstants.maxPlayers, server.getConnections().getCount(), desc);

        msg.writeString(json);
    }

    private String getResponseJson(String version, int protocol, int maxPlayers, int online, String description){
        return String.format(TEMPLATE, version, protocol, maxPlayers, online, description);
    }
}
