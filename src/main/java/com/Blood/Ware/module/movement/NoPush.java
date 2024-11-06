package com.Blood.Ware.module.movement;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoPush extends Module
{
    public NoPush() {
        super("NoPush", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        NoPush.mc.player.entityCollisionReduction = 0.0f;
    }
}
