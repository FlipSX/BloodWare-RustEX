package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.ColorUtils;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.RenderUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.Blood.Ware.utils.Font.CastomFontUtils.fr4;


public class NameTags extends Module
{
    public static List Modes;
    private FontRenderer fontRenderer;

    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        final String valString = BloodWare.instance.settingsManager.getSettingByName(this, "Modes").getValString();
        if (Objects.equals(valString, "Blood")) {
            for (final Entity entity : NameTags.mc.world.loadedEntityList) {
                if (entity instanceof EntityPlayer) {
                    if (entity == NameTags.mc.player) {
                        continue;
                    }
                    final double n = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * renderWorldLastEvent.getPartialTicks() - NameTags.mc.getRenderManager().viewerPosX;
                    final double n2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * renderWorldLastEvent.getPartialTicks() - NameTags.mc.getRenderManager().viewerPosY;
                    final double n3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * renderWorldLastEvent.getPartialTicks() - NameTags.mc.getRenderManager().viewerPosZ;
                    GL11.glPushMatrix();
                    GL11.glDisable(2929);
                    GL11.glDisable(3553);
                    GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                    GlStateManager.disableLighting();
                    GlStateManager.enableBlend();
                    final float n4 = Math.min(Math.max(1.2f * (NameTags.mc.player.getDistance(entity) * 0.15f), 1.25f), 6.0f) * 0.015f;
                    GL11.glTranslatef((float)n, (float)n2 + entity.height + 0.7f, (float)n3);
                    GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(-NameTags.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(NameTags.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                    GL11.glScalef(-n4, -n4, -n4);
                    final String str = "BloodWare";
                    if (FriendManager.FRIENDS.contains(entity.getName())) {
                        final int i = (int)(((EntityPlayer)entity).getHealth() / ((EntityPlayer)entity).getMaxHealth() * 100.0f);
                        Gui.drawRect(-NameTags.mc.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(str).append(" ").append(i).append("%"))) / 2 - 2, -2, NameTags.mc.fontRenderer.getStringWidth(str) / 2 + 16, 10, FriendManager.FRIENDS.contains(entity.getName()) ? Color.green.getRGB() : Integer.MIN_VALUE);
                        NameTags.mc.fontRenderer.drawString(String.valueOf(new StringBuilder().append(str).append(" ").append(TextFormatting.GREEN).append(i).append("%")), -this.center(String.valueOf(new StringBuilder().append(str).append(" ").append(TextFormatting.DARK_GREEN).append(i).append("%"))), 1, -1);
                        int n5 = -NameTags.mc.fontRenderer.getStringWidth(entity.getName()) / 2 - 8;
                        if (Item.getIdFromItem(((EntityPlayer)entity).inventory.getCurrentItem().getItem()) != 0) {
                            NameTags.mc.getRenderItem().zLevel = -100.0f;
                            NameTags.mc.getRenderItem().renderItemIntoGUI(((EntityPlayer)entity).inventory.getCurrentItem(), n5 - 2, -20);
                            NameTags.mc.getRenderItem().zLevel = 0.0f;
                            int n6 = -30;
                            for (final Enchantment enchantment : EnchantmentHelper.getEnchantments(((EntityPlayer)entity).inventory.getCurrentItem()).keySet()) {
                                final int getEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel(enchantment, ((EntityPlayer)entity).inventory.getCurrentItem());
                                NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel)), (float)(n5 + 6 - this.center(String.valueOf(new StringBuilder().append(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel)))), (float)n6, -1);
                                n6 -= 12;
                            }
                            n5 += 15;
                        }
                        for (final ItemStack itemStack : entity.getArmorInventoryList()) {
                            NameTags.mc.getRenderItem().zLevel = -100.0f;
                            NameTags.mc.getRenderItem().renderItemIntoGUI(new ItemStack(itemStack.getItem()), n5, -20);
                            NameTags.mc.getRenderItem().zLevel = 0.0f;
                            int n7 = -30;
                            for (final Enchantment enchantment2 : EnchantmentHelper.getEnchantments(itemStack).keySet()) {
                                final int getEnchantmentLevel2 = EnchantmentHelper.getEnchantmentLevel(enchantment2, itemStack);
                                NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(String.valueOf(enchantment2.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel2)), (float)(n5 + 9 - this.center(enchantment2.getName().substring(12).charAt(0) + getEnchantmentLevel2)), (float)n7, -1);
                                n7 -= 12;
                            }
                            n5 += 17;
                        }
                        GL11.glEnable(2929);
                        GL11.glPopMatrix();
                    }
                    else {
                        final int j = (int)(((EntityPlayer)entity).getHealth() / ((EntityPlayer)entity).getMaxHealth() * 100.0f);
                        Gui.drawRect(-NameTags.mc.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(entity.getName()).append(" ").append(j).append("%"))) / 2 - 2, -2, NameTags.mc.fontRenderer.getStringWidth(entity.getName()) / 2 + 16, 10, FriendManager.FRIENDS.contains(entity.getName()) ? Color.green.getRGB() : Integer.MIN_VALUE);
                        NameTags.mc.fontRenderer.drawString(String.valueOf(new StringBuilder().append(entity.getName()).append(" ").append(TextFormatting.GREEN).append(j).append("%")), -this.center(String.valueOf(new StringBuilder().append(entity.getName()).append(" ").append(TextFormatting.DARK_GREEN).append(j).append("%"))), 1, -1);
                        int n8 = -NameTags.mc.fontRenderer.getStringWidth(entity.getName()) / 2 - 8;
                        if (Item.getIdFromItem(((EntityPlayer)entity).inventory.getCurrentItem().getItem()) != 0) {
                            NameTags.mc.getRenderItem().zLevel = -100.0f;
                            NameTags.mc.getRenderItem().renderItemIntoGUI(((EntityPlayer)entity).inventory.getCurrentItem(), n8 - 2, -20);
                            NameTags.mc.getRenderItem().zLevel = 0.0f;
                            int n9 = -30;
                            for (final Enchantment enchantment3 : EnchantmentHelper.getEnchantments(((EntityPlayer)entity).inventory.getCurrentItem()).keySet()) {
                                final int getEnchantmentLevel3 = EnchantmentHelper.getEnchantmentLevel(enchantment3, ((EntityPlayer)entity).inventory.getCurrentItem());
                                NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(String.valueOf(enchantment3.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel3)), (float)(n8 + 6 - this.center(String.valueOf(new StringBuilder().append(String.valueOf(enchantment3.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel3)))), (float)n9, -1);
                                n9 -= 12;
                            }
                            n8 += 15;
                        }
                        for (final ItemStack itemStack2 : entity.getArmorInventoryList()) {
                            NameTags.mc.getRenderItem().zLevel = -100.0f;
                            NameTags.mc.getRenderItem().renderItemIntoGUI(new ItemStack(itemStack2.getItem()), n8, -20);
                            NameTags.mc.getRenderItem().zLevel = 0.0f;
                            int n10 = -30;
                            for (final Enchantment enchantment4 : EnchantmentHelper.getEnchantments(itemStack2).keySet()) {
                                final int getEnchantmentLevel4 = EnchantmentHelper.getEnchantmentLevel(enchantment4, itemStack2);
                                NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(String.valueOf(enchantment4.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel4)), (float)(n8 + 9 - this.center(enchantment4.getName().substring(12).charAt(0) + getEnchantmentLevel4)), (float)n10, -1);
                                n10 -= 12;
                            }
                            n8 += 17;
                        }
                        GL11.glEnable(2929);
                        GL11.glPopMatrix();
                    }
                }
            }
        }
        else if (Objects.equals(valString, "BloodV1")) {
            for (final Entity entity2 : NameTags.mc.world.loadedEntityList) {
                if (entity2 instanceof EntityPlayer && entity2 != NameTags.mc.player) {
                    final double n11 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * NameTags.mc.getRenderPartialTicks() - NameTags.mc.getRenderManager().viewerPosX;
                    final double n12 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * NameTags.mc.getRenderPartialTicks() - NameTags.mc.getRenderManager().viewerPosY;
                    final double n13 = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * NameTags.mc.getRenderPartialTicks() - NameTags.mc.getRenderManager().viewerPosZ;
                    GL11.glPushMatrix();
                    GL11.glDisable(2929);
                    GL11.glDisable(3553);
                    GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                    GlStateManager.disableLighting();
                    GlStateManager.enableBlend();
                    final float n14 = Math.min(Math.max(1.2f * (NameTags.mc.player.getDistance(entity2) * 0.15f), 1.25f), 6.0f) * 0.015f;
                    GL11.glTranslatef((float)n11, (float)n12 + entity2.height + 0.6f, (float)n13);
                    GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(-NameTags.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(NameTags.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                    GL11.glScalef(-n14, -n14, -n14);
                    if (FriendManager.FRIENDS.contains(entity2.getName())) {
                        final int n15 = (int)(((EntityPlayer)entity2).getHealth() / ((EntityPlayer)entity2).getMaxHealth() * 100.0f);
                        final int n16 = (int)((NameTags.mc.fontRenderer.getStringWidth(entity2.getName()) / 2 + 16 - (-NameTags.mc.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(entity2.getName()).append(" ").append(n15).append("%"))) / 2 - 2)) / ((EntityPlayer)entity2).getMaxHealth());
                        RenderUtil.drawSmoothRect((float)(-this.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append("Protect ").append(n15).append("%"))) / 2 - 2), -2.0f, (float)(this.fontRenderer.getStringWidth("Protect") / 2 + 16), 10.0f, Integer.MIN_VALUE);
                        RenderUtil.drawSmoothRect((float)(-this.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append("Protect ").append(n15).append("%"))) / 2 - 2), 10.0f, -this.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append("Protect ").append(n15).append("%"))) / 2 + n16 * ((EntityPlayer)entity2).getHealth(), 12.0f, ColorUtils.getHealthColor((EntityLivingBase)entity2).getRGB());
                        this.fontRenderer.drawString(String.valueOf(new StringBuilder().append("Protect ").append(TextFormatting.GREEN).append(n15).append("%")), 0 - this.getcenter(String.valueOf(new StringBuilder().append("Protect ").append(TextFormatting.GREEN).append(n15).append("%"))), 1, -1);
                        int n17 = -NameTags.mc.fontRenderer.getStringWidth(entity2.getName()) / 2 - 8;
                        if (Item.getIdFromItem(((EntityPlayer)entity2).inventory.getCurrentItem().getItem()) != 0) {
                            NameTags.mc.getRenderItem().zLevel = -100.0f;
                            NameTags.mc.getRenderItem().renderItemIntoGUI(((EntityPlayer)entity2).getHeldItem(EnumHand.MAIN_HAND), n17 - 2, -20);
                            NameTags.mc.getRenderItem().zLevel = 0.0f;
                            int n18 = -30;
                            for (final Enchantment enchantment5 : EnchantmentHelper.getEnchantments(((EntityPlayer)entity2).inventory.getCurrentItem()).keySet()) {
                                final int getEnchantmentLevel5 = EnchantmentHelper.getEnchantmentLevel(enchantment5, ((EntityPlayer)entity2).inventory.getCurrentItem());
                                NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(String.valueOf(enchantment5.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel5)), (float)(n17 + 6 - this.getcenter(String.valueOf(new StringBuilder().append(String.valueOf(enchantment5.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel5)))), (float)n18, -1);
                                n18 -= 12;
                            }
                            n17 += 15;
                        }
                        for (final ItemStack itemStack3 : entity2.getArmorInventoryList()) {
                            NameTags.mc.getRenderItem().zLevel = -100.0f;
                            NameTags.mc.getRenderItem().renderItemIntoGUI(itemStack3, n17, -20);
                            NameTags.mc.getRenderItem().zLevel = 0.0f;
                            int n19 = -30;
                            for (final Enchantment enchantment6 : EnchantmentHelper.getEnchantments(itemStack3).keySet()) {
                                final int getEnchantmentLevel6 = EnchantmentHelper.getEnchantmentLevel(enchantment6, itemStack3);
                                NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(String.valueOf(enchantment6.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel6)), (float)(n17 + 9 - this.getcenter(enchantment6.getName().substring(12).charAt(0) + getEnchantmentLevel6)), (float)n19, -1);
                                n19 -= 12;
                            }
                            n17 += 17;
                        }
                        final int n20 = 0;
                        if (n20 > 0) {
                            NameTags.mc.getRenderItem().zLevel = -100.0f;
                            NameTags.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.GOLDEN_APPLE), n17, -20);
                            NameTags.mc.getRenderItem().zLevel = 0.0f;
                            NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(n20), (float)(n17 + 9 - this.getcenter(String.valueOf(n20))), -30.0f, -1);
                        }
                        GL11.glEnable(2929);
                        GL11.glPopMatrix();
                    }
                    else {
                        final int n21 = (int)(((EntityPlayer)entity2).getHealth() / ((EntityPlayer)entity2).getMaxHealth() * 100.0f);
                        final int n22 = (int)((NameTags.mc.fontRenderer.getStringWidth(entity2.getName()) / 2 + 16 - (-NameTags.mc.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(entity2.getName()).append(" ").append(n21).append("%"))) / 2 - 2)) / ((EntityPlayer)entity2).getMaxHealth());
                        RenderUtil.drawSmoothRect((float)(-this.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(entity2.getName()).append(" ").append(n21).append("%"))) / 2 - 2), -2.0f, (float)(this.fontRenderer.getStringWidth(entity2.getName()) / 2 + 16), 10.0f, Integer.MIN_VALUE);
                        RenderUtil.drawSmoothRect((float)(-this.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(entity2.getName()).append(" ").append(n21).append("%"))) / 2 - 2), 10.0f, -this.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(entity2.getName()).append(" ").append(n21).append("%"))) / 2 + n22 * ((EntityPlayer)entity2).getHealth(), 12.0f, ColorUtils.getHealthColor((EntityLivingBase)entity2).getRGB());
                        this.fontRenderer.drawString(String.valueOf(new StringBuilder().append(entity2.getName()).append(" ").append(TextFormatting.GREEN).append(n21).append("%")), 0 - this.getcenter(String.valueOf(new StringBuilder().append(entity2.getName()).append(" ").append(TextFormatting.GREEN).append(n21).append("%"))), 1, -1);
                        int n23 = -NameTags.mc.fontRenderer.getStringWidth(entity2.getName()) / 2 - 8;
                        if (Item.getIdFromItem(((EntityPlayer)entity2).inventory.getCurrentItem().getItem()) != 0) {
                            NameTags.mc.getRenderItem().zLevel = -100.0f;
                            NameTags.mc.getRenderItem().renderItemIntoGUI(((EntityPlayer)entity2).getHeldItem(EnumHand.MAIN_HAND), n23 - 2, -20);
                            NameTags.mc.getRenderItem().zLevel = 0.0f;
                            int n24 = -30;
                            for (final Enchantment enchantment7 : EnchantmentHelper.getEnchantments(((EntityPlayer)entity2).inventory.getCurrentItem()).keySet()) {
                                final int getEnchantmentLevel7 = EnchantmentHelper.getEnchantmentLevel(enchantment7, ((EntityPlayer)entity2).inventory.getCurrentItem());
                                NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(String.valueOf(enchantment7.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel7)), (float)(n23 + 6 - this.getcenter(String.valueOf(new StringBuilder().append(String.valueOf(enchantment7.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel7)))), (float)n24, -1);
                                n24 -= 12;
                            }
                            n23 += 15;
                        }
                        for (final ItemStack itemStack4 : entity2.getArmorInventoryList()) {
                            NameTags.mc.getRenderItem().zLevel = -100.0f;
                            NameTags.mc.getRenderItem().renderItemIntoGUI(itemStack4, n23, -20);
                            NameTags.mc.getRenderItem().zLevel = 0.0f;
                            int n25 = -30;
                            for (final Enchantment enchantment8 : EnchantmentHelper.getEnchantments(itemStack4).keySet()) {
                                final int getEnchantmentLevel8 = EnchantmentHelper.getEnchantmentLevel(enchantment8, itemStack4);
                                NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(String.valueOf(enchantment8.getName().substring(12).charAt(0)).toUpperCase()).append(getEnchantmentLevel8)), (float)(n23 + 9 - this.getcenter(enchantment8.getName().substring(12).charAt(0) + getEnchantmentLevel8)), (float)n25, -1);
                                n25 -= 12;
                            }
                            n23 += 17;
                        }
                        final int n26 = 0;
                        if (n26 > 0) {
                            NameTags.mc.getRenderItem().zLevel = -100.0f;
                            NameTags.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.GOLDEN_APPLE), n23, -20);
                            NameTags.mc.getRenderItem().zLevel = 0.0f;
                            NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(n26), (float)(n23 + 9 - this.getcenter(String.valueOf(n26))), -30.0f, -1);
                        }
                        GL11.glEnable(2929);
                        GL11.glPopMatrix();
                    }
                }
            }
        }
        else if (Objects.equals(valString, "ZOV")) {
            for (final Entity entity3 : NameTags.mc.world.loadedEntityList) {
                if (entity3 instanceof EntityPlayer && entity3 != NameTags.mc.player) {
                    final double n27 = entity3.lastTickPosX + (entity3.posX - entity3.lastTickPosX) * NameTags.mc.getRenderPartialTicks() - NameTags.mc.getRenderManager().viewerPosX;
                    final double n28 = entity3.lastTickPosY + (entity3.posY - entity3.lastTickPosY) * NameTags.mc.getRenderPartialTicks() - NameTags.mc.getRenderManager().viewerPosY;
                    final double n29 = entity3.lastTickPosZ + (entity3.posZ - entity3.lastTickPosZ) * NameTags.mc.getRenderPartialTicks() - NameTags.mc.getRenderManager().viewerPosZ;
                    GL11.glPushMatrix();
                    GL11.glDisable(2929);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                    final float n30 = Math.min(Math.max(1.2f * NameTags.mc.player.getDistance(entity3) * 0.15f, 1.25f), 6.0f) * 0.015f;
                    GL11.glTranslatef((float)n27, (float)n28 + entity3.height + 0.6f, (float)n29);
                    GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(-NameTags.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(NameTags.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                    GL11.glScalef(-n30, -n30, -n30);
                    final float n31 = (float)(fr4.getStringWidth(String.valueOf(new StringBuilder().append(entity3.getDisplayName().getUnformattedText()).append(TextFormatting.GREEN).append("").append(""))) + 10);
                    RenderUtils.drawShadowRect(-(n31 / 2.0f), 0.0, n31 / 2.0f, 15.0, 3);
                    RenderUtil.drawSmoothRect(-(n31 / 2.0f), 0.0f, n31 / 2.0f, 15.0f, new Color(0, 0, 0, 255).getRGB());
                    final int n32 = (int)(n31 - 4.0f);
                    if (FriendManager.FRIENDS.contains(entity3.getName())) {
                        this.fontRenderer.drawString(String.valueOf(new StringBuilder().append(entity3.getName ()).append(TextFormatting.GREEN).append("").append("")), 0 - this.getcenter(String.valueOf(new StringBuilder().append(entity3.getName ()).append(TextFormatting.GREEN).append("").append(""))), 1, -1);
                        final ArrayList<ItemStack> list = new ArrayList<ItemStack>();
                        if (!(((EntityPlayer)entity3).getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)) {
                            list.add(((EntityPlayer)entity3).getHeldItem(EnumHand.MAIN_HAND));
                        }
                        for (final ItemStack e : entity3.getArmorInventoryList()) {
                            if (!(e.getItem() instanceof ItemAir)) {
                                list.add(e);
                            }
                        }
                        if (!(((EntityPlayer)entity3).getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemAir)) {
                            list.add(((EntityPlayer)entity3).getHeldItem(EnumHand.OFF_HAND));
                        }
                        int n33 = -(list.size() * 16 / 2);
                        for (final ItemStack itemStack5 : list) {
                            final RenderItem getRenderItem = NameTags.mc.getRenderItem();
                            GlStateManager.pushMatrix();
                            GlStateManager.enableBlend();
                            RenderHelper.enableStandardItemLighting();
                            getRenderItem.zLevel = -100.0f;
                            getRenderItem.renderItemIntoGUI(itemStack5, n33, -20);
                            getRenderItem.zLevel = 0.0f;
                            RenderHelper.disableStandardItemLighting();
                            GlStateManager.enableAlpha();
                            GlStateManager.disableBlend();
                            GlStateManager.disableLighting();
                            GlStateManager.popMatrix();
                            GlStateManager.pushMatrix();
                            GlStateManager.disableLighting();
                            GlStateManager.disableDepth();
                            GlStateManager.popMatrix();
                            n33 += 16;
                        }
                        GL11.glEnable(2929);
                        GL11.glColor3f(255.0f, 255.0f, 255.0f);
                        GL11.glEnable(2929);
                        GL11.glPopMatrix();
                    }
                    else {
                        this.fontRenderer.drawString(String.valueOf(new StringBuilder().append(entity3.getDisplayName().getUnformattedText())), 0 - this.getcenter(String.valueOf(new StringBuilder().append(entity3.getName()).append("").append(TextFormatting.GREEN).append("").append(""))), 1, -1);
                        final ArrayList<ItemStack> list2 = new ArrayList<ItemStack>();
                        if (!(((EntityPlayer)entity3).getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)) {
                            list2.add(((EntityPlayer)entity3).getHeldItem(EnumHand.MAIN_HAND));
                        }
                        for (final ItemStack e2 : entity3.getArmorInventoryList()) {
                            if (!(e2.getItem() instanceof ItemAir)) {
                                list2.add(e2);
                            }
                        }
                        if (!(((EntityPlayer)entity3).getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemAir)) {
                            list2.add(((EntityPlayer)entity3).getHeldItem(EnumHand.OFF_HAND));
                        }
                        int n34 = -(list2.size() * 16 / 2);
                        for (final ItemStack itemStack6 : list2) {
                            final RenderItem getRenderItem2 = NameTags.mc.getRenderItem();
                            GlStateManager.pushMatrix();
                            GlStateManager.enableBlend();
                            RenderHelper.enableStandardItemLighting();
                            getRenderItem2.zLevel = -100.0f;
                            getRenderItem2.renderItemIntoGUI(itemStack6, n34, -20);
                            getRenderItem2.zLevel = 0.0f;
                            RenderHelper.disableStandardItemLighting();
                            GlStateManager.enableAlpha();
                            GlStateManager.disableBlend();
                            GlStateManager.disableLighting();
                            GlStateManager.popMatrix();
                            GlStateManager.pushMatrix();
                            GlStateManager.disableLighting();
                            GlStateManager.disableDepth();
                            GlStateManager.popMatrix();
                            n34 += 16;
                        }
                        GL11.glEnable(2929);
                        GL11.glColor3f(255.0f, 255.0f, 255.0f);
                        GL11.glEnable(2929);
                        GL11.glPopMatrix();
                    }
                }
            }
        }
    }

    public int getcenter(final String s) {
        return NameTags.mc.fontRenderer.getStringWidth(s) / 2;
    }

    public int getcenter(final int i) {
        return NameTags.mc.fontRenderer.getStringWidth(String.valueOf(i)) / 2;
    }

    public int center(final String s) {
        return NameTags.mc.fontRenderer.getStringWidth(s) / 2;
    }

    public NameTags() {
        super("NameTags", Category.RENDER);
        this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
        NameTags.Modes.add("ZOV");
        BloodWare.instance.settingsManager.rSetting(new Setting("Modes", this, "ZOV", (ArrayList)NameTags.Modes));
    }

    static {
        NameTags.Modes = new ArrayList();
    }

    public int center(final int i) {
        return NameTags.mc.fontRenderer.getStringWidth(String.valueOf(i)) / 2;
    }
}