package ru.nanit.limbo.configuration;

import napi.configurate.Configuration;
import napi.configurate.source.ConfigSources;
import napi.configurate.yaml.YamlConfiguration;
import ru.nanit.limbo.server.data.*;
import ru.nanit.limbo.util.Colors;

import java.net.SocketAddress;
import java.nio.file.Path;

public final class LimboConfig {

    private final Path root;

    private SocketAddress address;

    private Position spawnPosition;

    private InfoForwarding infoForwarding;
    private long readTimeout;
    private int debugLevel = 3;

    private boolean useEpoll;
    private int bossGroupSize;
    private int workerGroupSize;

    public LimboConfig(Path root){
        this.root = root;
    }

    public void load() throws Exception {
        Configuration conf = YamlConfiguration.builder()
                .source(ConfigSources.resource("/settings.yml", this).copyTo(root))
                .build();

        conf.reload();

        address = conf.getNode("bind").getValue(SocketAddress.class);
        spawnPosition = conf.getNode("spawnPosition").getValue(Position.class);

        infoForwarding = conf.getNode("infoForwarding").getValue(InfoForwarding.class);
        readTimeout = conf.getNode("readTimeout").getLong();
        debugLevel = conf.getNode("debugLevel").getInt();

        useEpoll = conf.getNode("netty", "useEpoll").getBoolean(true);
        bossGroupSize = conf.getNode("netty", "threads", "bossGroup").getInt(1);
        workerGroupSize = conf.getNode("netty", "threads", "workerGroup").getInt(4);
    }

    public SocketAddress getAddress() {
        return address;
    }

    public Position getSpawnPosition() {
        return spawnPosition;
    }

    public InfoForwarding getInfoForwarding() {
        return infoForwarding;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public int getDebugLevel() {
        return debugLevel;
    }

    public boolean isUseEpoll() {
        return useEpoll;
    }

    public int getBossGroupSize() {
        return bossGroupSize;
    }

    public int getWorkerGroupSize() {
        return workerGroupSize;
    }
}
