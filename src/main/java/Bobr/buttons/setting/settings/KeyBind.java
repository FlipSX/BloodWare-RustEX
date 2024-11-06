package Bobr.buttons.setting.settings;

import Bobr.buttons.setting.CSSetting;
import com.Blood.Ware.module.Module;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class KeyBind extends CSSetting {
    private boolean binding;
  public KeyBind(int x, int y, int width, int height, Module s) {
        super(x, y, width, height, s);
    }



    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {

        super.keyTyped(typedChar, keyCode);
    }



    public void setBinding(boolean binding) {
        this.binding = binding;
    }




    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        String displayString = "KeyBind: " + Keyboard.getKeyName(mod.getKey());
        if(binding){

            displayString = "Listen... ";
        fr.drawString(displayString , x, y, Integer.MAX_VALUE);

        }
        fr.drawString(displayString, x, y, Integer.MAX_VALUE);

        int stringwidth = fr.getStringWidth(displayString);
        if(binding && Keyboard.getEventKey() != 0 && Keyboard.getEventKeyState()){
            mod.setKey(Keyboard.getEventKey());
            binding = false;
        }







        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (isHovered(mouseX, mouseY) && mouseButton == 0) {
            setBinding(true);
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    private boolean isHovered(int mouseX, int mouseY) {
        int stringwidth = fr.getStringWidth(displayString);
        boolean hoveredx = mouseX > this.x +  15 && mouseX < this.x + stringwidth + 35;
        boolean hoveredy = mouseY > this.y && mouseY < this.y + 10;
        return hoveredx && hoveredy;
    }

}
