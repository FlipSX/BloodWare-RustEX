
package com.Blood.Ware.module.hud;
import com.Blood.Ware.module.Category;

import com.Blood.Ware.module.Module;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.client.renderer.RenderItem;




public class ArmorHUD extends Module

{
    private static RenderItem itemRender;

    public ArmorHUD() {
        super("ArmorHUD", Category.HUD);
    }

    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        GlStateManager.enableTexture2D();
        final ScaledResolution resolution = new ScaledResolution(mc);
        final int i = resolution.getScaledWidth() / 2;
        int iteration = 0;
        //final int y = resolution.getScaledHeight() - 55 - (mc.player.isInWater() ? 10 : 0);
        int y = resolution.getScaledHeight() - 55 - 10;
        for (final ItemStack is : mc.player.inventory.armorInventory) {
            ++iteration;
            if (is.isEmpty()) {
                continue;
            }
            final int x = i - 90 + (9 - iteration) * 20 + 2;
            GlStateManager.enableDepth();
            ArmorHUD.itemRender.zLevel = 200.0f;
            ArmorHUD.itemRender.renderItemAndEffectIntoGUI(is, x, y);
            ArmorHUD.itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, is, x, y, "");
            ArmorHUD.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            final String s = (is.getCount() > 1) ? (is.getCount() + "") : "";
            mc.fontRenderer.drawStringWithShadow(s, (float)(x + 19 - 2 - mc.fontRenderer.getStringWidth(s)), (float)(y + 9), 16777215);
            final float green = (is.getMaxDamage() - (float)is.getItemDamage()) / is.getMaxDamage();
            final float red = 1.0f - green;
            final int dmg = 100 - (int)(red * 100.0f);
            mc.fontRenderer.drawStringWithShadow(dmg + "", (float)(x + 8 - mc.fontRenderer.getStringWidth(dmg + "") / 2), (float)(y - 11), toHex((int)(red * 255.0f), (int)(green * 255.0f), 0));
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }

    public static int toHex(final int r, final int g, final int b) {
        return 0xFF000000 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | (b & 0xFF);
    }


    static {
        ArmorHUD.itemRender = Minecraft.getMinecraft().getRenderItem();
    }
}