package com.Blood.Ware.events.event;

public class EventUpdate extends Event
{
    double posX;
    double posY;
    double posZ;
    float rotationYaw;
    float rotationPitch;
    boolean onGround;
    
    public EventUpdate(final double posX, final double posY, final double posZ, final float rotationYaw, final float rotationPitch, final boolean onGround) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.onGround = onGround;
    }
    
    public double getPosY() {
        return this.posY;
    }
    
    public double getPosX() {
        return this.posX;
    }
    
    public double getPosZ() {
        return this.posZ;
    }
    
    public float getRotationPitch() {
        return this.rotationPitch;
    }
    
    public float getRotationYaw() {
        return this.rotationYaw;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public void setPosY(final double posY) {
        this.posY = posY;
    }
    
    public void setPosX(final double posX) {
        this.posX = posX;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public void setPosZ(final double posZ) {
        this.posZ = posZ;
    }
    
    public void setRotationPitch(final float rotationPitch) {
        this.rotationPitch = rotationPitch;
    }
    
    public void setRotationYaw(final float rotationYaw) {
        this.rotationYaw = rotationYaw;
    }
}
