package com.Blood.Ware.module.render;


import com.Blood.Ware.events.event.Event;

public class EventFogColor extends Event
{
    public float red;
    public int alpha;
    public float blue;
    public float green;

    public void setAlpha(final int alpha) {
        this.alpha = alpha;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public float getBlue() {
        return this.blue;
    }

    public float getGreen() {
        return this.green;
    }

    public static void setGreen(float n) {
        n = n;
    }

    public static void setRed(float n) {
        n = n;
    }

    public float getRed() {
        return this.red;
    }

    public static void setBlue(float n) {
        n = n;
    }

    public EventFogColor(final float red, final float green, final float blue, final int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }
}
