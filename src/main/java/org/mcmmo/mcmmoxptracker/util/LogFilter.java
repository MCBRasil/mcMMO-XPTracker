package org.mcmmo.mcmmoxptracker.util;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

import org.mcmmo.mcmmoxptracker.mcMMOXPTracker;

public class LogFilter implements Filter {
    private boolean debug;

    public LogFilter(mcMMOXPTracker plugin) {
        // Doing a config loading lite here, because we can't depend on the config loader to have loaded before any debug messages are sent
        debug = plugin.getConfig().getBoolean("General.Verbose_Logging");
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        if (record.getMessage().contains("[Debug]") && !debug) {
            return false;
        }

        return true;
    }
}
