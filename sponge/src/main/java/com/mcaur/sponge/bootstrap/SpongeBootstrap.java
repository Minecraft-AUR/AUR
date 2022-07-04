package com.mcaur.sponge.bootstrap;

import com.ejlchina.okhttps.HTTP;
import com.google.inject.Inject;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin("AUR")
public class SpongeBootstrap {
    private final PluginContainer container;
    private final Logger logger;
    private Path configDirectory;
    HTTP http = HTTP.builder().build();

    @Inject
    SpongeBootstrap(final PluginContainer container, final Logger logger) {
        this.container = container;
        this.logger = logger;
    }
    
    @Listener
    public void onConstructPlugin(final ConstructPluginEvent event) {
    }

    @Listener
    public void onServerStarting(final StartingEngineEvent<Server> event){
        logger.info("Downloading AUR core and other dependencies...");
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/AUR-core.jar").get().getBody()
                .toFolder(".//config/aur/AUR-libs")
                .start();
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/OkHttps3.jar").get().getBody()
                .toFolder(".//config/aur/AUR-libs")
                .start();
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/gson-2.9.0.jar").get().getBody()
                .toFolder(".//config/aur/AUR-libs")
                .start();
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/logback-classic-1.3.0-alpha16.jar").get().getBody()
                .toFolder(".//config/aur/AUR-libs")
                .start();
        logger.info("Download successful! Starting server...");
    }

    @Listener
    public void onServerStopping(final StoppingEngineEvent<Server> event) {

    }

}
