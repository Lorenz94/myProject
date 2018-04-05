package net.thumbtack.school.hiring.response.skill;

import net.thumbtack.school.hiring.vacancy.Skill;

import java.util.HashSet;
import java.util.Set;

public class GetSkillsDtoResponse {
    private Set<Skill> skills;

    public GetSkillsDtoResponse(Set<Skill> skills) {
        setSkills(skills);
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        if (skills == null) {
            this.skills = new HashSet<>();
        } else {
            this.skills = skills;
        }
    }

}
