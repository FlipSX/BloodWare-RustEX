package com.Blood.Ware.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.*;


public class ChatUtils {
    private static final String prefix = TextFormatting.GRAY + "[" + TextFormatting.BLUE + "BloodWare" + TextFormatting.GRAY + "]: " + TextFormatting.WHITE;//????

    public static void message(final String msg) {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString(prefix + msg));
    }

    public static void sendMessage(final String str) {
        Minecraft.getMinecraft().player.sendMessage((ITextComponent) new TextComponentString(ChatUtils.prefix + str));
    }

    public static void say(final String s) {
        Minecraft.getMinecraft().player.sendChatMessage(s);
    }
}
