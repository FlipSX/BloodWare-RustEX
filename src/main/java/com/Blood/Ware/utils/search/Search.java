package com.Blood.Ware.utils.search;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.render.BlockEsp;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
public class Search extends Thread{

    @Override
    public void run() {
        try {
            Logger lo = LogManager.getLogger();
            while (true) {

                String mode = BloodWare.instance.settingsManager.getSettingByName(BloodWare.moduleManager.getModule("BlockESP"), "Mode").getValString();

                CopyOnWriteArrayList<Blockinfo> render = new CopyOnWriteArrayList<>();
                ArrayList<Blockinfo> rendered = new ArrayList<>();
                Minecraft mc = Minecraft.getMinecraft();
                IBlockState defaultState;
                int radius = mc.gameSettings.renderDistanceChunks << 4;
                int minX = mc.player.getPosition().getX() - radius;
                int maxX = mc.player.getPosition().getX() + radius;
                int minY = Math.max(60, mc.player.getPosition().getY() - 92);
                int maxY = Math.min(130, mc.player.getPosition().getY() + 72);
                int minZ = mc.player.getPosition().getZ() - radius;
                int maxZ = mc.player.getPosition().getZ() + radius;
                ArrayList blackList = new ArrayList<Block>() {{
                    add(Blocks.AIR);
                    add(Blocks.BEDROCK);
                    add(Blocks.STONE);
                    add(Blocks.GRASS);
                    add(Blocks.DIRT);
                }};


                for (int chunkX = minX >> 4; chunkX <= maxX >> 4; chunkX++) {
                    int x = chunkX << 4;
                    int lowBoundX = (x < minX) ? minX - x : 0;
                    int highBoundX = (x + 15 > maxX) ? maxX - x : 15;

                    for (int chunkZ = minZ >> 4; chunkZ <= maxZ >> 4; chunkZ++) {
                        Chunk chunk = mc.world.getChunkFromChunkCoords(chunkX, chunkZ);
                        if (!chunk.isLoaded()) {
                            continue;
                        }
                        ExtendedBlockStorage[] extendsList = chunk.getBlockStorageArray();

                        int z = chunkZ << 4;
                        int lowBoundZ = (z < minZ) ? minZ - z : 0;
                        int highBoundZ = (z + 15 > maxZ) ? maxZ - z : 15;

                        for (int curExtend = minY >> 4; curExtend <= maxY >> 4; curExtend++) {
                            ExtendedBlockStorage ebs = extendsList[curExtend];

                            if (ebs == null)
                                continue;

                            int y = curExtend << 4;
                            int lowBoundY = (y < minY) ? minY - y : 0;
                            int highBoundY = (y + 15 > maxY) ? maxY - y : 15;

                            for (int i = lowBoundX; i <= highBoundX; i++) {
                                for (int j = lowBoundY; j <= highBoundY; j++) {
                                    for (int k = lowBoundZ; k <= highBoundZ; k++) {
                                        IBlockState currentState = ebs.get(i, j, k);

                                        if (blackList.contains(currentState.getBlock()))
                                            continue;
                                        if (mode.equalsIgnoreCase("ore")) {
                                            try {
                                                Block block = ebs.get(i, j - 1, k).getBlock();
                                                if (block.equals(Blocks.AIR))
                                                    continue;
                                                if (true) {
                                                    Block block2 = ebs.get(i, j + 1, k).getBlock();
                                                    if (mode.equalsIgnoreCase("ore")) {
                                                        if (!block2.equals(Blocks.AIR))
                                                            continue;
                                                    }
                                                }
                                            } catch (Throwable throwable) {
                                                BlockPos vo = new BlockPos(i + x, j + y - 1, k + z);
                                                IBlockState v = mc.world.getBlockState(vo);
                                                if (v.getBlock().equals(Blocks.AIR))
                                                    continue;
                                                if (true) {
                                                    BlockPos vo2 = new BlockPos(i + x, j + y + 1, k + z);
                                                    IBlockState v2 = mc.world.getBlockState(vo2);
                                                    if (mode.equalsIgnoreCase("ore")) {
                                                        if (!v2.getBlock().equals(Blocks.AIR))
                                                            continue;
                                                    }
                                                }
                                            }
                                        }
                                        if (mode.equalsIgnoreCase("barriers")) {
                                            try {
                                                Block block = ebs.get(i, j - 1, k).getBlock();
                                                if (block.equals(Blocks.AIR))
                                                    continue;
                                                if (true) {
                                                    Block block2 = ebs.get(i, j + 1, k).getBlock();
                                                    if (mode.equalsIgnoreCase("barriers")) {
                                                        if (!block2.equals(Blocks.AIR))
                                                            continue;
                                                    }
                                                }

                                            } catch (Throwable throwable) {
                                                BlockPos vo = new BlockPos(i + x, j + y - 1, k + z);
                                                IBlockState v = mc.world.getBlockState(vo);
                                                if (v.getBlock().equals(Blocks.AIR))
                                                    continue;
                                                if (true) {
                                                    BlockPos vo2 = new BlockPos(i + x, j + y + 1, k + z);
                                                    IBlockState v2 = mc.world.getBlockState(vo2);
                                                    if (mode.equalsIgnoreCase("barriers")) {
                                                        if (!v2.getBlock().equals(Blocks.AIR))
                                                            continue;
                                                    }
                                                }
                                            }
                                        }
                                        if (mode.equalsIgnoreCase("ore") ) {
                                            if (currentState.getBlock().equals(Blocks.IRON_ORE)) {
                                                render.add(new Blockinfo((x + i), (y + j), (z + k), 0f, 0.5f, 1f));
                                            } else if (currentState.getBlock().equals(Blocks.DIAMOND_ORE)) {
                                                render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0f, 0f));
                                            } else if (currentState.getBlock().equals(Blocks.GOLD_ORE)) {
                                                render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0.9f, 0f));
                                            }
                                        }else if (mode.equalsIgnoreCase("cloth")) {
                                            try {
                                                if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.TALLGRASS, "2"))) {
                                                    render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0.4f, 0f));
                                                }
                                            } catch (Exception exception) {
                                                //ChatUtils.sendMessage("Error");
                                                BloodWare.moduleManager.getModule("BlockESP").setToggled(false);
                                            }

                                        }else if (mode.equalsIgnoreCase("barriers")) {
                                            try {
                                                if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.BARRIER, "0"))) {
                                                    render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0.4f, 0f));
                                                }
                                            }catch (Exception exception) {
                                                //ChatUtils.sendMessage("Error");
                                                BloodWare.moduleManager.getModule("BlockESP").setToggled(false);
                                            }
                                        }else if (mode.equalsIgnoreCase("collector")) {
                                            try {
                                                if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.SAPLING, "1"))) {
                                                    render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0.4f, 0f));
                                                } else if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.SAPLING, "0")))
                                                    render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0.4f, 0f));
                                             else if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.SAPLING, "2"))) {
                                                        render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0.4f, 0f));
                                                    } else if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.SAPLING, "4"))) {
                                                            render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0.4f, 0f));}



                                            }catch (Exception exception){
                                                //ChatUtils.sendMessage("Error");
                                                BloodWare.moduleManager.getModule("BlockESP").setToggled(false);
                                            }
                                        }else if (mode.equalsIgnoreCase("food")) {
                                            try {
                                                if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.SAPLING, "5"))) {
                                                    render.add(new Blockinfo((x + i), (y + j), (z + k), 0f, 0f, 1f));
                                                } else if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.POTATOES, "7"))) {
                                                        render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0.4f, 0f));
                                                    } else if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.SAPLING, "3"))) {
                                                        render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0.4f, 0f));
                                                    }  else  if (currentState.equals(CommandBase.convertArgToBlockState(Blocks.BEETROOTS, "3"))) {
                                                        render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0.4f, 0f));
                                                }
                                            }catch (Exception exception){
                                                //ChatUtils.sendMessage("Error");
                                                BloodWare.moduleManager.getModule("BlockESP").setToggled(false);
                                            }
                                        }else {
                                            for (IBlockState state : BlockEsp.search_blocks) {
                                                if (currentState.equals(state)) {
                                                    render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0f, 0f));
                                                }
                                            }
                                            for (Block state : BlockEsp.search_blocks1) {
                                                if (currentState.getBlock().equals(state))
                                                    render.add(new Blockinfo((x + i), (y + j), (z + k), 1f, 0f, 0f));
                                            }
                                        }


                                    }
                                }
                            }
                        }
                    }
                }
                BlockEsp.render.clear();
                BlockEsp.render = render;


                Thread.sleep((long) (BloodWare.instance.settingsManager.getSettingByName(BloodWare.moduleManager.getModule("BlockESP"), "UpdateTime").getValDouble() * 1000));
            }
        } catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
