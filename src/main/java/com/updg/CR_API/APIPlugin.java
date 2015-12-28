package com.updg.CR_API;

import com.updg.CR_API.Bungee.PluginMessage;
import com.updg.CR_API.DataServer.DSUtils;
import com.updg.CR_API.Events.Listeners;
import com.updg.CR_API.MQ.listenerFromAllServers;
import com.updg.CR_API.MQ.qConnection;
import com.updg.CR_API.Models.APIPlayer;
import com.updg.CR_API.Models.APIProject;
import com.updg.CR_API.Utils.BlockCleaner;
import com.updg.CR_API.Utils.FileUtils;
import com.updg.CR_API.Utils.L;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.shininet.bukkit.itemrenamer.api.ItemsListener;
import org.shininet.bukkit.itemrenamer.api.RenamerAPI;
import org.shininet.bukkit.itemrenamer.api.RenamerPriority;
import org.shininet.bukkit.itemrenamer.api.RenamerSnapshot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * User: Alex
 * Date: 14.06.13
 * Time: 23:32
 */
public class APIPlugin extends JavaPlugin {

    private static APIPlugin instance;

    public HashMap<String, APIPlayer> authPlayers = new HashMap<String, APIPlayer>();
    public HashMap<Integer, APIProject> projects = new HashMap<Integer, APIProject>();

    public static APIPlugin getInstance() {
        return instance;
    }

    public void onLoad() {
        ArrayList<String> worlds = new ArrayList<String>();
        File folder = new File("./orig");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null)
            return;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isDirectory()) {
                worlds.add(listOfFile.getName());
            }
        }
        File tmp;
        for (String item : worlds) {
            tmp = new File(getServer().getWorldContainer(), item);
            FileUtils.deleteDirectory(tmp);
            try {
                File srcFolder = new File("./orig/" + item);
                File destFolder = new File(getServer().getWorldContainer(), item);
                FileUtils.copyFolder(srcFolder, destFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        L.$("Worlds was reset");
    }


    public void onEnable() {
        instance = this;
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "StreamBungee");
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(this,
                "StreamBungee", new PluginMessage());

        /*this.getCommand("lobby").setExecutor(new Lobby());
        this.getCommand("hub").setExecutor(new Lobby());  */

        try {
            DSUtils.connect(getConfig().getString("dataServer.host", "localhost"), getConfig().getInt("dataServer.port", 9898));
        } catch (IOException e) {
            getLogger().log(Level.WARNING, "FATAL ERROR: Cant connect to the DATA server");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        qConnection.connect(this.getConfig().getString("qServerHost", "localhost"));
        new listenerFromAllServers().start();

        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        RenamerAPI.getAPI().addListener(this, RenamerPriority.POST_NORMAL,
                new ItemsListener() {
                    @Override
                    public void onItemsSending(Player player, RenamerSnapshot snapshot) {
                        BlockCleaner.clear(snapshot);
                        BlockCleaner.hideLore(snapshot);
                    }
                });

        L.$("CrystReal API loaded");
    }

    public static void connectToMQ() {
        qConnection.connect(APIPlugin.getInstance().getConfig().getString("qServerHost", "localhost"));
    }

    public static APIPlayer getPlayer(String name) {
        if (APIPlugin.getInstance().authPlayers.containsKey(name.toLowerCase()))
            return APIPlugin.getInstance().authPlayers.get(name.toLowerCase());
        else
            return new APIPlayer();
    }

    public static APIProject getProject(int id) {
        if (APIPlugin.getInstance().projects.containsKey(id))
            return APIPlugin.getInstance().projects.get(id);
        else
            return new APIProject();
    }
}
