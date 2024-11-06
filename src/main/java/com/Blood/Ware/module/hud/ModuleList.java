package com.Blood.Ware.module.hud;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.font.FontUtils;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.combat.AimBot;
import com.Blood.Ware.module.misc.Panic;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.RenderUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import com.Blood.Ware.module.Category;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;


import java.awt.*;
import java.util.ArrayList;

import static com.Blood.Ware.utils.Font.CastomFontUtils.fr4;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.rgb;


public class ModuleList extends Module {
    ArrayList<Module> modules;

    public ModuleList() {
        super("ModuleList", Category.HUD);

    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post e) {
        switch (e.getType()) {
            case TEXT:
                if (!Panic.isPanic) {
                    int y = 0;
                    final int[] counter = {0};

                    String server;
                    int glowColor = rainbowGlow(0);
                    Minecraft mc = Minecraft.getMinecraft();
                    FontRenderer fr = mc.fontRenderer;
                    ScaledResolution sr = new ScaledResolution(mc);

                    ArrayList<Module> enabledMods = new ArrayList<>();

                    for (Module module : BloodWare.moduleManager.modules) {
                        if (module.isToggled()) {
                            enabledMods.add(module);
                        }
                    }
                    enabledMods.sort((module1, module2) -> fr4.getStringWidth(module2.getName()) - fr4.getStringWidth(module1.getName()));

                    for (Module module : enabledMods) {
                        Gui.drawRect(sr.getScaledWidth() - 3, y, sr.getScaledWidth() - fr4.getStringWidth(module.getName()) - 12, y + 17, rainbow1(0));
                        Gui.drawRect(sr.getScaledWidth() - 6, y - 4, sr.getScaledWidth() - 2, y + 17, rainbow(0));

                        fr4.drawString(module.getName(), sr.getScaledWidth() - fr4.getStringWidth(module.getName()) - 10, y + 4, rainbow2(0));
                        y += 17;
                        counter[0]++;
                    }
                }
        }
    }

    public static int rainbow(int delay) {
        double RGBState = Math.ceil((System.currentTimeMillis() + delay) / 100000);
        RGBState %= 360;
        return Color.MAGENTA.getRGB();
    }

    public static int rainbow2(int delay) {
        double RGBState = Math.ceil((System.currentTimeMillis() + delay) / 100000);
        RGBState %= 360;
        return Color.white.getRGB();
    }

    public static int rainbow1(int delay) {
        double RGBState = Math.ceil((System.currentTimeMillis() + delay) / 100000);
        RGBState %= 360;
        return Color.black.getRGB();
    }

    public int rainbowGlow(float offset) {
        float hue = (System.currentTimeMillis() % 5000) / 5000.0F + offset;
        int rgb = Color.getHSBColor(hue, 1.0F, 1.0F).getRGB();
        return rgb;
    }
}