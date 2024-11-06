//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils;

import java.util.*;

public class DraggableManager
{
    public ArrayList mods;
    
    public void setMods(final ArrayList mods) {
        this.mods = mods;
    }
    
    public DraggableManager() {
        (this.mods = new ArrayList()).add(new IndicatorsComponent());
    }
    
    public ArrayList getMods() {
        return this.mods;
    }
}
