package com.algorithmjunkie.mc.bashful;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.apache.commons.exec.LogOutputStream;
import org.bukkit.command.CommandSender;

public class OutputHandler extends LogOutputStream {
    private CommandSender sender;

    public OutputHandler(CommandSender sender) {
        this.sender = sender;
    }

    @Override
    protected void processLine(String line, int logLevel) {
        sender.spigot().sendMessage(
                new ComponentBuilder(">> ").color(ChatColor.GRAY)
                        .append(line).color(ChatColor.YELLOW)
                        .create()
        );
    }
}
