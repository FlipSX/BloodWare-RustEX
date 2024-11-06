package com.Blood.Ware.module.movement;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class NoClip extends Module {
    public NoClip() {
        super("NoClip",Category.MOVEMENT);

        BloodWare.instance.settingsManager.rSetting(new Setting("Forward", this, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("Up", this, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("UpSpeed", this, 0.2, 0.1, 10.0, false));
        BloodWare.instance.settingsManager.rSetting(new Setting("Down", this, true));
        BloodWare.instance.settingsManager.rSetting(new Setting("DownSpeed", this, 0.2, 0.1, 10.0, false));
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        boolean forward = BloodWare.instance.settingsManager.getSettingByName(this, "Forward").getValBoolean();
        boolean up = BloodWare.instance.settingsManager.getSettingByName(this, "Up").getValBoolean();
        double upspeed = BloodWare.instance.settingsManager.getSettingByName(this, "UpSpeed").getValDouble();
        boolean down = BloodWare.instance.settingsManager.getSettingByName(this, "Down").getValBoolean();
        double downspeed = BloodWare.instance.settingsManager.getSettingByName(this, "DownSpeed").getValDouble();

        if (mc.player.collidedHorizontally && forward && (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown())) {
            double newX = mc.player.posX + Math.sin(Math.toRadians(mc.player.rotationYaw)) * 2;
            double newZ = mc.player.posZ - Math.cos(Math.toRadians(mc.player.rotationYaw)) * 2;

            mc.player.setPosition(newX, mc.player.posY, newZ);
        }

        if (up && mc.gameSettings.keyBindJump.isKeyDown()) {

            mc.player.noClip = true;
            mc.player.setPosition(mc.player.posX, mc.player.posY + upspeed, mc.player.posZ);
            mc.player.noClip = false;
        }

        if (down && mc.gameSettings.keyBindSneak.isKeyDown()) {

            mc.player.noClip = true;
            mc.player.setPosition(mc.player.posX, mc.player.posY - downspeed, mc.player.posZ);
            mc.player.noClip = false;
        }
    }
}