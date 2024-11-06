package com.Blood.Ware.utils;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class EntityHelper
{
    static Minecraft mc;

    public static int getPing(final EntityPlayer entityPlayer) {
        return EntityHelper.mc.player.connection.getPlayerInfo(entityPlayer.getUniqueID()).getResponseTime();
    }

    public static boolean checkArmor(final Entity entity) {
        return ((EntityLivingBase)entity).getTotalArmorValue() < 6;
    }

    static {
        EntityHelper.mc = Minecraft.getMinecraft();
    }

    public static boolean isTeamWithYou(final EntityLivingBase entityLivingBase) {
        return EntityHelper.mc.player != null && entityLivingBase != null && EntityHelper.mc.player.getDisplayName() != null && entityLivingBase.getDisplayName() != null && ((EntityHelper.mc.player.getTeam() != null && entityLivingBase.getTeam() != null && EntityHelper.mc.player.getTeam().isSameTeam(entityLivingBase.getTeam())) || entityLivingBase.getDisplayName().getFormattedText().replace("\u0412§r", "").startsWith(String.valueOf(new StringBuilder().append("\u0412§").append(EntityHelper.mc.player.getDisplayName().getFormattedText().replace("\u0412§r", "").charAt(1)))));
    }

    public static double getDistance(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        final double n7 = n - n4;
        final double n8 = n2 - n5;
        final double n9 = n3 - n6;
        return Math.sqrt(n7 * n7 + n8 * n8 + n9 * n9);
    }

    public static double getDistanceOfEntityToBlock(final Entity entity, final BlockPos blockPos) {
        return getDistance(entity.posX, entity.posY, entity.posZ, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static double getDistance(final double n, final double n2, final double n3, final double n4) {
        return Math.hypot(n - n3, n2 - n4);
    }
}
