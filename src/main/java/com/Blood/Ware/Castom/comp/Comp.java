package com.Blood.Ware.Castom.comp;

import com.Blood.Ware.Castom.CastomGui;
import com.Blood.Ware.Castom.setting.Setting;
import com.Blood.Ware.module.*;

public class Comp
{
    public double y;
    public CastomGui parent;
    public Setting setting;
    public String displayString;
    public double x;
    public double y2;
    public double x2;
    public Module module;
    
    public void keyTyped(final char c, final int n) {
    }
    
    public void drawScreen(final int n, final int n2) {
    }
    
    public boolean isInside(final int n, final int n2, final double n3, final double n4, final double n5, final double n6) {
        return n > n3 && n < n5 && n2 > n4 && n2 < n6;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
    }
}
