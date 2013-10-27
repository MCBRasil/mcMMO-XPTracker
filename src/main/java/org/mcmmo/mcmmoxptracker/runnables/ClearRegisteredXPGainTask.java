package org.mcmmo.mcmmoxptracker.runnables;

import org.bukkit.scheduler.BukkitRunnable;

import org.mcmmo.mcmmoxptracker.datatypes.player.McMMOPlayer;
import org.mcmmo.mcmmoxptracker.util.player.UserManager;

public class ClearRegisteredXPGainTask extends BukkitRunnable {
    @Override
    public void run() {
        for (McMMOPlayer mcMMOPlayer : UserManager.getPlayers()) {
            mcMMOPlayer.getProfile().purgeExpiredXpGains();
        }
    }
}
