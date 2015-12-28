package com.updg.CR_API.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Alex
 * Date: 18.01.14  20:04
 */
public class BungeeIdErrorEvent extends Event {

    private String username;

    private static final HandlerList handlers = new HandlerList();

    public BungeeIdErrorEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
