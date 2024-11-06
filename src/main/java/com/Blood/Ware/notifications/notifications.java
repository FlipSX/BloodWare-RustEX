package com.Blood.Ware.notifications;

import com.Blood.Ware.module.hud.ClickGUI;
import com.Blood.Ware.utils.Font.CastomFontUtils;
import com.Blood.Ware.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class notifications {
    public static List<String> Names = new ArrayList<String>();
    public static List<String> Tests = new ArrayList<String>();
    public static List<Type> Types = new ArrayList<Type>();
    public static List<Integer> Times = new ArrayList<Integer>();

    //Settings
    private static float height = 30;
    private static float width = 100;

    public static void add(String main_input, String text_input, Type type_input){
        Names.add(main_input);
        Tests.add(text_input);
        Types.add(type_input);
        Times.add(0);
    }

    public static void drawnotif(int i, String name, String text, Type type, int time){
        GlStateManager.enableTexture2D();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glPushMatrix();
        //GL11.glTranslated(width / 100 + 0, sr.getScaledHeight() / 100 + 0, 0.0);
        if(time >= 110){
            GL11.glTranslated(sr.getScaledWidth() - (110), sr.getScaledHeight() - (time * 1.5) + 10, 0);

        }else{
            GL11.glTranslated(sr.getScaledWidth() - (time), sr.getScaledHeight() - (time * 1.5) + 10, 0);

        }
        int alpha;
        alpha = (int) (230 - (time - 7.6));
        if(alpha >= 0){
            if(type == Type.Green){
                RenderUtil.drawSmoothRect(0, 0, width, height, new Color(35, 35, 40, 255).getRGB());
                RenderUtil.drawSmoothRect(0, 0, 7, height, new Color(51, 255, 0, 255).getRGB());
                CastomFontUtils.fr5.drawStringWithShadow(name, 10, 4, ClickGUI.getColor());
                CastomFontUtils.fr5.drawString(text, 10, 4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, new Color(255, 255, 255, alpha).getRGB());
            } else if(type == Type.Red){
                RenderUtil.drawSmoothRect(0, 0, width, height, new Color(35, 35, 40, 255).getRGB());
                RenderUtil.drawSmoothRect(0, 0, 7, height, new Color(255, 0, 0, 255).getRGB());
                CastomFontUtils.fr5.drawStringWithShadow(name, 10, 4, ClickGUI.getColor());
                CastomFontUtils.fr5.drawString(text, 10, 4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, new Color(255, 255, 255, alpha).getRGB());
            } else if(type == Type.OK){
                RenderUtil.drawSmoothRect(0, 0, width, height, new Color(35, 35, 40, 255).getRGB());
                RenderUtil.drawSmoothRect(0, 0, 7, height, new Color(51, 255, 0, 255).getRGB());
                CastomFontUtils.fr5.drawStringWithShadow(name, 10, 4, ClickGUI.getColor());
                CastomFontUtils.fr5.drawString(text, 10, 4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, new Color(255, 255, 255, alpha).getRGB());
            }

        }

        GL11.glPopMatrix();
        GlStateManager.disableTexture2D();

    }

    public static void show(){
        for(int i = 0; i < Names.size(); i++){
            if(Times.get(i) != 230){
                Times.set(i, Times.get(i) + 1);

            } else{
                Names.remove(i);
                Tests.remove(i);
                Types.remove(i);
                Times.remove(i);
            }
        }
        height = 22;
        for(int i = 0; i < Names.size(); i++){
            // get info
            String name = Names.get(i);
            String text = Tests.get(i);
            Type type = Types.get(i);
            int time = Times.get(i);
            drawnotif(i, name, text, type, time);
        }
    }
}