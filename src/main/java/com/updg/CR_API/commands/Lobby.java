package com.updg.CR_API.commands;

import com.updg.CR_API.Bungee.Bungee;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 14.06.13
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
public class Lobby implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Bungee.teleportPlayer((Player) sender, "lobby");
        }
        return true;
    }
}
