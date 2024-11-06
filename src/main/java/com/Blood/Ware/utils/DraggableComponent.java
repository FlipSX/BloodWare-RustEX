//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils;

import java.util.*;
import org.lwjgl.input.*;
import net.minecraft.client.gui.*;

public class DraggableComponent
{
    private final int height;
    private int x;
    private int lastX;
    private int color;
    private int lastY;
    private boolean canRender;
    private final int width;
    public static ArrayList draggableModules;
    private int y;
    private boolean dragging;
    
    public DraggableComponent(final int x, final int y, final int width, final int height, final int color) {
        this.canRender = true;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    static {
        DraggableComponent.draggableModules = new ArrayList();
    }
    
    private void draggingFix(final int n, final int n2) {
        if (this.dragging) {
            this.x = n + this.lastX;
            this.y = n2 + this.lastY;
            if (!Mouse.isButtonDown(0)) {
                this.dragging = false;
            }
        }
    }
    
    public void draw(final int n, final int n2) {
        if (this.canRender) {
            this.draggingFix(n, n2);
            Gui.drawRect(this.getXPosition(), this.getYPosition() - this.getHeight() / 4, this.getXPosition() + this.getWidth(), this.getYPosition() + this.getHeight(), this.getColor());
            final boolean b = n >= this.getXPosition() && n <= this.getXPosition() + this.getWidth();
            int n3 = 0;
            if (n2 < this.getYPosition() - this.getHeight() / 4 || n2 > this.getYPosition() - this.getHeight() / 4 + this.getHeight()) {
                n3 = 0;
            }
            final int n4 = n3;
            if (b && n4 != 0) {
                if (Mouse.isButtonDown(0)) {
                    if (!this.dragging && DraggableComponent.draggableModules.size() <= 1) {
                        this.lastX = this.x - n;
                        this.lastY = this.y - n2;
                        this.dragging = true;
                    }
                }
                else {
                    DraggableComponent.draggableModules.clear();
                }
            }
        }
    }
    
    public int getColor() {
        return this.color;
    }
    
    public void setXPosition(final int x) {
        this.x = x;
    }
    
    public void setColor(final int color) {
        this.color = color;
    }
    
    public boolean isCanRender() {
        return this.canRender;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getYPosition() {
        return this.y;
    }
    
    public void setYPosition(final int y) {
        this.y = y;
    }
    
    public boolean isDragging() {
        return this.dragging;
    }
    
    public void setCanRender(final boolean canRender) {
        this.canRender = canRender;
    }
    
    public boolean isHovered(final int n, final int n2) {
        return n >= this.getXPosition() && n <= this.getXPosition() + this.getWidth() && n2 >= this.getYPosition() - this.getHeight() / 4 && n2 <= this.getYPosition() - this.getHeight() / 4 + this.getHeight();
    }
    
    public int getXPosition() {
        return this.x;
    }
}
