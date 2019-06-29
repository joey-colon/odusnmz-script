package dev.joey.nmz.services;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.scene.Players;

public class CombatService {

    public static int getCombatXp() {
        if (Players.getLocal() == null) return -1;

        return Skills.getExperience(Skill.ATTACK) +
                Skills.getExperience(Skill.STRENGTH) +
                Skills.getExperience(Skill.DEFENCE) +
                Skills.getExperience(Skill.HITPOINTS) +
                Skills.getExperience(Skill.RANGED) +
                Skills.getExperience(Skill.MAGIC);
    }
}
