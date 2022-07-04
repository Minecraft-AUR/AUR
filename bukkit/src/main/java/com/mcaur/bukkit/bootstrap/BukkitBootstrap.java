package com.mcaur.bukkit.bootstrap;

import com.ejlchina.okhttps.HTTP;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BukkitBootstrap extends JavaPlugin {

    @Override
    public void onLoad(){
        File dataFolder = getDataFolder();
        HTTP http = HTTP.builder().build();

        if (!dataFolder.exists()) {
            if (!dataFolder.mkdir()) {
                getLogger().severe("Unable to create data folder,Disable plugin...");
                getServer().getPluginManager().disablePlugin(this);
                return;
            }
        }
        File libsFolder = new File(dataFolder, "AUR-libs");
        if (!libsFolder.exists()) {
            if (!libsFolder.mkdir()) {
                getLogger().severe("Unable to create libs folder,Disable plugin...");
                getServer().getPluginManager().disablePlugin(this);
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


    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }


}
