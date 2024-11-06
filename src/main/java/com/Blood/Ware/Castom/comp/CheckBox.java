
package com.Blood.Ware.Castom.comp;

import com.Blood.Ware.Castom.CastomGui;
import com.Blood.Ware.Castom.setting.Setting;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.utils.Font.CastomFontUtils;
import com.Blood.Ware.utils.RenderUtil;

import java.awt.*;

public class CheckBox extends Comp
{
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isInside(n, n2, this.parent.posX + this.x - 70.0, this.parent.posY + this.y, this.parent.posX + this.x + 10.0 - 70.0, this.parent.posY + this.y + 8.5) && n3 == 0) {
            this.setting.setValBoolean(!this.setting.getValBoolean());
        }
    }
    
    public CheckBox(final double x, final double y, final CastomGui parent, final Module module, final Setting setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.module = module;
        this.setting = setting;
    }
    
    @Override
    public void drawScreen(final int n, final int n2) {
        super.drawScreen(n, n2);
        RenderUtil.drawRect(this.parent.posX + this.x - 70.0, this.parent.posY + this.y, this.parent.posX + this.x + 10.0 - 70.0, this.parent.posY + this.y + 8.5, this.setting.getValBoolean() ? new Color(10, 179, 230).getRGB() : new Color(61, 61, 61).getRGB());
        CastomFontUtils.fr2.drawString(this.setting.getName(), (int)(this.parent.posX + this.x - 55.0), (int)(this.parent.posY + this.y + 0.5), new Color(200, 200, 200).getRGB());
    }
}
