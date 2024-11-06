package com.Blood.Ware.module.misc;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiZoomSmooth extends Module
{

    public AntiZoomSmooth() {
        super("AntiZoomSmooth", Category.RENDER);
    }
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent playerTickEvent) {
        AntiZoomSmooth.mc.gameSettings.smoothCamera = false;
    }
}
