/*
 * Decompiled with CFR 0.150.
 */
package Caesium.components;

public interface GuiComponent {
    public void render(int var1, int var2, int var3, int var4, int var5, int var6);

    public void mouseClicked(int var1, int var2, int var3);

    public void keyTyped(int var1, char var2);

    public int getWidth();

    public int getHeight();

    public boolean allowScroll();
}

