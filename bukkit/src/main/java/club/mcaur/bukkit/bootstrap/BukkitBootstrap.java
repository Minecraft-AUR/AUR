package club.mcaur.bukkit.bootstrap;

import com.ejlchina.okhttps.HTTP;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitBootstrap extends JavaPlugin {
    HTTP http = HTTP.builder().build();

    public static BukkitBootstrap INSTANCE;

    @Override
    public void onLoad(){
        System.out.println("Downloading AUR core and other dependencies...");
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/AUR-core.jar").get().getBody()
                .toFolder("/AUR/AUR-libs/")
                .start();
        http.sync("https://aur-core.oss-cn-beijing.aliyuncs.com/aur/OkHttps3.jar").get().getBody()
                .toFolder("/AUR/AUR-libs/")
                .start();
        System.out.println("Download successful! Starting server...");
    }


    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {

    }


}
