package com.Blood.Ware.module.render;

import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Tracers extends Module
{
    public Tracers() {
        super("Tracers", Category.RENDER);
    }
    @Override
    public void onDisable() {
        super.onDisable();


    }
    @Override
    public void onEnable() {
        super.onEnable();
    }

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        this.mc.gameSettings.viewBobbing = false;
        GL11.glPushMatrix();
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glLineWidth(2F);
        for (final Entity entity : this.mc.world.loadedEntityList) {
            if (entity != this.mc.player) {
                if (!(entity instanceof EntityPlayer)) {
                    continue;
                }
                assert this.mc.getRenderViewEntity() != null;
                this.mc.player.getDistance(entity);
                final double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) - this.mc.getRenderManager().viewerPosX;
                final double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) - this.mc.getRenderManager().viewerPosY;
                final double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) - this.mc.getRenderManager().viewerPosZ;
                Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0);
                vec3d = vec3d.rotatePitch(-(float)Math.toRadians(this.mc.player.rotationPitch));
                final Vec3d vec3d2 = vec3d.rotateYaw(-(float)Math.toRadians(this.mc.player.rotationYaw));
                GL11.glBegin(2);
                if (FriendManager.isFriend(entity.getName())) {
                    RenderUtils.glColor(Color.GREEN);
                }
                else {
                    RenderUtils.glColor(Color.white.getRGB());
                }
                GL11.glVertex3d(vec3d2.x, this.mc.player.getEyeHeight() + vec3d2.y, vec3d2.z);
                GL11.glVertex3d(d, d2 + 1.1, d3);
                GL11.glEnd();
            }
        }
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
}
