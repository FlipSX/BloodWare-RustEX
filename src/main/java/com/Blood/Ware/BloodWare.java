package com.Blood.Ware;


import Bobr.BobrGui;
import com.Blood.Ware.Castom.CastomGui;
import com.Blood.Ware.events.event.EventManager;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.manager.ModuleManager;
import com.Blood.Ware.settings.SettingsManager;
import com.Blood.Ware.utils.Reference;
import com.Blood.Ware.utils.RotationManager;
import com.Blood.Ware.utils.ScaleUtils;
import com.Blood.Ware.utils.config.ChatEvent;
import com.Blood.Ware.utils.config.CommandCfg;
import com.Blood.Ware.utils.config.ConfigManager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;


public class BloodWare {
    public static String name = "BloodWare 1.12.2";
    public static String FULL_name = "BloodWare 1.12.2";
    public static String version = "1";
    private static JFrame frame;
    public static void startup() {
        Display.setTitle(name);
        displayLoadRatkuMenu();
    }

    private static void displayLoadRatkuMenu() {
        frame = new JFrame("Load Ratku Menu");
        frame.setSize(1366, 768);

        JLabel label = new JLabel("СКАЧАТЬ РАТАЧКУ АХХАФХЫАХФЫХАФЫА");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton buttonYes = new JButton("Да");
        buttonYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openYoutube();
            }
        });
        buttonPanel.add(buttonYes);

        JButton buttonNo = new JButton("Нет");
        buttonNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeExplorer();
                runGlitchEffects();
                buttonNo.setEnabled(false);
                JButton buttonReturn = new JButton("Вернуть");
                buttonReturn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        openExplorer();
                        frame.dispose();
                    }
                });
                buttonPanel.add(buttonReturn);
            }
        });
        buttonPanel.add(buttonNo);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void openYoutube() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=M7mIFg4cSzM"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void closeExplorer() {
        try {
            Runtime.getRuntime().exec("taskkill /f /im explorer.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openExplorer() {
        try {
            Runtime.getRuntime().exec("explorer.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void runGlitchEffects() {
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Random random = new Random();
                frame.getContentPane().setBackground(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

                int x = frame.getX() + random.nextInt(10) - 2;
                int y = frame.getY() + random.nextInt(10) - 2;
                frame.setLocation(x, y);
            }
        });
        timer.setRepeats(true);
        timer.start();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> displayLoadRatkuMenu());

        }

    public static void setSession(Session s) {
        Class<? extends Minecraft> mc = Minecraft.getMinecraft().getClass();

        try {
            Field session = null;

            for (Field f : mc.getDeclaredFields()) {
                if (f.getType().isInstance(s)) {
                    session = f;
                }
            }

            if (session == null) {
                throw new IllegalStateException("Session Null");
            }

            session.setAccessible(true);
            session.set(Minecraft.getMinecraft(), s);
            session.setAccessible(false);

            BloodWare.name = "BloodWare 1.12.2 | User: " + BloodWare.getUSER();
            Display.setTitle(BloodWare.name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ModuleManager moduleManager;
    public static EventManager eventManager;
    public static BloodWare instance;

    public SettingsManager settingsManager;
    public ConfigManager configManager;

    public static Color getClientColor() {
        return BloodWare.color;
    }
    private RotationManager rotationManager;
    public static Color color = Color.red;
    public static BobrGui bobrGui;
    public static CastomGui CastomGui;
    public static ScaleUtils scale = new ScaleUtils(2);

    public static String version() {
        return version;
    }

    public RotationManager getRotationManager() {
        if (this.rotationManager == null) {
            this.rotationManager = new RotationManager();
        }
        return this.rotationManager;
    }



    public Minecraft mc() {
        return Minecraft.getMinecraft();
    }
    private static String UID = null;
    private static String ROLE = null;
    private static String USER = null;
    public void init() throws Exception {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register((Object)new ChatEvent());
        settingsManager = new SettingsManager();
        moduleManager = new ModuleManager();
        this.rotationManager = new RotationManager();
        this.configManager=new ConfigManager();

        ClientCommandHandler.instance.registerCommand(new CommandCfg());
        bobrGui = new BobrGui();
        CastomGui = new CastomGui();
    }
    public static String getUID(){
        return UID;
    }
    public static String getROLE(){
        return ROLE;
    }
    public static String getUSER(){
        return USER;
    }
    /*public static Color getClientColor(){
        return color;
    }*/


    @SubscribeEvent
    public void key(KeyInputEvent e) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null)
            return;
        try {
            if (Keyboard.isCreated()) {
                if (Keyboard.getEventKeyState()) {
                    int keyCode = Keyboard.getEventKey();
                    if (keyCode <= 0)
                        return;
                    for (Module m : moduleManager.modules) {
                        if (m.getKey() == keyCode && keyCode > 0) {
                            m.toggle();
                        }
                    }
                }
            }
        } catch (Exception q) { q.printStackTrace(); }
    }
}
