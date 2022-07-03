package com.mcaur.velocity.bootstrap.events;

import com.ejlchina.okhttps.HTTP;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;

import java.io.File;
import java.util.logging.Logger;

public class DownloadEvent {
    HTTP http = HTTP.builder().build();
    Logger logger;
    DataDirectory dataDirectory;

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