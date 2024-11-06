/*
 * Decompiled with CFR 0.150.
 */
package com.Blood.Ware.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

public class MathUtils
{
    public static final float deg2Rad = 0.017453292f;
    private static final Random random;

    public static double[] directionSpeedNoForward(final double n) {
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        float moveForward = 1.0f;
        if (getMinecraft.gameSettings.keyBindLeft.isPressed() || getMinecraft.gameSettings.keyBindRight.isPressed() || getMinecraft.gameSettings.keyBindBack.isPressed() || getMinecraft.gameSettings.keyBindForward.isPressed()) {
            moveForward = getMinecraft.player.movementInput.moveForward;
        }
        float moveStrafe = getMinecraft.player.movementInput.moveStrafe;
        float n2 = getMinecraft.player.prevRotationYaw + (getMinecraft.player.rotationYaw - getMinecraft.player.prevRotationYaw) * getMinecraft.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }

    public static boolean isInteger(final Double n) {
        return n == Math.floor(n) && !Double.isInfinite(n);
    }

    public static double roundToPlace(final double val, final int newScale) {
        if (newScale < 0) {
            throw new IllegalArgumentException();
        }
        return new BigDecimal(val).setScale(newScale, RoundingMode.HALF_UP).doubleValue();
    }

    public static double[] directionSpeed(final double n) {
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        float moveForward = getMinecraft.player.movementInput.moveForward;
        float moveStrafe = getMinecraft.player.movementInput.moveStrafe;
        float n2 = getMinecraft.player.prevRotationYaw + (getMinecraft.player.rotationYaw - getMinecraft.player.prevRotationYaw) * getMinecraft.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }

    public static float lerp(final float n, final float n2, final float n3) {
        return n + n3 * (n2 - n);
    }

    public static void drawUnfilledCircle(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n8 = (n5 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 24 & 0xFF) / 255.0f;
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GlStateManager.enableBlend();
        GL11.glColor4f(n6, n7, n8, n9);
        GL11.glLineWidth(n4);
        GL11.glBegin(2);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n3, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n3);
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
        GlStateManager.disableBlend();
    }

    public static int getRandomInRange(final int n, final int n2) {
        return (int)(n2 + (n - n2) * MathUtils.random.nextDouble());
    }

    public static BigDecimal round(final float f, final double n) {
        return new BigDecimal(Float.toString(f)).setScale((int)n, 4);
    }

    public static double[] getDirection(final float n) {
        return new double[] { -Math.sin(Math.toRadians(n)), Math.cos(Math.toRadians(n)) };
    }

    public static double randomize(final double n, final double n2) {
        double n3 = new Random().nextDouble() * (n2 - n);
        if (n3 > n2) {
            n3 = n2;
        }
        double n4;
        if ((n4 = n3 + n) > n2) {
            n4 = n2;
        }
        return n4;
    }

    public static int randomize(final int n, final int n2) {
        return -n2 + (int)(Math.random() * (n - -n2 + 1));
    }

    public static double random(final double n, final double n2) {
        return Math.random() * (n2 - n) + n;
    }

    public static float sqrt_double(final double a) {
        return (float)Math.sqrt(a);
    }

    public static double roundToDecimalPlace(final double n, final double n2) {
        final double n3 = n2 / 2.0;
        final double val = Math.floor(n / n2) * n2;
        if (n >= val + n3) {
            return new BigDecimal(Math.ceil(n / n2) * n2, MathContext.DECIMAL64).stripTrailingZeros().doubleValue();
        }
        return new BigDecimal(val, MathContext.DECIMAL64).stripTrailingZeros().doubleValue();
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

    public static double degToRad(final double n) {
        return n * 0.01745329238474369;
    }

    public static Vec3d interpolateVec3d(final Vec3d vec3d, final Vec3d vec3d2, final float n) {
        return vec3d.subtract(vec3d2).scale((double)n).add(vec3d2);
    }

    public static double preciseRound(final double n, final double b) {
        final double pow = Math.pow(10.0, b);
        return Math.round(n * pow) / pow;
    }

    static {
        random = new Random();
    }

    public static Vec3d direction(final float n) {
        return new Vec3d(Math.cos(degToRad(n + 90.0f)), 0.0, Math.sin(degToRad(n + 90.0f)));
    }

    public static double randomNumber(final double n, final double n2) {
        return Math.random() * (n - n2) + n2;
    }

    public static double getRandomInRange(final double n, final double n2) {
        return n2 + (n - n2) * MathUtils.random.nextDouble();
    }

    public static double getNormalDouble(final double val) {
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static double getNormalDouble(final double val, final int newScale) {
        return new BigDecimal(val).setScale(newScale, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static boolean isEven(final int n) {
        return n % 2 == 0;
    }

    public static float wrapAngleTo180_float(float n) {
        n %= 360.0f;
        if (n >= 180.0f) {
            n -= 360.0f;
        }
        if (n < -180.0f) {
            n += 360.0f;
        }
        return n;
    }

    public static float random(final float n, final float n2) {
        return (float)(Math.random() * (n2 - n) + n);
    }

    public static Vec3d interpolateEntity(final Entity entity) {
        final double n = Minecraft.getMinecraft().getRenderPartialTicks();
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
    }

    public static float[] constrainAngle(final float[] array) {
        array[0] %= 360.0f;
        array[1] %= 360.0f;
        while (array[0] <= -180.0f) {
            array[0] += 360.0f;
        }
        while (array[1] <= -180.0f) {
            array[1] += 360.0f;
        }
        while (array[0] > 180.0f) {
            array[0] -= 360.0f;
        }
        while (array[1] > 180.0f) {
            array[1] -= 360.0f;
        }
        return array;
    }

    public static double getIncremental(final double n, final double n2) {
        final double n3 = 1.0 / n2;
        return Math.round(n * n3) / n3;
    }
}