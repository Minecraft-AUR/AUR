package com.mcaur.velocity.bootstrap;

import com.google.inject.Inject;
import com.mcaur.velocity.bootstrap.events.DownloadEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

@Plugin(id = "aur", name = "AUR", version = "1.0.0",
        url = "https://mcaur.com", description = "Advanced plugin control", authors = {"Minecraft AUR OpenSource Community"})
public class VelocityBootstrap {
    private final ProxyServer server;

    @Inject
    public VelocityBootstrap(ProxyServer server) {
        this.server = server;
    }

    @Subscribe
    public void onInitialize(ProxyInitializeEvent event) {
        server.getEventManager().register(this, new DownloadEvent());
    }
}


