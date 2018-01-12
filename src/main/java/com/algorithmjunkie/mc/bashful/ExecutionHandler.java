package com.algorithmjunkie.mc.bashful;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.bukkit.command.CommandSender;

public class ExecutionHandler extends DefaultExecuteResultHandler {
    private BashfulPlugin bashful;
    private CommandSender sender;
    private ExecuteWatchdog watchdog;

    public ExecutionHandler(final BashfulPlugin bashful, final CommandSender sender, final ExecuteWatchdog watchdog) {
        this.bashful = bashful;
        this.sender = sender;
        this.watchdog = watchdog;
    }

    @Override
    public void onProcessComplete(int exitValue) {
        super.onProcessComplete(exitValue);
        sender.spigot().sendMessage(
                new ComponentBuilder("Execution finished successfully.")
                        .color(ChatColor.GREEN)
                        .create()
        );
    }

    @Override
    public void onProcessFailed(ExecuteException e) {
        super.onProcessFailed(e);
        sender.spigot().sendMessage(
                new ComponentBuilder("Execution failed! See console for details.")
                        .color(ChatColor.RED)
                        .create()
        );
        bashful.getLogger().warning(e.getMessage());
    }


}
