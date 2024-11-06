package com.Blood.Ware.module;


import com.Blood.Ware.module.misc.Panic;
import com.Blood.Ware.module.misc.SelfDestruct;
import com.Blood.Ware.notifications.Type;
import com.Blood.Ware.notifications.notifications;
import com.Blood.Ware.settings.Setting;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public abstract class Module {

	protected static Minecraft mc = Minecraft.getMinecraft();

	private String name, description;
	private int key;
	private Category category;
	private boolean toggled;
	int posX;
	int posY;
	public boolean visible = true;
	private boolean hud;
	private Setting parent = null;
	public Module(String name, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.key = 0;
		this.category = category;
		this.toggled = false;
	}
	public int getPosY() {
		return this.posY;
	}
	public int getPosX() {
		return this.posX;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isToggled() {
		return toggled;

	}

	public void setToggled(boolean toggled) {
		if (this.toggled==toggled) return;
		this.toggled = toggled;
		if (!SelfDestruct.self) {
			if (this.toggled) {
				this.onEnable();
			} else {
				this.onDisable();
			}
		}



	}
	public void toggle() {
		this.toggled = !this.toggled;
		if (!SelfDestruct.self) {
			if (this.toggled) {
				this.onEnableR();
				//this.onEnable();
			} else {
				this.onDisableR();
				this.onDisable();
			}
		}

	}


	public void onEnable() {

		MinecraftForge.EVENT_BUS.register(this);

		notifications.add(this.name,   "" +"Enable!" ,Type.Green);
		//notif.add(this.name,  TextFormatting.GREEN + "Enable!", cf.vaccat.BloodWare.noti.Category.INFO);
	}


	public boolean onEnableR() {

		onEnable();
        return false;
    }

	public void onDisableR() {

		onDisable();
	}

	public void onDisable() {
		MinecraftForge.EVENT_BUS.unregister(this);
		notifications.add(this.name,  "" +"Disable!" ,Type.Red);
		//notif.add(this.name,  TextFormatting.RED + "Disable!", cf.vaccat.BloodWare.noti.Category.INFO);

	}
	public void onClientTick(TickEvent.ClientTickEvent event) {}

	public String getName() {
		return this.name;
	}

	public Category getCategory() {
		return this.category;
	}


	protected void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
	}

	public boolean isToggler() {
		return this.toggled;
	}

	public boolean isHud() {
		return hud;
	}

	public void setHud(boolean hud) {
		this.hud = hud;
	}


}
