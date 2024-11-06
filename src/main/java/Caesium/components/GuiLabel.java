//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 */
package Caesium.components;

import Caesium.Panel;

import static com.Blood.Ware.utils.Font.CastomFontUtils.fr4;

public class GuiLabel
implements GuiComponent {
    private String text;

    public GuiLabel(String text) {
        this.text = text;
    }

    @Override
    public void render(int posX, int posY, int width, int mouseX, int mouseY, int wheelY) {
        fr4.drawStringWithShadow(this.text, (float)(posX + width / 2 - fr4.getStringWidth(this.text) / 2), (float)(posY + 2), Panel.fontColor);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
    }

    @Override
    public void keyTyped(int keyCode, char typedChar) {
    }

    @Override
    public int getWidth() {
        return fr4.getStringWidth(this.text) + 4;
    }

    @Override
    public int getHeight() {
        return fr4.getHeight() + 2;
    }

    @Override
    public boolean allowScroll() {
        return true;
    }
}

