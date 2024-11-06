package Bobr.buttons.setting.settings;

import Bobr.buttons.setting.CSSetting;
import Bobr.util.RenderHelper;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.io.IOException;

public class CSSettingCheck extends CSSetting {

	public CSSettingCheck(int x, int y, int width, int height, Setting s) {
		super(x, y, width, height, s);
	}

	private int animation = 20;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		fr.drawString(displayString, x, y, Integer.MAX_VALUE);

		int stringwidth = fr.getStringWidth(displayString);

		Gui.drawRect(x + stringwidth + 20, y, x + stringwidth + 30, y + 10, Color.black.getRGB());
		RenderHelper.drawFullCircle(x + stringwidth + 20, y + 5, 5, Color.black.getRGB());
		RenderHelper.drawFullCircle(x + stringwidth + 30, y + 5, 5, Color.black.getRGB());
		if(set.getValBoolean()){
			RenderHelper.drawFullCircle(x + stringwidth + animation, y + 5, 5, ClickGUI.getColor());

		}else{
			RenderHelper.drawFullCircle(x + stringwidth + animation, y + 5, 5, Integer.MAX_VALUE * 2);

		}

		if (this.set.getValBoolean()) {
			if (animation < 30) {
				animation++;
			}
		} else {
			if (animation > 20) {
				animation--;
			}
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (isHovered(mouseX, mouseY) && mouseButton == 0) {
			this.set.setValBoolean(!this.set.getValBoolean());
		}

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	private boolean isHovered(int mouseX, int mouseY) {
		int stringwidth = fr.getStringWidth(displayString);
		boolean hoveredx = mouseX > this.x + stringwidth + 15 && mouseX < this.x + stringwidth + 35;
		boolean hoveredy = mouseY > this.y && mouseY < this.y + 10;
		return hoveredx && hoveredy;
	}

}
