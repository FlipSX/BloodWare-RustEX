//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils;

import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;

public class FovUtils
{
    public static boolean isInAttackFOV(final Entity entity, final int fov) {
        return getDistanceFromMouse(entity) <= fov;
    }

    public static int getDistanceFromMouse(final Entity entity) {
        final float[] neededRotations = getNeededRotations(entity);
        final float neededYaw = Minecraft.getMinecraft().player.rotationYaw - neededRotations[0];
        final float neededPitch = Minecraft.getMinecraft().player.rotationPitch - neededRotations[1];
        final float distanceFromMouse = MathHelper.sqrt(neededYaw * neededYaw + neededPitch * neededPitch);
        return (int)distanceFromMouse;
    }

    private static float[] getNeededRotations(final Entity vec) {
        Minecraft mc = Minecraft.getMinecraft();
        double deltaX = vec.posX - mc.player.posX;
        double deltaY = vec.posY + 1.86 - (mc.player.posY + mc.player.getEyeHeight());
        double deltaZ = vec.posZ - mc.player.posZ;

        double distanceXZ = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

        double yaw = Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90;
        double pitch = -Math.toDegrees(Math.atan2(deltaY, distanceXZ));

        double yaw1 = mc.player.rotationYaw+MathHelper.wrapDegrees(yaw-mc.player.rotationYaw);
        double pitch1 = mc.player.rotationPitch+MathHelper.wrapDegrees(pitch-mc.player.rotationPitch);

        return new float[]{(float) yaw1, (float) pitch1};
    }
}
