/*
 * Decompiled with CFR 0.150.
 */
package Caesium.listeners;

import Caesium.components.GuiButton;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListener
implements ActionListener {
    private GuiButton button;

    public ClickListener(GuiButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Module m = BloodWare.instance.moduleManager.getModule(this.button.getText());
        m.toggle();
    }
}

