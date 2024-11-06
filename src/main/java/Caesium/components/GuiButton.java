//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 */
package Caesium.components;

import Caesium.Panel;
import Caesium.ClickGui;
import Caesium.components.listeners.ComponentListener;
import Caesium.util.RenderUtil;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.hud.ClickGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.Blood.Ware.utils.Font.CastomFontUtils.fr4;

public class GuiButton
implements GuiComponent {
    public static int expandedID = -1;
    private int id;
    private String text;
    private ArrayList<ActionListener> clickListeners = new ArrayList();
    private ArrayList<GuiComponent> guiComponents = new ArrayList();
    private int width;
    private int textWidth;
    private int posX;
    private int posY;

    public GuiButton(String text) {
        this.text = text;
        this.textWidth = fr4.getStringWidth(text);
        this.id = ++ClickGui.compID;
    }

    @Override
    public void render(int posX, int posY, int width, int mouseX, int mouseY, int wheelY) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        int height = this.getHeight();
        this.renderCaesium(posX, posY, width, height);
    }

    private void renderCaesium(int posX, int posY, int width, int height) {
        RenderUtil.drawRect(posX, posY, posX + width - 1, posY + height, Panel.black100);
        int color = Panel.fontColor;
        if (BloodWare.instance.moduleManager.getModule(this.getText()).isToggled()) {
            color = Color.red.getRGB();
        }
        fr4.drawStringWithShadow(this.getText(), (float)(posX + width / 2 - fr4.getStringWidth(this.getText()) / 2), (float)(posY + 2), color);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (GuiFrame.dragID == -1 && RenderUtil.isHovered(this.posX, this.posY, this.width, this.getHeight(), mouseX, mouseY)) {
            if (mouseButton == 1) {
                expandedID = expandedID != this.id ? this.id : -1;
            } else if (mouseButton == 0) {
                for (ActionListener listener : this.clickListeners) {
                    listener.actionPerformed(new ActionEvent(this, this.id, "click", System.currentTimeMillis(), 0));
                }
            }
        }
        if (expandedID == this.id) {
            for (GuiComponent component : this.guiComponents) {
                component.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }

    @Override
    public void keyTyped(int keyCode, char typedChar) {
        if (expandedID == this.id) {
            for (GuiComponent component : this.guiComponents) {
                component.keyTyped(keyCode, typedChar);
            }
        }
    }

    @Override
    public int getWidth() {
        return 5 + this.textWidth;
    }

    @Override
    public int getHeight() {
        return fr4.getHeight() + 3;
    }

    @Override
    public boolean allowScroll() {
        return true;
    }

    public String getText() {
        return this.text;
    }

    public int getButtonID() {
        return this.id;
    }

    public ArrayList<GuiComponent> getComponents() {
        return this.guiComponents;
    }

    public void addClickListener(ActionListener actionlistener) {
        this.clickListeners.add(actionlistener);
    }

    public void addExtendListener(ComponentListener listener) {
        listener.addComponents();
        this.guiComponents.addAll(listener.getComponents());
    }
}

