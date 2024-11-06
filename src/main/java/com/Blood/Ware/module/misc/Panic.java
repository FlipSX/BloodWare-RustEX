package com.Blood.Ware.module.misc;

import com.Blood.Ware.BloodWare;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.Category;

public class Panic extends Module {
    public static boolean isPanic = false;

    public Panic() {
        super("Panic", Category.OUTHER);
    }

    @Override
    public void onEnable() {
        isPanic = true;

        Display.setTitle("Minecraft 1.12.2");

        for (Module m : BloodWare.moduleManager.modules) {
            if (m != this) {
                m.setToggled(false);
            }
        }
    }

    @Override
    public void onDisable() {
        isPanic = false;

        Display.setTitle(BloodWare.name);
    }
}
