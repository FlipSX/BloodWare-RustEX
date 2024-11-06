package com.Blood.Ware.module.misc;

import com.Blood.Ware.miscs.Discord;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.utils.ChatUtils;

public class DiscordRPC extends Module
{
    @Override
    public void onEnable() {
        super.onEnable();
        try {
            Discord.startRPC();
        }
        catch (UnsatisfiedLinkError | NoClassDefFoundError unsatisfiedLinkError) {
            ChatUtils.sendMessage("Error: not found discord-rps.dll in natives");
            this.onDisableR();
        }
    }

    public DiscordRPC() {
        super("DiscordRPC", Category.OUTHER);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        try {
            Discord.stopRPC();
        }
        catch (NoClassDefFoundError noClassDefFoundError) {}
    }
}