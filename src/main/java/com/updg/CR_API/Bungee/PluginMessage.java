package com.updg.CR_API.Bungee;

import com.updg.CR_API.APIPlugin;
import com.updg.CR_API.Events.BungeeReturnIdEvent;
import com.updg.CR_API.Models.APIPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Alex
 * Date: 18.06.13  19:56
 */
public class PluginMessage implements PluginMessageListener {
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("StreamBungee"))
            return;
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(
                message));

        String channel1 = null;
        try {
            channel1 = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (channel1 != null && channel1.equals("isLoggedBack")) {
            String msg = null;
            Boolean online = false;
            int id = 0, rang = 0, vip = 0, project = 0;
            try {
                msg = in.readUTF();
                online = in.readBoolean();
                if (online) {
                    id = in.readInt();
                    rang = in.readInt();
                    vip = in.readInt();
                   // project = in.readInt();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!online) {
                Bungee.teleportPlayer(Bukkit.getPlayer(msg), "lobby");
            } else if (APIPlugin.getPlayer(msg) != null) {
                APIPlugin.getPlayer(msg).auth(id, rang, vip, project);
                BungeeReturnIdEvent event = new BungeeReturnIdEvent(msg, id, rang, vip);
                Bukkit.getPluginManager().callEvent(event);
            }
        }
    }
}