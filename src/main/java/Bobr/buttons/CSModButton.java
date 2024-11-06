package Bobr.buttons;

import Bobr.buttons.setting.CSSetting;
import Bobr.buttons.setting.settings.CSSettingCheck;
import Bobr.buttons.setting.settings.CSSettingCombo;
import Bobr.buttons.setting.settings.CSSettingDouble;
import Bobr.buttons.setting.settings.KeyBind;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.io.IOException;
import java.util.ArrayList;

public class CSModButton extends CSButton {
	public static String old_name;
	public Module mod;
	public static boolean first = false;
	public static boolean binding;
	public ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

	public CSModButton(int x, int y, int width, int height, int color, String displayString, Module mod) {
		super(x, y, width, height, color, displayString);
		this.mod = mod;
		initSettings();
	}

	private void initSettings() {
		int y = 110;
		int x = this.x + 100;
		for (int i = 0; i < BloodWare.instance.settingsManager.getSettings().size(); i++) {
			Setting s = BloodWare.instance.settingsManager.getSettings().get(i);
			if (s.getParentMod() == this.mod) {
				if (s.isCheck()) {

					CSSettingCheck check = new CSSettingCheck(x, y, y, x, s);

					settings.add(check);

					y += 13;
				}
				if (s.isSlider()) {

					CSSettingDouble doubleset = new CSSettingDouble(x, y, 0, 0, s);

					settings.add(doubleset);

					y += 15;

				}
				if (s.isCombo()) {
					int yplus = y;

					CSSettingCombo combo = new CSSettingCombo(x, y, 70, mc.fontRenderer.FONT_HEIGHT + 2, s);
					settings.add(combo);

					for (int i1 = 0; i1 < s.getOptions().size(); i1++) {
						y += fr.FONT_HEIGHT + 2;
						if (y > 100 + sr.getScaledWidth() - 220) {
							y = 0;
							x += mc.fontRenderer.getStringWidth(s.getName()) + 50;
						}

					}

					y += fr.FONT_HEIGHT + 5;

				}

				if (y > 100 + sr.getScaledWidth() - 220) {
					y = 0;
					x += mc.fontRenderer.getStringWidth(s.getName()) + 50;
				}

			}

				}
		KeyBind key = new KeyBind(x, y, 70, mc.fontRenderer.FONT_HEIGHT + 2, mod);
		settings.add(key);
	}

	public ArrayList<CSSetting> settings = new ArrayList<CSSetting>();

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		int color = this.isHovered(mouseX, mouseY) ? ClickGUI.getColor() : 0xFFFFFFFF;

		if (this.mod.isToggled()) {
			color = ClickGUI.getColor();
		}

		if (this.isCurrentMod()) {
			color = ClickGUI.getColor();
		}

		fr.drawString(displayString, x, y, color);

		for (CSSetting cs : settings) {
			if (isCurrentMod()) {
				cs.drawScreen(mouseX, mouseY, partialTicks);
			}
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public void setBinding(boolean binding) {
		this.binding = binding;
	}



	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

		if (this.isHovered(mouseX, mouseY)) {

			if (mouseButton == 0 && isHovered(mouseX, mouseY) && BloodWare.instance.bobrGui.currentCategory != null
					&& BloodWare.instance.bobrGui.currentCategory.category == this.mod.getCategory()) {
				this.mod.toggle();

			} else if (mouseButton == 2 && isHovered(mouseX, mouseY) && BloodWare.instance.bobrGui.currentCategory != null
					&& BloodWare.instance.bobrGui.currentCategory.category == this.mod.getCategory()) {
				setBinding(true);

			}
			else if (mouseButton == 1) {
				try {

				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
		for (CSSetting cs : settings) {
			if (isCurrentMod()) {
				cs.mouseClicked(mouseX, mouseY, mouseButton);
			}
		}

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public boolean isHovered(int mouseX, int mouseY) {
		boolean hoveredx = mouseX > this.x && mouseX < this.x + this.width;
		boolean hoveredy = mouseY > this.y && mouseY < this.y + this.height;
		return hoveredx && hoveredy;
	}

	private boolean isCurrentMod() {
		return BloodWare.instance.bobrGui.currentCategory != null && BloodWare.instance.bobrGui.currentCategory.currentMod != null
				&& BloodWare.instance.bobrGui.currentCategory.currentMod == this;
	}

	@Override
	public void initButton() {
		initSettings();

		super.initButton();
	}

}
