//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.module.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.Blood.Ware.*;
import com.Blood.Ware.module.*;
import com.Blood.Ware.settings.*;

public class WallHack extends Module
{
    void render(final Entity entity, final float n) {
        try {
                if (entity == null || entity == Minecraft.getMinecraft().player) {
                    return;
            }
            if (entity == Minecraft.getMinecraft().getRenderViewEntity() && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
                return;
            }
            Minecraft.getMinecraft().entityRenderer.disableLightmap();
            Minecraft.getMinecraft().getRenderManager().renderEntityStatic(entity, n, false);
            Minecraft.getMinecraft().entityRenderer.enableLightmap();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        final boolean valBoolean = BloodWare.instance.settingsManager.getSettingByName(this, "Mob").getValBoolean();
        final boolean valBoolean2 = BloodWare.instance.settingsManager.getSettingByName(this, "Animal").getValBoolean();
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        if (valBoolean) {
            for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                if (entity instanceof EntityMob) {
                    if (entity == Minecraft.getMinecraft().getRenderViewEntity()) {
                        continue;
                    }
                    this.render(entity, renderWorldLastEvent.getPartialTicks());
                }
            }
        }
        if (valBoolean2) {
            for (final Entity entity2 : Minecraft.getMinecraft().world.loadedEntityList) {
                if (entity2 instanceof EntityAnimal) {
                    if (entity2 == Minecraft.getMinecraft().getRenderViewEntity()) {
                        continue;
                    }
                    this.render(entity2, renderWorldLastEvent.getPartialTicks());
                }
            }
        }
    }
    
    public WallHack() {
        super("WallHackMob", Category.RENDER);
        BloodWare.instance.settingsManager.rSetting(new Setting("Mob", this, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("Animal", this, false));
    }
}
