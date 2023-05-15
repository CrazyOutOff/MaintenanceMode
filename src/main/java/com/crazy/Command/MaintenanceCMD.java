package com.crazy.Command;

import com.crazy.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.Console;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MaintenanceCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission(command.getPermission())){
                return toggleMaintenance(sender);
            } else {
                player.sendMessage(command.getPermissionMessage());
            }
        } else if (sender instanceof ConsoleCommandSender){
            return toggleMaintenance(sender);
        }
        return false;
    }

    private boolean toggleMaintenance(CommandSender sender) {
        FileConfiguration config = Main.getCustomConfig();
        if (Main.getCustomConfig().getBoolean("maintenance.enabled")){
            Main.getCustomConfig().set("maintenance.enabled", false);
            sender.sendMessage(Main.getInstance().prefix + config.getString("maintenance.disable"));
            return false;
        }
        Main.getCustomConfig().set("maintenance.enabled", true);
        sender.sendMessage(Main.getInstance().prefix + config.getString("maintenance.enable"));
        return false;
    }
}