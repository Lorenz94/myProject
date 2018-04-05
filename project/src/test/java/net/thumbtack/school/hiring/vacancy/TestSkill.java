package net.thumbtack.school.hiring.vacancy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSkill {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void testCreateSkill() throws ServerException {
        Skill skill = new Skill("Java",5);
        assertEquals("Java", skill.getName());
        assertEquals(5, skill.getScore());
    }

    @Test
    public void testCreateWrongSkill(){
        try {
            Skill skill = new Skill("Java",6);
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.SKILL_WRONG_SCORE, e.getErrorCode());
        }
        try {
            Skill skill = new Skill("",2);
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.SKILL_WRONG_NAME, e.getErrorCode());
        }
        try {
            Skill skill = new Skill(null,2);
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.SKILL_WRONG_NAME, e.getErrorCode());
        }
    }

    @Test
    public void testCreateWrongSkillFromJson() throws ServerException {
        Skill skill = new Skill("Java",2);
        String json = gson.toJson(skill);
        String s = "{\n" +
                "  \"name\": \"null\",\n" +
                "  \"score\": 2\n" +
                "}";
        Skill skill1 = gson.fromJson(s,Skill.class);
        assertEquals("", "");
    }
}
