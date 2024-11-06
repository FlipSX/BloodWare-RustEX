package com.Blood.Ware.module.misc;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.util.Colors;

import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.gui.*;
import java.awt.*;

public class Crosshair extends Module {


    @SubscribeEvent
    public void onUpdate(final RenderGameOverlayEvent renderGameOverlayEvent) {
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            renderGameOverlayEvent.setCanceled(true);
        }
    }

    public boolean isMoving() {
        return (boolean) BloodWare.instance.settingsManager.getSettingByName(this, "DYNAMIC").getValBoolean() && !Crosshair.mc.player.collidedHorizontally && !Crosshair.mc.player.isSneaking() && (Crosshair.mc.player.movementInput.moveForward != 0.0f || Crosshair.mc.player.movementInput.moveStrafe != 0.0f);
    }

    @SubscribeEvent
    public void Crosshair(final RenderGameOverlayEvent.Text text) {
        if (text.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            final float n = (float)BloodWare.instance.settingsManager.getSettingByName(this, "red").getValDouble();
            final float n2 = (float)BloodWare.instance.settingsManager.getSettingByName(this, "green").getValDouble();
            final float n3 = (float)BloodWare.instance.settingsManager.getSettingByName(this, "blue").getValDouble();
            final float n4 = (float)BloodWare.instance.settingsManager.getSettingByName(this, "GAP").getValDouble();
            final float n5 = (float)BloodWare.instance.settingsManager.getSettingByName(this, "WIDTH").getValDouble();
            final float n6 = (float)BloodWare.instance.settingsManager.getSettingByName(this, "SIZE").getValDouble();
            final int n7 = (int)n;
            final int n8 = (int)n2;
            final int n9 = (int)n3;
            final int n10 = 255;
            final double n11 = n4;
            final double n12 = n5;
            final double n13 = n6;
            final ScaledResolution scaledResolution = new ScaledResolution(Crosshair.mc);
            RenderUtil.rectangleBordered(scaledResolution.getScaledWidth() / 2 - n12, scaledResolution.getScaledHeight() / 2 - n11 - n13 - (this.isMoving() ? 2 : 0), scaledResolution.getScaledWidth() / 2 + 1.0f + n12, scaledResolution.getScaledHeight() / 2 - n11 - (this.isMoving() ? 2 : 0), 0.5, Colors.getColor(n7, n8, n9, n10), new Color(0, 0, 0, n10).getRGB());
            RenderUtil.rectangleBordered(scaledResolution.getScaledWidth() / 2 - n12, scaledResolution.getScaledHeight() / 2 + n11 + 1.0 + (this.isMoving() ? 2 : 0) - 0.15, scaledResolution.getScaledWidth() / 2 + 1.0f + n12, scaledResolution.getScaledHeight() / 2 + 1 + n11 + n13 + (this.isMoving() ? 2 : 0) - 0.15, 0.5, Colors.getColor(n7, n8, n9, n10), new Color(0, 0, 0, n10).getRGB());
            RenderUtil.rectangleBordered(scaledResolution.getScaledWidth() / 2 - n11 - n13 - (this.isMoving() ? 2 : 0) + 0.15, scaledResolution.getScaledHeight() / 2 - n12, scaledResolution.getScaledWidth() / 2 - n11 - (this.isMoving() ? 2 : 0) + 0.15, scaledResolution.getScaledHeight() / 2 + 1.0f + n12, 0.5, Colors.getColor(n7, n8, n9, n10), new Color(0, 0, 0, n10).getRGB());
            RenderUtil.rectangleBordered(scaledResolution.getScaledWidth() / 2 + 1 + n11 + (this.isMoving() ? 2 : 0), scaledResolution.getScaledHeight() / 2 - n12, scaledResolution.getScaledWidth() / 2 + n13 + n11 + 1.0 + (this.isMoving() ? 2 : 0), scaledResolution.getScaledHeight() / 2 + 1.0f + n12, 0.5, Colors.getColor(n7, n8, n9, n10), new Color(0, 0, 0, n10).getRGB());
        }
    }

    public Crosshair() {
        super("Crosshair",  Category.RENDER);
        BloodWare.instance.settingsManager.rSetting(new Setting("red", this, 255.0, 1.0, 255.0, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("green", this, 255.0, 1.0, 255.0, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("blue", this, 255.0, 1.0, 255.0, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("DYNAMIC", this, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("GAP", this, 0.25, 0.0, 15.0, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("WIDTH", this, 0.25, 0.0, 10.0, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("SIZE", this, 1.0, 0.0, 15.0, false));
    }
}
