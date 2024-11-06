package com.Blood.Ware.module.misc;


import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FogColor extends Module
{
    public static List Modes;
    private long spin;

    public FogColor() {
        super("FogColor", Category.OUTHER);
        this.spin = 0L;
        FogColor.Modes.add("Spin");
        FogColor.Modes.add("Day");
        FogColor.Modes.add("Night");
        FogColor.Modes.add("Morning");
        FogColor.Modes.add("Sunset");
        BloodWare.instance.settingsManager.rSetting(new Setting("Mode", this, "Day", (ArrayList)FogColor.Modes));
        BloodWare.instance.settingsManager.rSetting(new Setting("TimeSpin Speed", this, 2.0, 1.0, 10.0, false));
    }

    @SubscribeEvent
    public void onUpdate(final EntityViewRenderEvent.FogColors fogColors) {
        final String valString = BloodWare.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        final float n = (float)BloodWare.instance.settingsManager.getSettingByName(this, "TimeSpin Speed").getValDouble();
        if (valString.equalsIgnoreCase("Spin")) {
            FogColor.mc.world.setWorldTime(this.spin);
            this.spin += (long)(n * 100.0f);
        }
        else if (valString.equalsIgnoreCase("Day")) {
            FogColor.mc.world.setWorldTime(5000L);
        }
        else if (valString.equalsIgnoreCase("Night")) {
            FogColor.mc.world.setWorldTime(17000L);
        }
        else if (valString.equalsIgnoreCase("Morning")) {
            FogColor.mc.world.setWorldTime(0L);
        }
        else if (valString.equalsIgnoreCase("Sunset")) {
            FogColor.mc.world.setWorldTime(13000L);
        }
    }

    static {
        FogColor.Modes = new ArrayList();
    }

    @SubscribeEvent
    public void onFogColorRender(final EntityViewRenderEvent.FogColors fogColors) {
        final Color hsbColor = Color.getHSBColor((float)(Math.ceil((double)(System.currentTimeMillis() + 300L + 300L)) / 15.0 % 360.0 / 360.0), 0.4f, 1.0f);
        fogColors.setRed(hsbColor.getRed() / 255.0f);
        fogColors.setGreen(hsbColor.getGreen() / 255.0f);
        fogColors.setBlue(hsbColor.getBlue() / 255.0f);
    }
}