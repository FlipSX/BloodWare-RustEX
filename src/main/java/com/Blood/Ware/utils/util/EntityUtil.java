package com.Blood.Ware.utils.util;

import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.utils.MathUtils;
import com.Blood.Ware.utils.MinecraftHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class EntityUtil
{
    public static Color getColor(final Entity entity, final int n, final int n2, final int n3, final int n4, final boolean b) {
        Color color = new Color(n / 255.0f, n2 / 255.0f, n3 / 255.0f, n4 / 255.0f);
        if (entity instanceof EntityPlayer && b && FriendManager.isFriend(String.valueOf(entity))) {
            color = new Color(0.33333334f, 1.0f, 1.0f, n4 / 255.0f);
        }
        return color;
    }

    public static float[] calculateLookAt(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double n4 = entityPlayer.posX - n;
        final double n5 = entityPlayer.posY - n2;
        final double n6 = entityPlayer.posZ - n3;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        return new float[] { (float)(Math.atan2(n6 / sqrt, n4 / sqrt) * 180.0 / 3.141592653589793 + 90.0), (float)(Math.asin(n5 / sqrt) * 180.0 / 3.141592653589793) };
    }

    public static boolean isPassive(final Entity entity) {
        return (!(entity instanceof EntityWolf) || !((EntityWolf)entity).isAngry()) && (entity instanceof EntityAnimal || entity instanceof EntityAgeable || entity instanceof EntityTameable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid || (entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getRevengeTarget() == null));
    }

    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float n) {
        return getInterpolatedPos(entity, n).subtract(MinecraftHelper.mc.getRenderManager().viewerPosX, MinecraftHelper.mc.getRenderManager().viewerPosY, MinecraftHelper.mc.getRenderManager().viewerPosZ);
    }

    public static Vec3d getInterpolatedRenderPos(final Vec3d vec3d) {
        return new Vec3d(vec3d.x, vec3d.y, vec3d.z).subtract(MinecraftHelper.mc.getRenderManager().viewerPosX, MinecraftHelper.mc.getRenderManager().viewerPosY, MinecraftHelper.mc.getRenderManager().viewerPosZ);
    }

    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }

    public static double GetDistanceOfEntityToBlock(final Entity entity, final BlockPos blockPos) {
        return GetDistance(entity.posX, entity.posY, entity.posZ, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static boolean isLiving(final Entity entity) {
        return entity instanceof EntityLivingBase;
    }

    public static boolean stopSneaking(final boolean b) {
        if (b && MinecraftHelper.mc.player != null) {
            MinecraftHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)MinecraftHelper.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }

    public static boolean isAnimal(final Entity entity) {
        return entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntityWaterMob || entity instanceof EntityGolem;
    }

    public static Vec3d getInterpolatedAmount(final Entity entity, final Vec3d vec3d) {
        return getInterpolatedAmount(entity, vec3d.x, vec3d.y, vec3d.z);
    }

    public static Vec3d getInterpolatedAmount(final Entity entity, final double n) {
        return getInterpolatedAmount(entity, n, n, n);
    }

    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return MathUtils.interpolateVec3d(entity.getPositionVector(), new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ), n);
    }

    public static double GetDistance(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        final double n7 = n - n4;
        final double n8 = n2 - n5;
        final double n9 = n3 - n6;
        return MathHelper.sqrt(n7 * n7 + n8 * n8 + n9 * n9);
    }
}
