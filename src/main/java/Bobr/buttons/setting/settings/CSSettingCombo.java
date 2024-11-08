package Bobr.buttons.setting.settings;

import Bobr.buttons.setting.CSSetting;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.RenderUtil;
import net.minecraft.client.gui.Gui;

import java.io.IOException;
import java.util.ArrayList;

public class CSSettingCombo extends CSSetting {

	public CSSettingCombo(int x, int y, int width, int height, Setting s) {
		super(x, y, width, height, s);
		initValues();
	}

	public ArrayList<CSSettingComboValue> values = new ArrayList<CSSettingComboValue>();

	private void initValues() {
		int x = this.x;
		int y = this.y + this.height;
		for (String s : this.set.getOptions()) {
			CSSettingComboValue value = new CSSettingComboValue(x, y, 70, mc.fontRenderer.FONT_HEIGHT + 2, set, s);

			this.values.add(value);
			y += mc.fontRenderer.FONT_HEIGHT + 2;
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		RenderUtil.drawGlow(this.x, this.y, this.x + this.width, this.y + this.height, ClickGUI.getColor(), 230);
		Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, Integer.MIN_VALUE);
		//Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, Integer.MIN_VALUE);
		fr.drawString(displayString, this.x + width / 2 - fr.getStringWidth(displayString) / 2, this.y + 1, Integer.MAX_VALUE);

		for (CSSettingComboValue value : values) {
			value.drawScreen(mouseX, mouseY);
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		for(CSSettingComboValue value : values) {
			value.mouseClicked(mouseX, mouseY, mouseButton);
		}
		
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

}
