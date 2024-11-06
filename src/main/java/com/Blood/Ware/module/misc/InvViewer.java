package com.Blood.Ware.module.misc;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.RenderUtils;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;


public class InvViewer extends Module
{
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent renderGameOverlayEvent) {
        final ScaledResolution scaledResolution = new ScaledResolution(InvViewer.mc);
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            final float n = (float) BloodWare.instance.settingsManager.getSettingByName(this, "Horizontal").getValDouble();
            final float n2 = (float)BloodWare.instance.settingsManager.getSettingByName(this, "Vertical").getValDouble();
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            RenderUtils.drawShadowRect(n, n2, n + 145.0f, n2 + 48.0f, 2);
            GlStateManager.resetColor();
            for (int i = 0; i < 27; ++i) {
                final ItemStack itemStack = (ItemStack)InvViewer.mc.player.inventory.mainInventory.get(i + 9);
                final float n3 = n + i % 9 * 16;
                final float n4 = n2 + i / 9 * 16;
                InvViewer.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, (int)n3, (int)n4);
                InvViewer.mc.getRenderItem().renderItemOverlayIntoGUI(InvViewer.mc.fontRenderer, itemStack, (int)n3, (int)n4, (String)null);
            }
            RenderHelper.disableStandardItemLighting();
            InvViewer.mc.getRenderItem().zLevel = 0.0f;
            GlStateManager.popMatrix();
        }
    }

    public InvViewer() {super("InvViewer", Category.OUTHER);
        BloodWare.instance.settingsManager.rSetting(new Setting("Horizontal", this, 387.0, 0.0, 810.0, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("Vertical", this, 424.0, 0.0, 490.0, true));
    }
}
