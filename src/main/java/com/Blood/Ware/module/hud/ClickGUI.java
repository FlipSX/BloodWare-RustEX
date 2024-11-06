package com.Blood.Ware.module.hud;


import Caesium.Panel;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.ColourUtils;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ClickGUI extends Module {
	public static int red;
	public static int green;
	public static int blue;
	public static int color;

	public static List<String> Modes = new ArrayList<String>();
	public ClickGUI() {
		super("ClickGUI", Category.HUD);
		this.setKey(Keyboard.KEY_RSHIFT);
	//	Modes.add("Blood");
		Modes.add("New");
		BloodWare.instance.settingsManager.rSetting(new Setting("Gui", this, "New", (ArrayList<String>) Modes));
		BloodWare.instance.settingsManager.rSetting(new Setting("Rainbow", this, true));
	//	BloodWare.instance.settingsManager.rSetting(new Setting("Red", this, 225, 0, 225, true));
	//	BloodWare.instance.settingsManager.rSetting(new Setting("Green", this, 0, 0, 225, true));
	//	BloodWare.instance.settingsManager.rSetting(new Setting("Blue", this, 0, 0, 225, true));
	}



	public static int getColor() {
		int color;
		boolean setcolor = (boolean) BloodWare.instance.settingsManager.getSettingByName("Rainbow", "Range").getValBoolean();

		float[] hue = new float[]{(float) (System.currentTimeMillis() % 11520L) / 11520.0f};
		int rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
	//	int red = (int) BloodWare.instance.settingsManager.getSettingByName("Red", "Range").getValDouble();
		//int green =  (int) BloodWare.instance.settingsManager.getSettingByName("Green", "Range").getValDouble();
		//int blue = (int) BloodWare.instance.settingsManager.getSettingByName("Blue", "Range").getValDouble();
		color = ColourUtils.toRGBA(red, green, blue, 255);
		int TheColor = setcolor ? ColourUtils.genRainbow() : color;

		return TheColor;
	}
	public static int getColor2() {
		int color;
		boolean setcolor = (boolean) BloodWare.instance.settingsManager.getSettingByName("Rainbow", "Range").getValBoolean();

		float[] hue = new float[]{(float) (System.currentTimeMillis() % 11520L) / 11520.0f};
		int rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
		//int red = (int) BloodWare.instance.settingsManager.getSettingByName("Red", "Range").getValDouble();
		//int green =  (int) BloodWare.instance.settingsManager.getSettingByName("Green", "Range").getValDouble();
		//int blue = (int) BloodWare.instance.settingsManager.getSettingByName("Blue", "Range").getValDouble();
		color = ColourUtils.toRGBA(red, green, blue, 195);
		int TheColor = setcolor ? ColourUtils.genRainbowShadow() : color;

		return TheColor;
	}
	public static float getColor3() {
		int color;
		boolean setcolor = (boolean) BloodWare.instance.settingsManager.getSettingByName("Rainbow", "Range").getValBoolean();

		float[] hue = new float[]{(float) (System.currentTimeMillis() % 11520L) / 11520.0f};
		float rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
		//float red = (float) BloodWare.instance.settingsManager.getSettingByName("Red", "Range").getValDouble();
		//float green =  (float) BloodWare.instance.settingsManager.getSettingByName("Green", "Range").getValDouble();
		//float blue = (float) BloodWare.instance.settingsManager.getSettingByName("Blue", "Range").getValDouble();
		color = ColourUtils.toRGBA(red, green, blue, 255f);
		float TheColor = setcolor ? ColourUtils.genRainbowShadow() : color;

		return TheColor;
	}
	@Override
	public void onEnable() {
		String Mode = (String) BloodWare.instance.settingsManager.getSettingByName(this, "Gui").getValString();
		switch (Mode){
			case "New":
				mc.displayGuiScreen(new Panel("Caesium", 22));
				break;
			case "Old":
				mc.displayGuiScreen(BloodWare.instance.bobrGui);
				break;
			case "Castom":
				mc.displayGuiScreen(BloodWare.instance.CastomGui);
				break;

		}

		super.onEnable();
		this.setToggled(false);

	}



}
