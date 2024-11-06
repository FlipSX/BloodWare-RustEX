package com.Blood.Ware.utils.util;

import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.util.Random;

public class ColorUtil {
    private static final Random r = new Random();
    public static final int a = new Color(246, 7, 214, 255).getRGB();
    public static final int b = new Color(0, 255, 0, 255).getRGB();
    public static final int c = new Color(0, 0, 255, 255).getRGB();
    public static final int d = new Color(0, 0, 0, 255).getRGB();
    public static final int e = new Color(200, 200, 200, 255).getRGB();
    public static final int f = new Color(255, 255, 255, 155).getRGB();
    public static final int g = new Color(50, 50, 50, 255).getRGB();
    public static final int h = new Color(20, 20, 20, 255).getRGB();
    public static final int i = new Color(120, 120, 120, 200).getRGB();
    public static final int j = new Color(255, 255, 0, 255).getRGB();
    public static final int k = new Color(255, 165, 0, 255).getRGB();
    public static final int l = new Color(255, 0, 255, 255).getRGB();
    public static final int m = new Color(0, 255, 255, 255).getRGB();
    public static final int n = new Color(0, 155, 155, 255).getRGB();
    public static final int o = new Color(155, 20, 20, 255).getRGB();
    public static final int p = new Color(0, 175, 255, 255).getRGB();
    public static final int q = new Color(0, 0, 0, 0).getRGB();

    public static void setColor(float f2, float f3, float f4, float f5) {
        GlStateManager.color((float)f2, (float)f3, (float)f4, (float)f5);
    }

    public static void setColor(Color color) {
        GlStateManager.color((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
    }

    public static void setColor(int n2) {
        GlStateManager.color((float)((float)(n2 >> 16 & 0xFF) / 255.0f), (float)((float)(n2 >> 8 & 0xFF) / 255.0f), (float)((float)(n2 & 0xFF) / 255.0f), (float)((float)(n2 >> 24 & 0xFF) / 255.0f));
    }
    public static int b(int n2, float f2) {
        float[] fArray = ColorUtil.a(n2);
        int n3 = ColorUtil.a(fArray[0], fArray[1], fArray[2], f2);
        return n3;
    }
    public static Color a(int n2, long l2, float f2) {
        double d2 = Math.ceil(System.currentTimeMillis() + l2 + (long)n2) / 15.0;
        Color color = ColorUtil.a((float)((d2 %= 360.0) / 360.0), f2, 1.0f);
        return color;
    }
    public static Color a(float f2, float f3, float f4) {
        return Color.getHSBColor(f2, f3, f4);
    }
    public static int a(int n2, int n3, float f2) {
        float f3 = 1;
        float f4;
        float f5 = 2900.0f;
        for (f4 = (float)(System.currentTimeMillis() % (long)((int)f5)) + (float)((n3 - n2) * 9); f4 > f5; f4 -= f5) {
        }
        f4 /= f5;
        if ((double)f3 > 0.5) {
            f4 = 0.5f - (f4 - 0.5f);
        }
        return Color.HSBtoRGB(f4 += 0.5f, f2, 1.0f);
    }
    public static float[] a(int n2) {
        float f2 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n2 & 0xFF) / 255.0f;
        return new float[]{f3, f4, f5, f2};
    }
    public static int a(float f2, float f3, float f4, float f5) {
        return new Color(f2, f3, f4, f5).getRGB();
    }
}