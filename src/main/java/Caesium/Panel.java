//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 */
package Caesium;

import Caesium.components.Frame;
import Caesium.components.GuiButton;
import Caesium.components.GuiFrame;
import Caesium.listeners.ClickListener;
import Caesium.listeners.ComponentsListener;
import Caesium.util.FramePosition;
import com.Blood.Ware.BloodWare;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.hud.ClickGUI;

import java.awt.*;
import java.util.HashMap;

public class Panel
extends ClickGui {
    public static HashMap<String, FramePosition> framePositions = new HashMap();
    public static FontRenderer fR = Minecraft.getMinecraft().fontRenderer;
    public static String theme;
    public static int FRAME_WIDTH;
    public static int color;
    public static int fontColor;
    public static int grey40_240;
    public static int black195;
    public static int black100;

    public Panel(String theme, int fontSize) {
        Panel.theme = theme;
    }

    public void ref(Category cat, int x) {
        GuiFrame frame = new GuiFrame(cat.name(), x, 50, true);
        for (Module m : BloodWare.instance.moduleManager.modules) {
            if (cat != m.getCategory() || !m.visible) continue;
            GuiButton button = new GuiButton(m.getName());
            button.addClickListener(new ClickListener(button));
            button.addExtendListener(new ComponentsListener(button));
            frame.addButton(button);
        }
        this.addFrame(frame);
    }

    @Override
    public void initGui() {
        int x = 25;
        this.ref(Category.COMBAT, x);
        this.ref(Category.MOVEMENT, x += 135);
        this.ref(Category.RENDER, x += 135);
        this.ref(Category.OUTHER, x += 135);
        this.ref(Category.HUD, x += 135);
        super.initGui();
    }

    public void onGuiClosed() {
        if (!this.getFrames().isEmpty()) {
            for (Frame frame : this.getFrames()) {
                GuiFrame guiFrame = (GuiFrame)frame;
                framePositions.put(guiFrame.getTitle(), new FramePosition(guiFrame.getPosX(), guiFrame.getPosY(), guiFrame.isExpaned()));
            }
        }
    }

    static {
        FRAME_WIDTH = 100;
        color = ClickGUI.getColor();
        fontColor = Color.white.getRGB();
        grey40_240 = new Color(40, 40, 40, 100).getRGB();
        black195 = new Color(255, 255, 255, 100).getRGB();
        black100 = new Color(0, 0, 0, 255).getRGB();
    }
}

