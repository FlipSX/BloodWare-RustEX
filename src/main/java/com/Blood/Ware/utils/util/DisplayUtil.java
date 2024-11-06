package com.Blood.Ware.utils.util;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class DisplayUtil {

    public static void Display() {
        Frame frame = new Frame();
        frame.setVisible(false);
        throw new NoStackTraceThrowable("Verification was unsuccessful!");
    }

    /**
     * The frame if the HWID check was unsuccessful.
     */

    public static class Frame extends JFrame {
        public Frame() {
            this.setTitle("Праверка правалина нахуй");
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);
            copyToClipboard();
            String message = "Ты не в ХВИде LOLAS" + "\n" + "ХВИД: " + SystemUtil.getSystemInfo() + "\n(CКАПИРОВАНО НЕ НАДО ПЕРЕПИСЫВАТЬ)";
            JOptionPane.showMessageDialog(this, message, "Не удалось проверить тебя на хвид", JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.errorIcon"));
        }

        /**
         * The convenience function to automatically copy the HWID to clipboard.
         */

        public static void copyToClipboard() {
            StringSelection selection = new StringSelection(SystemUtil.getSystemInfo());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }
}
