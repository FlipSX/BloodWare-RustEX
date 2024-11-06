//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.Castom.setting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Random;

public class DrawHelper
{
    private static int test;
    protected static float zLevel;
    private static int time;
    private static boolean anim;
    private static float alpheble;
    private static float animtest;
    private static final Frustum frustrum;
    private static Minecraft mc;
    
    private static boolean isInViewFrustrum(final AxisAlignedBB axisAlignedBB) {
        final Entity getRenderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
        DrawHelper.frustrum.setPosition(getRenderViewEntity.posX, getRenderViewEntity.posY, getRenderViewEntity.posZ);
        return DrawHelper.frustrum.isBoundingBoxInFrustum(axisAlignedBB);
    }
    
    public static int toRGBA(final float n, final float n2, final float n3, final float n4) {
        return toRGBA((int)(n * 255.0f), (int)(n2 * 255.0f), (int)(n3 * 255.0f), (int)(n4 * 255.0f));
    }
    
    public static int reAlpha(final int rgb, final float a) {
        final Color color = new Color(rgb);
        return new Color(0.003921569f * color.getRed(), 0.003921569f * color.getGreen(), 0.003921569f * color.getBlue(), a).getRGB();
    }
    
    public static void enableSmoothLine(final float n) {
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2884);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glLineWidth(n);
    }
    
    public static void startSmooth() {
        GL11.glEnable(2848);
        GL11.glEnable(2881);
        GL11.glEnable(2832);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glHint(3153, 4354);
    }
    
    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }
    
    public static Color getGradientOffset(final Color color, final Color color2, double n) {
        if (n > 1.0) {
            final double n2 = n % 1.0;
            n = (((int)n % 2 == 0) ? n2 : (1.0 - n2));
        }
        final double n3 = 1.0 - n;
        return new Color((int)(color.getRed() * n3 + color2.getRed() * n), (int)(color.getGreen() * n3 + color2.getGreen() * n), (int)(color.getBlue() * n3 + color2.getBlue() * n));
    }
    
    public static int getColor(final int n, final int n2, final int n3) {
        return getColor(n, n2, n3, 255);
    }
    
    public static int getColor(final int n, final int n2) {
        return getColor(n, n, n, n2);
    }
    
    public static void drawRect2(final double n, final double n2, final double n3, final double n4, final int n5) {
        drawRect(n, n2, n + n3, n2 + n4, n5);
    }
    
    public static double interpolate(final double n, final double n2, final double n3) {
        return n2 + (n - n2) * n3;
    }
    
    public static int getTeamColor(final Entity entity) {
        return entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf(new StringBuilder().append("\u043f\u0457\u0405f[\u043f\u0457\u0405cR\u043f\u0457\u0405f]\u043f\u0457\u0405c").append(entity.getName()))) ? getColor(new Color(255, 60, 60).getRGB()) : (entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf(new StringBuilder().append("\u043f\u0457\u0405f[\u043f\u0457\u04059B\u043f\u0457\u0405f]\u043f\u0457\u04059").append(entity.getName()))) ? getColor(new Color(60, 60, 255).getRGB()) : (entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf(new StringBuilder().append("\u043f\u0457\u0405f[\u043f\u0457\u0405eY\u043f\u0457\u0405f]\u043f\u0457\u0405e").append(entity.getName()))) ? getColor(new Color(255, 255, 60).getRGB()) : (entity.getDisplayName().getUnformattedText().equalsIgnoreCase(String.valueOf(new StringBuilder().append("\u043f\u0457\u0405f[\u043f\u0457\u0405aG\u043f\u0457\u0405f]\u043f\u0457\u0405a").append(entity.getName()))) ? getColor(new Color(60, 255, 60).getRGB()) : getColor(new Color(255, 255, 255).getRGB()))));
    }
    
    public static void drawRect(double n, double n2, double n3, double n4, final int n5) {
        if (n < n3) {
            final double n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final double n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        final float n8 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(n9, n10, n11, n8);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n4, 0.0).endVertex();
        getBuffer.pos(n3, n4, 0.0).endVertex();
        getBuffer.pos(n3, n2, 0.0).endVertex();
        getBuffer.pos(n, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static int getRandomColor() {
        final char[] charArray = "012345678".toCharArray();
        String value = "0x";
        for (int i = 0; i < 6; ++i) {
            value = String.valueOf(new StringBuilder().append(value).append(charArray[new Random().nextInt(charArray.length)]));
        }
        return Integer.decode(value);
    }
    
    public static void drawSelectionBoundingBox(final AxisAlignedBB axisAlignedBB) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getInstance.draw();
    }
    
    public static int astolfoColors5(final float n, final float n2, final float saturation, final float n3) {
        float n4;
        float n5;
        for (n4 = 1800.0f, n5 = System.currentTimeMillis() % (int)n4 + (n2 - n) * n3; n5 > n4; n5 -= n4) {}
        float n6 = n5 / n4;
        if (n6 > 0.5) {
            n6 = 0.5f - (n6 - 0.5f);
        }
        return Color.HSBtoRGB(n6 + 0.5f, saturation, 1.0f);
    }
    
    public static Color TwoColoreffect(final Color color, final Color color2, final double n) {
        final float clamp = MathHelper.clamp((float)Math.sin(18.84955592153876 * (n / 4.0 % 1.0)) / 2.0f + 0.5f, 0.0f, 1.0f);
        return new Color(lerp(color.getRed() / 255.0f, color2.getRed() / 255.0f, clamp), lerp(color.getGreen() / 255.0f, color2.getGreen() / 255.0f, clamp), lerp(color.getBlue() / 255.0f, color2.getBlue() / 255.0f, clamp));
    }
    
    public static int[] getFractionIndicies(final float[] array, final float n) {
        final int[] array2 = new int[2];
        int n2;
        for (n2 = 0; n2 < array.length && array[n2] <= n; ++n2) {}
        if (n2 >= array.length) {
            n2 = array.length - 1;
        }
        array2[0] = n2 - 1;
        array2[1] = n2;
        return array2;
    }
    
    public static Color rainbow2(final int n, final float s, final float b) {
        return Color.getHSBColor((float)(Math.ceil((double)((System.currentTimeMillis() + n) / 16L)) % 360.0 / 360.0), s, b);
    }
    
    public static void drawImage(final ResourceLocation resourceLocation, final int n, final int n2, final int n3, final int n4, final int color) {
        GL11.glPushMatrix();
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        setColor(color);
        DrawHelper.mc.getTextureManager().bindTexture(resourceLocation);
        GL11.glTexParameteri(3553, 10240, 9729);
        GL11.glTexParameteri(3553, 10241, 9729);
        Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0f, 0.0f, n3, n4, (float)n3, (float)n4);
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glPopMatrix();
    }
    
    public static void prepareScissorBox(final float n, final float n2, final float n3, final float n4) {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        final int getScaleFactor = scaledResolution.getScaleFactor();
        GL11.glScissor((int)(n * getScaleFactor), (int)((scaledResolution.getScaledHeight() - n4) * getScaleFactor), (int)((n3 - n) * getScaleFactor), (int)((n4 - n2) * getScaleFactor));
    }
    
    public static Color astolfoColor(final int n, final int n2) {
        float n3;
        float n4;
        for (n3 = 2900.0f, n4 = System.currentTimeMillis() % (int)n3 + (float)((n2 - n) * 9); n4 > n3; n4 -= n3) {}
        float n5 = n4 / n3;
        if (n5 > 0.5) {
            n5 = 0.5f - (n5 - 0.5f);
        }
        return new Color(n5 + 0.5f, 0.5f, 1.0f);
    }
    
    static {
        DrawHelper.mc = Minecraft.getMinecraft();
        frustrum = new Frustum();
    }
    
    public static void drawFilledBox(final AxisAlignedBB axisAlignedBB) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getInstance.draw();
    }
    
    public static void drawOutline(final AxisAlignedBB axisAlignedBB, final float n, final Color color) {
        GL11.glPushMatrix();
        GL11.glLineWidth(n);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        getInstance.draw();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glPopMatrix();
    }
    
    public static int getColor(final int n, final int n2, final int n3, final int n4) {
        return 0x0 | n4 << 24 | n << 16 | n2 << 8 | n3;
    }
    
    public static int rainbow(final int n, final float s, final float b) {
        return Color.getHSBColor((float)(Math.ceil((double)((System.currentTimeMillis() + n) / 16L)) % 360.0 / 360.0), s, b).getRGB();
    }
    
    public static void disableSmoothLine() {
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDepthMask(true);
        GL11.glCullFace(1029);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static Color getRainbow(final int n, final int n2) {
        return Color.getHSBColor((System.currentTimeMillis() + n) % n2 / (float)n2, 0.7f, 1.0f);
    }
    
    public static float lerp(final float n, final float n2, final float n3) {
        return n + n3 * (n2 - n);
    }
    
    public static void glColor(final Color color, final int n) {
        glColor(color, n / 255.0f);
    }
    
    public static void endSmooth() {
        GL11.glDisable(2848);
        GL11.glDisable(2881);
        GL11.glEnable(2832);
    }
    
    public static void drawGradientSideways(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(n8, n9, n10, n7);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n, n4);
        GL11.glColor4f(n12, n13, n14, n11);
        GL11.glVertex2d(n3, n4);
        GL11.glVertex2d(n3, n2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static void drawVGradientRect(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static int getColor(final int n) {
        return getColor(n, n, n, 255);
    }
    
    public static Color blend(final Color color, final Color color2, final double n) {
        final float n2 = (float)n;
        final float n3 = 1.0f - n2;
        final float[] compArray = new float[3];
        final float[] compArray2 = new float[3];
        color.getColorComponents(compArray);
        color2.getColorComponents(compArray2);
        float r = compArray[0] * n2 + compArray2[0] * n3;
        float g = compArray[1] * n2 + compArray2[1] * n3;
        float b = compArray[2] * n2 + compArray2[2] * n3;
        if (r < 0.0f) {
            r = 0.0f;
        }
        else if (r > 255.0f) {
            r = 255.0f;
        }
        if (g < 0.0f) {
            g = 0.0f;
        }
        else if (g > 255.0f) {
            g = 255.0f;
        }
        if (b < 0.0f) {
            b = 0.0f;
        }
        else if (b > 255.0f) {
            b = 255.0f;
        }
        Color color3 = null;
        try {
            color3 = new Color(r, g, b);
        }
        catch (IllegalArgumentException ex) {
            NumberFormat.getNumberInstance();
        }
        return color3;
    }
    
    public static void drawNewRect(double n, double n2, double n3, double n4, final int n5) {
        if (n < n3) {
            final double n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final double n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        final float n8 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(n9, n10, n11, n8);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n4, 0.0).endVertex();
        getBuffer.pos(n3, n4, 0.0).endVertex();
        getBuffer.pos(n3, n2, 0.0).endVertex();
        getBuffer.pos(n, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void glColor(final Color color, final float n) {
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n);
    }
    
    public static Color fade(final Color color, final int n, final int n2) {
        final float[] hsbvals = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbvals);
        hsbvals[2] = (0.5f + 0.5f * Math.abs((System.currentTimeMillis() % 2000L / 1000.0f + n / (float)n2 * 2.0f) % 2.0f - 1.0f)) % 2.0f;
        return new Color(Color.HSBtoRGB(hsbvals[0], hsbvals[1], hsbvals[2]));
    }
    
    public static int setColor(final int n) {
        final float n2 = (n >> 24 & 0xFF) / 255.0f;
        GL11.glColor4f((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n2 == 0.0f) ? 1.0f : n2);
        return n;
    }
    
    public static Color getHealthColor(final EntityLivingBase entityLivingBase) {
        final float getHealth = entityLivingBase.getHealth();
        final float[] array = { 0.0f, 0.15f, 0.55f, 0.7f, 0.9f };
        final Color[] array2 = { new Color(133, 0, 0), Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN };
        final float n = getHealth / entityLivingBase.getMaxHealth();
        return (getHealth >= 0.0f) ? blendColors(array, array2, n).brighter() : array2[0];
    }
    
    public static void drawHead(final ResourceLocation resourceLocation, final int n, final int n2, final int n3, final int n4) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        DrawHelper.mc.getTextureManager().bindTexture(resourceLocation);
        Gui.drawScaledCustomSizeModalRect(n, n2, 8.0f, 8.0f, 8, 8, n3, n4, 64.0f, 64.0f);
    }
    
    public static void drawFilledCircle(final int n, final int n2, final float n3, final Color color) {
        final int n4 = 50;
        final double n5 = 6.283185307179586 / n4;
        GL11.glPushAttrib(8192);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glBegin(6);
        for (int i = 0; i < n4; ++i) {
            final float n6 = (float)(n3 * Math.sin(i * n5));
            final float n7 = (float)(n3 * Math.cos(i * n5));
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glVertex2f(n + n6, n2 + n7);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnd();
        GL11.glPopAttrib();
    }
    
    public static void drawGradientRect(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(n3, n2, (double)DrawHelper.zLevel).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos(n, n2, (double)DrawHelper.zLevel).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos(n, n4, (double)DrawHelper.zLevel).color(n12, n13, n14, n11).endVertex();
        getBuffer.pos(n3, n4, (double)DrawHelper.zLevel).color(n12, n13, n14, n11).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static int astolfoColors4(final float n, final float n2, final float saturation) {
        float n3;
        float n4;
        for (n3 = 1800.0f, n4 = System.currentTimeMillis() % (int)n3 + (n2 - n) * 12.0f; n4 > n3; n4 -= n3) {}
        float n5 = n4 / n3;
        if (n5 > 0.5) {
            n5 = 0.5f - (n5 - 0.5f);
        }
        return Color.HSBtoRGB(n5 + 0.5f, saturation, 1.0f);
    }
    
    public static Color setAlpha(final Color color, int clamp) {
        clamp = MathHelper.clamp(clamp, 0, 255);
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), clamp);
    }
    
    public static void drawPolygonPart(final double n, final double n2, final int n3, final int n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(6, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(n, n2, 0.0).color(n8, n9, n10, n7).endVertex();
        for (int i = n4 * 90; i <= n4 * 90 + 90; ++i) {
            final double n15 = 6.283185307179586 * i / 360.0 + Math.toRadians(180.0);
            getBuffer.pos(n + Math.sin(n15) * n3, n2 + Math.cos(n15) * n3, 0.0).color(n12, n13, n14, n11).endVertex();
        }
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static boolean isInViewFrustrum(final Entity entity) {
        return isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }
    
    public static Color blendColors(final float[] array, final Color[] array2, final float n) {
        if (array == null) {
            throw new IllegalArgumentException("Fractions can't be null");
        }
        if (array2 == null) {
            throw new IllegalArgumentException("Colours can't be null");
        }
        if (array.length != array2.length) {
            throw new IllegalArgumentException("Fractions and colours must have equal number of elements");
        }
        final int[] fractionIndicies = getFractionIndicies(array, n);
        final float[] array3 = { array[fractionIndicies[0]], array[fractionIndicies[1]] };
        final Color[] array4 = { array2[fractionIndicies[0]], array2[fractionIndicies[1]] };
        return blend(array4[0], array4[1], 1.0f - (n - array3[0]) / (array3[1] - array3[0]));
    }
    
    public static int astolfoColors(final int n, final int n2) {
        float n3;
        float n4;
        for (n3 = 2900.0f, n4 = System.currentTimeMillis() % (int)n3 + (float)((n2 - n) * 9); n4 > n3; n4 -= n3) {}
        float n5;
        if ((n5 = n4 / n3) > 0.5) {
            n5 = 0.5f - (n5 - 0.5f);
        }
        return Color.HSBtoRGB(n5 + 0.5f, 0.5f, 1.0f);
    }
    
    public static final void drawSmoothRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        drawRect(n, n2, n3, n4, n5);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawRect(n * 2.0f - 1.0f, n2 * 2.0f, n * 2.0f, n4 * 2.0f - 1.0f, n5);
        drawRect(n * 2.0f, n2 * 2.0f - 1.0f, n3 * 2.0f, n2 * 2.0f, n5);
        drawRect(n3 * 2.0f, n2 * 2.0f, n3 * 2.0f + 1.0f, n4 * 2.0f - 1.0f, n5);
        drawRect(n * 2.0f, n4 * 2.0f - 1.0f, n3 * 2.0f, n4 * 2.0f, n5);
        GL11.glDisable(3042);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public static Color astolfoColors45(final float n, final float n2, final float s, final float n3) {
        float n4;
        float n5;
        for (n4 = 1800.0f, n5 = System.currentTimeMillis() % (int)n4 + (n2 - n) * n3; n5 > n4; n5 -= n4) {}
        float n6 = n5 / n4;
        if (n6 > 0.5) {
            n6 = 0.5f - (n6 - 0.5f);
        }
        return Color.getHSBColor(n6 + 0.5f, s, 1.0f);
    }
    
    public static int toRGBA(final int n, final int n2, final int n3, final int n4) {
        return (n << 16) + (n2 << 8) + (n3 << 0) + (n4 << 24);
    }
    
    public static final void color(final double n, final double n2, final double n3, final double n4) {
        GL11.glColor4d(n, n2, n3, n4);
    }
    
    public static int color(final int r, final int g, final int b, int a) {
        a = 255;
        return new Color(r, g, b, a).getRGB();
    }
    
    public static final void color(Color white) {
        if (white == null) {
            white = Color.white;
        }
        color(white.getRed() / 255.0f, white.getGreen() / 255.0f, white.getBlue() / 255.0f, white.getAlpha() / 255.0f);
    }
    
    public static void drawGlow(final double n, final double n2, final double n3, final double n4, final int n5) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        drawVGradientRect((float)(int)n, (float)(int)n2, (float)(int)n3, (float)(int)(n2 + (n4 - n2) / 2.0), setAlpha(new Color(n5), 0).getRGB(), n5);
        drawVGradientRect((float)(int)n, (float)(int)(n2 + (n4 - n2) / 2.0), (float)(int)n3, (float)(int)n4, n5, setAlpha(new Color(n5), 0).getRGB());
        final int n6 = (int)((n4 - n2) / 2.0);
        drawPolygonPart(n, n2 + (n4 - n2) / 2.0, n6, 0, n5, setAlpha(new Color(n5), 0).getRGB());
        drawPolygonPart(n, n2 + (n4 - n2) / 2.0, n6, 1, n5, setAlpha(new Color(n5), 0).getRGB());
        drawPolygonPart(n3, n2 + (n4 - n2) / 2.0, n6, 2, n5, setAlpha(new Color(n5), 0).getRGB());
        drawPolygonPart(n3, n2 + (n4 - n2) / 2.0, n6, 3, n5, setAlpha(new Color(n5), 0).getRGB());
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static int astolfo(final int n, final float n2) {
        float n3;
        float abs;
        for (n3 = 3000.0f, abs = Math.abs(System.currentTimeMillis() % n + -n2 / 21.0f * 2.0f); abs > n3; abs -= n3) {}
        float n4;
        if ((n4 = abs / n3) > 0.5) {
            n4 = 0.5f - (n4 - 0.5f);
        }
        return Color.HSBtoRGB(n4 + 0.5f, 0.5f, 1.0f);
    }
    
    public static class Colors
    {
        public static final int RED;
        public static final int BLUE;
        public static final int BLACK;
        public static final int GRAY;
        public static final int DARK_RED;
        public static final int PURPLE;
        public static final int ORANGE;
        public static final int WHITE;
        public static final int YELLOW;
        public static final int GREEN;
        
        static {
            WHITE = DrawHelper.toRGBA(255, 255, 255, 255);
            BLACK = DrawHelper.toRGBA(0, 0, 0, 255);
            RED = DrawHelper.toRGBA(255, 0, 0, 255);
            GREEN = DrawHelper.toRGBA(0, 255, 0, 255);
            BLUE = DrawHelper.toRGBA(0, 0, 255, 255);
            ORANGE = DrawHelper.toRGBA(255, 128, 0, 255);
            PURPLE = DrawHelper.toRGBA(163, 73, 163, 255);
            GRAY = DrawHelper.toRGBA(127, 127, 127, 255);
            DARK_RED = DrawHelper.toRGBA(64, 0, 0, 255);
            YELLOW = DrawHelper.toRGBA(255, 255, 0, 255);
        }
    }
}
