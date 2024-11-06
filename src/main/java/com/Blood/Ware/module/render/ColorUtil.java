package com.Blood.Ware.module.render;

import java.awt.*;

public class ColorUtil
{
    public static int RGBtoHex(final int n, final int n2, final int n3) {
        return n << 16 | n2 << 8 | n3;
    }

    public static int toRGBA(final float[] array) {
        if (array.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return toRGBA(array[0], array[1], array[2], array[3]);
    }

    public static int toARGB(final int r, final int g, final int b, final int a) {
        return new Color(r, g, b, a).getRGB();
    }

    public static int toRGBA(final int n, final int n2, final int n3) {
        return toRGBA(n, n2, n3, 255);
    }

    public static int toRGBA(final Color color) {
        return toRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static int toRGBA(final int n, final int n2, final int n3, final int n4) {
        return (n << 16) + (n2 << 8) + n3 + (n4 << 24);
    }

    public static int[] getRainbow(final int n, final float n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        final float n6 = 6.0f * ((System.currentTimeMillis() - Math.round(n2 * 1000.0f)) % (n * 1000)) / (n * 1000);
        final int round = Math.round(255.0f * (n6 - (float)Math.floor(n6)));
        if (n6 < 1.0f) {
            n3 = 255;
            n4 = round;
        }
        else if (n6 < 2.0f) {
            n3 = 255 - round;
            n4 = 255;
        }
        else if (n6 < 3.0f) {
            n4 = 255;
            n5 = round;
        }
        else if (n6 < 4.0f) {
            n4 = 255 - round;
            n5 = 255;
        }
        else if (n6 < 5.0f) {
            n3 = round;
            n5 = 255;
        }
        else if (n6 < 6.0f) {
            n3 = 255;
            n5 = 255 - round;
        }
        return new int[] { n3, n4, n5 };
    }

    public static int toRGBA(final double[] array) {
        if (array.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return toRGBA((float)array[0], (float)array[1], (float)array[2], (float)array[3]);
    }

    public static int toRGBA(final float n, final float n2, final float n3, final float n4) {
        return toRGBA((int)(n * 255.0f), (int)(n2 * 255.0f), (int)(n3 * 255.0f), (int)(n4 * 255.0f));
    }
}
