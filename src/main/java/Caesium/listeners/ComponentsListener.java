/*
 * Decompiled with CFR 0.150.
 */
package Caesium.listeners;

import Caesium.components.*;
import Caesium.components.listeners.ComboListener;
import Caesium.components.listeners.ComponentListener;
import Caesium.components.listeners.KeyListener;
import Caesium.components.listeners.ValueListener;
import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComponentsListener
extends ComponentListener {
    private GuiButton button;

    public ComponentsListener(GuiButton button) {
        this.button = button;
    }

    @Override
    public void addComponents() {
        this.add(new GuiLabel("< Settings >"));
        final Module m = BloodWare.instance.moduleManager.getModule(this.button.getText());
        if (BloodWare.instance.settingsManager.getSettingsByMod(m) != null) {
            for (final Setting set : BloodWare.instance.settingsManager.getSettingsByMod(m)) {
                if (set.isSlider()) {
                    GuiSlider slider = new GuiSlider(set.getName(), (float)set.getMin(), (float)set.getMax(), (float)set.getValDouble(), set.onlyInt() ? 0 : 2);
                    slider.addValueListener(new ValueListener(){

                        @Override
                        public void valueUpdated(float value) {
                            set.setValDouble(value);
                        }

                        @Override
                        public void valueChanged(float value) {
                            set.setValDouble(value);
                            new Thread(() -> {}).start();
                        }
                    });
                    this.add(slider);
                }
                if (set.isCheck()) {
                    final GuiToggleButton toggleButton = new GuiToggleButton(set.getName());
                    toggleButton.setToggled(set.getValBoolean());
                    toggleButton.addClickListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            set.setValBoolean(toggleButton.isToggled());
                        }
                    });
                    this.add(toggleButton);
                }
                if (!set.isCombo()) continue;
                GuiComboBox comboBox = new GuiComboBox(set);
                comboBox.addComboListener(new ComboListener(){

                    @Override
                    public void comboChanged(String combo) {
                    }
                });
                this.add(comboBox);
            }
        }
        GuiGetKey ggk = new GuiGetKey("KeyBind", m.getKey());
        ggk.addKeyListener(new KeyListener(){

            @Override
            public void keyChanged(int key) {
                m.setKey(key);
                new Thread(() -> {}).start();
            }
        });
        this.add(ggk);
        final GuiToggleButton toggleButton = new GuiToggleButton("Visible");
        toggleButton.setToggled(m.visible);
        toggleButton.addClickListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                m.visible = toggleButton.isToggled();
            }
        });
        this.add(toggleButton);
    }
}

