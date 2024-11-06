package com.Blood.Ware.utils.config;

import com.Blood.Ware.BloodWare;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;


import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandCfg extends CommandBase {
    public String getName()
    {
        return "cfg";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("config");
        return aliases;
    }

    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    public String getUsage(ICommandSender sender)
    {
        return "/cfg <save/load> <name>";
    }


    public CommandCfg() {
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length<2){
            throw new WrongUsageException(getUsage(sender), new Object[0]);
        }else {
            switch (args[0]) {
                case "save":
                    BloodWare.instance.configManager.saveCFG(args[1]);
                    break;
                case "load":
                    BloodWare.instance.configManager.loadCFG(args[1]);
                    break;
                default:
                    throw new WrongUsageException(getUsage(sender), new Object[0]);
            }
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add("save");
            options.add("load");
            return options;
        }
        return Collections.emptyList();
    }



}