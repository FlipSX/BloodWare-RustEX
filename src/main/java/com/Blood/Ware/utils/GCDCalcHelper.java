package com.Blood.Ware.utils;

import net.minecraft.client.*;

public class GCDCalcHelper implements Helper
{
    public static float getGCDValue() {
        return (float)(getGCD() * 0.15);
    }

    public static float getFixedRotation(final float n) {
        return getDeltaMouse(n) * getGCDValue();
    }

    public static float getGCD() {
        final float n;
        return (n = (float)(MinecraftHelper.mc.gameSettings.mouseSensitivity * 0.6 + 0.2)) * n * n * 8.0f;
    }

    public static float getDeltaMouse(final float n) {
        return (float)Math.round(n / getGCDValue());
    }

    @Override
    public Minecraft mc() {
        return null;
    }
}
