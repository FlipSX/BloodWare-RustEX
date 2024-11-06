package com.Blood.Ware.utils;

import static com.Blood.Ware.utils.MinecraftHelper.mc;

public class NullCheck implements Mc {
    public static boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }
}
