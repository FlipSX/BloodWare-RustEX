//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.Castom.setting;

import com.Blood.Ware.module.Module;

import java.util.ArrayList;

public class Setting
{
    private String mode;
    private double min;
    private String sval;
    private double dval;
    private boolean bval;
    private double max;
    private ArrayList options;
    private Module parent;
    private String name;
    private boolean onlyint;
    
    public double getValDouble() {
        if (this.onlyint) {
            this.dval = (int)this.dval;
        }
        return this.dval;
    }
    
    public double getMin() {
        return this.min;
    }
    
    public String getValString() {
        return this.sval;
    }
    
    public boolean isSlider() {
        return this.mode.equalsIgnoreCase("Slider");
    }
    
    public boolean isCombo() {
        return this.mode.equalsIgnoreCase("Combo");
    }
    
    public String getName() {
        return this.name;
    }
    
    public Setting(final String name, final Module parent, final boolean bval) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.bval = bval;
        this.mode = "Check";
    }
    
    public boolean isCheck() {
        return this.mode.equalsIgnoreCase("Check");
    }
    
    public ArrayList getOptions() {
        return this.options;
    }
    
    public Setting(final String name, final Module parent, final double dval, final double min, final double max, final boolean onlyint) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.mode = "Slider";
    }
    
    public boolean getValBoolean() {
        return this.bval;
    }
    
    public Module getParentMod() {
        return this.parent;
    }
    
    public void setValString(final String sval) {
        this.sval = sval;
    }
    
    public Setting() {
        this.onlyint = false;
    }
    
    public void setValBoolean(final boolean bval) {
        this.bval = bval;
    }
    
    public Setting(final String name, final Module parent, final String sval, final ArrayList options) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.sval = sval;
        this.options = options;
        this.mode = "Combo";
    }
    
    public double getMax() {
        return this.max;
    }
    
    public void setValDouble(final double dval) {
        this.dval = dval;
    }
    
    public boolean onlyInt() {
        return this.onlyint;
    }
}
