package com.Blood.Ware.module.render;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.RenderUtils;
import com.Blood.Ware.utils.search.Blockinfo;
import com.Blood.Ware.utils.search.Search;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class BlockEsp extends Module {

    public static boolean trace;
    public static CopyOnWriteArrayList<Blockinfo> render = new CopyOnWriteArrayList<>();
    public static ArrayList<IBlockState> search_blocks = new ArrayList<>();
    public static ArrayList<Block> search_blocks1 = new ArrayList<>();
    Thread search;
    public BlockEsp(){
        super("BlockESP", Category.RENDER);

        BloodWare.instance.settingsManager.rSetting(new Setting("UpdateTime",this,2d,0.1d,10d,false));
        BloodWare.instance.settingsManager.rSetting(new Setting("Trace",this,true));
        BloodWare.instance.settingsManager.rSetting(new Setting("Mode",this,"ore", new ArrayList<String>() {{
            add("ore");
            add("cloth");
            add("barriers");
            add("food");
            add("collector");
        }} ));
    }

    @Override
    public void onEnable() {
        super.onEnable();

        render = new CopyOnWriteArrayList<>();

        search = new Search();
        search.start();
    }

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent e){
        trace = BloodWare.instance.settingsManager.getSettingByName(this,"Trace").getValBoolean();
        try {
            ArrayList<Blockinfo> render = new ArrayList<>(BlockEsp.render);
            for (Blockinfo bi : render) {
                RenderUtils.blockEspFrame(new BlockPos(bi.getX(), bi.getY(), bi.getZ()), bi.getR(), bi.getG(), bi.getB());
                if (trace) {
                    RenderUtils.trace(mc, bi);
                }
            }
        }catch (Throwable ignored){}
    }

    @Override
    public void onDisable() {
        super.onDisable();
        search.interrupt();
    }
}
