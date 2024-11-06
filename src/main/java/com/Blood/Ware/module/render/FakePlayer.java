package com.Blood.Ware.module.render;


import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.Category;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class FakePlayer extends Module {
    EntityOtherPlayerMP fakePlayer;

    public FakePlayer() {
        super("FakePlayer",Category.RENDER);
    }

    @Override
    public void onEnable() {
        fakePlayer = new EntityOtherPlayerMP(mc.world, mc.player.getGameProfile());
        fakePlayer.setEntityId(-1882);
        fakePlayer.copyLocationAndAnglesFrom(mc.player);
        fakePlayer.rotationYawHead = mc.player.rotationYawHead;
        mc.world.addEntityToWorld(fakePlayer.getEntityId(), fakePlayer);
    }

    @Override
    public void onDisable() {
            mc.world.removeEntityFromWorld(fakePlayer.getEntityId());

    }
}

