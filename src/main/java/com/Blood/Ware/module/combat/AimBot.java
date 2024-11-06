package com.Blood.Ware.module.combat;


import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.FovUtils;
import com.Blood.Ware.utils.TimerUtils;
import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;

public class AimBot extends Module {
    public float[] facing;
    public String[] gunlist;
    public TimerUtils timer;
    public TimerUtils timer2;
    private float[] lastFacing;
    private double smoothFactor;
    public static Entity target;




    public AimBot() {
        super("AimBot", Category.COMBAT);
        this.gunlist = new String[]{""};
        this.timer = new TimerUtils();
        this.timer2 = new TimerUtils();

        BloodWare.instance.settingsManager.rSetting(new Setting("Range", (Module) this, 300.0, 0.0, 300.0, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("Predict", (Module) this, 6.1, 0.0, 7.0, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("VPredict", (Module) this, 300.0, 0.0, 300.0, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("FOV", (Module) this, 48.0, 1.0, 360.0, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("Wall", (Module) this, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("Smooth", (Module) this, 5.0, 1.0, 100.0, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("InstantLock", (Module) this, false));

        smoothFactor = 2.0;
        lastFacing = new float[]{0, 0};
    }

    public float[] getPredict(final Entity e) {
        final float VPredict = (float) BloodWare.instance.settingsManager.getSettingByName(this, "VPredict").getValDouble();
        final float Predict = (float) BloodWare.instance.settingsManager.getSettingByName(this, "Predict").getValDouble();
        final float Range = (float) BloodWare.instance.settingsManager.getSettingByName(this, "Range").getValDouble();
        final double n = mc.getRenderPartialTicks();
        final double d = e.lastTickPosX + (e.posX - e.lastTickPosX) * n;
        final double d2 = e.lastTickPosY + (e.posY - e.lastTickPosY) * n;
        final double d3 = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * n;
        final double xDiff = (e.posX - e.prevPosX);
        final double zDiff = (e.posZ - e.prevPosZ);
        final float predict = Predict + this.getDistance(e) / Range;
        final double WillPosX = d + xDiff * predict;
        final double WillPosZ = d3 + zDiff * predict;
        double WillPosY;
        if (VPredict != 0.0f) {
            WillPosY = d2 + this.getDistance(e) / VPredict;
        } else {
            WillPosY = d2;
        }
        return new float[]{(float) WillPosX, (float) WillPosZ, (float) WillPosY};
    }

    public static float[] faceHead(final float posX, final float posY, final float posZ, final float p_706252, final float p_706253, final boolean miss) {
        double deltaX = posX - mc.player.posX;
        double deltaY = posY + (!BloodWare.moduleManager.getModule("BAim").isToggled() ? 1.86 : 1.5) - (mc.player.posY + mc.player.getEyeHeight());
        double deltaZ = posZ - mc.player.posZ;

        double distanceXZ = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

        double yaw = Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90;
        double pitch = -Math.toDegrees(Math.atan2(deltaY, distanceXZ));

        double yaw1 = mc.player.rotationYaw + MathHelper.wrapDegrees(yaw - mc.player.rotationYaw);
        double pitch1 = mc.player.rotationPitch + MathHelper.wrapDegrees(pitch - mc.player.rotationPitch);
        return new float[]{(float) yaw1, (float) pitch1};
    }


    public static float updateRotation(final float current, final float intended, final float speed) {
        float f = MathHelper.wrapDegrees(intended - current);
        if (f > speed) {
            f = speed;
        }
        if (f < -speed) {
            f = -speed;
        }
        return current + f;
    }

    private boolean lambdagetTarget(final Entity entity) {
        float Range = (float) BloodWare.instance.settingsManager.getSettingByName(this, "Range").getValDouble();
        return this.attackCheck(entity) && getDistance(entity) <= Range;
    }


    public Entity getTarget() throws Throwable {
        if (AimBot.mc.player != null && !AimBot.mc.player.isDead) {
            final List list = (List) AimBot.mc.world.loadedEntityList.stream().filter(entity -> entity != AimBot.mc.player).filter(entity -> !entity.isDead).filter(this::lambdagetTarget).sorted(Comparator.comparing(entity -> AimBot.mc.player.getDistance(entity))).collect(Collectors.toList());
            return (list.size() > 0) ? (Entity) list.get(0) : null;
        }
        return null;
    }
    private static int getSortEntities(final EntityLivingBase entityLivingBase, final EntityLivingBase entityLivingBase2) {
        return (int)(entityLivingBase.getHealth() - entityLivingBase2.getHealth());
    }
    public boolean attackCheck(final Entity target) {
        final boolean Walls = BloodWare.instance.settingsManager.getSettingByName(this, "Wall").getValBoolean();
        if (Walls) {
            return target instanceof EntityPlayer && !FriendManager.FRIENDS.contains(target.getName());
        }
        return !Walls && AimBot.mc.player.canEntityBeSeen(target) && target instanceof EntityPlayer && !FriendManager.FRIENDS.contains(target.getName());
    }

    private float lerp(float start, float end, float percent) {
        return start + percent * (end - start);
    }

    @SubscribeEvent
    public void onLivingUpdate(final LivingEvent.LivingUpdateEvent e) throws Throwable {
        final boolean Wall = BloodWare.instance.settingsManager.getSettingByName(this, "Wall").getValBoolean();
        final float Range = (float) BloodWare.instance.settingsManager.getSettingByName(this, "Range").getValDouble();
        final double FOV = BloodWare.instance.settingsManager.getSettingByName(this, "FOV").getValDouble();
        final boolean InstantLock = BloodWare.instance.settingsManager.getSettingByName(this, "InstantLock").getValBoolean();
        smoothFactor = BloodWare.instance.settingsManager.getSettingByName(this, "Smooth").getValDouble();
        final EntityPlayer target = (EntityPlayer) this.getTarget();

        if (target != null && !FriendManager.FRIENDS.contains(target.getName()) && FovUtils.isInAttackFOV((Entity) target, (int) FOV)) {
            this.facing = this.getPredict((Entity) target);
            this.facing = faceHead(this.facing[0], this.facing[2], this.facing[1], 360.0f, 360.0f, false);


            if (!InstantLock) {
                mc.player.rotationYaw += (this.facing[0] - mc.player.rotationYaw) / smoothFactor;
                mc.player.rotationPitch += (this.facing[1] - mc.player.rotationPitch) / smoothFactor;
                lastFacing[0] = mc.player.rotationYaw;
                lastFacing[1] = mc.player.rotationPitch;
            } else {
                mc.player.rotationYaw = facing[0];
                mc.player.rotationPitch = facing[1];
            }


            mc.player.rotationYaw = MathHelper.wrapDegrees(mc.player.rotationYaw);
            mc.player.rotationPitch = MathHelper.wrapDegrees(mc.player.rotationPitch);


            this.timer.setLastMS(15L);
        }
    }



    private float getDistance(final Entity entityIn) {
        final float f = (float) (AimBot.mc.player.posX - entityIn.posX);
        final float f2 = (float) (AimBot.mc.player.posZ - entityIn.posZ);
        return MathHelper.sqrt(f * f + f2 * f2);
    }

    private static float getDistance(final BlockPos blockPos) {
        final float f = (float) (AimBot.mc.player.posX - blockPos.getX());
        final float f2 = (float) (AimBot.mc.player.posZ - blockPos.getZ());
        return MathHelper.sqrt(f * f + f2 * f2);
    }

    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post event) {
        double p = BloodWare.instance.settingsManager.getSettingByName(this, "FOV").getValDouble() / Minecraft.getMinecraft().gameSettings.fovSetting;
        ScaledResolution scaledResolution=event.getResolution();
        drawCircle228((float)(scaledResolution.getScaledWidth() / 2), (float)(scaledResolution.getScaledHeight() / 2), (int) (p*485), Color.RED.getRGB(), 360, 0.5f);
        GL11.glColor4f(1,1,1,1);
    }
    public static void drawCircle228(final float n, final float n2, final float n3, final int n4, final int n5,final float width) {
        final float n6 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n4 & 0xFF) / 255.0f;
        final boolean glIsEnabled = GL11.glIsEnabled(3042);
        final boolean glIsEnabled2 = GL11.glIsEnabled(2848);
        final boolean glIsEnabled3 = GL11.glIsEnabled(3553);
        if (!glIsEnabled) {
            GL11.glEnable(3042);
        }
        if (!glIsEnabled2) {
            GL11.glEnable(2848);
        }
        if (glIsEnabled3) {
            GL11.glDisable(3553);
        }
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(n7, n8, n9, n6);
        GL11.glLineWidth(width);
        GL11.glBegin(3);
        for (int i = 0; i <= n5; ++i) {
            GL11.glVertex2d(n + Math.sin(i * Math.PI / 180.0) * n3, n2 + Math.cos(i * Math.PI / 180.0) * n3);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        if (glIsEnabled3) {
            GL11.glEnable(3553);
        }
        if (!glIsEnabled2) {
            GL11.glDisable(2848);
        }
        if (!glIsEnabled) {
            GL11.glDisable(3042);
        }
    }
}



