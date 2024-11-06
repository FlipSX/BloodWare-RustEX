//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package Caesium.components;

import Caesium.Panel;
import Caesium.components.listeners.KeyListener;
import Caesium.util.RenderUtil;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

import static com.Blood.Ware.utils.Font.CastomFontUtils.fr4;

public class GuiGetKey
implements GuiComponent {
    private boolean wasChanged;
    private boolean allowChange;
    private int key;
    private int posX;
    private int posY;
    private int width;
    private String text;
    private ArrayList<KeyListener> keylisteners = new ArrayList();

    public GuiGetKey(String text, int key) {
        this.text = text;
        this.key = key;
        if (key < 0) {
            this.key = 0;
        }
    }

    public void addKeyListener(KeyListener listener) {
        this.keylisteners.add(listener);
    }

    @Override
    public void render(int posX, int posY, int width, int mouseX, int mouseY, int wheelY) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        switch (Panel.theme) {
            case "Caesium": {
                this.renderCaesium(posX, posY);
            }
        }
    }

    private void renderCaesium(int posX, int posY) {
        String keyString = Keyboard.getKeyName((int)this.key);
        this.wasChanged = this.allowChange ? !this.wasChanged : true;
        fr4.drawStringWithShadow(this.text + ":", (float)(posX + 3), (float)(posY + 3), Panel.fontColor);
        if (this.wasChanged) {
            fr4.drawStringWithShadow(keyString, (float)(posX + this.width - fr4.getStringWidth(keyString) - 3), (float)(posY + 3), Panel.fontColor);
        } else {
            fr4.drawString(keyString, posX + this.width - fr4.getStringWidth(keyString) - 3, posY + 3, Panel.fontColor);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        String keyString = Keyboard.getKeyName((int)this.key);
        int w = fr4.getStringWidth(this.text);
        this.allowChange = RenderUtil.isHovered(this.posX, this.posY, this.width, 11, mouseX, mouseY);
    }

    @Override
    public void keyTyped(int keyCode, char typedChar) {
        if (this.allowChange) {
            for (KeyListener listener : this.keylisteners) {
                listener.keyChanged(keyCode);
            }
            this.key = keyCode;
            this.allowChange = false;
        }
    }

    @Override
    public int getWidth() {
        return fr4.getStringWidth(this.text + Keyboard.getKeyName((int)this.key)) + 15;
    }

    @Override
    public int getHeight() {
        return fr4.getHeight() + 4;
    }

    @Override
    public boolean allowScroll() {
        return true;
    }
}

