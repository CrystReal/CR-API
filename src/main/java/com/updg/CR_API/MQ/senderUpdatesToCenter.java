package com.updg.CR_API.MQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.updg.CR_API.APIPlugin;
import com.updg.CR_API.Utils.L;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Alex
 * Date: 29.10.13  21:51
 */
public class senderUpdatesToCenter {
    private Channel c = null;
    public static senderUpdatesToCenter instance = null;

    public senderUpdatesToCenter() {
        if (instance != null)
            return;
        try {
            if(qConnection.c == null){
                APIPlugin.connectToMQ();
            }
            c = qConnection.c.createChannel();
        } catch (IOException e) {
            L.$(Level.INFO, "FATAL ERROR: Cant create channel for sender to all servers");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(APIPlugin.getInstance());
            return;
        }
        try {
            c.queueDeclare("serversUpdates", true, false, false, null);
        } catch (IOException e) {
            L.$(Level.INFO, "FATAL ERROR: cant declare channel for sender to all servers");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(APIPlugin.getInstance());
        }
    }

    public static void send(String msg) {
        if (instance == null)
            instance = new senderUpdatesToCenter();
        try {
            instance.c.basicPublish("", "serversUpdates",
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    msg.getBytes());
        } catch (IOException e) {
            L.$(Level.INFO, "ERROR: cant sent message to all servers");
            e.printStackTrace();
        }
    }
}
