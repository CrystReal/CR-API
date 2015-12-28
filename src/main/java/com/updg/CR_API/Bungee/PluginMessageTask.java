package com.updg.CR_API.Bungee;

import com.updg.CR_API.Utils.L;
import net.minecraft.server.v1_7_R2.PacketPlayOutCustomPayload;
import org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;

public class PluginMessageTask extends BukkitRunnable {
    private final ByteArrayOutputStream bytes;
    private final Player player;

    public PluginMessageTask(Player player, ByteArrayOutputStream bytes) {
        this.bytes = bytes;
        this.player = player;
    }

    public void run() {
        L.$("Send to bungee");
        //player.sendPluginMessage(DefkillPlugin.pl, "BungeeCord", this.bytes.toByteArray());
        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("BungeeCord", this.bytes.toByteArray());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}