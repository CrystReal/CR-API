package com.updg.CR_API.Events;

import com.updg.CR_API.APIPlugin;
import com.updg.CR_API.Bungee.Bungee;
import com.updg.CR_API.Models.APIPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;

/**
 * Created by Alex
 * Date: 18.01.14  20:49
 */
public class Listeners implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(final PlayerJoinEvent event) {
        final Player user = event.getPlayer();
        final String username = user.getName().toLowerCase();
        Bungee.isLogged(user, user.getName().toLowerCase());
        APIPlugin.getInstance().authPlayers.put(user.getName().toLowerCase(), new APIPlayer(event.getPlayer()));
        Bukkit.getScheduler().runTaskLaterAsynchronously(APIPlugin.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (!APIPlugin.getInstance().authPlayers.containsKey(username)) {
                    BungeeIdErrorEvent event = new BungeeIdErrorEvent(username);
                    Bukkit.getPluginManager().callEvent(event);
                }
            }
        }, 90);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        if (APIPlugin.getInstance().authPlayers.containsKey(event.getPlayer().getName())) {
            APIPlugin.getInstance().authPlayers.remove(event.getPlayer().getName());
        }
    }

    @EventHandler
    public void onSign(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
                Sign sign = (Sign) e.getClickedBlock().getState();
                if (sign.getLine(0).contains("[Lobby]")) {
                    Bungee.teleportToLobby(e.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Player user = event.getPlayer();
        APIPlayer p = APIPlugin.getPlayer(user.getName());
        String[] split = event.getMessage().split(" ");
        if ((split[0].equalsIgnoreCase("/help") || split[0].equalsIgnoreCase("/?")) && !event.getPlayer().isOp()) {
            event.setCancelled(true);
            user.sendMessage("Вы можете просмотреть команды на " + p.getProject().getSiteUrl());
        }
        if ((split[0].equalsIgnoreCase("/op") || split[0].equalsIgnoreCase("/deop")) && !event.getPlayer().isOp()) {
            event.setCancelled(true);
            user.sendMessage("Команда не найдена. Вы можете просмотреть команды на " + p.getProject().getSiteUrl());
        }
        if ((split[0].equalsIgnoreCase("/plugins") || split[0].equalsIgnoreCase("/pl")) && !event.getPlayer().isOp()) {
            user.sendMessage("Команда не найдена. Вы можете просмотреть команды на " + p.getProject().getSiteUrl());
            event.setCancelled(true);
        }
        if ((split[0].equalsIgnoreCase("/ver") || split[0].equalsIgnoreCase("/version")) && !event.getPlayer().isOp()) {
            user.sendMessage("Crystal Reality Platform");
            event.setCancelled(true);
        }
        if (split[0].equalsIgnoreCase("/reload") && !event.getPlayer().isOp()) {
            user.sendMessage("Команда не найдена. Вы можете просмотреть команды на " + p.getProject().getSiteUrl());
            event.setCancelled(true);
        }
        if (split[0].equalsIgnoreCase("/stop") && !event.getPlayer().isOp()) {
            user.sendMessage("Команда не найдена. Вы можете просмотреть команды на " + p.getProject().getSiteUrl());
            event.setCancelled(true);
        }
        if (split[0].equalsIgnoreCase("/give") && !event.getPlayer().isOp()) {
            user.sendMessage("Команда не найдена. Вы можете просмотреть команды на " + p.getProject().getSiteUrl());
            event.setCancelled(true);
        }
    }
}
