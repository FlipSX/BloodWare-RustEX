package Bobr.buttons.setting.settings;

import Bobr.buttons.setting.CSSetting;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import org.lwjgl.input.Keyboard;

import java.io.IOException;


public class CSSettingDouble extends CSSetting {

	public CSSettingDouble(int x, int y, int width, int height, Setting s) {
		super(x, y, width, height, s);
	}



    @Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		double reach = this.set.getValDouble();

		double reach1 = reach * 100;

		double reach2 = Math.round(reach1);

		double round = reach2 / 100;
		mc.fontRenderer.drawString("<", this.x + 1, this.y + 1,
				this.isHoveredLeft(mouseX, mouseY) ? ClickGUI.getColor() : Integer.MAX_VALUE);
		mc.fontRenderer.drawString(">", this.x + 1 + fr.getStringWidth(this.set.getName() + " " + round) + 15,
				this.y + 1, this.isHoveredRight(mouseX, mouseY) ? ClickGUI.getColor() : Integer.MAX_VALUE);
		mc.fontRenderer.drawString(this.set.getName() + " " + round, this.x + 10, this.y + 1, Integer.MAX_VALUE);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public boolean isHoveredLeft(int mouseX, int mouseY) {
		boolean hoveredx = mouseX > this.x + 1 && mouseX < this.x + 1 + 5;
		boolean hoveredy = mouseY > this.y + 1 && mouseY < this.y + mc.fontRenderer.FONT_HEIGHT;
		return hoveredx && hoveredy;

	}

	public boolean isHoveredRight(int mouseX, int mouseY) {
		double round = Math.round(this.set.getValDouble() * 10) / 10;

		boolean hoveredx = mouseX > this.x + 1 + fr.getStringWidth(this.set.getName() + " " + round) + 15
				&& mouseX < this.x + 1 + fr.getStringWidth(this.set.getName() + " " + round) + 20;
		boolean hoveredy = mouseY > this.y + 1 && mouseY < this.y + mc.fontRenderer.FONT_HEIGHT;
		return hoveredx && hoveredy;
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		Setting s = this.set;
		if (mouseButton == 0) {
			if (isHoveredLeft(mouseX, mouseY)) {
				boolean more = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);

				double plus = 0;
				if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					if (s.onlyInt()) {
						plus = 1;
					} else {
						plus = 0.1;
					}
				} else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					plus = s.onlyInt() ? 10 : 1;
				} else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					plus = s.onlyInt() ? 1 : 0.01;
				} else if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					plus = 0;
					s.setValDouble(s.getMin());
				}

				if (s.getValDouble() - plus < s.getMin() || s.getValDouble() - plus == s.getMin()) {
					s.setValDouble(s.getMin());
				} else if (s.getValDouble() - plus > s.getMin()) {

					s.setValDouble(s.getValDouble() - plus);

				}
				if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					s.setValDouble(s.getMin());
				}
			} else if (isHoveredRight(mouseX, mouseY)) {
				double plus = 0;

				if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					if (s.onlyInt()) {
						plus = 1;
					} else {
						plus = 0.1;
					}
				} else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					plus = s.onlyInt() ? 10 : 1;
				} else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					plus = s.onlyInt() ? 1 : 0.01;
				} else if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					plus = 0;
					s.setValDouble(s.getMax());
				}

				if (s.getValDouble() + plus > s.getMax() || s.getValDouble() + plus == s.getMax()) {
					s.setValDouble(s.getMax());
				} else if (s.getValDouble() + plus < s.getMax()) {

					s.setValDouble(s.getValDouble() + plus);

				}

			}

		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

}
