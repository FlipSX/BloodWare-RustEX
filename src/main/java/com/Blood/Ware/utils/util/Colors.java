package com.Blood.Ware.utils.util;

import java.awt.*;

public class Colors
{
    public static int getColor(final int n) {
        return getColor(n, n, n, 255);
    }

    public static int getColor(final int n, final int n2, final int n3) {
        return getColor(n, n2, n3, 255);
    }

    public static int getColor(final int n, final int n2, final int n3, final int n4) {
        return 0x0 | n4 << 24 | n << 16 | n2 << 8 | n3;
    }

    public static int getColor(final int n, final int n2) {
        return getColor(n, n, n, n2);
    }

    public static int getColor(final Color color) {
        return getColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
}
