package Bobr.buttons;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.hud.ClickGUI;

import java.io.IOException;
import java.util.ArrayList;


public class CSCategoryButton extends CSButton {
	public static boolean binding;
	public Category category;
	public CSModButton currentMod;

	public ArrayList<CSModButton> buttons = new ArrayList<CSModButton>();

	public CSCategoryButton(int x, int y, int width, int height, int color, String displayString, Category category) {
		super(x, y, width, height, color, displayString);
		this.category = category;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		int color = this.isHovered(mouseX, mouseY) || BloodWare.instance.bobrGui.currentCategory == this ? ClickGUI.getColor()
				: this.color;

		fr.drawString(displayString, x, y, color);

		for (CSModButton csm : buttons) {
			if (BloodWare.instance.bobrGui.currentCategory == this) {
				csm.drawScreen(mouseX, mouseY, partialTicks);
			}
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void onKeyPress(int typedChar, int keyCode) {

	}

	public boolean isHovered(int mouseX, int mouseY) {
		boolean hoveredx = mouseX > this.x && mouseX < this.x + this.width;
		boolean hoveredy = mouseY > this.y && mouseY < this.y + this.height;
		return hoveredx && hoveredy;
	}

	public void setBinding(boolean binding) {
		this.binding = binding;
	}




	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		for (CSModButton cmb : buttons) {
			cmb.mouseClicked(mouseX, mouseY, mouseButton);

			if (mouseButton == 1 && cmb.isHovered(mouseX, mouseY)) {
				this.currentMod = cmb;
			}
			if (mouseButton == 2 && cmb.isHovered(mouseX, mouseY)) {
				this.setBinding(true);
			}


		}

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void initButton() {
		this.buttons.clear();
		int x = this.x + 65;
		int y = 110;
		for (int i = 0; i < BloodWare.instance.moduleManager.modules.size(); i++) {
			Module m = BloodWare.instance.moduleManager.modules.get(i);
			if (m.getCategory() == this.category) {
				CSModButton csm = new CSModButton(x, y, fr.getStringWidth(m.getName()), fr.FONT_HEIGHT, 0xFFFFFFFF,
						m.getName(), m);

				this.buttons.add(csm);

				y += 10;

			}
		}

		super.initButton();
	}

	private boolean isCurrentPanel() {
		return BloodWare.instance.bobrGui.currentCategory == this;
	}

}
