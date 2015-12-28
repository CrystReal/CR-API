package com.updg.CR_API.Bungee;

import com.updg.CR_API.APIPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Alex
 * Date: 18.06.13  19:49
 */
public class Bungee {
    public static void teleportPlayer(Player p, String url) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(url);
        } catch (IOException eee) {
            Bukkit.getLogger().info("You'll never see me!");
        }
        send(p, b);
    }

    public static void isLogged(final Player p, final String player) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(APIPlugin.getInstance(), new Runnable() {
            public void run() {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                try {
                    out.writeUTF("isLogged");
                    out.writeUTF(player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new PluginMessageTask(Bukkit.getOnlinePlayers()[0], b).runTaskAsynchronously(APIPlugin.getInstance());
            }
        }, 1);
    }

    public static void goOnline(int id, String player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("setLoggedIn");
            out.writeInt(id);
            out.writeUTF(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new PluginMessageTask(Bukkit.getOnlinePlayers()[0], b).runTaskAsynchronously(APIPlugin.getInstance());
    }


    public static void goOffline(String player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("setLoggedOut");
            out.writeUTF(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new PluginMessageTask(Bukkit.getOnlinePlayers()[0], b).runTaskAsynchronously(APIPlugin.getInstance());
    }

    public static void send(ByteArrayOutputStream b) {
        new PluginMessageTask(Bukkit.getOnlinePlayers()[0], b).runTaskAsynchronously(APIPlugin.getInstance());
    }

    public static void send(Player p, ByteArrayOutputStream b) {
        new PluginMessageTask(p, b).runTaskAsynchronously(APIPlugin.getInstance());
    }

    public static void teleportToLobby(Player p) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("toLobby");
        } catch (IOException eee) {
            Bukkit.getLogger().info("You'll never see me!");
        }
        send(p, b);
    }
}
