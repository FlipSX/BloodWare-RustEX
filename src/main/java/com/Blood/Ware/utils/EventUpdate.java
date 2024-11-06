package com.Blood.Ware.utils;

import com.Blood.Ware.events.event.Event;

public class EventUpdate extends Event
{
    double posZ;
    private static float pitch;
    double posX;
    float rotationYaw;
    private static float yaw;
    double posY;
    float rotationPitch;
    boolean onGround;

    public EventUpdate(final double posX, final double posY, final double posZ, final float rotationYaw, final float rotationPitch, final boolean onGround, final float yaw, final float pitch) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.onGround = onGround;
        EventUpdate.yaw = yaw;
        EventUpdate.pitch = pitch;
    }

    public float getRotationPitch() {
        return this.rotationPitch;
    }

    public static void setYaw(final float yaw) {
        EventUpdate.yaw = yaw;
    }

    public double getPosX() {
        return this.posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public float getRotationYaw() {
        return this.rotationYaw;
    }

    public void setPosX(final double posX) {
        this.posX = posX;
    }

    public static void setPitch(final float pitch) {
        EventUpdate.pitch = pitch;
    }

    public void setPosY(final double posY) {
        this.posY = posY;
    }

    public static void setOnGround(final boolean b) {
    }

    public static void setRotationYaw(float n) {
        n = n;
    }

    public void setPosZ(final double posZ) {
        this.posZ = posZ;
    }

    public double getPosZ() {
        return this.posZ;
    }

    public static void setRotationPitch(float n) {
        n = n;
    }
}
