package com.Blood.Ware.utils;

import java.awt.*;

public class notification {
    public static void main(String text) throws Exception {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().getImage("images/bor.png");
            TrayIcon trayIcon = new TrayIcon(image);
            tray.add(trayIcon);
            trayIcon.displayMessage(Reference.Name, text, TrayIcon.MessageType.INFO);
        }
    }
}
