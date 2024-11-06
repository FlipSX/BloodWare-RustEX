//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils;

public class EventStrafe extends EventCancellable
{
    private float yaw;
    private float forward;
    private float strafe;
    private float friction;
    
    public float getForward() {
        return this.forward;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setStrafe(final float strafe) {
        this.strafe = strafe;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void setFriction(final float friction) {
        this.friction = friction;
    }
    
    public float getFriction() {
        return this.friction;
    }
    
    public void setForward(final float forward) {
        this.forward = forward;
    }
    
    public boolean cancel() {
        return false;
    }
    
    public EventStrafe(final float yaw, final float strafe, final float forward, final float friction) {
        this.yaw = yaw;
        this.strafe = strafe;
        this.forward = forward;
        this.friction = friction;
    }
    
    public float getStrafe() {
        return this.strafe;
    }
}
