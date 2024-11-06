//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.module.hud;

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.ColorUtils;
import com.Blood.Ware.utils.Font.CastomFontUtils;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.TimerUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.ServerPinger;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static com.Blood.Ware.utils.Font.CastomFontUtils.*;

public class Watermark extends Module {
    public static List Modes;
    private final FontRenderer fr;
    public TimerUtils timer;

    static {
        Watermark.Modes = new ArrayList();
    }



    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent.Text text) {
        final String valString = BloodWare.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (text.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            if (Objects.equals(valString, "Anim")) {
                final int[] rainbow = getRainbow(5, 0.1f);
                RGBtoHex(rainbow[0], rainbow[1], rainbow[2]);
                final String format = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                String str = "B";
                switch ((int) (System.currentTimeMillis() / 400L % 42L)) {
                    case 1: {
                        str = "B ";
                        break;
                    }
                    case 2: {
                        str = "B3 ";
                        break;
                    }
                    case 3: {
                        str = "Bl ";
                        break;
                    }
                    case 4: {
                        str = "Blo\\ ";
                        break;
                    }
                    case 5: {
                        str = "Blo\\/ ";
                        break;
                    }
                    case 6: {
                        str = "Bloo ";
                        break;
                    }
                    case 7: {
                        str = "Bloo3 ";
                        break;
                    }
                    case 8: {
                        str = "Blood| ";
                        break;
                    }
                    case 9: {
                        str = "Blood|2 ";
                        break;
                    }
                    case 10: {
                        str = "Blood|_ ";
                        break;
                    }
                    case 11: {
                        str = "BloodW ";
                        break;
                    }
                    case 12: {
                        str = "BloodW/ ";
                        break;
                    }
                    case 13: {
                        str = "BloodWa ";
                        break;
                    }
                    case 14: {
                        str = "BloodWa@# ";
                        break;
                    }
                    case 15: {
                        str = "BloodWar ";
                        break;
                    }
                    case 16: {
                        str = "BloodWar|/ ";
                        break;
                    }
                    case 17: {
                        str = "BloodWare";
                        break;
                    }
                    case 18: {
                        str = "BloodWar|/ ";
                        break;
                    }
                    case 19: {
                        str = "BloodWar ";
                        break;
                    }
                    case 20: {
                        str = "BloodWa@# ";
                        break;
                    }
                    case 21: {
                        str = "BloodWa ";
                        break;
                    }
                    case 22: {
                        str = "BloodW/ ";
                        break;
                    }
                    case 23: {
                        str = "BloodW ";
                        break;
                    }
                    case 24: {
                        str = "Blood|_ ";
                        break;
                    }
                    case 25: {
                        str = "Blood|2 ";
                        break;
                    }
                    case 26: {
                        str = "Blood| ";
                        break;
                    }
                    case 27: {
                        str = "Bloo3 ";
                        break;
                    }
                    case 28: {
                        str = "Bloo ";
                        break;
                    }
                    case 29: {
                        str = "Blo\\/ ";
                        break;
                    }
                    case 30: {
                        str = "Blo\\ ";
                        break;
                    }
                    case 31: {
                        str = "Bl ";
                        break;
                    }
                    case 32: {
                        str = "B3 ";
                        break;
                    }
                    case 33: {
                        str = "B ";
                        break;

                    }
                }


                ServerData server = mc.getCurrentServerData();
                long ping = 2281337;
                if (server != null) {
                    if (this.timer.isDelay((long) 400L % 42L) && mc.world.isRemote) {
                        new Thread(() -> {
                            try {
                                ServerPinger sp = new ServerPinger();
                                sp.ping(server);
                            } catch (UnknownHostException ignored) {
                            }
                        }).start();
                        this.timer.setLastMS();
                    }
                    ping = server.pingToServer;
                }

                final String value = String.valueOf(new StringBuilder().append(str).append(" | ").append(format).append(" |  Fps ").append(Minecraft.getDebugFPS()).append(" |  Ping ").append(ping));
                RenderUtil.drawSmoothRect(5, 6, fr5.getStringWidth(value) + 19, 21, new Color(0, 0, 0, 255).getRGB());
                RenderUtil.drawSmoothRect(5, 9, fr5.getStringWidth(value) + 19, 6, new Color(255, 0, 0, 255).getRGB());
                fr5.drawStringWithShadow(value, 8.0f, 13.0f, -1);
            } else if (Objects.equals(valString, "Skeet")) {
                final int[] rainbow3 = getRainbow(5, 0.1f);
                RGBtoHex(rainbow3[0], rainbow3[1], rainbow3[2]);
                final StringBuilder obj = new StringBuilder(String.valueOf(new StringBuilder().append((Object) new StringBuilder(String.valueOf(new StringBuilder().append((Object) new StringBuilder(String.valueOf(new StringBuilder().append((Object) new StringBuilder().append("BloodWare").append(" ").append("Beta")).append(" | ").append(mc.player.getName()).append(" | Fps ").append(Minecraft.getDebugFPS()).append(" | ").append("UID: ").append(BloodWare.getUID())))))))));
                final Minecraft mc = Watermark.mc;
                final String value3 = String.valueOf(obj);
                final float n = (float) (fr4.getStringWidth(value3) + 6);
                final int n2 = 20;
                final int n3 = 2;
                final int n4 = 2;
                RenderUtil.drawRect(n3, n4, n3 + n + 2.0f, n4 + n2, new Color(5, 5, 5, 255).getRGB());
                RenderUtil.drawBorderedRect(n3 + 0.5, n4 + 0.5, n3 + n + 1.5, n4 + n2 - 0.5, 0.5, new Color(40, 40, 40, 255).getRGB(), new Color(60, 60, 60, 255).getRGB(), true);
                RenderUtil.drawBorderedRect(n3 + 2, n4 + 2, n3 + n, n4 + n2 - 2, 0.5, new Color(22, 22, 22, 255).getRGB(), new Color(60, 60, 60, 255).getRGB(), true);
                RenderUtil.drawRect(n3 + 2.5, n4 + 2.5, n3 + n - 0.5, n4 + 4.5, new Color(9, 9, 9, 255).getRGB());
                RenderUtil.drawGradientSideways(4.0, n4 + 3, 4.0f + n / 3.0f, n4 + 4, new Color(81, 149, 219, 255).getRGB(), new Color(180, 49, 218, 255).getRGB());
                RenderUtil.drawGradientSideways(4.0f + n / 3.0f, n4 + 3, 4.0f + n / 3.0f * 2.0f, n4 + 4, new Color(180, 49, 218, 255).getRGB(), new Color(236, 93, 128, 255).getRGB());
                RenderUtil.drawGradientSideways(4.0f + n / 3.0f * 2.0f, n4 + 3, n / 3.0f * 3.0f + 1.0f, n4 + 4, new Color(236, 93, 128, 255).getRGB(), new Color(235, 255, 0, 255).getRGB());
                fr4.drawStringWithShadow(value3, (float) (4 + n3), (float) (7 + n4), -1);
            } else if (Objects.equals(valString, "Static")) {
                final int[] rainbow3 = getRainbow(5, 0.1f);
                RGBtoHex(rainbow3[0], rainbow3[1], rainbow3[2]);
                final StringBuilder obj = new StringBuilder(String.valueOf(new StringBuilder().append((Object) new StringBuilder(String.valueOf(new StringBuilder().append((Object) new StringBuilder(String.valueOf(new StringBuilder().append((Object) new StringBuilder().append("BloodWare").append(" ").append(" | ").append(mc.player.getName()).append(" | Fps ").append(Minecraft.getDebugFPS()).append(" | ").append("UID: ").append(BloodWare.getUID()))))))))));
                final Minecraft mc = Watermark.mc;
                final String value3 = String.valueOf(obj);
                final float n = (float) (fr4.getStringWidth(value3) + 6);
                final int n2 = 16;
                final int n3 = 2;
                final int n4 = 1;
                RenderUtil.drawSmoothRect(n3, n4, n3 + n + 5.0f, n4 + n2, new Color(0, 0, 0, 255).getRGB());

                fr4.drawStringWithShadow(value3, (float) (4 + n3), (float) (5 + n4), -1);
            } else if (Objects.equals(valString, "CBO")) {
                final String value4 = String.valueOf(new StringBuilder().append("BloodWare | ").append(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())).append(" |  Fps ").append(Minecraft.getDebugFPS()).append(" | UID: ").append(BloodWare.getUID()));
                RenderUtil.drawSmoothRect(5, 7, fr5.getStringWidth(value4) + 20, 20, new Color(0, 0, 0, 255).getRGB());
                RenderUtil.drawSmoothRect(5, 6, fr5.getStringWidth(value4) + 20, 8, new Color(181, 8, 239,255).getRGB());
                RenderUtil.drawSmoothRect(5, 9, fr5.getStringWidth(value4) + 20, 9, new Color(207, 0, 255, 200).getRGB());
                fr5.drawStringWithShadow(value4, 8.0f, 13f, Color.MAGENTA.getRGB());
            }
        } else if (Objects.equals(valString, "Blood")) {
            final String string = "BloodWare 0.1 | " + (BloodWare.instance.settingsManager.getSettingByName(this, "Show Name").getValBoolean() ? Watermark.mc.player.getName() : "NoName") + " | " + new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
            Gui.drawRect(4, 3, this.fr.getStringWidth(string) + 7, 22, ClickGUI.getColor());
            Gui.drawRect(5, 5, this.fr.getStringWidth(string) + 6, 21, new Color(37, 37, 37, 255).getRGB());
            Gui.drawRect(5, 9, this.fr.getStringWidth(string) + 7, 9, new Color(20, 20, 20, 100).getRGB());
            CastomFontUtils.fr.drawStringWithShadow(string, 6.0f, 15.0f, -1);
        }
    }
    public static int RGBtoHex(final int n, final int n2, final int n3) {
        return n << 16 | n2 << 8 | n3;
    }

    public Watermark() {
        super("Watermark", Category.HUD);
        this.fr = Minecraft.getMinecraft().fontRenderer;
        Watermark.Modes.add("Skeet");
        Watermark.Modes.add("Static");
        Watermark.Modes.add("CBO");
        Watermark.Modes.add("Anim");
        BloodWare.instance.settingsManager.rSetting(new Setting("Mode", this, "Static", (ArrayList)Watermark.Modes));
      //  BloodWare.instance.settingsManager.rSetting(new Setting("Show Name", this, true));
        this.timer = new TimerUtils();
    }

    public static int[] getRainbow(final int n, final float n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        final float n6 = 6.0f * ((System.currentTimeMillis() - Math.round(n2 * 1000.0f)) % (n * 1000)) / (n * 1000);
        final int round = Math.round(255.0f * (n6 - (float)Math.floor(n6)));
        if (n6 < 1.0f) {
            n3 = 255;
            n4 = round;
        }
        else if (n6 < 2.0f) {
            n3 = 255 - round;
            n4 = 255;
        }
        else if (n6 < 3.0f) {
            n4 = 255;
            n5 = round;
        }
        else if (n6 < 4.0f) {
            n4 = 255 - round;
            n5 = 255;
        }
        else if (n6 < 5.0f) {
            n3 = round;
            n5 = 255;
        }
        else if (n6 < 6.0f) {
            n3 = 255;
            n5 = 255 - round;
        }
        return new int[] { n3, n4, n5 };
    }
}