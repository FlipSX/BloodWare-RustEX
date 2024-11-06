//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils;

import net.minecraft.client.*;
import java.awt.*;

public class DraggableModule
{
    public DraggableComponent drag;
    public int x;
    protected Minecraft mc;
    public String name;
    public int y;
    
    public String getName() {
        return this.name;
    }
    
    public void setX2(final int xPosition) {
        this.drag.setXPosition(xPosition);
    }
    
    public int getX2() {
        return this.x;
    }
    
    public DraggableModule(final String name, final int x, final int y) {
        this.mc = Minecraft.getMinecraft();
        this.name = name;
        this.x = x;
        this.y = y;
        this.drag = new DraggableComponent(this.x, this.y, this.getWidth(), this.getHeight(), new Color(255, 255, 255, 0).getRGB());
    }
    
    public int getX() {
        return this.drag.getXPosition();
    }
    
    public void render(final int n, final int n2) {
        this.drag.draw(n, n2);
    }
    
    public int getY() {
        return this.drag.getYPosition();
    }
    
    public void draw() {
    }
    
    public int getHeight() {
        return 50;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getY2() {
        return this.y;
    }
    
    public void setY2(final int yPosition) {
        this.drag.setYPosition(yPosition);
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public int getWidth() {
        return 50;
    }
}
