package org.mcmmo.mcmmoxptracker.datatypes.player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;

import com.gmail.nossr50.datatypes.skills.SkillType;

import org.mcmmo.mcmmoxptracker.datatypes.experience.SkillXpGain;
import org.mcmmo.mcmmoxptracker.util.Logger;

public class PlayerProfile {
    private final String playerName;
    private boolean loaded;
    private boolean changed;

    // Store previous XP gains for diminished returns
    private DelayQueue<SkillXpGain> gainedSkillsXp = new DelayQueue<SkillXpGain>();
    private HashMap<SkillType, Float> rollingSkillsXp = new HashMap<SkillType, Float>();

    // Store highest XP gains in 10 minutes for debug purposes
    private HashMap<SkillType, Float> highestGainedXP = new HashMap<SkillType, Float>();

    private PlayerProfile(String playerName) {
        this.playerName = playerName;
    }

    public PlayerProfile(String playerName, boolean isLoaded) {
        this(playerName);
        this.loaded = isLoaded;
    }

    /**
     * Calling this constructor is considered loading the profile.
     */
    public PlayerProfile(String playerName, Map<SkillType, Integer> argSkills) {
        this(playerName);
        loaded = true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Get the registered amount of experience gained
     * This is used for diminished XP returns
     *
     * @return xp Experience amount registered
     */
    public float getRegisteredXpGain(SkillType skillType) {
        float xp = 0F;

        if (rollingSkillsXp.get(skillType) != null) {
            xp = rollingSkillsXp.get(skillType);
        }

        return xp;
    }

    /**
     * Register an experience gain
     * This is used for diminished XP returns
     *
     * @param skillType Skill being used
     * @param xp        Experience amount to add
     */
    public void registerXpGain(SkillType skillType, float xp) {
        gainedSkillsXp.add(new SkillXpGain(skillType, xp));
        rollingSkillsXp.put(skillType, getRegisteredXpGain(skillType) + xp);
    }

    /**
     * Remove experience gains older than a given time
     * This is used for diminished XP returns
     */
    public void purgeExpiredXpGains() {
        SkillXpGain gain;
        boolean print = true;

        while ((gain = gainedSkillsXp.poll()) != null) {
            SkillType skillType = gain.getSkill();
            rollingSkillsXp.put(skillType, getRegisteredXpGain(skillType) - gain.getXp());

            if (getRegisteredXpGain(skillType) > 0) {
                if (!highestGainedXP.containsKey(skillType) || (getRegisteredXpGain(skillType) > highestGainedXP.get(skillType))) {
                    highestGainedXP.put(skillType, getRegisteredXpGain(skillType));

                    if (print) {
                        Logger.getInstance().log("New maximum amount of XP Earned in 10 minutes: " + playerName + "...");
                    }
                    print = false;
                    Logger.getInstance().log("SkillType: " + skillType + " xp = " + getRegisteredXpGain(skillType));
                }
            }
        }
    }
}
