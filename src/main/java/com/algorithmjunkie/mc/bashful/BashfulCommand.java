package com.algorithmjunkie.mc.bashful;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.apache.commons.exec.*;
import org.bukkit.command.CommandSender;
import java.io.IOException;

@CommandAlias("bashful|bash|sh|execute|exe")
@CommandPermission("bashful")
public class BashfulCommand extends BaseCommand {
    private BashfulPlugin bashful;

    public BashfulCommand(BashfulPlugin bashful) {
        this.bashful = bashful;
    }

    @Default
    public void onDefault(CommandSender sender) {
        sender.spigot().sendMessage(new ComponentBuilder("Bashful - 1.0.0").color(ChatColor.YELLOW).create());
        sender.spigot().sendMessage(new ComponentBuilder("Usage: ").color(ChatColor.YELLOW)
                .append("/bashful ").color(ChatColor.AQUA)
                .append("<OS Command> (i.e., help)").color(ChatColor.LIGHT_PURPLE)
                .create());
    }

    @UnknownHandler
    public void handleProgram(CommandSender sender, @Split(" ") String[] command) {
        if (command == null || command.length == 0) {
            sender.spigot().sendMessage(
                    new ComponentBuilder("You must provide a program to execute.").color(ChatColor.RED)
                            .create()
            );

            return;
        }

        sender.spigot().sendMessage(
                new ComponentBuilder(String.format("Executing \"%s\"...", String.join(" ", command)))
                        .color(ChatColor.YELLOW)
                        .create()
        );

        try {
            CommandLine cli = new CommandLine(command[0]);
            for (int i = 1; i < command.length; i++) cli.addArgument(command[i], true);
            Executor executor = new DefaultExecutor();
            executor.setExitValues(new int[] { 0, 1 });
            executor.setStreamHandler(new PumpStreamHandler(new OutputHandler(sender)));
            executor.execute(cli, new ExecutionHandler(bashful, sender, new ExecuteWatchdog(120 * 1000)));
        } catch (IOException e) {
            sender.spigot().sendMessage(
                    new ComponentBuilder("An unknown error occurred processing the requested command.")
                            .color(ChatColor.RED)
                            .create()
            );
        }
    }
}
