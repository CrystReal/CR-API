package com.updg.CR_API.Models;

import com.updg.CR_API.APIPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Alex
 * Date: 31.01.14  2:35
 */
public class APIPlayer {
    private Player bukkitModel;
    private int id = 0;

    private int rang = 0;
    private int vip = 0;
    private int project = 0;

    public APIPlayer() {
    }

    public APIPlayer(Player p) {
        this.bukkitModel = p;
    }

    public void auth(int id, int rang, int vip, int project) {
        this.id = id;
        this.rang = rang;
        this.vip = vip;
        this.project = project;
    }

    public String getPrefix() {
        if (rang != 0) {
            switch (rang) {
                case 1:
                    return ChatColor.GRAY + "[" + ChatColor.GOLD + "Администратор" + ChatColor.GRAY + "] " + ChatColor.RESET;
                case 2:
                    return ChatColor.GRAY + "[" + ChatColor.GREEN + "Модератор" + ChatColor.GRAY + "] " + ChatColor.RESET;
                case 3:
                    return ChatColor.GRAY + "[" + ChatColor.BLUE + "Помощник" + ChatColor.GRAY + "] " + ChatColor.RESET;
                default:
                    return "";
            }
        } else if (vip != 0) {
            switch (vip) {
                case 1:
                    return ChatColor.GRAY + "[" + ChatColor.YELLOW + "VIP" + ChatColor.GRAY + "] " + ChatColor.RESET;
                case 2:
                    return ChatColor.GRAY + "[" + ChatColor.YELLOW + "VIP+" + ChatColor.GRAY + "] " + ChatColor.RESET;
                case 3:
                    return ChatColor.GRAY + "[" + ChatColor.YELLOW + "Master" + ChatColor.GRAY + "] " + ChatColor.RESET;
                case 10:
                    return ChatColor.GRAY + "[" + ChatColor.AQUA + "Друг Проекта" + ChatColor.GRAY + "] " + ChatColor.RESET;
                case 11:
                    return ChatColor.GRAY + "[" + ChatColor.AQUA + "Стример" + ChatColor.GRAY + "] " + ChatColor.RESET;

                default:
                    return "";
            }
        } else {
            return "";
        }
    }

    public ChatColor getNickColor() {
        if (rang != 0) {
            switch (rang) {
                case 1:
                    return ChatColor.GOLD;
                case 2:
                    return ChatColor.GREEN;
                case 3:
                    return ChatColor.AQUA;
                case 4:
                    return ChatColor.AQUA;
                default:
                    return ChatColor.GRAY;
            }
        } else if (vip != 0) {
            switch (vip) {
                case 1:
                    return ChatColor.YELLOW;
                case 2:
                    return ChatColor.YELLOW;
                case 3:
                    return ChatColor.YELLOW;
                default:
                    return ChatColor.GRAY;
            }
        } else {
            return ChatColor.GRAY;
        }
    }

    public ChatColor getMessageColor() {
        if (rang != 0) {
            switch (rang) {
                case 1:
                    return ChatColor.WHITE;
                case 2:
                    return ChatColor.WHITE;
                case 3:
                    return ChatColor.WHITE;
                case 4:
                    return ChatColor.WHITE;
                default:
                    return ChatColor.WHITE;
            }
        } else if (vip != 0) {
            switch (vip) {
                case 1:
                    return ChatColor.WHITE;
                case 2:
                    return ChatColor.WHITE;
                case 3:
                    return ChatColor.WHITE;
                default:
                    return ChatColor.WHITE;
            }
        } else {
            return ChatColor.GRAY;
        }
    }

    public ChatColor getColonColor() {
        return ChatColor.GRAY;
    }

    public APIProject getProject() {
        return APIPlugin.getProject(this.project);
    }
}
