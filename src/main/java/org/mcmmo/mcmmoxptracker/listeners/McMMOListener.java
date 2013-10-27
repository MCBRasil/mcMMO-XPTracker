package org.mcmmo.mcmmoxptracker.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class McMMOListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerXpGain(McMMOPlayerXpGainEvent event) {
        final float rawXp = event.getRawXpGained();
        if (rawXp <= 0) {
            return;
        }

        Player player = event.getPlayer();
//        SkillType skillType = event.getSkill();

//        if (skillType.isChildSkill()) {
//            return;
//        }
    }
}
