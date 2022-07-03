package com.mcaur.velocity.bootstrap;

import com.ejlchina.okhttps.HTTP;
import com.google.inject.Inject;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

import java.io.File;
import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(id = "aur", name = "AUR", version = "1.0.0",
        url = "https://mcaur.com", description = "Advanced plugin control", authors = {"Minecraft AUR OpenSource Community"})
public class VelocityBootstrap {
    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;
    HTTP http = HTTP.builder().build();

    @Inject
    public VelocityBootstrap(ProxyServer server, Logger logger, Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onInitialize(ProxyInitializeEvent event) {
        server.getEventManager().register(this, new DownloadEvent());
    }
public class DownloadEvent {
    @Subscribe(order = PostOrder.FIRST)
    public void onEnable(ProxyInitializeEvent e) {
        File libsFolder = new File(String.valueOf(dataDirectory), "AUR-libs");
        logger.info("Downloading AUR core and other dependencies...");
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/AUR-core.jar").get().getBody()
                .toFolder(libsFolder)
                .start();
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/OkHttps3.jar").get().getBody()
                .toFolder(libsFolder)
                .start();
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/gson-2.9.0.jar").get().getBody()
                .toFolder(libsFolder)
                .start();
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/logback-classic-1.3.0-alpha16.jar").get().getBody()
                .toFolder(libsFolder)
                .start();
        logger.info("Download successful! Starting server...");
        }
    }
}

