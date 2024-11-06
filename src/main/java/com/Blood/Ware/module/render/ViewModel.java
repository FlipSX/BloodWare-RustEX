package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import net.minecraft.client.model.IMultipassModel;
import net.minecraft.item.ItemMap;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.glInitNames;
import static org.lwjgl.opengl.GL11.glTranslated;

public class ViewModel extends Module {
    public ViewModel() {
        super("ViewModel", Category.RENDER);

        BloodWare.instance.settingsManager.rSetting(new Setting("Position X", this, 0, -10, 10, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("Position Y", this, -0.23, -10, 10, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("Position Z", this, -0.45, -10, 10, false));
    }

    @SubscribeEvent
    public void onRender(RenderSpecificHandEvent e) {
        if (e.getItemStack().getItem() instanceof ItemMap) {
            return; 
        }

        double x, y, z;
        x = BloodWare.instance.settingsManager.getSettingByName(this, "Position X").getValDouble();
        y = BloodWare.instance.settingsManager.getSettingByName(this, "Position Y").getValDouble();
        z = BloodWare.instance.settingsManager.getSettingByName(this, "Position Z").getValDouble();

        GL11.glTranslated(x, y, z);
    }
}
