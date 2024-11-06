//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ESP extends Module
{
    public static List listA;
    public static List Heal;
    public static List Modes;
    private Entity EntityArmorStand;

    public static void renderEntityBoundingBox(final Entity entity, final float n, final float n2, final float n3, final float n4) {
        final RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
        final Vec3d interpolateEntity = interpolateEntity(entity);
        GlStateManager.glLineWidth(5.0f);
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(interpolateEntity.x - entity.width / 2.0f, interpolateEntity.y, interpolateEntity.z - entity.width / 2.0f, interpolateEntity.x + entity.width / 2.0f, interpolateEntity.y + entity.height, interpolateEntity.z + entity.width / 2.0f).offset(-getRenderManager.viewerPosX, -getRenderManager.viewerPosY, -getRenderManager.viewerPosZ), n, n2, n3, n4);
        GlStateManager.glLineWidth(1.0f);
    }

    public void onDisable() {
        super.onDisable();
        for (final Entity entity : ESP.mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer) {
                if (!entity.isGlowing()) {
                    continue;
                }
                entity.setGlowing(false);
            }
        }
    }

    public static void renderEntityFilledBoundingBox(final Entity entity, final float n, final float n2, final float n3, final float n4) {
        final RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
        final Vec3d interpolateEntity = interpolateEntity(entity);
        RenderGlobal.renderFilledBox(new AxisAlignedBB(interpolateEntity.x - entity.width / 2.0f, interpolateEntity.y, interpolateEntity.z - entity.width / 2.0f, interpolateEntity.x + entity.width / 2.0f, interpolateEntity.y + entity.height, interpolateEntity.z + entity.width / 2.0f).offset(-getRenderManager.viewerPosX, -getRenderManager.viewerPosY, -getRenderManager.viewerPosZ), n, n2, n3, n4);
    }

    public void onEnable() {
        super.onEnable();
    }

    public static Vec3d interpolateEntity(final Entity entity) {
        final double n = Minecraft.getMinecraft().getRenderPartialTicks();
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
    }

    static {
        ESP.listA = new ArrayList();
        ESP.Modes = new ArrayList();
        ESP.Heal = new ArrayList();
    }

    public ESP() {
        super("ESP", Category.RENDER);
        ESP.Modes.add("3D");
     //   ESP.Modes.add("2D");
        ESP.Modes.add("NoneCode");
        ESP.Modes.add("Glowing");
        ESP.Modes.add("Corners");
        ESP.Modes.add("OnHeal");
        BloodWare.instance.settingsManager.rSetting(new Setting("Mode", this, "2D", (ArrayList)ESP.Modes));
      //  BloodWare.instance.settingsManager.rSetting(new Setting("Health[2D]", this, true));
     //   BloodWare.instance.settingsManager.rSetting(new Setting("healthValue[2D]", this, true));
     //   BloodWare.instance.settingsManager.rSetting(new Setting("Box[2D]", this, true));
      //  BloodWare.instance.settingsManager.rSetting(new Setting("Tag[2D]", this, true));
     //   BloodWare.instance.settingsManager.rSetting(new Setting("Show item[2D]", this, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("3DAlpha[3D]", this, 0.7, 0.0, 1.0, false));
      //  ESP.Heal.add("Right");
      //  ESP.Heal.add("Left");
      //  BloodWare.instance.settingsManager.rSetting(new Setting("PosHp[2D]", this, "Right", (ArrayList)ESP.Heal));
     //   BloodWare.instance.settingsManager.rSetting(new Setting("redBox[2D]", this, 0.8, 0.1, 255.0, false));
      //  BloodWare.instance.settingsManager.rSetting(new Setting("greenBox[2D]", this, 0.8, 0.1, 255.0, false));
       // BloodWare.instance.settingsManager.rSetting(new Setting("blueBox[2D]", this, 0.8, 0.1, 255.0, false));
    }

    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        //  final String valString = BloodWare.instance.settingsManager.getSettingByName(this, "PosHp[2D]").getValString();
        final String valString2 = BloodWare.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        //  final boolean valBoolean = BloodWare.instance.settingsManager.getSettingByName(this, "Health[2D]").getValBoolean();
        //  final boolean valBoolean2 = BloodWare.instance.settingsManager.getSettingByName(this, "healthValue[2D]").getValBoolean();
        // final boolean valBoolean3 = BloodWare.instance.settingsManager.getSettingByName(this, "Box[2D]").getValBoolean();
        // final boolean valBoolean4 = BloodWare.instance.settingsManager.getSettingByName(this, "Tag[2D]").getValBoolean();
        // final boolean valBoolean5 = BloodWare.instance.settingsManager.getSettingByName(this, "Show item[2D]").getValBoolean();
         final float n = (float)BloodWare.instance.settingsManager.getSettingByName(this, "3DAlpha[3D]").getValDouble();
      //  final float n2 = (float) BloodWare.instance.settingsManager.getSettingByName(this, "redBox[2D]").getValDouble();
      //  final float n3 = (float) BloodWare.instance.settingsManager.getSettingByName(this, "greenBox[2D]").getValDouble();
       // final float n4 = (float) BloodWare.instance.settingsManager.getSettingByName(this, "blueBox[2D]").getValDouble();
        if (Objects.equals(valString2, "2D")) {
            final Iterator<Entity> iterator = (Iterator<Entity>) ESP.mc.world.loadedEntityList.iterator();
            if (iterator.hasNext()) {
                final Entity entity = iterator.next();
                for (final Entity entity2 : ESP.mc.world.loadedEntityList) {
                    if (entity2 instanceof EntityPlayer && entity2 != ESP.mc.world.loadedEntityList) {
                        final double n5 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * renderWorldLastEvent.getPartialTicks() - ESP.mc.getRenderManager().viewerPosX;
                        final double n6 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * renderWorldLastEvent.getPartialTicks() - ESP.mc.getRenderManager().viewerPosY;
                        final double n7 = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * renderWorldLastEvent.getPartialTicks() - ESP.mc.getRenderManager().viewerPosZ;
                        GL11.glPushMatrix();
                        GL11.glLineWidth(1.5f);
                        GL11.glTranslated(n5, n6, n7);
                        GL11.glDisable(3553);
                        GL11.glDisable(2929);
                        GL11.glRotated((double) (-ESP.mc.getRenderManager().playerViewY), 0.0, 1.0, 0.0);
                    }
                }
            }
        }

         if (Objects.equals(valString2, "Glowing")) {
            for (final Entity entity3 : ESP.mc.world.loadedEntityList) {
                if (entity3 instanceof EntityPlayer && entity3 != ESP.mc.player) {
                    if (entity3.isGlowing()) {
                        continue;
                    }
                    entity3.setGlowing(true);
                }
            }
        }
        else if (Objects.equals(valString2, "3D")) {
            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.disableDepth();
            for (final Entity entity4 : ESP.mc.world.loadedEntityList) {
                if (entity4 instanceof EntityArmorStand) {
                    if (entity4 == EntityArmorStand) {
                        continue;
                    }
                    renderEntityBoundingBox(entity4, Color.RED.getRed() / 255.0f, Color.RED.getGreen() / 255.0f, Color.RED.getBlue() / 255.0f, n);
                }
            }
            GlStateManager.enableDepth();
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            GlStateManager.enableAlpha();
            GlStateManager.popMatrix();
        }

            if (Objects.equals(valString2, "NoneCode")) {
                for (final Entity entity5 : ESP.mc.world.loadedEntityList) {
                    if (entity5 instanceof EntityPlayer && (entity5 != ESP.mc.player || ESP.mc.gameSettings.thirdPersonView == 1)) {
                        final double n8 = entity5.lastTickPosX + (entity5.posX - entity5.lastTickPosX) * ESP.mc.getRenderPartialTicks() - ESP.mc.getRenderManager().viewerPosX;
                        final double n9 = entity5.lastTickPosY + (entity5.posY - entity5.lastTickPosY) * ESP.mc.getRenderPartialTicks() - ESP.mc.getRenderManager().viewerPosY;
                        final double n10 = entity5.lastTickPosZ + (entity5.posZ - entity5.lastTickPosZ) * ESP.mc.getRenderPartialTicks() - ESP.mc.getRenderManager().viewerPosZ;
                        GL11.glPushMatrix();
                        GL11.glDisable(2929);
                        GL11.glDisable(3553);
                        GL11.glTranslatef((float)n8, (float)n9, (float)n10);
                        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                        GL11.glRotatef(-ESP.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                        GL11.glRotatef(ESP.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                        GL11.glLineWidth(5.0f);
                        GL11.glColor3f(0.0f, 0.0f, 0.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3d(entity5.width + 0.1, -0.4, 0.0);
                        GL11.glVertex3d(entity5.width + 0.1, entity5.height + 0.4, 0.0);
                        GL11.glVertex3d(-entity5.width - 0.1, entity5.height + 0.4, 0.0);
                        GL11.glVertex3d(-entity5.width - 0.1, -0.4, 0.0);
                        GL11.glVertex3d(entity5.width + 0.1, -0.4, 0.0);
                        GL11.glEnd();
                        GL11.glLineWidth(1f);
                        GL11.glColor3f(148.0f, 0.0f, 211.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3d(entity5.width + 0.1, -0.4, 0.0);
                        GL11.glVertex3d(entity5.width + 0.1, entity5.height + 0.4, 0.0);
                        GL11.glVertex3d(-entity5.width - 0.1, entity5.height + 0.4, 0.0);
                        GL11.glVertex3d(-entity5.width - 0.1, -0.4, 0.0);
                        GL11.glVertex3d(entity5.width + 0.1, -0.4, 0.0);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glEnd();
                        GL11.glEnable(2929);
                        GL11.glEnable(3553);
                        GL11.glColor3f(148.0f, 0.0f, 211.0f);
                        GL11.glPopMatrix();
                    }
                }
                return;
            }
            if (Objects.equals(valString2, "Corners")) {
                for (final Entity entity6 : ESP.mc.world.loadedEntityList) {
                    if (entity6 instanceof EntityPlayer) {
                        if (entity6 == ESP.mc.player) {
                            continue;
                        }
                        final double n11 = entity6.lastTickPosX + (entity6.posX - entity6.lastTickPosX) * renderWorldLastEvent.getPartialTicks() - ESP.mc.getRenderManager().viewerPosX;
                        final double n12 = entity6.lastTickPosY + (entity6.posY - entity6.lastTickPosY) * renderWorldLastEvent.getPartialTicks() - ESP.mc.getRenderManager().viewerPosY;
                        final double n13 = entity6.lastTickPosZ + (entity6.posZ - entity6.lastTickPosZ) * renderWorldLastEvent.getPartialTicks() - ESP.mc.getRenderManager().viewerPosZ;
                        GL11.glPushMatrix();
                        GL11.glDisable(2929);
                        GL11.glDisable(3553);
                        GL11.glTranslated(n11, n12, n13);
                        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                        GL11.glRotatef(-ESP.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                        GL11.glLineWidth(6.0f);
                        GL11.glColor3f(0.0f, 0.0f, 0.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3d((double)entity6.width, 0.2, 0.0);
                        GL11.glVertex3d((double)entity6.width, -0.3, 0.0);
                        GL11.glVertex3d(0.15, -0.3, 0.0);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3d((double)(-entity6.width), 0.2, 0.0);
                        GL11.glVertex3d((double)(-entity6.width), -0.3, 0.0);
                        GL11.glVertex3d(-0.15, -0.3, 0.0);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3d(0.15, entity6.height + 0.3, 0.0);
                        GL11.glVertex3d((double)entity6.width, entity6.height + 0.3, 0.0);
                        GL11.glVertex3d((double)entity6.width, entity6.height - 0.3, 0.0);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3d(-0.15, entity6.height + 0.3, 0.0);
                        GL11.glVertex3d((double)(-entity6.width), entity6.height + 0.3, 0.0);
                        GL11.glVertex3d((double)(-entity6.width), entity6.height - 0.3, 0.0);
                        GL11.glEnd();
                        GL11.glLineWidth(1.0f);
                        GL11.glColor3f((float)Color.RED.darker().getRed(), (float)Color.RED.darker().getGreen(), (float)Color.RED.darker().getBlue());
                        GL11.glBegin(3);
                        GL11.glVertex3d((double)entity6.width, 0.2, 0.0);
                        GL11.glVertex3d((double)entity6.width, -0.3, 0.0);
                        GL11.glVertex3d(0.15, -0.3, 0.0);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3d((double)(-entity6.width), 0.2, 0.0);
                        GL11.glVertex3d((double)(-entity6.width), -0.3, 0.0);
                        GL11.glVertex3d(-0.15, -0.3, 0.0);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3d(0.15, entity6.height + 0.3, 0.0);
                        GL11.glVertex3d((double)entity6.width, entity6.height + 0.3, 0.0);
                        GL11.glVertex3d((double)entity6.width, entity6.height - 0.3, 0.0);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3d(-0.15, entity6.height + 0.3, 0.0);
                        GL11.glVertex3d((double)(-entity6.width), entity6.height + 0.3, 0.0);
                        GL11.glVertex3d((double)(-entity6.width), entity6.height - 0.3, 0.0);
                        GL11.glEnd();
                        GL11.glColor3f(1.0f, 1.0f, 1.0f);
                        GL11.glDisable(2960);
                        GL11.glEnable(2929);
                        GL11.glEnable(3553);
                        GL11.glPopMatrix();
                    }
                }
            }
            else if (Objects.equals(valString2, "OnHeal")) {
                for (final Entity entity7 : ESP.mc.world.loadedEntityList) {
                    if (entity7 instanceof EntityPlayer && entity7 != ESP.mc.player) {
                        final double n14 = entity7.lastTickPosX + (entity7.posX - entity7.lastTickPosX) * renderWorldLastEvent.getPartialTicks() - ESP.mc.getRenderManager().viewerPosX;
                        final double n15 = entity7.lastTickPosY + (entity7.posY - entity7.lastTickPosY) * renderWorldLastEvent.getPartialTicks() - ESP.mc.getRenderManager().viewerPosY;
                        final double n16 = entity7.lastTickPosZ + (entity7.posZ - entity7.lastTickPosZ) * renderWorldLastEvent.getPartialTicks() - ESP.mc.getRenderManager().viewerPosZ;
                        GL11.glPushMatrix();
                        GL11.glDisable(2929);
                        GL11.glDisable(3553);
                        GL11.glTranslatef((float)n14, (float)n15, (float)n16);
                        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                        GlStateManager.rotate(-ESP.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                        GlStateManager.rotate(ESP.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                        GL11.glLineWidth(4.7f);
                        GL11.glColor3f(0.0f, 0.0f, 0.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.4f, 0.0f, 0.0f);
                        GL11.glVertex3f(0.4f, entity7.height, 0.0f);
                        GL11.glEnd();
                        GL11.glColor3f(0.0f, 255.0f, 0.0f);
                        GL11.glLineWidth(0.8f);
                        GL11.glColor3f(255.0f, 255.0f, 255.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.4f, 0.0f, 0.0f);
                        GL11.glVertex3f(0.4f, entity7.height, 0.0f);
                        GL11.glColor3f(0.0f, 255.0f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.4f, 0.0f, 0.0f);
                        GL11.glVertex3f(0.4f, ((EntityPlayer)entity7).getHealth() / ((EntityPlayer)entity7).getMaxHealth() * entity7.height, 0.0f);
                        GL11.glEnd();
                        GL11.glEnable(3553);
                        GL11.glEnable(2929);
                        GL11.glColor3f(255.0f, 255.0f, 255.0f);
                        GL11.glPopMatrix();
                    }
                }
            }
        }
    }
