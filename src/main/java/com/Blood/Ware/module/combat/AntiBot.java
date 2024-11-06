
package com.Blood.Ware.module.combat;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;

public class AntiBot
extends Module {
    public static List<String> BOTS = new ArrayList<String>();
    public static boolean toggle;

    public AntiBot() {
        super("AntiBot", Category.COMBAT);
        BloodWare.instance.settingsManager.rSetting(new Setting("Remove", this, true));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        toggle = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        toggle = false;
        BOTS.clear();
    }

    public static boolean isBot(String nick) {
        if (toggle) {
            for (String friend : BOTS) {
                if (!friend.equalsIgnoreCase(nick)) continue;
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean isBlockMaterial(BlockPos blockPos, Block block) {
        return AntiBot.getBlock(blockPos) == Blocks.AIR;
    }

    public static Block getBlock(BlockPos pos) {
        return AntiBot.getState(pos).getBlock();
    }

    public static IBlockState getState(BlockPos pos) {
        return AntiBot.mc.world.getBlockState(pos);
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        boolean Remove = BloodWare.instance.settingsManager.getSettingByName(this, "Remove").getValBoolean();
        for (Entity e : AntiBot.mc.world.loadedEntityList) {
            if (e == AntiBot.mc.player || e.ticksExisted >= 5 || !(e instanceof EntityOtherPlayerMP) || !(AntiBot.mc.player.getDistanceSq(e) <= 15.0)) continue;
            if (AntiBot.isBlockMaterial(new BlockPos(e).down(), Blocks.AIR)) {
                BOTS.add(e.getName());
                if (Remove) {
                    AntiBot.mc.world.removeEntity(e);
                }
            }
            if (!e.isInvisible()) continue;
            BOTS.add(e.getName());
            if (!Remove) continue;
            AntiBot.mc.world.removeEntity(e);
        }
    }
}

