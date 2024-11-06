package com.Blood.Ware.module.misc;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Zoom extends Module {
    @Override
    public void onEnable() {
        super.onEnable();
    }

    public void onDisable() {
        super.onDisable();
        Zoom.mc.gameSettings.fovSetting =  (float)BloodWare.instance.settingsManager.getSettingByName(this,"range1").getValDouble();
    }

    @SubscribeEvent
    public void FOVchanger(final EntityViewRenderEvent.FOVModifier fovModifier) {
        Zoom.mc.gameSettings.fovSetting = (float)BloodWare.instance.settingsManager.getSettingByName(this, "range").getValDouble();
    }

    public Zoom() {
        super("Zoom", Category.RENDER);
        BloodWare.instance.settingsManager.rSetting(new Setting("range1", this, 50, 50,100, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("range", this, 100.0, 10.0, 150.0, false));
    }
}
