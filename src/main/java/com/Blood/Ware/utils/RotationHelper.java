package com.Blood.Ware.utils;

import com.Blood.Ware.events.EventTarget;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraft.network.play.client.*;

public class RotationHelper implements Helper
{
    public static float[] getRotationVector(final Vec3d vec3d, final boolean b, final float n, final float n2, final float n3) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - (MinecraftHelper.mc.player.posY + MinecraftHelper.mc.player.getEyeHeight() + 0.5);
        final double y2 = vec3d.z - eyesPos.z;
        final double sqrt = Math.sqrt(x * x + y2 * y2);
        float randomizeFloat = 0.0f;
        if (b) {
            randomizeFloat = MathematicHelper.randomizeFloat(-n, n);
        }
        float randomizeFloat2 = 0.0f;
        if (b) {
            randomizeFloat2 = MathematicHelper.randomizeFloat(-n2, n2);
        }
        return new float[] { updateRotation(MinecraftHelper.mc.player.rotationYaw, MinecraftHelper.mc.player.rotationYaw + GCDCalcHelper.getFixedRotation(MathHelper.wrapDegrees((float)(Math.toDegrees(Math.atan2(y2, x)) - 90.0) + randomizeFloat - MinecraftHelper.mc.player.rotationYaw)), n3), updateRotation(MinecraftHelper.mc.player.rotationPitch, MathHelper.clamp(MinecraftHelper.mc.player.rotationPitch + GCDCalcHelper.getFixedRotation(MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, sqrt))) + randomizeFloat2 - MinecraftHelper.mc.player.rotationPitch)), -90.0f, 90.0f), n3) };
    }

    public static float getAngleEntity(final Entity entity) {
        final float rotationYaw = MinecraftHelper.mc.player.rotationYaw;
        return rotationYaw + MathHelper.wrapDegrees((float)(Math.atan2(entity.posZ - MinecraftHelper.mc.player.posZ, entity.posX - MinecraftHelper.mc.player.posX) * 180.0 / 3.141592653589793 - 90.0) - rotationYaw);
    }

    public static float[] getFacePosRemote(final EntityLivingBase entityLivingBase, final Entity entity, final boolean b) {
        final Vec3d vec3d = new Vec3d(entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ);
        final Vec3d vec3d2 = new Vec3d(entity.posX, entity.posY, entity.posZ);
        final double x = vec3d2.x - vec3d.x;
        final double y = vec3d2.y - vec3d.y;
        final double y2 = vec3d2.z - vec3d.z;
        float randomizeFloat = 0.0f;
        if (b) {
            randomizeFloat = MathematicHelper.randomizeFloat(-2.0f, 2.0f);
        }
        float randomizeFloat2 = 0.0f;
        if (b) {
            randomizeFloat2 = MathematicHelper.randomizeFloat(-2.0f, 2.0f);
        }
        return new float[] { MathHelper.wrapDegrees((float)(Math.atan2(y2, x) * 180.0 / 3.141592653589793) - 90.0f + randomizeFloat), MathHelper.wrapDegrees((float)(-(Math.atan2(y, MathHelper.sqrt(x * x + y2 * y2)) * 180.0 / 3.141592653589793)) + randomizeFloat2) };
    }

    public Minecraft mc() {
        return null;
    }

    public static Vec3d getEyesPos() {
        return new Vec3d(MinecraftHelper.mc.player.posX, MinecraftHelper.mc.player.getEntityBoundingBox().minY + MinecraftHelper.mc.player.getEyeHeight(), MinecraftHelper.mc.player.posZ);
    }

    public static float updateRotation(final float n, final float n2, final float n3) {
        float wrapDegrees = MathHelper.wrapDegrees(n2 - n);
        if (wrapDegrees > n3) {
            wrapDegrees = n3;
        }
        if (wrapDegrees < -n3) {
            wrapDegrees = -n3;
        }
        return n + wrapDegrees;
    }

    public static class Rotation
    {
        public static float lastRenderPacketYaw;
        public static float lastPacketPitch;
        public static float renderPacketYaw;
        public static int rotationCounter;
        public static boolean isAiming;
        public static boolean isReady;
        public static float packetPitch;
        public static float lastBodyYaw;
        public static float bodyYaw;
        public static float lastPacketYaw;
        public static float packetYaw;

        static {
            Rotation.isReady = false;
        }

        public static double calcMove() {
            return Math.hypot(MinecraftHelper.mc.player.posX - MinecraftHelper.mc.player.prevPosX, MinecraftHelper.mc.player.posZ - MinecraftHelper.mc.player.prevPosZ);
        }

        public static boolean isAiming() {
            return !Rotation.isAiming;
        }

        @EventTarget
        public void onSendPacket(final EventSendPacket eventSendPacket) {
            if (eventSendPacket.getPacket() instanceof CPacketAnimation) {
                Rotation.rotationCounter = 10;
            }
        }

        @EventTarget
        public void onPlayerState(final EventPlayerState eventPlayerState) {
            if (isAiming() && eventPlayerState.isPre()) {
                Rotation.isReady = true;
            }
            else if (isAiming() && Rotation.isReady && eventPlayerState.isPost()) {
                Rotation.packetPitch = MinecraftHelper.mc.player.rotationPitch;
                Rotation.packetYaw = MinecraftHelper.mc.player.rotationYaw;
                Rotation.lastPacketPitch = MinecraftHelper.mc.player.rotationPitch;
                Rotation.lastPacketYaw = MinecraftHelper.mc.player.rotationYaw;
                Rotation.bodyYaw = MinecraftHelper.mc.player.renderYawOffset;
                Rotation.isReady = false;
            }
            else {
                Rotation.isReady = false;
            }
            if (eventPlayerState.isPre()) {
                Rotation.lastBodyYaw = Rotation.bodyYaw;
                Rotation.lastPacketPitch = Rotation.packetPitch;
                Rotation.lastPacketYaw = Rotation.packetYaw;
                Rotation.bodyYaw = MathematicHelper.clamp(Rotation.bodyYaw, Rotation.packetYaw, 50.0f);
                if (calcMove() > 2.500000277905201E-7) {
                    Rotation.bodyYaw = MathematicHelper.clamp(MovementHelper.getMoveDirection(), Rotation.packetYaw, 50.0f);
                    Rotation.rotationCounter = 0;
                }
                else if (Rotation.rotationCounter > 0) {
                    --Rotation.rotationCounter;
                    Rotation.bodyYaw = MathematicHelper.checkAngle(Rotation.packetYaw, Rotation.bodyYaw, 10.0f);
                }
                Rotation.lastRenderPacketYaw = Rotation.renderPacketYaw;
                Rotation.renderPacketYaw = Rotation.packetYaw;
            }
        }
    }
}
