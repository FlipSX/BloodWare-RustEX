package com.Blood.Ware.utils.config;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.utils.ChatUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ChatEvent
{
    public static HashSet Player;
    private static Minecraft mc;

    @SubscribeEvent
    public void ChatEvent(final ClientChatEvent clientChatEvent) {
        clientChatEvent.getMessage();
        final String lowerCase = clientChatEvent.getMessage().toLowerCase();
        if (lowerCase.startsWith("/aim")) {
            try {
                BloodWare.instance.settingsManager.getSettingByName(BloodWare.moduleManager.getModule("AimBot"), "Predict").setValDouble((double) Float.parseFloat(lowerCase.split(" ")[1]));
            } catch (Throwable t) {
                ChatUtils.sendMessage("Error");
            }
            clientChatEvent.setMessage("");
        }
            if (lowerCase.startsWith("/smooth")) {
                try {
                    BloodWare.instance.settingsManager.getSettingByName(BloodWare.moduleManager.getModule("AimBot"), "Smooth").setValDouble((double)Float.parseFloat(lowerCase.split(" ")[1]));
                }
                catch (Throwable t) {
                    ChatUtils.sendMessage("Error");
                }
                clientChatEvent.setMessage("");
        }
        else if (lowerCase.startsWith("/vaim")) {
            try {
                BloodWare.instance.settingsManager.getSettingByName(BloodWare.moduleManager.getModule("AimBot"), "VPredict").setValDouble((double)Float.parseFloat(lowerCase.split(" ")[1]));
            }
            catch (Throwable t2) {
                ChatUtils.sendMessage("Error");
            }
            clientChatEvent.setMessage("");
        }
        else if (lowerCase.startsWith("/range")) {
            try {
                BloodWare.instance.settingsManager.getSettingByName(BloodWare.moduleManager.getModule("AimBot"), "Range").setValDouble((double)Float.parseFloat(lowerCase.split(" ")[1]));
            }
            catch (Throwable t3) {
                ChatUtils.sendMessage("Error");
            }
            clientChatEvent.setMessage("");

        }
    }

    static {
        ChatEvent.Player = new HashSet();
        ChatEvent.mc = Minecraft.getMinecraft();
    }
}