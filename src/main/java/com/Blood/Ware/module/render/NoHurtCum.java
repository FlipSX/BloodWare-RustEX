
package com.Blood.Ware.module.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;

public class NoHurtCum extends Module
{
    public NoHurtCum() {
        super("NoHurtCum", Category.RENDER);
    }

    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        NoHurtCum.mc.player.hurtTime = 0;
        NoHurtCum.mc.player.hurtResistantTime = 0;
        NoHurtCum.mc.player.maxHurtResistantTime = 0;
        NoHurtCum.mc.player.maxHurtTime = 0;
    }
}
