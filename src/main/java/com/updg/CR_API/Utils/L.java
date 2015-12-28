package com.updg.CR_API.Utils;

import com.updg.CR_API.APIPlugin;

import java.util.logging.Level;

/**
 * Created by Alex
 * Date: 18.01.14  20:10
 */
public class L {
    public static void $(Object o) {
        System.out.println(o);
    }

    public static void $(Level info, String s) {
        APIPlugin.getInstance().getLogger().log(info, s);
    }
}
