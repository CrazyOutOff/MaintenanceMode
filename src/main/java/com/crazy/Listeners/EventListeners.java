package com.crazy.Listeners;

import com.crazy.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;

import java.awt.*;

public class EventListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        FileConfiguration config = Main.getCustomConfig();
        Boolean enabled = config.getBoolean("maintenance.enabled");
        String perms = config.getString("maintenance.permissions");
        String reason = config.getString("maintenance.reason");

        if (enabled && !player.hasPermission(perms)) {
            player.kickPlayer(reason);
        }
    }

    @EventHandler
    public void ServerListPingEvent (ServerListPingEvent e){
        FileConfiguration config = Main.getCustomConfig();
        Boolean enabled = config.getBoolean("maintenance.enabled");
        String motd = config.getString("maintenance.motd");

        if (enabled){
            e.setMotd(motd);
        }

    }
}
