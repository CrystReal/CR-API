package com.updg.CR_API.Utils;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * Created by Alex
 * Date: 24.10.13  19:10
 */
public class ColorizeArmor {
    public static ItemStack c(ItemStack item, Color color) {
        LeatherArmorMeta lam1 = (LeatherArmorMeta) item.getItemMeta();
        lam1.setColor(color);
        item.setItemMeta(lam1);
        return item;
    }
}
