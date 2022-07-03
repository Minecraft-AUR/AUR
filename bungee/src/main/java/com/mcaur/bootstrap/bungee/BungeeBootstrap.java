package com.mcaur.bootstrap.bungee;

import com.ejlchina.okhttps.HTTP;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;

public class BungeeBootstrap extends Plugin {
    HTTP http = HTTP.builder().build();

    @Override
    public void onEnable() {
        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdir()) {
                getLogger().severe("Unable to create data folder");
                return;
            }
        }
        File libsFolder = new File(dataFolder, "AUR-libs");
        if (!libsFolder.exists()) {
            if (!libsFolder.mkdir()) {
                getLogger().severe("Unable to create libs folder");
                return;
            }
        }

        getLogger().info("Downloading AUR core and other dependencies...");
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
        getLogger().info("Download successful! Starting server...");
    }
}
