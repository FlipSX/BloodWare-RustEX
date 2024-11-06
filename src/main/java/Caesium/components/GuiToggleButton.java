//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 */
package Caesium.components;

import Caesium.Panel;
import Caesium.util.RenderUtil;
import com.Blood.Ware.module.hud.ClickGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.Blood.Ware.utils.Font.CastomFontUtils.fr4;

public class GuiToggleButton
implements GuiComponent {
    private String text;
    private boolean toggled;
    private int posX;
    private int posY;
    private ArrayList<ActionListener> clickListeners = new ArrayList();

    public GuiToggleButton(String text) {
        this.text = text;
    }

    @Override
    public void render(int posX, int posY, int width, int mouseX, int mouseY, int wheelY) {
        this.posX = posX;
        this.posY = posY;
        switch (Caesium.Panel.theme) {
            case "Caesium": {
                this.renderCaesium(posX, posY);
            }
        }
    }

    private void renderCaesium(int posX, int posY) {
        com.Blood.Ware.utils.RenderUtil.drawFilledCircle(posX + 8, posY + 7, 6.0f, new Color(Color.red.getRGB()));
        if (!this.toggled) {
            com.Blood.Ware.utils.RenderUtil.drawFilledCircle(posX + 8, posY + 7, 5.0f, new Color(Caesium.Panel.grey40_240));
        }
        fr4.drawStringWithShadow(this.text, (float)(posX + 17), (float)(posY + 3), Caesium.Panel.fontColor);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        int width = fr4.getStringWidth(this.text) + 10;
        if (RenderUtil.isHovered(this.posX, this.posY + 2, width, this.getHeight(), mouseX, mouseY)) {
            this.toggled = !this.toggled;
            for (ActionListener listener : this.clickListeners) {
                listener.actionPerformed(new ActionEvent(this, this.hashCode(), "click", System.currentTimeMillis(), 0));
            }
        }
    }

    @Override
    public void keyTyped(int keyCode, char typedChar) {
    }

    @Override
    public int getWidth() {
        return fr4.getStringWidth(this.text) + 20;
    }

    @Override
    public int getHeight() {
        return fr4.getHeight() + 5;
    }

    @Override
    public boolean allowScroll() {
        return true;
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public void addClickListener(ActionListener actionlistener) {
        this.clickListeners.add(actionlistener);
    }
}

