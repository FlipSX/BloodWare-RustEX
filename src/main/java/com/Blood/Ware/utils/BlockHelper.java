package com.Blood.Ware.utils;

import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.block.*;

public class BlockHelper
{
    static Minecraft mc;

    public static boolean IsValidBlockPoss(final BlockPos blockPos) {
        return BlockHelper.mc.world.getBlockState(blockPos).getBlock() instanceof BlockStainedHardenedClay && BlockHelper.mc.world.getBlockState(blockPos.up()).getBlock() == Blocks.AIR;
    }

    public static ArrayList getBlocks(final int n, final int n2, final int n3) {
        return getAllInBox(new BlockPos(BlockHelper.mc.player.posX - n, BlockHelper.mc.player.posY - n2, BlockHelper.mc.player.posZ - n3), new BlockPos(BlockHelper.mc.player.posX + n, BlockHelper.mc.player.posY + n2, BlockHelper.mc.player.posZ + n3));
    }

    public static List getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (float n3 = getX - n; n3 <= getX + n; ++n3) {
            for (float n4 = getZ - n; n4 <= getZ + n; ++n4) {
                final float n5 = b2 ? (getY - n) : ((float)getY);
                if (n5 < (b2 ? (getY + n) : ((float)(getY + n2)))) {
                    final float n6 = (getX - n3) * (getX - n3) + (getZ - n4) * (getZ - n4) + (b2 ? ((getY - n5) * (getY - n5)) : 0.0f);
                    if (n6 < n * n && (!b || n6 >= n - 2.0f * (n - 2.0f))) {
                        list.add(new BlockPos((double)n3, (double)n5, (double)n4));
                    }
                }
            }
        }
        return list;
    }

    static {
        BlockHelper.mc = Minecraft.getMinecraft();
    }

    public static Block getBlock(final BlockPos blockPos) {
        return BlockHelper.mc.world.getBlockState(blockPos).getBlock();
    }

    public static boolean IsValidBlockPos(final Object o) {
        return IsValidBlockPoss((BlockPos)o);
    }

    public static ArrayList getAllInBox(final BlockPos blockPos, final BlockPos blockPos2) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final BlockPos blockPos3 = new BlockPos(Math.min(blockPos.getX(), blockPos2.getX()), Math.min(blockPos.getY(), blockPos2.getY()), Math.min(blockPos.getZ(), blockPos2.getZ()));
        final BlockPos blockPos4 = new BlockPos(Math.max(blockPos.getX(), blockPos2.getX()), Math.max(blockPos.getY(), blockPos2.getY()), Math.max(blockPos.getZ(), blockPos2.getZ()));
        for (int i = blockPos3.getX(); i <= blockPos4.getX(); ++i) {
            for (int j = blockPos3.getY(); j <= blockPos4.getY(); ++j) {
                for (int k = blockPos3.getZ(); k <= blockPos4.getZ(); ++k) {
                    list.add(new BlockPos(i, j, k));
                }
            }
        }
        return list;
    }

    public static BlockPos getPlayerPos() {
        return new BlockPos(BlockHelper.mc.player.posX, BlockHelper.mc.player.posY, BlockHelper.mc.player.posZ);
    }
}
