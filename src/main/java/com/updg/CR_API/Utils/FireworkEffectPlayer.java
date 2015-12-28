package com.updg.CR_API.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.updg.CR_API.APIPlugin;
import net.minecraft.server.v1_7_R2.Entity;
import net.minecraft.server.v1_7_R2.MobEffect;
import net.minecraft.server.v1_7_R2.PacketPlayOutEntityEffect;
import org.bukkit.EntityEffect;
import org.bukkit.FireworkEffect;
import org.bukkit.craftbukkit.v1_7_R2.CraftWorld;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * FireworkEffectPlayer v1.0
 * <p/>
 * FireworkEffectPlayer provides a thread-safe and (reasonably) version independant way to instantly explode a FireworkEffect at a given location.
 * You are welcome to use, redistribute, modify and destroy your own copies of this source with the following conditions:
 * <p/>
 * 1. No warranty is given or implied.
 * 2. All damage is your own responsibility.
 * 3. You provide credit publicly to the original source should you release the plugin.
 *
 * @author codename_B
 */
public class FireworkEffectPlayer {

    /*
     * Example use:
     *
     * public class FireWorkPlugin implements Listener {
     *
     * FireworkEffectPlayer fplayer = new FireworkEffectPlayer();
     *
     * @EventHandler
     * public void onPlayerLogin(PlayerLoginEvent event) {
     *   fplayer.playFirework(event.getPlayer().getWorld(), event.getPlayer.getLocation(), Util.getRandomFireworkEffect());
     * }
     *
     * }
     */

    // internal references, performance improvements
    private Method world_getHandle = null;
    private Method nms_world_broadcastEntityEffect = null;
    private Method firework_getHandle = null;

    /**
     * Play a pretty firework at the location with the FireworkEffect when called
     *
     * @param location
     * @param effect
     * @throws Exception
     */
    public void playFirework(Location location, FireworkEffect effect) {
        final Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkMeta fMeta = firework.getFireworkMeta();
        fMeta.clearEffects();
        fMeta.setPower(1);
        fMeta.addEffect(effect);
        firework.setFireworkMeta(fMeta);
       /* new BukkitRunnable() {
            @Override
            public void run() {*/
                firework.detonate();
          /*  }
        }.runTaskLater(APIPlugin.getInstance(), 1);*/
    }

    /**
     * Internal method, used as shorthand to grab our method in a nice friendly manner
     *
     * @param cl
     * @param method
     * @return Method (or null)
     */
    private static Method getMethod(Class<?> cl, String method) {
        for (Method m : cl.getMethods()) {
            if (m.getName().equals(method)) {
                return m;
            }
        }
        return null;
    }

}