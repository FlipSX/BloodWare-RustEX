package Bobr;

import Bobr.buttons.CSCategoryButton;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.utils.Reference;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class BobrGui extends GuiScreen {
	private float curAlpha;
	private float anim;
	public ArrayList<CSCategoryButton> buttons = new ArrayList<CSCategoryButton>();

	public CSCategoryButton currentCategory;

	public int x, y, width, height;
	public 		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
	public BobrGui() {
		this.x = 100;
		this.y = 100;
		this.width = sr.getScaledWidth() - 100;
		this.height = sr.getScaledHeight() - 100;

		this.x = 100;
		this.y = 100;
		this.width = sr.getScaledWidth() - 100;
		this.height = sr.getScaledHeight() - 100;

	}

	private void initButtons() {
		this.buttons.clear();
		int x = 110;
		int y = 110;

		for (Category c : Category.values()) {

			String categoryname = c.name().substring(0, 1).toUpperCase()
					+ c.name().substring(1, c.name().length()).toLowerCase();
			CSCategoryButton cscb = new CSCategoryButton(x, y, mc.fontRenderer.getStringWidth(categoryname),
					mc.fontRenderer.FONT_HEIGHT, 0xFFFFFFFF, categoryname, c);

			this.buttons.add(cscb);

			y += 15;
		}

	}
	public ScaledResolution getScaledRes() {
		return new ScaledResolution(Minecraft.getMinecraft());
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		this.drawDefaultBackground();
		anim--;
		float alpha = 150.0f;
		int step = (int)(alpha / 100.0f);
		if (this.curAlpha < alpha - (float)step) {
			this.curAlpha += (float)step;
		} else if (this.curAlpha > alpha - (float)step && this.curAlpha != alpha) {
			this.curAlpha = (int)alpha;
		} else if (this.curAlpha != alpha) {
			this.curAlpha = (int)alpha;
		}
		//Color c = new Color(BloodWare.getClientColor().getRed(), BloodWare.getClientColor().getGreen(), BloodWare.getClientColor().getBlue(), (int) curAlpha);
		Color none = new Color(0, 0, 0, 0);
		this.drawGradientRect(0, 0, this.getScaledRes().getScaledWidth(), this.getScaledRes().getScaledHeight(), new Color(0, 0, 0, 0).getRGB(), ClickGUI.getColor());
		//RenderUtils.drawImage(new ResourceLocation("kuriyama.png"), (float) (sr.getScaledWidth()), (float) (sr.getScaledHeight()), 280, 380 );
		RenderUtils.drawImage(new ResourceLocation("kuriyama.png"), (float) (sr.getScaledWidth()) + 200, (float) (sr.getScaledHeight()) - 75, 345, 380 );
		RenderUtil.drawNewRect(x, height, width, height + 0.5, ClickGUI.getColor());
		RenderUtil.drawNewRect(width, y - fontRenderer.FONT_HEIGHT * 2, width + 0.5, height, ClickGUI.getColor());
		Gui.drawRect(x, y - fontRenderer.FONT_HEIGHT * 2, width, height, new Color(50, 50, 50, 190).getRGB());
		fontRenderer.drawStringWithShadow(Reference.Name + " Client " + Reference.Version, x + 3, y - fontRenderer.FONT_HEIGHT - 3, Color.white.getRGB());
		RenderUtil.drawGlow(x + 3, y - fontRenderer.FONT_HEIGHT - 3, x + 3 + fontRenderer.getStringWidth(Reference.Name + " Client " + Reference.Version) + 3, y + fontRenderer.FONT_HEIGHT, ClickGUI.getColor(), 70);
		for (CSCategoryButton cb : buttons) {
			cb.drawScreen(mouseX, mouseY, partialTicks);
		}

		Gui.drawRect(this.x + 65, this.y, this.x + 67, this.height, ClickGUI.getColor());
		Gui.drawRect(this.x + 165, this.y, this.x + 167, this.height, ClickGUI.getColor());
		drawinfo(mouseX, mouseY);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		for (CSCategoryButton cb : buttons) {
			cb.keyTyped(typedChar, keyCode);
		}
		super.keyTyped(typedChar, keyCode);
	}

	public void drawinfo(int mouseX, int mouseY){
		int xpos = 600;
		int ypos = 100;
		int g = 0;


		fontRenderer.drawStringWithShadow("Friends", xpos + (fontRenderer.getStringWidth("Friends") / 2), ypos - fontRenderer.FONT_HEIGHT, ClickGUI.getColor());

		RenderUtil.drawRect(xpos, ypos,  xpos + 100,  ypos + FriendManager.FRIENDS.size() * fontRenderer.FONT_HEIGHT, new Color(50, 50, 50, 190).getRGB());
		RenderUtil.drawNewRect(xpos - 1, ypos, xpos, ypos + FriendManager.FRIENDS.size() * fontRenderer.FONT_HEIGHT, ClickGUI.getColor());
		RenderUtil.drawNewRect(xpos + 100 - 1, ypos, xpos + 100, ypos + FriendManager.FRIENDS.size() * fontRenderer.FONT_HEIGHT, ClickGUI.getColor());
		RenderUtil.drawGlow(xpos, ypos - 1, xpos + 100, ypos, ClickGUI.getColor(), 250);
		RenderUtil.drawGlow(xpos, ypos + FriendManager.FRIENDS.size() * fontRenderer.FONT_HEIGHT - 1, xpos + 100, ypos + FriendManager.FRIENDS.size() * fontRenderer.FONT_HEIGHT,ClickGUI.getColor(), 250);


		for(String friends : FriendManager.FRIENDS){
			int butX1 = 0 + xpos;
			int butX2 =  100 + xpos;
			int butY1 = g + ypos;
			int butY2 = g + fontRenderer.FONT_HEIGHT + ypos;

			if(mouseX > butX1 && mouseX < butX2 && mouseY > butY1 && mouseY < butY2){
				fontRenderer.drawString(friends,   xpos + 50 - fontRenderer.getStringWidth(friends) / 2, g + ypos, ClickGUI.getColor());
				if(Mouse.isButtonDown(1)){
					FriendManager.toggleFriend(friends);
					return;
				}
			} else{
				fontRenderer.drawString(friends,  xpos + 50 - fontRenderer.getStringWidth(friends) / 2, g + ypos, Color.white.getRGB());
			}
			g += fontRenderer.FONT_HEIGHT;
		}





	}



	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		boolean anyhovered = true;
		for (CSCategoryButton cb : buttons) {

			if (cb.isHovered(mouseX, mouseY) && mouseButton == 0) {
				anyhovered = true;
				currentCategory = cb;
			}

			cb.mouseClicked(mouseX, mouseY, mouseButton);
		}

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		for (CSCategoryButton cb : buttons) {
			cb.mouseReleased(mouseX, mouseY, state);
		}

		super.mouseReleased(mouseX, mouseY, state);
	}

	@Override
	public void initGui() {
		initButtons();
		anim = 0;
		this.x = 100;
		this.y = 100;
		this.width = 500;
		this.height = 350;
		for (CSCategoryButton cb : buttons) {
			cb.initButton();
		}

		super.initGui();
	}

}
