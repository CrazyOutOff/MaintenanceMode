package com.crazy;

import com.crazy.Command.MaintenanceCMD;
import com.crazy.Listeners.EventListeners;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public static Main instance;

    public static String prefix = "MaintenanceMode | ";

    public Logger logger = this.getLogger();

    private File customConfigFile;
    private static FileConfiguration customConfig;


    @Override
    public void onEnable() {
        this.getLogger().info( prefix + "Initializing plugin");

        this.getCommand("maintenance").setExecutor(new MaintenanceCMD());

        this.createCustomConfig();

        this.setupListeners();

        prefix = customConfig.getString("maintenance.prefix");
    }
    public static Main getInstance() {
        return instance;
    }

    public Logger logger() {return logger;}

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource(customConfigFile.getName(), true);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static FileConfiguration getCustomConfig() {
        return customConfig;
    }

    private void setupListeners() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new EventListeners(), this);
    }
}