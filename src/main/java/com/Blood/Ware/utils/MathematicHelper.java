package com.Blood.Ware.utils;

import java.math.*;
import net.minecraft.util.math.*;
import net.minecraft.client.*;

public class MathematicHelper implements Helper
{
    public static double round(final double n, final double n2) {
        return new BigDecimal(Math.round(n / n2) * n2).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static float clamp(float n, final float n2, final float n3) {
        if (n <= n2) {
            n = n2;
        }
        if (n >= n3) {
            n = n3;
        }
        return n;
    }

    public static BigDecimal round(final float f, final int newScale) {
        return new BigDecimal(Float.toString(f)).setScale(newScale, RoundingMode.HALF_UP);
    }

    public static float checkAngle(final float n, final float n2, final float n3) {
        float wrapDegrees = MathHelper.wrapDegrees(n - n2);
        if (wrapDegrees < -n3) {
            wrapDegrees = -n3;
        }
        if (wrapDegrees >= n3) {
            wrapDegrees = n3;
        }
        return n - wrapDegrees;
    }

    public Minecraft mc() {
        return null;
    }

    public static int getMiddle(final int n, final int n2) {
        return (n + n2) / 2;
    }

    public static float randomizeFloat(final float n, final float n2) {
        return (float)(n + (n2 - n) * Math.random());
    }
}
