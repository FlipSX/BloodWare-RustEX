//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils;

import com.Blood.Ware.utils.search.Blockinfo;
import com.Blood.Ware.utils.util.ColorHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static com.Blood.Ware.utils.RenderUtil.*;

public class RenderUtils
{
    private static final Frustum frustrum;
    public static boolean click;
    private float spin;
    private float cumSize;
    private static final int GL_BLEND;
    private static final int GL_DEPTH_TEST;
    
    public static ResourceLocation drawPic(final double n, final double n2, final double n3, final double n4, final ResourceLocation resourceLocation) {
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        getBuffer.pos(n, n2 + n4, 0.0).tex(0.0, 1.0).color(255, 255, 255, 255).endVertex();
        getBuffer.pos(n + n3, n2 + n4, 0.0).tex(1.0, 1.0).color(255, 255, 255, 255).endVertex();
        getBuffer.pos(n + n3, n2, 0.0).tex(1.0, 0.0).color(255, 255, 255, 255).endVertex();
        getBuffer.pos(n, n2, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
        getInstance.draw();
        GlStateManager.disableAlpha();
        return resourceLocation;
    }
    
    public static void drawCircle228(final float n, final float n2, final float n3, final int n4, final int n5) {
        final float n6 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n4 & 0xFF) / 255.0f;
        final boolean glIsEnabled = GL11.glIsEnabled(3042);
        final boolean glIsEnabled2 = GL11.glIsEnabled(2848);
        final boolean glIsEnabled3 = GL11.glIsEnabled(3553);
        if (!glIsEnabled) {
            GL11.glEnable(3042);
        }
        if (!glIsEnabled2) {
            GL11.glEnable(2848);
        }
        if (glIsEnabled3) {
            GL11.glDisable(3553);
        }
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(n7, n8, n9, n6);
        GL11.glLineWidth(2.5f);
        GL11.glBegin(3);
        for (int i = 0; i <= n5; ++i) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n3, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n3);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        if (glIsEnabled3) {
            GL11.glEnable(3553);
        }
        if (!glIsEnabled2) {
            GL11.glDisable(2848);
        }
        if (!glIsEnabled) {
            GL11.glDisable(3042);
        }
    }
    
    public static void drawRectStatic(final int n, final int n2, final int n3, final int n4, final Color color) {
        Gui.drawRect(n, n2, n3, n4, color.getRGB());
    }
    
    public static void drawSector2(final double n, final double n2, final int n3, final int n4, final int n5) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GL11.glBegin(6);
        glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d(n, n2);
        glColor(new Color(0, 0, 0, 0).getRGB());
        for (int i = n3; i <= n4; ++i) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n5, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n5);
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawESP(final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5) {
        try {
            final double viewerPosX = Wrapper.mc.getRenderManager().viewerPosX;
            final double viewerPosY = Wrapper.mc.getRenderManager().viewerPosY;
            final double viewerPosZ = Wrapper.mc.getRenderManager().viewerPosZ;
            final double n6 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n5 - viewerPosX;
            final double n7 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n5 + entity.height / 2.0f - viewerPosY;
            final double n8 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n5 - viewerPosZ;
            final float playerViewY = Wrapper.mc.getRenderManager().playerViewY;
            final float playerViewX = Wrapper.mc.getRenderManager().playerViewX;
            final boolean b = Wrapper.mc.getRenderManager().options.thirdPersonView == 2;
            GL11.glPushMatrix();
            GlStateManager.translate(n6, n7, n8);
            GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((b ? -1 : 1) * playerViewX, 1.0f, 0.0f, 0.0f);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glLineWidth(1.0f);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glColor4f(n, n2, n3, n4);
            GL11.glBegin(1);
            GL11.glVertex3d(0.0, 1.0, 0.0);
            GL11.glVertex3d(-0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 1.0, 0.0);
            GL11.glVertex3d(0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(-0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.5, 0.5, 0.0);
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glEnable(3553);
            GL11.glEnable(2896);
            GL11.glDisable(2848);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void setColor(final Color color) {
        GL11.glColor4d((double)(color.getRed() / 255.0f), (double)(color.getGreen() / 255.0f), (double)(color.getBlue() / 255.0f), (double)(color.getAlpha() / 255.0f));
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
    
    public static void customScaledObject2D(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        GL11.glTranslated((double)(n3 / 2.0f), (double)(n4 / 2.0f), 1.0);
        GL11.glTranslated((double)(-n * n5 + n + n3 / 2.0f * -n5), (double)(-n2 * n5 + n2 + n4 / 2.0f * -n5), 1.0);
        GL11.glScaled((double)n5, (double)n5, 0.0);
    }
    
    public static void drawDownShadow(final float n, final float n2, final float n3, final float n4) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GL11.glBegin(7);
        glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)n3, (double)n4);
        glColor(new Color(0, 0, 0, 0).getRGB());
        GL11.glVertex2d((double)n3, (double)n2);
        GL11.glVertex2d((double)n, (double)n2);
        glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)n, (double)n4);
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawShadowRect(final double n, final double n2, final double n3, final double n4, final int n5) {
        drawGradientRect(n, n2 - n5, n3, n2, false, true, new Color(0, 0, 0, 150).getRGB(), new Color(0, 0, 0, 0).getRGB());
        drawGradientRect(n, n4, n3, n4 + n5, false, false, new Color(0, 0, 0, 155).getRGB(), new Color(0, 0, 0, 0).getRGB());
        drawSector2(n3, n4, 0, 90, n5);
        drawSector2(n3, n2, 90, 180, n5);
        drawSector2(n, n2, 180, 270, n5);
        drawSector2(n, n4, 270, 360, n5);
        drawGradientRect(n - n5, n2, n, n4, true, true, new Color(0, 0, 0, 155).getRGB(), new Color(0, 0, 0, 0).getRGB());
        drawGradientRect(n3, n2, n3 + n5, n4, true, false, new Color(0, 0, 0, 155).getRGB(), new Color(0, 0, 0, 0).getRGB());
    }
    
    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }
    
    public static void drawGradientRect(final double n, final double n2, final double n3, final double n4, final boolean b, final int n5, final int n6) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        glColor(n5);
        GL11.glBegin(7);
        if (b) {
            GL11.glVertex2d(n, n2);
            GL11.glVertex2d(n, n4);
            glColor(n6);
            GL11.glVertex2d(n3, n4);
            GL11.glVertex2d(n3, n2);
        }
        else {
            GL11.glVertex2d(n, n2);
            glColor(n6);
            GL11.glVertex2d(n, n4);
            GL11.glVertex2d(n3, n4);
            glColor(n5);
            GL11.glVertex2d(n3, n2);
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void renderItem(final ItemStack itemStack, final int n, final int n2) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        MinecraftHelper.mc.getRenderItem().zLevel = -100.0f;
        MinecraftHelper.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, n2);
        MinecraftHelper.mc.getRenderItem().renderItemOverlays(MinecraftHelper.mc.fontRenderer, itemStack, n, n2);
        MinecraftHelper.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.disableDepth();
    }
    
    public static void drawRectSized(final float n, final float n2, final float n3, final float n4, final int n5) {
        drawRect(n, n2, n + n3, n2 + n4, n5);
    }
    
    public static double lerp(final double n, final double n2, final double n3) {
        return (1.0 - n3) * n + n3 * n2;
    }
    
    static {
        GL_BLEND = 0;
        GL_DEPTH_TEST = 0;
        frustrum = new Frustum();
    }
    
    public static void draw2DRect(final int n, final int n2, final int n3, final int n4, final int n5) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos((double)n, (double)(n2 + n4), (double)n5).endVertex();
        getBuffer.pos((double)(n + n3), (double)(n2 + n4), (double)n5).endVertex();
        getBuffer.pos((double)(n + n3), (double)n2, (double)n5).endVertex();
        getBuffer.pos((double)n, (double)n2, (double)n5).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void setupColor(final Color color, final int n) {
        GL11.glColor4d((double)(color.getRed() / 255.0f), (double)(color.getGreen() / 255.0f), (double)(color.getBlue() / 255.0f), (double)(n / 255.0f));
    }
    
    public static void drawImage(final ResourceLocation resourceLocation, final int n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        glColor(n5);
        MinecraftHelper.mc.getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0f, 0.0f, n3, n4, (float)n3, (float)n4);
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
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
    
    public static void drawGradient(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        RenderUtil.glRenderStart();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(n8, n9, n10, n7);
        GL11.glVertex2d(n3, n2);
        GL11.glVertex2d(n, n2);
        GL11.glColor4f(n12, n13, n14, n11);
        GL11.glVertex2d(n, n4);
        GL11.glVertex2d(n3, n4);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
        GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
        RenderUtil.glRenderStop();
    }
    
    public static void drawGradientRect(final double n, final double n2, final double n3, final double n4, final boolean b, final boolean b2, final int n5, final int n6) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GL11.glBegin(7);
        glColor(n5);
        if (b) {
            if (b2) {
                GL11.glVertex2d(n3, n4);
                GL11.glVertex2d(n3, n2);
                glColor(n6);
                GL11.glVertex2d(n, n2);
                GL11.glVertex2d(n, n4);
            }
            else {
                GL11.glVertex2d(n, n2);
                GL11.glVertex2d(n, n4);
                glColor(n6);
                GL11.glVertex2d(n3, n4);
                GL11.glVertex2d(n3, n2);
            }
        }
        else if (b2) {
            GL11.glVertex2d(n3, n4);
            glColor(n6);
            GL11.glVertex2d(n3, n2);
            GL11.glVertex2d(n, n2);
            glColor(n5);
            GL11.glVertex2d(n, n4);
        }
        else {
            GL11.glVertex2d(n, n2);
            glColor(n6);
            GL11.glVertex2d(n, n4);
            GL11.glVertex2d(n3, n4);
            glColor(n5);
            GL11.glVertex2d(n3, n2);
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void blockEspFrame(final BlockPos blockPos, final float n, final float n2, final float n3) {
        final double n4 = blockPos.getX() - MinecraftHelper.mc.getRenderManager().viewerPosX;
        final double n5 = blockPos.getY() - MinecraftHelper.mc.getRenderManager().viewerPosY;
        final double n6 = blockPos.getZ() - MinecraftHelper.mc.getRenderManager().viewerPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GlStateManager.color(n, n2, n3, 1.0f);
        RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(n4, n5, n6, n4 + 1.0, n5 + 1.0, n6 + 1.0));
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void blockEspBox(final BlockPos blockPos, final double n, final double n2, final double n3) {
        final double n4 = blockPos.getX() - Minecraft.getMinecraft().getRenderManager().viewerPosX;
        final double n5 = blockPos.getY() - Minecraft.getMinecraft().getRenderManager().viewerPosY;
        final double n6 = blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(0);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(0);
        GL11.glDisable(2896);
        GL11.glDepthMask(false);
        GL11.glColor4d(n, n2, n3, 0.15000000596046448);
        RenderUtil.drawColorBox(new AxisAlignedBB(n4, n5, n6, n4 + 1.0, n5 + 1.0, n6 + 1.0), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glDepthMask(false);
        GL11.glColor4d(n, n2, n3, 0.15000000596046448);
        RenderUtil.drawColorBox(new AxisAlignedBB(n4, n5, n6, n4 + 1.0, n5 + 1.0, n6 + 1.0), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glEnable(3553);
        GL11.glEnable(0);
        GL11.glEnable(2896);
        GL11.glDepthMask(true);
        GL11.glDisable(0);
        GL11.glPopMatrix();
    }
    
    public static void trace(final Minecraft minecraft, final Entity entity, final float n, final int n2) {
        if (minecraft.getRenderManager().renderViewEntity != null) {
            GL11.glDisable(2929);
            GL11.glDisable(2896);
            GL11.glLineWidth(2.0f);
            GL11.glPushMatrix();
            GL11.glDepthMask(false);
            GL11.glColor4d(0.0, (n2 == 1) ? 1.0 : 0.0, 0.0, 1.0);
            GL11.glBlendFunc(770, 771);
            GL11.glDisable(3553);
            GL11.glBegin(1);
            final RenderManager getRenderManager = minecraft.getRenderManager();
            final Vec3d rotateYaw = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(minecraft.player.rotationPitch)).rotateYaw(-(float)Math.toRadians(minecraft.player.rotationYaw));
            GL11.glVertex3d(rotateYaw.x, minecraft.player.getEyeHeight() + rotateYaw.y, rotateYaw.z);
            GL11.glVertex3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n - getRenderManager.viewerPosX, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n - getRenderManager.viewerPosY + 0.25, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n - getRenderManager.viewerPosZ);
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glEnable(3553);
            GL11.glPopMatrix();
        }
    }

    public static void trace(final Minecraft minecraft, final Blockinfo blockinfo) {
        if (minecraft.getRenderManager().renderViewEntity != null) {
            GL11.glDisable(2929);
            GL11.glDisable(2896);
            GL11.glLineWidth(2.0f);
            GL11.glPushMatrix();
            GL11.glDepthMask(false);
            GL11.glColor4d(blockinfo.getR(), blockinfo.getG(), blockinfo.getB(), 1.0);
            GL11.glBlendFunc(770, 771);
            GL11.glDisable(3553);
            GL11.glBegin(1);
            final RenderManager RenderManager = minecraft.getRenderManager();
            final Vec3d rotateYaw = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(minecraft.player.rotationPitch)).rotateYaw(-(float)Math.toRadians(minecraft.player.rotationYaw));
            GL11.glVertex3d(rotateYaw.x, minecraft.player.getEyeHeight() + rotateYaw.y, rotateYaw.z);
            GL11.glVertex3d(blockinfo.getX() - RenderManager.viewerPosX, blockinfo.getY() - RenderManager.viewerPosY, blockinfo.getZ() - RenderManager.viewerPosZ);
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            //GL11.glEnable(2896);
            GL11.glEnable(3553);
            GL11.glPopMatrix();
        }
    }
    
    public static void glColor(final int n) {
        GlStateManager.color((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f);
    }
    
    public static void drawBorderedRect(final double n, final double n2, final double n3, final double n4, final float n5, final int n6, final int n7) {
        drawRect((float)n, (float)n2, (float)n3, (float)n4, n7);
        final float n8 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n6 & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glColor4f(n9, n10, n11, n8);
        GL11.glLineWidth(n5);
        GL11.glBegin(1);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n, n4);
        GL11.glVertex2d(n3, n4);
        GL11.glVertex2d(n3, n2);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n3, n2);
        GL11.glVertex2d(n, n4);
        GL11.glVertex2d(n3, n4);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void drawEntityOnScreen(final float n, final float n2, final float n3, final EntityLivingBase entityLivingBase) {
        GlStateManager.pushMatrix();
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GlStateManager.enableColorMaterial();
        GlStateManager.translate(n, n2, 50.0f);
        GlStateManager.scale(-n3, n3, n3);
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-(float)Math.atan(entityLivingBase.rotationPitch / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
        getRenderManager.setPlayerViewY(180.0f);
        getRenderManager.setRenderShadow(false);
        getRenderManager.renderEntity((Entity)entityLivingBase, 0.0, 0.0, 0.0, entityLivingBase.rotationYaw, 1.0f, false);
        getRenderManager.setRenderShadow(true);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.popMatrix();
    }
    
    public static void putVertex3d(final Vec3d vec3d) {
        GL11.glVertex3d(vec3d.x, vec3d.y, vec3d.z);
    }
    
    public static void drawRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glColor4f(n7, n8, n9, n6);
        GL11.glBegin(7);
        GL11.glVertex2d((double)n3, (double)n2);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)n, (double)n4);
        GL11.glVertex2d((double)n3, (double)n4);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    public static void drawRectWithGlow(double X, double Y, double Width, double Height, double GlowRange, double GlowMultiplier, Color color) {
        // Draw the filled rectangle first
        RenderUtils.drawRoundedRect99(X, Y, Width, Height, color.getRGB());

        // Now draw the glow rectangles
        for (float i = 2; i < GlowRange; i += 0.5f) {
            RenderUtils.drawRoundedRect99(X - (GlowRange - i), Y - (GlowRange - i), Width + (GlowRange - i), Height + (GlowRange - i), ColorHelper.injectAlpha(color, (int) (Math.round(i * GlowMultiplier))).getRGB());
        }
    }
    public static void drawRoundedRect(float left, float top, float right, float bottom, int smooth, Color color) {
        Gui.drawRect(((int) left + smooth), (int) top, ((int) right - smooth), (int) bottom, color.getRGB());
        Gui.drawRect((int) left, ((int) top + smooth), (int) right, ((int) bottom - smooth), color.getRGB());
        drawFilledCircle((int) left + smooth, (int) top + smooth, smooth, color);
        drawFilledCircle((int) right - smooth, (int) top + smooth, smooth, color);
        drawFilledCircle((int) right - smooth, (int) bottom - smooth, smooth, color);
        drawFilledCircle((int) left + smooth, (int) bottom - smooth, smooth, color);
    }
    public static void drawRoundedRect99(double x, double y, double x1, double y1, int insideC) {
        Gui.drawRect((int) (x + 0.5), (int) y, (int) (x1 - 0.5), (int) (y + 0.5), insideC);
        Gui.drawRect((int) (x + 0.5), (int) (y1 - 0.5), (int) (x1 - 0.5), (int) y1, insideC);
        Gui.drawRect((int) x, (int) (y + 0.5), (int) x1, (int) (y1 - 0.5), insideC);
    }
    public static void drawUpShadow(final float n, final float n2, final float n3, final float n4) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GL11.glBegin(7);
        glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)n, (double)n2);
        glColor(new Color(0, 0, 0, 0).getRGB());
        GL11.glVertex2d((double)n, (double)n4);
        GL11.glVertex2d((double)n3, (double)n4);
        glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)n3, (double)n2);
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
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

    public static void FillLine(Entity entity, AxisAlignedBB box) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);

        RenderGlobal.renderFilledBox(box, 0, 1, 0, 0.3F);
        RenderGlobal.drawSelectionBoundingBox(box, 0, 1, 0, 0.8F);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    public static void glColor(final Color color, final float n) {
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n);
    }
    
    public static void scissorRect(final float n, final float n2, final float n3, final double n4) {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        final int getScaleFactor = scaledResolution.getScaleFactor();
        GL11.glScissor((int)(n * getScaleFactor), (int)(((float)scaledResolution.getScaledHeight() - n4) * (float)getScaleFactor), (int)((n3 - n) * getScaleFactor), (int)((n4 - n2) * (float)getScaleFactor));
    }
    
    public static Vec3d getRenderPos(double n, double n2, double n3) {
        n -= MinecraftHelper.mc.getRenderManager().viewerPosX;
        n2 -= MinecraftHelper.mc.getRenderManager().viewerPosY;
        n3 -= MinecraftHelper.mc.getRenderManager().viewerPosZ;
        return new Vec3d(n, n2, n3);
    }
    
    public static void renderEntity(final EntityLivingBase entityLivingBase, final int n, final int n2, final int n3) {
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
        GL11.glPushAttrib(524288);
        GL11.glDisable(3089);
        GlStateManager.clear(256);
        GL11.glPopAttrib();
        GlStateManager.enableDepth();
        GlStateManager.disableAlpha();
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GuiInventory.drawEntityOnScreen(n2, n3, n, 1.0f, 1.0f, entityLivingBase);
        GlStateManager.popMatrix();
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
    }
    
    public static void drawEntityESP(final Entity entity, final Color color) {
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(0);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glDisable(0);
        GL11.glDepthMask(false);
        GL11.glColor4d((double)(color.getRed() / 255.0f), (double)(color.getGreen() / 255.0f), (double)(color.getBlue() / 255.0f), 0.15000000596046448);
        final RenderManager getRenderManager = Minecraft.getMinecraft().getRenderManager();
        RenderUtil.drawColorBox(new AxisAlignedBB(-0.05 - entity.posX + (entity.posX - getRenderManager.viewerPosX), -entity.posY + (entity.posY - getRenderManager.viewerPosY), -0.05 - entity.posZ + (entity.posZ - getRenderManager.viewerPosZ), 0.05 - entity.posX + (entity.posX - getRenderManager.viewerPosX), 0.1 - entity.posY + (entity.posY - getRenderManager.viewerPosY), 0.05 - entity.posZ + (entity.posZ - getRenderManager.viewerPosZ)), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(-0.05 - entity.posX + (entity.posX - getRenderManager.viewerPosX), -entity.posY + (entity.posY - getRenderManager.viewerPosY), -0.05 - entity.posZ + (entity.posZ - getRenderManager.viewerPosZ), 0.05 - entity.posX + (entity.posX - getRenderManager.viewerPosX), 0.1 - entity.posY + (entity.posY - getRenderManager.viewerPosY), 0.05 - entity.posZ + (entity.posZ - getRenderManager.viewerPosZ)));
        GL11.glLineWidth(2.0f);
        GL11.glEnable(3553);
        GL11.glEnable(0);
        GL11.glDepthMask(true);
        GL11.glDisable(0);
        GL11.glPopMatrix();
    }
    
    public static void drawboxESP5(final BlockPos blockPos, final Color color) {
        final double n = blockPos.getX();
        final double viewerPosX = Wrapper.mc.getRenderManager().viewerPosX;
        final double viewerPosY = Wrapper.mc.getRenderManager().viewerPosY;
        final double viewerPosZ = Wrapper.mc.getRenderManager().viewerPosZ;
        Minecraft.getMinecraft().getRenderManager();
        final double n2 = n - viewerPosX;
        final double n3 = blockPos.getY();
        Minecraft.getMinecraft().getRenderManager();
        final double n4 = n3 - viewerPosY;
        final double n5 = blockPos.getZ();
        Minecraft.getMinecraft().getRenderManager();
        final double n6 = n5 - viewerPosZ;
        GL11.glBlendFunc(770, 771);
        Tessellator.getInstance().getBuffer();
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glDepthMask(true);
        GL11.glColor4d((double)(color.getRed() / 255.0f), (double)(color.getGreen() / 255.0f), (double)(color.getBlue() / 255.0f), 0.25);
        Minecraft.getMinecraft().getRenderManager();
        RenderUtil.drawColorBox(new AxisAlignedBB(n2, n4, n6, n2 + 1.0, n4 + 1.0, n6 + 1.0), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glColor4d(0.4000000059604645, 0.6000000238418579, 1.0, 1.0);
        GL11.glLineWidth(2.0f);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void customScaledObject2D(final float n, final float n2, final float n3, final float n4, final float n5) {
        GL11.glTranslated((double)(n3 / 2.0f), (double)(n4 / 2.0f), 1.0);
        GL11.glTranslated((double)(-n * n5 + n + n3 / 2.0f * -n5), (double)(-n2 * n5 + n2 + n4 / 2.0f * -n5), 1.0);
        GL11.glScaled((double)n5, (double)n5, 0.0);
    }
    
    public static void drawImage(final ResourceLocation resourceLocation, final int n, final int n2, final int n3, final int n4) {
        GL11.glEnable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0f, 0.0f, n3, n4, (float)n3, (float)n4);
        GL11.glPopMatrix();
    }
    
    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    public static void drawImage(ResourceLocation resourceLocation, float x, float y, float width, float height, Color color) {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        setColor(color.getRGB());
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture((int) x, (int) y, (float) 0, (float) 0, (int) width, (int) height, width, height);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public static void setColor(int color) {
        GL11.glColor4ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF), (byte) (color >> 24 & 0xFF));
    }

    public static void drawImage(ResourceLocation resourceLocation, float x, float y, float width, float height) {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(false);
        GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture((int) x, (int) y, (float) 0, (float) 0, (int) width, (int) height, width, height);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GlStateManager.resetColor();

    }
    public static void drawCircle(float x, float y, float start, float end, float radius, boolean filled, Color color) {
        float sin;
        float cos;
        float i;
        GlStateManager.color(0, 0, 0, 0);

        float endOffset;
        if (start > end) {
            endOffset = end;
            end = start;
            start = endOffset;
        }

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        setColor(color.getRGB());
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(2);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        for (i = end; i >= start; i -= 4) {
            cos = (float) (Math.cos(i * Math.PI / 180) * radius * 1);
            sin = (float) (Math.sin(i * Math.PI / 180) * radius * 1);
            GL11.glVertex2f(x + cos, y + sin);
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(filled ? GL11.GL_TRIANGLE_FAN : GL11.GL_LINE_STRIP);
        for (i = end; i >= start; i -= 4) {
            cos = (float) Math.cos(i * Math.PI / 180) * radius;
            sin = (float) Math.sin(i * Math.PI / 180) * radius;
            GL11.glVertex2f(x + cos, y + sin);
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    public static void drawGlow(final double x, final double y, final double x1, final double y1, final int color) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        drawVGradientRect((float) (int) x, (float) (int) y, (float) (int) x1, (float) (int) (y + (y1 - y) / 2.0), ColorHelper.injectAlpha(new Color(color), 0).getRGB(), color);
        drawVGradientRect((float) (int) x, (float) (int) (y + (y1 - y) / 2.0), (float) (int) x1, (float) (int) y1, color, ColorHelper.injectAlpha(new Color(color), 0).getRGB());
        final int radius = (int) ((y1 - y) / 2.0);
        drawPolygonPart(x, y + (y1 - y) / 2.0, radius, 0, color, ColorHelper.injectAlpha(new Color(color), 0).getRGB());
        drawPolygonPart(x, y + (y1 - y) / 2.0, radius, 1, color, ColorHelper.injectAlpha(new Color(color), 0).getRGB());
        drawPolygonPart(x1, y + (y1 - y) / 2.0, radius, 2, color, ColorHelper.injectAlpha(new Color(color), 0).getRGB());
        drawPolygonPart(x1, y + (y1 - y) / 2.0, radius, 3, color, ColorHelper.injectAlpha(new Color(color), 0).getRGB());
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    public static void drawVerticalGradientSmoothRect(float left, float top, float right, float bottom, int color, int color2) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        RenderUtils.drawGradientRect((int) left, (int) top, (int) right, (int) bottom, color, color2);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        RenderUtils.drawGradientRect((int) (left * 2 - 1), (int) (top * 2), (int) (left * 2), (int) (bottom * 2 - 1), color, color2);
        RenderUtils.drawGradientRect((int) (left * 2), (int) (top * 2 - 1), (int) (right * 2), (int) (top * 2), color, color2);
        RenderUtils.drawGradientRect((int) (right * 2), (int) (top * 2), (int) (right * 2 + 1), (int) (bottom * 2 - 1), color, color2);
        RenderUtils.drawGradientRect((int) (left * 2), (int) (bottom * 2 - 1), (int) (right * 2), (int) (bottom * 2), color, color2);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glScalef(2F, 2F, 2F);
    }
    public static void glColor(final Color color) {
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }

    public static void drawGradientRect(int i, int i1, int i2, int i3, int i4, int i5) {

    }
}
