package org.mcmmo.mcmmoxptracker.datatypes.player;

import org.bukkit.entity.Player;

import org.mcmmo.mcmmoxptracker.mcMMOXPTracker;

public class McMMOPlayer {
    private Player player;
    private PlayerProfile profile;

    public McMMOPlayer(Player player) {
        String playerName = player.getName();

        this.player = player;
        profile = new PlayerProfile(playerName, true);

        if (!profile.isLoaded()) {
            mcMMOXPTracker.p.getLogger().warning("Unable to load the PlayerProfile for " + playerName + ".");
        }
    }

    /*
     * Players & Profiles
     */

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerProfile getProfile() {
        return profile;
    }
}
