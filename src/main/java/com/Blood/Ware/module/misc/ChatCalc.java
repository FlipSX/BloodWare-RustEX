package com.Blood.Ware.module.misc;

import com.Blood.Ware.module.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import com.Blood.Ware.module.Category;

public class ChatCalc extends Module {
    public ChatCalc(){super("ChatCalc", Category.OUTHER);}

    @SubscribeEvent
    public void chatCalc(ClientChatEvent e) {
        String[] calcArgs = {"*", "+", "-", "/"};

        try {
            for (String msg : e.getMessage().split(" ")) {
                for (String s : calcArgs) {
                    if (msg.contains(s)) {
                        if (s == "*") {
                            e.setMessage(e.getMessage().replace(msg,
                                    Integer.toString(Integer.parseInt(msg.split("\\*")[0])*Integer.parseInt(msg.split("\\*")[1]))));
                        } if (s == "+") {
                            e.setMessage(e.getMessage().replace(msg,
                                    Integer.toString(Integer.parseInt(msg.split("\\+")[0])+Integer.parseInt(msg.split("\\+")[1]))));
                        } if (s == "-") {
                            e.setMessage(e.getMessage().replace(msg,
                                    Integer.toString(Integer.parseInt(msg.split("-")[0])-Integer.parseInt(msg.split("-")[1]))));
                        } if (s == "/") {
                            e.setMessage(e.getMessage().replace(msg,
                                    Integer.toString(Integer.parseInt(msg.split("/")[0])/Integer.parseInt(msg.split("/")[1]))));
                        }
                    }
                }
            }
        } catch (Exception ignored) {}
    }
}

