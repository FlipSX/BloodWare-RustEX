package com.Blood.Ware.module.misc;


import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.utils.GenerateNumber;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.awt.*;

import static com.Blood.Ware.utils.NullCheck.nullCheck;

public class Africa extends Module {
    private int africaColor = new Color(255, 0, 0, 76).hashCode();

    public Africa() {
        super("Africa", Category.OUTHER);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent e) {
        if (nullCheck()) return;

        mc.player.setFire(1);
    }

    private short tickTimer = 0;
    private int colorAlpha = 76;

    @SubscribeEvent
    public void onGuiRender(RenderGameOverlayEvent e) {
        if (e.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            Gui.drawRect(0,0, e.getResolution().getScaledWidth(), e.getResolution().getScaledHeight(), africaColor);
            tickTimer++;
            if (tickTimer > 15) {
                int alpha = (int) (colorAlpha * GenerateNumber.generateFloat(0.94f, 1.05f));
                if (alpha > 110) {
                    alpha = 110;
                } else if (alpha < 50) {
                    alpha = 50;
                }
                colorAlpha = alpha;

                africaColor = new Color(255, 101, 0, alpha).hashCode();
                tickTimer = 0;
            }
        }
    }
}
