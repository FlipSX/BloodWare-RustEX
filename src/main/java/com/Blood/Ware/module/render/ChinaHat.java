//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.module.render;

import com.Blood.Ware.module.hud.ClickGUI;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import com.Blood.Ware.module.*;
import com.Blood.Ware.settings.*;
import com.Blood.Ware.utils.*;
import com.Blood.Ware.*;
import com.Blood.Ware.utils.util.ColorUtil;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;

public class ChinaHat extends Module {
    private float height;
    private EnumDyeColor onecolor;

    public ChinaHat() {
        super("ChinaHat", Category.RENDER);
    }
    @SubscribeEvent
    public void onRender(RenderWorldLastEvent e) {
        if (mc.gameSettings.thirdPersonView == 1 || mc.gameSettings.thirdPersonView == 2) {
            GlStateManager.pushMatrix();
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            enableSmoothLine(2.5f);
            glShadeModel(GL_SMOOTH);
            glDisable(GL_CULL_FACE);
            GL11.glEnable(GL11.GL_BLEND);
            glEnable(GL_DEPTH_TEST);
            GL11.glTranslatef(0f, (float) (mc.player.height + height), 0f);
            GL11.glRotatef(-mc.player.rotationYaw, 0f, 1f, 0f);


            GL11.glBegin(GL11.GL_TRIANGLE_FAN);
            GlStateManager.color(255 / 255F, 255 / 255F, 80 / 255F, 255 / 255F);
            GL11.glVertex3d(0.0, 0.3, 0.0);

            for (float i = 0; i < 360.5; i += 1) {


                GlStateManager.color(0 / 255F, 165 / 255F, 0 / 255F, 255 / 255F);
                GL11.glVertex3d(Math.cos(i * Math.PI / 180.0) * 0.66, 0, Math.sin(i * Math.PI / 180.0) * 0.66);
            }
            GL11.glVertex3d(0.0, 0.3, 0.0);
            GL11.glEnd();

            GL11.glBegin(GL_LINE_LOOP);
            for (float i = 0; i < 360.5; i += 1) {
                GlStateManager.color(80 / 255F, 255 / 255F, 80 / 255F, 255 / 255F);
                GL11.glVertex3d(Math.cos(i * Math.PI / 180.0) * 0.66, 0, Math.sin(i * Math.PI / 180.0) * 0.66);
            }
            GL11.glEnd();
            GlStateManager.enableAlpha();
            disableSmoothLine();
            glShadeModel(GL_FLAT);
            glEnable(GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            GlStateManager.resetColor();
            GlStateManager.popMatrix();
        }
    }

    public static void enableSmoothLine(float width) {
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
        GL11.glLineWidth(width);
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
}
