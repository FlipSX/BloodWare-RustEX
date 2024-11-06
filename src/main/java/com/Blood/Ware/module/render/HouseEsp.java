package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class HouseEsp extends Module {
    public HouseEsp() {
        super("HouseEsp", Category.RENDER);
        BloodWare.instance.settingsManager.rSetting(new Setting("WH", this, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("Glow", this, false));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        for (Entity entity : HouseEsp.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityArmorStand) || !entity.isGlowing()) continue;
            entity.setGlowing(false);
        }
    }

    void render(Entity entity, float ticks) {
        try {
            if (entity == null || entity == Minecraft.getMinecraft().player) {
                return;
            }
            if (entity != Minecraft.getMinecraft().getRenderViewEntity() || Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {

            }
            Minecraft.getMinecraft().getRenderManager().renderEntityStatic(entity, ticks, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        GlStateManager.clear((int)256);
        RenderHelper.enableStandardItemLighting();
        boolean Wallhack = BloodWare.instance.settingsManager.getSettingByName(this, "WH").getValBoolean();
        boolean Glowing = BloodWare.instance.settingsManager.getSettingByName(this, "Glow").getValBoolean();
        for (Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (!(entity instanceof EntityArmorStand) || entity == Minecraft.getMinecraft().getRenderViewEntity()) continue;
            if (Wallhack) {
                this.render(entity, event.getPartialTicks());
            }
            if (!Glowing || entity.isGlowing()) continue;
            entity.setGlowing(true);
        }
    }
}
