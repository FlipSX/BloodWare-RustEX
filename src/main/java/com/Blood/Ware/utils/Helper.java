//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils;

import java.util.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import net.minecraft.network.*;

public interface Helper
{
    public static final Gui gui = new Gui();
    public static final Random random = new Random();
    public static final ScaledResolution sr = new ScaledResolution(MinecraftHelper.mc);
    public static final TimerHelper timerHelper = new TimerHelper();
    
    Minecraft mc();
    
    default void sendPacket(final Packet packet) {
        MinecraftHelper.mc.player.connection.sendPacket(packet);
    }
}
