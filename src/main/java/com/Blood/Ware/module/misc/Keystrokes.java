package com.Blood.Ware.module.misc;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.RenderUtils;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

import static com.Blood.Ware.utils.Font.CastomFontUtils.fr6;

public class Keystrokes extends Module {
    public int color;

    public Keystrokes() {
        super("Keystrokes", Category.HUD);
        BloodWare.instance.settingsManager.rSetting(new Setting("keyX", this, 1.0, 1.0, 897.0, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("keyY", this, 1.0, -105.0, 340.0, true));
    }

    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text text) {
        final ScaledResolution scaledResolution = new ScaledResolution(Keystrokes.mc);
        if (text.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            final float n = (float) BloodWare.instance.settingsManager.getSettingByName(this, "keyX").getValDouble();
            final float n2 = (float) BloodWare.instance.settingsManager.getSettingByName(this, "keyY").getValDouble();
            int n3 = 0;
            int n4 = 0;
                GlStateManager.pushMatrix();
                if (!Keystrokes.mc.gameSettings.showDebugInfo) {
                    n3 = (int) n;
                    n4 = (int) n2;
                    this.color = Color.white.getRGB();
                    RenderUtils.drawRect((float) (n3 + 20), (float) (n4 + 111), (float) (n3 + 41), (float) (n4 + 130), -1879048192);
                    RenderUtils.drawRect((float) (n3 + 1), (float) (n4 + 130), (float) (n3 + 61), (float) (n4 + 150), -1879048192);
                    if (Keystrokes.mc.gameSettings.keyBindForward.isKeyDown()) {
                        RenderUtils.drawRect((float) (n3 + 21), (float) (n4 + 112), (float) (n3 + 40), (float) (n4 + 130), this.color);
                    }
                    if (Keystrokes.mc.gameSettings.keyBindBack.isKeyDown()) {
                        RenderUtils.drawRect((float) (n3 + 21), (float) (n4 + 131), (float) (n3 + 40), (float) (n4 + 149), this.color);
                    }
                    if (Keystrokes.mc.gameSettings.keyBindLeft.isKeyDown()) {
                        RenderUtils.drawRect((float) (n3 + 2), (float) (n4 + 131), (float) (n3 + 20), (float) (n4 + 149), this.color);
                    }
                    if (Keystrokes.mc.gameSettings.keyBindRight.isKeyDown()) {
                        RenderUtils.drawRect((float) (n3 + 41), (float) (n4 + 131), (float) (n3 + 60), (float) (n4 + 149), this.color);
                    }
                    fr6.drawString("W", (float) (n3 + 28), (float) (n4 + 117), Color.red.getRGB());
                    fr6.drawString("A", (float) (n3 + 8), (float) (n4 + 136), Color.red.getRGB());
                    fr6.drawString("S", (float) (n3 + 28), (float) (n4 + 136), Color.red.getRGB());
                    fr6.drawString("D", (float) (n3 + 48), (float) (n4 + 136), Color.red.getRGB());
                }
                GlStateManager.popMatrix();
            }
        }
    }

