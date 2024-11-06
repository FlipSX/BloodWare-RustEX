package com.Blood.Ware.utils;

public class EventPlayerState extends EventCancellable
{
    private float yaw;
    private boolean onGround;
    private float pitch;
    private double x;
    private final boolean isPre;
    private double y;
    private double z;

    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }

    public EventPlayerState(final double x, final double y, final double z, final float yaw, final float pitch, final boolean onGround) {
        this.y = y;
        this.x = x;
        this.z = z;
        this.isPre = true;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public boolean isPre() {
        return this.isPre;
    }

    public double getX() {
        return this.x;
    }

    public EventPlayerState() {
        this.isPre = false;
    }

    public void setZ(final double z) {
        this.z = z;
    }

    public double getZ() {
        return this.z;
    }

    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }

    public double getY() {
        return this.y;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public boolean isPost() {
        return !this.isPre;
    }

    public boolean cancel() {
        return false;
    }

    public boolean isOnGround() {
        return this.onGround;
    }
}
