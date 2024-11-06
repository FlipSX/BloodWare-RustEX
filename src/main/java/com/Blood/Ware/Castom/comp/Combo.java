

package com.Blood.Ware.Castom.comp;

import com.Blood.Ware.Castom.CastomGui;
import com.Blood.Ware.Castom.setting.Setting;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.utils.Font.CastomFontUtils;
import com.Blood.Ware.utils.RenderUtil;

import java.awt.*;

public class Combo extends Comp
{
    public Combo(final double x, final double y, final CastomGui parent, final Module module, final Setting setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.module = module;
        this.setting = setting;
    }

    @Override
    public void drawScreen(final int n, final int n2) {
        super.drawScreen(n, n2);
        RenderUtil.drawRect(this.parent.posX + this.x - 70.0, this.parent.posY + this.y, this.parent.posX + this.x + 20.0, this.parent.posY + this.y + 10.0, this.setting.getValBoolean() ? new Color(230, 10, 230).getRGB() : new Color(59, 59, 59).getRGB());
        CastomFontUtils.fr2.drawString(String.valueOf(new StringBuilder().append(this.setting.getName()).append(": ").append(this.setting.getValString())), (int)(this.parent.posX + this.x - 64.0), (int)(this.parent.posY + this.y + 3.0), new Color(200, 200, 200).getRGB());
    }

    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isInside(n, n2, this.parent.posX + this.x - 70.0, this.parent.posY + this.y, this.parent.posX + this.x + 20.0, this.parent.posY + this.y + 10.0) && n3 == 0) {
            if (this.parent.modeIndex + 1 >= this.setting.getOptions().size()) {
                this.parent.modeIndex = 0;
            }
            else {
                final CastomGui parent = this.parent;
                ++parent.modeIndex;
            }
            this.setting.setValString((String) this.setting.getOptions().get(this.parent.modeIndex));
        }
    }
}
