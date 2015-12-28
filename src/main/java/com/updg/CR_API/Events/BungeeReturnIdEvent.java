package com.updg.CR_API.Events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Alex
 * Date: 18.01.14  20:04
 */
public class BungeeReturnIdEvent extends Event {

    private int vip;
    private int rang;
    private String username;
    private int id;

    private static final HandlerList handlers = new HandlerList();

    public BungeeReturnIdEvent(String username, int id, int rang, int vip) {
        this.username = username;
        this.id = id;
        this.rang = rang;
        this.vip = vip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }
}
