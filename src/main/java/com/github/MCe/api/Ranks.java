package com.github.MCe.api;

import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Lemtzas
 * Date: 12/19/12
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Ranks {
    /** @return The names of the skills recognized by the system. */
    public abstract String[] getSkills();
    /** @return The description of a skill. */
    public abstract String getSkillDescription(final String skill);

    /**
     * Check how many ranks a player has in a skill. Minimum/maximum determined by specific implementation.
     * @param player_name The name of the player to inspect
     * @param skill The name of the skill to check
     * @return The number of ranks the player has in that skill
     */
    public abstract int getPlayerRanksInSkill(final String player_name, final String skill);

    /** @return The lowest possible value "RanksInSkill" can have. Defaults to 0. */
    public int getMinRanksInSkill() { return 0;}
    /** @return The highest possible value "RanksInSkill" can have. Defaults to 5. */
    public int getMaxRanksInSkill() { return 10;}
    /** @return The number of "RanksInSkill" considered to be "steve" levels. Defaults to 10. */
    public int getSteveLevelInSkill() { return 5;}
}
