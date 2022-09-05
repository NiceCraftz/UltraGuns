package tech.calista.ultraguns;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import tech.calista.ultraguns.commands.GunCommand;
import tech.calista.ultraguns.repository.impl.GunRepository;

@Getter
public final class UltraGuns extends JavaPlugin {
    private GunRepository gunRepository;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        gunRepository = new GunRepository(this);
        gunRepository.load();

        GunCommand gunCommand = new GunCommand(this);
        getCommand("guns").setExecutor(gunCommand);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
