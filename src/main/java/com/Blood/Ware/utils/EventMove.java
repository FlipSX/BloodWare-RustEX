//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils;

public class EventMove extends EventCancellable
{
    private double z;
    private double x;
    private double y;
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public boolean cancel() {
        return false;
    }
    
    public double getY() {
        return this.y;
    }
    
    public EventMove(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
