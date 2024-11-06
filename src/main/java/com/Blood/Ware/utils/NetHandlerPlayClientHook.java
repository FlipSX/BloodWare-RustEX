
package com.Blood.Ware.utils;

import com.mojang.authlib.GameProfile;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.util.text.ITextComponent;
import com.Blood.Ware.notifications.Type;
import com.Blood.Ware.notifications.notifications;


public class NetHandlerPlayClientHook extends NetHandlerPlayClient {

	public NetHandlerPlayClientHook(Minecraft mcIn, GuiScreen p_i46300_2_, NetworkManager networkManagerIn, GameProfile profileIn) {
		super(mcIn, p_i46300_2_, networkManagerIn, profileIn);
	}
	
	public void sendPacket(Packet<?> packetIn) {
        notifications.add("rotation", "work", Type.OK);

        super.sendPacket(packetIn);

	}
	
	public void onDisconnect(ITextComponent reason) {
		super.onDisconnect(reason);
	}
}


