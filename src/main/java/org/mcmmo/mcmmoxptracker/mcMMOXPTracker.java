package org.mcmmo.mcmmoxptracker;

import java.util.logging.Level;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import org.mcmmo.mcmmoxptracker.commands.XPTrackerCommand;
import org.mcmmo.mcmmoxptracker.listeners.McMMOListener;

public class mcMMOXPTracker extends JavaPlugin {
    public static mcMMOXPTracker p;

    private boolean mcMMOEnabled = false;

    /**
     * Run things on enable.
     */
    @Override
    public void onEnable() {
        p = this;

        setupMcMMO();

        if (!isMcMMOEnabled()) {
            this.getLogger().log(Level.WARNING, " requires mcMMO to run, please download mcMMO");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        registerEvents();

        registerCommands();
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new McMMOListener(), this);
    }

    /**
     * Register all the command and set Executor.
     */
    private void registerCommands() {
        getCommand("xptracker").setExecutor(new XPTrackerCommand());
    }

    /**
     * Run things on disable.
     */
    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
    }

    public void debug(String message) {
        getLogger().info("[Debug] " + message);
    }

    private void setupMcMMO() {
        if (getServer().getPluginManager().isPluginEnabled("mcMMO")) {
            mcMMOEnabled = true;
            debug("mcMMO found!");
        }
    }

    public boolean isMcMMOEnabled() {
        return mcMMOEnabled;
    }
}
