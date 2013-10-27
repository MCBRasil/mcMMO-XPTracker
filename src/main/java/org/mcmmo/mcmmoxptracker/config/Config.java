package org.mcmmo.mcmmoxptracker.config;

public class Config extends AutoUpdateConfigLoader {
    private static Config instance;

    private Config() {
        super("config.yml");
        validate();
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }

        return instance;
    }

    @Override
    protected boolean validateKeys() {
        return true;
    }

    @Override
    protected void loadKeys() {}

    /*
     * GENERAL SETTINGS
     */

    /* General Settings */
    public boolean getVerboseLoggingEnabled() { return config.getBoolean("General.Verbose_Logging", false); }

    public boolean getConfigOverwriteEnabled() { return config.getBoolean("General.Config_Update_Overwrite", true); }

    public int getTrackerTimeInterval() { return config.getInt("XP_Tracker.Track_Interval", 10); }
}
