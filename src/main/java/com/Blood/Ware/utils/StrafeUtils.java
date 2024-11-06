package com.Blood.Ware.utils;

import net.minecraft.client.Minecraft;

public class StrafeUtils implements Mc {

    public static float getPlayerDirection() {
        Minecraft mc = Minecraft.getMinecraft();
        float yaw = mc.player.rotationYaw;
        float strafe = 45;
        if (mc.player.moveForward < 0) {
            strafe = -45;
            yaw += 180;
        }
        if (mc.player.moveStrafing > 0) {
            yaw -= strafe;
            if (mc.player.moveForward == 0) {
                yaw -= 45;
            }
        } else if (mc.player.moveStrafing < 0) {
            yaw += strafe;
            if (mc.player.moveForward == 0) {
                yaw += 45;
            }
        }
        return yaw;
    }
}
