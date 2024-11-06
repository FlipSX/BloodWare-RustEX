package com.Blood.Ware.module.movement;

import Bobr.BobrGui;
import Caesium.ClickGui;
import com.Blood.Ware.Castom.CastomGui;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;

import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class GuiWalk extends Module
{
    public void onDisable() {
        super.onDisable();
    }

    public void onEnable() {
        super.onEnable();
    }

    @SubscribeEvent
    public void onKeyUpdate(final InputUpdateEvent inputUpdateEvent) {
        final boolean valBoolean = BloodWare.instance.settingsManager.getSettingByName(this, "Sneak").getValBoolean();
        if (GuiWalk.mc.world != null && GuiWalk.mc.player != null && (GuiWalk.mc.currentScreen instanceof GuiContainer || GuiWalk.mc.currentScreen instanceof GuiIngameMenu || GuiWalk.mc.currentScreen instanceof GuiOptions || GuiWalk.mc.currentScreen instanceof BobrGui || GuiWalk.mc.currentScreen instanceof ClickGui || GuiWalk.mc.currentScreen instanceof CastomGui)) {
            if (Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindForward.getKeyCode())) {
                final MovementInput movementInput = GuiWalk.mc.player.movementInput;
                ++movementInput.moveForward;
                GuiWalk.mc.player.movementInput.forwardKeyDown = true;
            }
            if (Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindBack.getKeyCode())) {
                final MovementInput movementInput2 = GuiWalk.mc.player.movementInput;
                --movementInput2.moveForward;
                GuiWalk.mc.player.movementInput.backKeyDown = true;
            }
            if (Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindRight.getKeyCode())) {
                final MovementInput movementInput3 = GuiWalk.mc.player.movementInput;
                --movementInput3.moveStrafe;
                GuiWalk.mc.player.movementInput.rightKeyDown = true;
            }
            if (Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindLeft.getKeyCode())) {
                final MovementInput movementInput4 = GuiWalk.mc.player.movementInput;
                ++movementInput4.moveStrafe;
                GuiWalk.mc.player.movementInput.rightKeyDown = true;
            }
            GuiWalk.mc.player.movementInput.jump = Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindJump.getKeyCode());
            GuiWalk.mc.player.movementInput.sneak = (valBoolean && Keyboard.isKeyDown(GuiWalk.mc.gameSettings.keyBindSneak.getKeyCode()));
            if (GuiWalk.mc.player.movementInput.sneak) {
                GuiWalk.mc.player.movementInput.moveStrafe *= (float)0.3;
                GuiWalk.mc.player.movementInput.moveForward *= (float)0.3;
            }
        }
    }

    public GuiWalk() {
        super("GuiWalk", Category.MOVEMENT);
        BloodWare.instance.settingsManager.rSetting(new Setting("Sneak", this, false));
    }
}
