

package com.Blood.Ware.Castom;

import com.Blood.Ware.Castom.comp.CheckBox;
import com.Blood.Ware.Castom.comp.Combo;
import com.Blood.Ware.Castom.comp.Comp;
import com.Blood.Ware.Castom.comp.Slider;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.Font.CastomFontUtils;
import com.Blood.Ware.utils.MathUtils;
import com.Blood.Ware.utils.Reference;
import com.Blood.Ware.utils.RenderUtil;
import com.Blood.Ware.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class CastomGui extends GuiScreen
{
    private float curAlpha;
    public Category selectedCategory;
    public static boolean binding;
    public double width;
    public ArrayList comps;
    private static RenderItem itemRender;
    float anim;
    public int modeIndex;

    public double dragX;
    public double x;
    public double posX;
    public double posY;
    public double height;
    public double dragY;
    public boolean dragging;
    public double y;
    private boolean editing;

    private Module selectedModule;
    public Module mod;

    public void setBinding(boolean binding) {
        this.binding = binding;
    }
    
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        super.mouseClicked(n, n2, n3);
        if (this.isInside(n, n2, this.posX, this.posY - 10.0, this.width, this.posY) && n3 == 0) {
            this.dragging = true;
            this.dragX = n - this.posX;
            this.dragY = n2 - this.posY;
        }
        int n4 = 0;
        for (final Category selectedCategory : Category.values()) {
            if (this.isInside(n, n2, this.posX, this.posY + 1.0 + n4, this.posX + 60.0, this.posY + 15.0 + n4) && n3 == 0) {
                this.selectedCategory = selectedCategory;
            }
            n4 += 15;
        }
        int n5 = 0;
        for (final Module selectedModule : BloodWare.instance.moduleManager.getModules(this.selectedCategory)) {
            if (this.isInside(n, n2, this.posX + 65.0, this.posY + 1.0 + n5, this.posX + 125.0, this.posY + 15.0 + n5)) {
                if (n3 == 0) {
                    selectedModule.toggle();
                }
                if (n3 == 1) {
                    int n6 = 3;
                    this.comps.clear();
                    if (BloodWare.instance.settingsManager.getSettingsByMod(selectedModule) != null) {
                        for (final Setting  setting : BloodWare.instance.settingsManager.getSettingsByMod(selectedModule)) {
                            this.selectedModule = selectedModule;
                            setBinding(true);
                            if (setting.isCombo()) {
                                this.comps.add(new Combo(275.0, n6, this, this.selectedModule, setting));
                                n6 += 15;
                            }
                            if (setting.isCheck()) {
                                this.comps.add(new CheckBox(275.0, n6, this, this.selectedModule, setting));
                                n6 += 13;
                            }
                            if (setting.isSlider()) {
                                this.comps.add(new Slider(275.0, n6, this, this.selectedModule, setting));
                                n6 += 18;

                            }

                        }
                    }
                }
            }
            n5 += 15;
        }
        final Iterator<Comp> iterator3 = (Iterator<Comp>)this.comps.iterator();
        while (iterator3.hasNext()) {
            iterator3.next().mouseClicked(n, n2, n3);
        }
    }
    
    public boolean isInside(final int n, final int n2, final double n3, final double n4, final double n5, final double n6) {
        return n > n3 && n < n5 && n2 > n4 && n2 < n6;
    }
    
    public void initGui() {
        super.initGui();
        this.anim = 0.0f;
        this.dragging = false;
    }
    
    public void setEditing(final boolean editing) {
        this.editing = editing;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        if (this.dragging) {
            this.posX = n - this.dragX;
            this.posY = n2 - this.dragY;
        }
        this.width = this.posX + 456.0;
        this.height = this.posY + 380.0;
        final float n4 = 255.0f;
        final int n5 = (int)(n4 / 255.0f);
        if (this.curAlpha < n4 - n5) {
            this.curAlpha += n5;
        }
        else if (this.curAlpha > n4 - n5 && this.curAlpha != n4) {
            this.curAlpha = (float)(int)n4;
        }
        else if (this.curAlpha != n4) {
            this.curAlpha = (float)(int)n4;
        }
        this.anim = MathUtils.lerp(this.anim, 1.0f, 0.13f);

        RenderUtil.drawRect(this.posX -1, this.posY - 6.0, this.width + 1, this.height + 1, ClickGUI.getColor());
        RenderUtil.drawRect(this.posX, this.posY, this.width, this.height, new Color(45, 45, 45, 249).getRGB());
        int n6 = 0;
        for (final Category category : Category.values()) {
            RenderUtil.drawRect(this.posX, this.posY + 1.0 + n6, this.posX + 60.0, this.posY + 15.0 + n6, category.equals((Object)this.selectedCategory) ? new Color(46, 85, 243, 156).getRGB() : new Color(28, 28, 28, 250).getRGB());
            CastomFontUtils.fr3.drawString(category.name(), (int)this.posX + 2, (int)(this.posY + 5.0) + n6, new Color(170, 170, 170).getRGB());
            n6 += 15;
        }
        int n7 = 0;
        for (final Module module : BloodWare.instance.moduleManager.getModules(this.selectedCategory)) {
            RenderUtil.drawRect(this.posX + 65.0, this.posY + 1.0 + n7, this.posX + 150.0, this.posY + 15.0 + n7, module.isToggled() ? new Color(46, 85, 234, 155).getRGB() : new Color(28, 28, 28, 249).getRGB());
            CastomFontUtils.fr3.drawString(module.getName(), (int)this.posX + 67, (int)(this.posY + 5.0) + n7, new Color(170, 170, 170).getRGB());
            n7 += 15;
        }
        Gui.drawRect((int)(this.posX + 455.0), (int)(this.posY + 360.0), (int)(this.posX + 323.0), (int)(this.posY + 320.5), new Color(31, 31, 31, 250).getRGB());
        CastomFontUtils.fr.drawStringWithShadow(Reference.Name, (float)(this.posX + 353.0), (float)(this.posY + 329.0), ClickGUI.getColor());
        Gui.drawRect((int)(this.posX + 300.0), (int)(this.posY + 370.0), (int)(this.posX + 200.0), (int)(this.posY + 2.0), new Color(31, 31, 31, 250).getRGB());
        CastomFontUtils.fr.drawStringWithShadow("Name:"+ mc.getSession().getUsername(), (float)(this.posX + 353.0), (float)(this.posY + 339.5), Color.white.getRGB());
        try {
            this.mc.getTextureManager().bindTexture(Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(this.mc.player.getUniqueID()).getLocationSkin());
            Gui.drawScaledCustomSizeModalRect((int)(this.posX + 328.0), (int)(this.posY + 330.0), 8.0f, 8.0f, 8, 8, 22, 22, 64.0f, 64.0f);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final Iterator<Comp> iterator2 = (Iterator<Comp>)this.comps.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().drawScreen(n, n2);
        }
        this.drawinfo(n, n2);
        this.player();

    }
    
    public ScaledResolution getScaledRes() {
        return new ScaledResolution(Minecraft.getMinecraft());
    }
    
    public boolean isEditing() {
        return this.editing;
    }
    
    public void player() {
        try {
            this.mc.getTextureManager().bindTexture(Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(this.mc.player.getUniqueID()).getLocationSkin());
            RenderUtils.renderEntity((EntityLivingBase)this.mc.player, 70, (int)(this.posX + 375.0), (int)(this.posY + 230.0));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void drawinfo(int mouseX, int mouseY){
        int xpos = (int) (this.posX + 511.0);
        int ypos = (int) (this.posY + 18);
        int g = 5;

        RenderUtil.drawRect(this.posX + 636, this.posY - 6, this.width + 29, this.height + 1, ClickGUI.getColor());
        RenderUtil.drawRect(this.posX + 635, this.posY, this.width + 30, this.height, new Color(31, 31, 31, 250).getRGB());
        CastomFontUtils.fr2.drawStringWithShadow("Freands", (float) (this.posX + 540.0), (float) (this.posY + 10), Color.white.getRGB());

        for(String friends : FriendManager.FRIENDS){
            int butX1 = 0 + xpos;
            int butX2 =  100 + xpos;
            int butY1 = g + ypos;
            int butY2 = g + fontRenderer.FONT_HEIGHT + ypos;

            if(mouseX > butX1 && mouseX < butX2 && mouseY > butY1 && mouseY < butY2){
                CastomFontUtils.fr2.drawString(friends,   xpos + 50 - fontRenderer.getStringWidth(friends) / 2, g + ypos, ClickGUI.getColor());
                if(Mouse.isButtonDown(1)){
                    FriendManager.toggleFriend(friends);
                    return;
                }
            } else{
                CastomFontUtils.fr2.drawString(friends,  xpos + 50 - fontRenderer.getStringWidth(friends) / 2, g + ypos, Color.white.getRGB());
            }
            g += fontRenderer.FONT_HEIGHT;
        }





    }

    static {
        CastomGui.itemRender = Minecraft.getMinecraft().getRenderItem();
    }
    
    protected void mouseReleased(final int n, final int n2, final int n3) {
        super.mouseReleased(n, n2, n3);
        this.dragging = false;
        final Iterator<Comp> iterator = this.comps.iterator();
        while (iterator.hasNext()) {
            iterator.next().mouseReleased(n, n2, n3);
        }
    }
    
    protected void keyTyped(final char c, final int n) throws IOException {
        super.keyTyped(c, n);
        final Iterator<Comp> iterator = this.comps.iterator();
        while (iterator.hasNext()) {
            iterator.next().keyTyped(c, n);
        }
    }
    
    public CastomGui() {
        this.anim = 0.0f;
        this.comps = new ArrayList();
        this.dragging = false;
        this.posX = this.getScaledRes().getScaledWidth() / 2 - 150;
        this.posY = this.getScaledRes().getScaledHeight() / 2 - 100;
        this.width = this.posX + 456.0;
        this.height += 380.0;
        this.selectedCategory = Category.COMBAT;
        this.x = 100.0;
        this.y = 200.0;
    }

}
