//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils;

import com.Blood.Ware.utils.Helper;
import com.Blood.Ware.utils.MinecraftHelper;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;
import net.minecraft.init.*;
import net.minecraft.client.*;

public class MovementHelper implements Helper
{
    public static float getSpeed() {
        return (float)Math.sqrt(MinecraftHelper.mc.player.motionX * MinecraftHelper.mc.player.motionX + MinecraftHelper.mc.player.motionZ * MinecraftHelper.mc.player.motionZ);
    }
    
    public static boolean isBlockAboveHead() {
        return MinecraftHelper.mc.world.getCollisionBoxes((Entity) MinecraftHelper.mc.player, new AxisAlignedBB(MinecraftHelper.mc.player.posX - 0.3, MinecraftHelper.mc.player.posY + MinecraftHelper.mc.player.getEyeHeight(), MinecraftHelper.mc.player.posZ + 0.3, MinecraftHelper.mc.player.posX + 0.3, MinecraftHelper.mc.player.posY + (MinecraftHelper.mc.player.onGround ? 2.5 : 1.5), MinecraftHelper.mc.player.posZ - 0.3)).isEmpty();
    }
    
    public static void calculateSilentMove(final EventStrafe eventStrafe, final float n) {
        final float strafe = eventStrafe.getStrafe();
        final float forward = eventStrafe.getForward();
        final float friction = eventStrafe.getFriction();
        final int n2 = (int)((MathHelper.wrapDegrees(MinecraftHelper.mc.player.rotationYaw - n - 23.5f - 135.0f) + 180.0f) / 45.0f);
        float n3 = 0.0f;
        float n4 = 0.0f;
        switch (n2) {
            case 0: {
                n3 = forward;
                n4 = strafe;
                break;
            }
            case 1: {
                final float n5 = n3 + forward;
                final float n6 = n4 - forward;
                n3 = n5 + strafe;
                n4 = n6 + strafe;
                break;
            }
            case 2: {
                n3 = strafe;
                n4 = -forward;
                break;
            }
            case 3: {
                final float n7 = n3 - forward;
                final float n8 = n4 - forward;
                n3 = n7 + strafe;
                n4 = n8 - strafe;
                break;
            }
            case 4: {
                n3 = -forward;
                n4 = -strafe;
                break;
            }
            case 5: {
                final float n9 = n3 - forward;
                final float n10 = n4 + forward;
                n3 = n9 - strafe;
                n4 = n10 - strafe;
                break;
            }
            case 6: {
                n3 = -strafe;
                n4 = forward;
                break;
            }
            case 7: {
                final float n11 = n3 + forward;
                final float n12 = n4 + forward;
                n3 = n11 - strafe;
                n4 = n12 + strafe;
                break;
            }
        }
        if (n3 > 1.0f || (n3 < 0.9f && n3 > 0.3f) || n3 < -1.0f || (n3 > -0.9f && n3 < -0.3f)) {
            n3 *= 0.5f;
        }
        if (n4 > 1.0f || (n4 < 0.9f && n4 > 0.3f) || n4 < -1.0f || (n4 > -0.9f && n4 < -0.3f)) {
            n4 *= 0.5f;
        }
        final float n13 = n4 * n4 + n3 * n3;
        if (n13 >= 1.0E-4f) {
            final float n14 = (float)(friction / Math.max(1.0, Math.sqrt(n13)));
            final float n15 = n4 * n14;
            final float n16 = n3 * n14;
            final float sin = MathHelper.sin((float)(n * 3.141592653589793 / 180.0));
            final float cos = MathHelper.cos((float)(n * 3.141592653589793 / 180.0));
            final EntityPlayerSP player = MinecraftHelper.mc.player;
            player.motionX += n15 * cos - n16 * sin;
            final EntityPlayerSP player2 = MinecraftHelper.mc.player;
            player2.motionZ += n16 * cos + n15 * sin;
        }
    }
    
    public static int getSpeedEffect() {
        if (MinecraftHelper.mc.player.isPotionActive(MobEffects.SPEED)) {
            return MinecraftHelper.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier() + 1;
        }
        return 0;
    }
    
    public static double getDirectionAll() {
        float rotationYaw = MinecraftHelper.mc.player.rotationYaw;
        float n = 1.0f;
        if (MinecraftHelper.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        if (MinecraftHelper.mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (MinecraftHelper.mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (MinecraftHelper.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (MinecraftHelper.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return Math.toRadians(rotationYaw);
    }
    
    public static boolean isMoving() {
        return MinecraftHelper.mc.player.movementInput.moveForward != 0.0f || MinecraftHelper.mc.player.movementInput.moveStrafe != 0.0f;
    }
    
    public static void setEventSpeed(final EventMove eventMove, final double n) {
        double n2 = MinecraftHelper.mc.player.movementInput.moveForward;
        double n3 = MinecraftHelper.mc.player.movementInput.moveStrafe;
        float rotationYaw = MinecraftHelper.mc.player.rotationYaw;
        if (n2 == 0.0 && n3 == 0.0) {
            eventMove.setX(0.0);
            eventMove.setZ(0.0);
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
            eventMove.setX(n2 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + n3 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f)));
            eventMove.setZ(n2 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - n3 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f)));
        }
    }
    
    public Minecraft mc() {
        return null;
    }
    
    public static float getMoveDirection() {
        return -(float)(Math.atan2(MinecraftHelper.mc.player.motionX, MinecraftHelper.mc.player.motionZ) / 3.141592653589793 * 180.0);
    }
    
    public static void setSpeed(final float n) {
        float rotationYaw = MinecraftHelper.mc.player.rotationYaw;
        float moveForward = MinecraftHelper.mc.player.movementInput.moveForward;
        float moveStrafe = MinecraftHelper.mc.player.movementInput.moveStrafe;
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                rotationYaw += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                rotationYaw += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        MinecraftHelper.mc.player.motionX = moveForward * n * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + moveStrafe * n * Math.sin(Math.toRadians(rotationYaw + 90.0f));
        MinecraftHelper.mc.player.motionZ = moveForward * n * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - moveStrafe * n * Math.cos(Math.toRadians(rotationYaw + 90.0f));
    }
    
    public static float getBaseMoveSpeed() {
        float n = 0.2873f;
        if (MinecraftHelper.mc.player != null && MinecraftHelper.mc.player.isPotionActive(MobEffects.SPEED)) {
            n *= (float)(1.0 + 0.2 * (MinecraftHelper.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier() + 1));
        }
        return n;
    }
    
    public static void strafePlayer(final float n) {
        final double directionAll = getDirectionAll();
        final float n2 = (n == 0.0f) ? getSpeed() : n;
        MinecraftHelper.mc.player.motionX = -Math.sin(directionAll) * n2;
        MinecraftHelper.mc.player.motionZ = Math.cos(directionAll) * n2;
    }
}
