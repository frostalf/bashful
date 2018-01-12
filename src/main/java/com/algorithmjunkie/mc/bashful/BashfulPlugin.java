package com.algorithmjunkie.mc.bashful;

import co.aikar.commands.BukkitCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BashfulPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        BukkitCommandManager commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new BashfulCommand(this));
    }
}
