package com.Blood.Ware.utils;

import com.Blood.Ware.BloodWare;
import net.minecraft.client.Minecraft;

public interface Wrapper {

	Minecraft mc = Minecraft.getMinecraft();

	default boolean nullCheck() {
		return mc.player != null || mc.world != null;
	}

	default BloodWare getCosmos() {
		return BloodWare.instance;
	}
}
