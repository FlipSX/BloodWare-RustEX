package com.Blood.Ware.miscs;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.miscs.discordhelper.*;

import net.minecraft.client.Minecraft;

import java.util.Calendar;

import static com.Blood.Ware.utils.Font.CastomFontUtils.fr4;


public class Discord {
    public static String discordID = "1292514930138021910";
    public static DiscordRichPresence discordRichP = new DiscordRichPresence();

    public static DiscordRPC discordRPC = DiscordRPC.INSTANCE;


    public static void startRPC() {

        DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1:" + var1 + ", var2: " + var2));


        discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);

        discordRichP.startTimestamp = System.currentTimeMillis() / 1000L;
        discordRichP.details = "Build: Beta";
        discordRichP.largeImageKey = "https://i.imgur.com/OPAoihs.gif";
        discordRichP.largeImageText = "BloodWare";
        discordRichP.state = "UID: СВО ЗОВ НАШ СЛОН";
        discordRPC.Discord_UpdatePresence(discordRichP);
    }

    public static void stopRPC() {
        discordRPC.Discord_Shutdown();
        discordRPC.Discord_ClearPresence();
    }
}
