//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils;

public class TimerHelper
{
    private long previousTime;
    private long ms;
    
    public long getCurrentMS() {
        return System.currentTimeMillis();
    }
    
    public TimerHelper() {
        this.previousTime = -1L;
        this.ms = this.getCurrentMS();
    }
    
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
    
    public short convert(final float n) {
        return (short)(1000.0f / n);
    }
    
    public boolean check(final float n) {
        return this.getCurrentTime() - this.previousTime >= n;
    }
    
    public boolean hasReached(final float n) {
        return this.getCurrentMS() - this.ms > n;
    }
    
    public void reset() {
        this.previousTime = this.getCurrentTime();
    }
    
    public long getTime() {
        return System.currentTimeMillis() - this.previousTime;
    }
    
    public long get() {
        return this.previousTime;
    }
}
