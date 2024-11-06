package com.Blood.Ware.module.misc;

import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.utils.ChatUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClickPosInfo extends Module
{
    public ClickPosInfo() {
        super("ClickPosInfo", Category.OUTHER);
    }

    @SubscribeEvent
    public void onRightClickBlock(final PlayerInteractEvent.RightClickBlock rightClickBlock) {
        final BlockPos pos = rightClickBlock.getPos();
        ChatUtils.sendMessage((double)pos.getX() + " " + (double)pos.getY() + " " + (double)pos.getZ());
        ChatUtils.sendMessage(String.valueOf(ClickPosInfo.mc.world.getBlockState(pos).getBlock()));
    }
}
