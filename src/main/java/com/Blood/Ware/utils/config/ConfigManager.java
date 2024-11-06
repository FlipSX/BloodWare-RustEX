package com.Blood.Ware.utils.config;

import com.Blood.Ware.BloodWare;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.settings.Setting;
import com.Blood.Ware.utils.ChatUtils;
import net.minecraft.client.Minecraft;


import java.io.*;
import java.util.ArrayList;

public class ConfigManager {

    public final String path;

    public ConfigManager() {
        path = new File(Minecraft.getMinecraft().mcDataDir, "BloodWare").getPath();
        File paste_dir = new File(path);

        if (!paste_dir.exists()) {
            paste_dir.mkdir();
        }
    }


    public void saveCFG(String name) {
        try {
            String json = getJson();
            File cfg = new File(path + File.separator + name + ".json");

            if (!cfg.exists()) {
                try {
                    //create a new file
                    if (cfg.createNewFile()) {
                    }
                    ChatUtils.message("Created " + name + ".json");

                    ChatUtils.message("Error");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try (FileWriter writer = new FileWriter(cfg)) {
                //write some data to the file
                writer.write(json);
                ChatUtils.message("Saved " + name + ".json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            ChatUtils.message("Error");
        }
    }

    private String getJson() {
        StringBuilder json = new StringBuilder();
        for (Module module : BloodWare.moduleManager.getModuleList()) {
            String name = module.getName();
            json.append(name);
            json.append(":");
            String toggled = String.valueOf(module.isToggled());
            json.append(toggled);
            json.append(":");
            String key = String.valueOf(module.getKey());
            json.append(key);
            json.append(":");
            ArrayList<Setting> arrayList = BloodWare.instance.settingsManager.getSettingsByMod(module);
            if (arrayList != null) {
                for (Setting setting : arrayList) {
                    String settings;
                    if (setting.isCheck()) {
                        settings = setting.getName() + "=" + setting.getValBoolean();
                    } else if (setting.isSlider()) {
                        settings = setting.getName() + "=" + setting.getValDouble();
                    } else {
                        settings = setting.getName() + "=" + setting.getValString();
                    }
                    json.append(settings);
                    json.append(":");
                }
            }
            json.append(";");
        }
        return json.toString();
    }

    public void loadCFG(String name) {
        try {
            File cfg = new File(path, name + ".json");
            if (!cfg.exists()) {
                ChatUtils.message("Not Found");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(cfg.getPath()));
            String line = reader.readLine();
            String json = line;

            for (String cfg_module : json.split(";")) {
                try {
                    if (cfg_module == null) continue;
                    String[] settings = cfg_module.split(":");
                    if (settings.length < 2) continue;
                    String module_name = settings[0];
                    boolean module_toggled = Boolean.parseBoolean(settings[1]);
                    int module_key = Integer.parseInt(settings[2]);
                    Module module = BloodWare.moduleManager.getModule(module_name);
                    module.setToggled(module_toggled);
                    module.setKey(module_key);
                    for (int n = 3; n < settings.length; n++) {
                        String[] cfg_setting = settings[n].split("=");
                        String setting_name = cfg_setting[0];
                        Setting setting = BloodWare.instance.settingsManager.getSettingByName(module, setting_name);
                        if (setting.isCheck()) {
                            setting.setValBoolean(Boolean.parseBoolean(cfg_setting[1]));
                        } else if (setting.isSlider()) {
                            setting.setValDouble(Double.parseDouble(cfg_setting[1]));
                        } else {
                            setting.setValString(cfg_setting[1]);
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            ChatUtils.message(name+" Loaded");
        } catch (Exception e) {
            e.printStackTrace();
            ChatUtils.message("Error");
            throw new RuntimeException(e);
        }
    }
}