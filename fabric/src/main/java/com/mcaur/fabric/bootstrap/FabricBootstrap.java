package com.mcaur.fabric.bootstrap;

import com.ejlchina.okhttps.HTTP;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FabricBootstrap implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("aur");

    HTTP http = HTTP.builder().build();

    @Override
    public void onInitialize() {
        LOGGER.info("Downloading AUR core and other dependencies...");
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/AUR-core.jar").get().getBody()
                .toFolder("//AUR/AUR-libs")
                .start();
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/OkHttps3.jar").get().getBody()
                .toFolder("//AUR/AUR-libs")
                .start();
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/gson-2.9.0.jar").get().getBody()
                .toFolder("//AUR/AUR-libs")
                .start();
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/logback-classic-1.3.0-alpha16.jar").get().getBody()
                .toFolder("//AUR/AUR-libs")
                .start();
        LOGGER.info("Download successful! Starting server...");
    }
}
