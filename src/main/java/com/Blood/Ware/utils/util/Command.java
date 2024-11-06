//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils.util;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public abstract class Command extends Module
{
    public static String prefix;
    public static final Minecraft mc;

    public Command(String name, Category category) {
        super(name, category);
    }

    public abstract void onClientCommand(final String p0, final String[] p1) throws Exception;
    
    public static void sendClientSideMessage(final String str) {
        if (Command.mc.player == null || Command.mc.world == null) {
            return;
        }
        Command.mc.player.sendMessage((ITextComponent)new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append("[BloodWare] ").append(ChatFormatting.WHITE).append(str))));
    }
    
    public static String getClientPrefix() {
        return Command.prefix;
    }
    
    public static void setClientPrefix(final String prefix) {
        Command.prefix = prefix;
    }
    
    public abstract String getClientSyntax();
    
    static {
        mc = Minecraft.getMinecraft();
        Command.prefix = ".";
    }
    
    public abstract String[] getClientAlias();
}
