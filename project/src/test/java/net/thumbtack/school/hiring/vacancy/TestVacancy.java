package net.thumbtack.school.hiring.vacancy;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class TestVacancy {

    @Test
    public void testCreateVacancy() throws ServerException {
        Set<Requirement> requirements = new HashSet<>();
        requirements.add(new Requirement("Java",5,true));
        requirements.add(new Requirement("SQL",5,true));
        requirements.add(new Requirement("English",3,false));
        Vacancy vacancy = new Vacancy("Back end", 100, requirements);
        assertEquals("Back end", vacancy.getName());
        assertEquals(100, vacancy.getSalary());
        assertEquals(requirements.size(), vacancy.getRequirements().size());
        assertEquals(requirements, vacancy.getRequirements());
    }

    @Test
    public void testCreateWrongVacancyName() throws ServerException {
        Set<Requirement> requirements = new HashSet<>();
        requirements.add(new Requirement("Java",5,true));
        requirements.add(new Requirement("SQL",5,true));
        requirements.add(new Requirement("English",3,false));

        try {
            Vacancy vacancy = new Vacancy("", 100, requirements);
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.VACANCY_WRONG_NAME, e.getErrorCode());
        }
        try {
            Vacancy vacancy = new Vacancy(null, 100, requirements);
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.VACANCY_WRONG_NAME, e.getErrorCode());
        }
    }

    @Test
    public void testCreateWrongVacancyRequirements(){
        Set<Requirement> requirements = new HashSet<>();


        try {
            requirements.add(new Requirement("",5,true));
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.SKILL_WRONG_NAME, e.getErrorCode());
        }
        try {
            requirements.add(new Requirement(null,5,true));
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.SKILL_WRONG_NAME, e.getErrorCode());
        }

//        try {
//            Vacancy vacancy = new Vacancy("Back End", 100, requirements);
//            fail();
//        } catch (ServerException e) {
//            assertEquals(ServerErrorCode.VACANCY_NULL_REQUIREMENTS, e.getErrorCode());
//        }
    }

    @Test
    public void testVacancyEquals() throws ServerException {
        Set<Requirement> requirements = new HashSet<>();
        requirements.add(new Requirement("Java",5,true));
        Vacancy vacancy = new Vacancy("Back End", 100, requirements);
        Vacancy vacancy1 = new Vacancy("Back End", 100,  requirements);
        assertTrue(vacancy.equals(vacancy1));
        Vacancy vacancy2 = new Vacancy("af", 100, requirements);
        assertFalse(vacancy.equals(vacancy2));
        Vacancy vacancy3 = new Vacancy("Back End", 1000, requirements);
        assertFalse(vacancy.equals(vacancy3));
        Set<Requirement> requirements1 = new HashSet<>();
        requirements1.add(new Requirement("Back End",5,false));
        Vacancy vacancy4 = new Vacancy("Back End", 100,  requirements1);
        assertFalse(vacancy.equals(vacancy4));
        requirements.add(new Requirement("English",5,false));
        vacancy4 = new Vacancy("Back End", 100,  requirements1);
        assertFalse(vacancy.equals(vacancy4));
    }
}
