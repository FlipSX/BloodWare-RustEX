package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.events.SendPacketEvent;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class Freecam extends Module
{
    private int lastThirdPerson;
    private final Minecraft mc;
    public static final Freecam INSTANCE;
    private boolean activeThisSession;
    public EntityOtherPlayerMP camera;

    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.activeThisSession = true;
        this.mc.renderGlobal.loadRenderers();
    }

    @SubscribeEvent
    public void onInput(final InputUpdateEvent inputUpdateEvent) {
        final float n = (float)BloodWare.instance.settingsManager.getSettingByName(this, "horizontalSpeed").getValDouble();
        final float n2 = (float)BloodWare.instance.settingsManager.getSettingByName(this, "verticalSpeed").getValDouble();
        if (this.camera == null) {
            return;
        }
        final MovementInput movementInput = inputUpdateEvent.getMovementInput();
        final Vec3d rotateYaw = new Vec3d((double)((movementInput.leftKeyDown ? n : 0.0f) - (movementInput.rightKeyDown ? n : 0.0f)), (double)((movementInput.jump ? n2 : 0.0f) - (movementInput.sneak ? n2 : 0.0f)), (double)((movementInput.forwardKeyDown ? n : 0.0f) - (movementInput.backKeyDown ? n : 0.0f))).rotateYaw((float)(-Math.toRadians(this.camera.rotationYawHead)));
        this.camera.setPositionAndRotationDirect(this.camera.posX + rotateYaw.x, this.camera.posY + rotateYaw.y, this.camera.posZ + rotateYaw.z, this.camera.rotationYawHead, this.camera.rotationPitch, 3, false);
        if (movementInput instanceof MovementInputFromOptions) {
            this.mc.player.moveVertical = 0.0f;
            this.mc.player.moveStrafing = 0.0f;
            this.mc.player.moveForward = 0.0f;
            this.mc.player.setJumping(false);
        }
    }

    static {
        INSTANCE = new Freecam();
    }

    public Freecam() {
        super("Freecam", Category.OUTHER);
        this.mc = Minecraft.getMinecraft();
        this.activeThisSession = false;
        BloodWare.instance.settingsManager.rSetting(new Setting("verticalspeed", this, 3.5, 1.0, 12.0, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("horizontalspeed", this, 3.5, 1.0, 12.0, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("rotationspeed", this, 3, 1.0, 20, false));
    }

    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (this.mc.world == null || !this.mc.world.isRemote) {
            this.camera = null;
            return;
        }
        if (this.camera == null) {
            this.lastThirdPerson = this.mc.gameSettings.thirdPersonView;
            this.camera = new EntityOtherPlayerMP((World)this.mc.world, this.mc.getSession().getProfile());
            this.mc.world.addEntityToWorld(333393333, (Entity)this.camera);
            this.camera.copyLocationAndAnglesFrom((Entity)this.mc.player);
            mc.setRenderViewEntity(this.camera);
            this.camera.noClip = true;
        }
        this.camera.rotationYawHead=MathHelper.wrapDegrees(mc.player.rotationYawHead);

        //  this.camera.rotationYaw=MathHelper.wrapDegrees(mc.player.rotationYaw);
        this.camera.rotationYaw=mc.player.rotationYaw;
        this.camera.rotationPitch= MathHelper.wrapDegrees(mc.player.rotationPitch);
        mc.player.rotationYawHead=this.camera.rotationYawHead;
        mc.player.rotationYaw=this.camera.rotationYaw;
        mc.player.rotationPitch=this.camera.rotationPitch;
        this.mc.gameSettings.thirdPersonView = 0;
        this.camera.inventory = this.mc.player.inventory;
        this.camera.setHealth(this.mc.player.getHealth());
    }

    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        if (this.mc.world != null && this.mc.world.isRemote) {
            this.mc.setRenderViewEntity((Entity)this.mc.player);
            if (this.activeThisSession) {
                this.mc.gameSettings.thirdPersonView = this.lastThirdPerson;
                this.mc.world.removeEntity((Entity)this.camera);
            }
        }
        this.camera = null;
        this.activeThisSession = false;
    }

    @SubscribeEvent
    public void onKeyUpdate(InputUpdateEvent event) {
        final float n = (float)BloodWare.instance.settingsManager.getSettingByName(this, "rotationspeed").getValDouble();
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            this.camera.rotationYawHead -=n;
            this.camera.rotationYaw -=n;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            this.camera.rotationYawHead +=n;
            this.camera.rotationYaw += n;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            this.camera.rotationPitch -=n;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            this.camera.rotationPitch +=n;
        }
    }

    @SubscribeEvent
    public void onSendPacket(final SendPacketEvent sendPacketEvent) {
        if (sendPacketEvent.getPacket() instanceof CPacketUseEntity && ((CPacketUseEntity)sendPacketEvent.getPacket()).getEntityFromWorld((World)this.mc.world) == this.mc.player) {
            sendPacketEvent.setCanceled(true);
        }
    }
}
