package com.Blood.Ware.utils;

import net.minecraft.client.*;

public class MoveUtils
{
    public static void setSpeed(final double n) {
        double n2 = Minecraft.getMinecraft().player.movementInput.moveForward;
        double n3 = Minecraft.getMinecraft().player.movementInput.moveStrafe;
        float rotationYaw = Minecraft.getMinecraft().player.rotationYaw;
        if (n2 == 0.0 && n3 == 0.0) {
            Minecraft.getMinecraft().player.motionX = 0.0;
            Minecraft.getMinecraft().player.motionZ = 0.0;
        }
        else {
            if (n2 != 0.0) {
                if (n3 > 0.0) {
                    rotationYaw += ((n2 > 0.0) ? -45 : 45);
                }
                else if (n3 < 0.0) {
                    rotationYaw += ((n2 > 0.0) ? 45 : -45);
                }
                n3 = 0.0;
                if (n2 > 0.0) {
                    n2 = 1.0;
                }
                else if (n2 < 0.0) {
                    n2 = -1.0;
                }
            }
            Minecraft.getMinecraft().player.motionX = n2 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + n3 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f));
            Minecraft.getMinecraft().player.motionZ = n2 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - n3 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f));
        }
    }

    public static void unsafeSetSpeed(final double n) {
        final double n2 = Minecraft.getMinecraft().player.movementInput.moveStrafe;
        final float rotationYaw = Minecraft.getMinecraft().player.rotationYaw;
        if (n2 == 0.0) {
            Minecraft.getMinecraft().player.motionX = 0.0;
            Minecraft.getMinecraft().player.motionZ = 0.0;
        }
        Minecraft.getMinecraft().player.motionX = n * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + n2 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f));
        Minecraft.getMinecraft().player.motionZ = n * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - n2 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f));
    }
}