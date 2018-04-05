package net.thumbtack.school.hiring.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.request.employee.ChangeActivityEmployeeDtoRequest;
import net.thumbtack.school.hiring.request.employee.DeleteEmployeeDtoRequest;
import net.thumbtack.school.hiring.request.employee.RegisterEmployeeDtoRequest;
import net.thumbtack.school.hiring.request.employee.UpdateEmployeeDtoRequest;
import net.thumbtack.school.hiring.request.employer.DeleteEmployerDtoRequest;
import net.thumbtack.school.hiring.request.employer.HireEmployeeDtoRequest;
import net.thumbtack.school.hiring.request.employer.RegisterEmployerDtoRequest;
import net.thumbtack.school.hiring.request.employer.UpdateEmployerDtoRequest;
import net.thumbtack.school.hiring.request.skill.RemoveSkillDtoRequest;
import net.thumbtack.school.hiring.request.skill.SkillDtoRequest;
import net.thumbtack.school.hiring.request.vacancy.*;
import net.thumbtack.school.hiring.response.employee.*;
import net.thumbtack.school.hiring.response.employer.GetEmployerDtoResponse;
import net.thumbtack.school.hiring.response.skill.GetSkillsDtoResponse;
import net.thumbtack.school.hiring.response.vacancy.*;
import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.vacancy.Requirement;
import net.thumbtack.school.hiring.vacancy.Skill;
import net.thumbtack.school.hiring.vacancy.Vacancy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TestServer {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Server server = new Server();


    @Before
    public void beforeTestStartServer() {
        server.startServer();
    }

    @After
    public void afterTestStopServer() throws IOException, ServerException {
        server.stopServer("test.txt");
    }

    @Test
    public void testLoadDataBase() throws IOException, ServerException {
        RegisterEmployeeDtoRequest dtoRequest = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
        String jsonToken = server.registerEmployee(gson.toJson(dtoRequest));
        server.stopServer("test.txt");
        server.startServer("test.txt");
        try {
            RegisterEmployeeDtoRequest dtoRequest1 = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
            String jsonToken1 = server.registerEmployee(gson.toJson(dtoRequest1));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.DATABASE_LOGIN_EXISTS);
        }
    }

    @Test
    public void testCreateEmployee() throws ServerException {
        RegisterEmployeeDtoRequest dtoRequest = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
        String jsonToken = server.registerEmployee(gson.toJson(dtoRequest));
        GetEmployeeDtoResponse dtoResponse = gson.fromJson(server.getEmployee(jsonToken), GetEmployeeDtoResponse.class);
        Employee employee = new Employee(dtoResponse.getName(), dtoResponse.getMiddleName(), dtoResponse.getLastName(), dtoResponse.getEmail(), dtoResponse.getLogin(), dtoResponse.getPassword(), dtoResponse.getSkills());
        assertEquals("John", employee.getName());
        assertEquals("Hamish", employee.getMiddleName());
        assertEquals("Watson", employee.getLastName());
        assertEquals("Dr.Watson@gmail.com", employee.getEmail());
        assertEquals("Dr.watson", employee.getLogin());
        assertEquals("kcolrehs", employee.getPassword());
    }

    @Test
    public void testDeleteEmployee() throws ServerException {
        RegisterEmployeeDtoRequest dtoEmployeeRequest = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
        String token = gson.fromJson(server.registerEmployee(gson.toJson(dtoEmployeeRequest)), String.class);
        SkillDtoRequest skillDtoRequest = new SkillDtoRequest("Java", 5, token);
        server.addSkill(gson.toJson(skillDtoRequest));
        GetEmployeeDtoResponse dtoEmployeeResponse = gson.fromJson(server.getEmployee(gson.toJson(token)), GetEmployeeDtoResponse.class);
        assertEquals("John", dtoEmployeeResponse.getName());

        DeleteEmployeeDtoRequest dtoDeleteRequest = new DeleteEmployeeDtoRequest(token);
        server.deleteEmployee(gson.toJson(dtoDeleteRequest));
        try {
            GetEmployeeDtoResponse dtoResponse = gson.fromJson(server.getEmployee(gson.toJson(token)), GetEmployeeDtoResponse.class);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.DATABASE_EMPLOYEE_NOT_FOUND);
        }
    }

    @Test
    public void testSetActivityEmployee() throws ServerException {
        RegisterEmployeeDtoRequest dtoEmployeeRequest = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
        String token = gson.fromJson(server.registerEmployee(gson.toJson(dtoEmployeeRequest)), String.class);
        GetEmployeeDtoResponse dtoEmployeeResponse = gson.fromJson(server.getEmployee(gson.toJson(token)), GetEmployeeDtoResponse.class);
        assertEquals(true, dtoEmployeeResponse.isActive());
        ChangeActivityEmployeeDtoRequest dtoRequest = new ChangeActivityEmployeeDtoRequest(token, false);
        server.setActivity(gson.toJson(dtoRequest));
        dtoEmployeeResponse = gson.fromJson(server.getEmployee(gson.toJson(token)), GetEmployeeDtoResponse.class);
        assertEquals(false, dtoEmployeeResponse.isActive());
    }

    @Test
    public void testUpdateEmployee() throws ServerException {
        RegisterEmployeeDtoRequest dtoEmployeeRequest = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
        String token = gson.fromJson(server.registerEmployee(gson.toJson(dtoEmployeeRequest)), String.class);
        UpdateEmployeeDtoRequest updateEmployeeDtoRequest = new UpdateEmployeeDtoRequest("Boris", "", "Watson", "Watson@gmail.com", "Dr.watson", "123456", null, token);
        server.updateEmployee(gson.toJson(updateEmployeeDtoRequest));
        GetEmployeeDtoResponse getEmployeeDtoResponse = gson.fromJson(server.getEmployee(gson.toJson(token)), GetEmployeeDtoResponse.class);
        assertEquals("Boris", getEmployeeDtoResponse.getName());
        assertEquals("", getEmployeeDtoResponse.getMiddleName());
        assertEquals("Watson", getEmployeeDtoResponse.getLastName());
        assertEquals("Watson@gmail.com", getEmployeeDtoResponse.getEmail());
        assertEquals("Dr.watson", getEmployeeDtoResponse.getLogin());
        assertEquals("123456", getEmployeeDtoResponse.getPassword());

        try {
            UpdateEmployeeDtoRequest updateWrongEmployeeDtoRequest = new UpdateEmployeeDtoRequest("Boris", "", "Watson", "Watson@gmail.com", "Dr.NotWatson", "123456", null, token);
            server.updateEmployee(gson.toJson(updateWrongEmployeeDtoRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.PERSON_WRONG_LOGIN);
        }
        try {
            UpdateEmployeeDtoRequest updateWrongEmployeeDtoRequest = new UpdateEmployeeDtoRequest("Boris", "", "Watson", "Watson@gmail.com", "Dr.Watson", "123456", null, "123");
            server.updateEmployee(gson.toJson(updateWrongEmployeeDtoRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.SERVER_WRONG_TOKEN);
        }

    }

    @Test
    public void testLogOnEmployee() throws ServerException {
        RegisterEmployeeDtoRequest dtoEmployeeRequest = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
        String token = gson.fromJson(server.registerEmployee(gson.toJson(dtoEmployeeRequest)), String.class);
        assertEquals("Ok", gson.fromJson(server.logOnEmployee(gson.toJson(token)), String.class));
        assertEquals("Ok", gson.fromJson(server.logOffEmployee(gson.toJson(token)), String.class));

    }


    @Test
    public void testCreateWrongEmployee() {
        try {
            RegisterEmployeeDtoRequest dtoWrongName = new RegisterEmployeeDtoRequest("", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.PERSON_EMPTY_NAME);
        }
        try {
            RegisterEmployeeDtoRequest dtoWrongLastName = new RegisterEmployeeDtoRequest("John", "Hamish", "", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.PERSON_EMPTY_LASTNAME);
        }
        try {
            RegisterEmployeeDtoRequest dtoWrongEmail = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Wat@", "Dr.watson", "kcolrehs", null);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.PERSON_WRONG_EMAIL);
        }
        try {
            RegisterEmployeeDtoRequest dtoNullEmail = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "", "Dr.watson", "kcolrehs", null);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.PERSON_NULL_EMAIL);
        }
        try {
            RegisterEmployeeDtoRequest dtoWrongLogin = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "", "kcolrehs", null);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.PERSON_NULL_LOGIN);
        }
        try {
            RegisterEmployeeDtoRequest dtoWrongPassword = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "123", null);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.PERSON_WRONG_PASSWORD);
        }
        try {
            RegisterEmployeeDtoRequest dtoNullPassword = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "", null);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.PERSON_NULL_PASSWORD);
        }
    }


    @Test
    public void testCreateEmployer() throws ServerException {
        RegisterEmployerDtoRequest dtoRequest = new RegisterEmployerDtoRequest("Sherlok", null, "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String jsonToken = server.registerEmployer(gson.toJson(dtoRequest));
        GetEmployerDtoResponse dtoResponse = gson.fromJson(server.getEmployer(jsonToken), GetEmployerDtoResponse.class);
        assertEquals("Sherlok", dtoResponse.getName());
        assertEquals("", dtoResponse.getMiddleName());
        assertEquals("Holmes", dtoResponse.getLastName());
        assertEquals("Sherlok@gmail.com", dtoResponse.getEmail());
        assertEquals("Sherlock", dtoResponse.getLogin());
        assertEquals("riddlesislife", dtoResponse.getPassword());
        assertEquals("London Police Department", dtoResponse.getCompanyName());
        assertEquals("221B Baker Street", dtoResponse.getAddress());
    }


    @Test
    public void testLogOnEmployer() throws ServerException {
        RegisterEmployerDtoRequest dtoRequest = new RegisterEmployerDtoRequest("Sherlok", null, "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String token = gson.fromJson(server.registerEmployer(gson.toJson(dtoRequest)), String.class);
        assertEquals("Ok", gson.fromJson(server.logOnEmployer(gson.toJson(token)), String.class));
        assertEquals("Ok", gson.fromJson(server.logOffEmployer(gson.toJson(token)), String.class));
    }

    @Test
    public void testDeleteEmployer() throws ServerException {
        RegisterEmployerDtoRequest dtoRequest = new RegisterEmployerDtoRequest("Sherlok", null, "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String token = gson.fromJson(server.registerEmployer(gson.toJson(dtoRequest)), String.class);
        GetEmployerDtoResponse dtoResponse = gson.fromJson(server.getEmployer(token), GetEmployerDtoResponse.class);
        assertEquals("Sherlok", dtoResponse.getName());
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Front-end", 150000, null, token);
        server.addVacancy(gson.toJson(vacancyDtoRequest));
        DeleteEmployerDtoRequest dtoDeleteRequest = new DeleteEmployerDtoRequest(token);

        server.deleteEmployer(gson.toJson(dtoDeleteRequest));
        try {
            dtoResponse = gson.fromJson(server.getEmployer(token), GetEmployerDtoResponse.class);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.DATABASE_EMPLOYER_NOT_FOUND);
        }

        try {
            DeleteEmployerDtoRequest dtoWrongDeleteRequest = new DeleteEmployerDtoRequest("123");
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.SERVER_WRONG_TOKEN);
        }


    }


    @Test
    public void testUpdateEmployer() throws ServerException {
        RegisterEmployerDtoRequest dtoRequest = new RegisterEmployerDtoRequest("Sherlok", null, "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String token = gson.fromJson(server.registerEmployer(gson.toJson(dtoRequest)), String.class);
        UpdateEmployerDtoRequest updateEmployerDtoRequest = new UpdateEmployerDtoRequest("Sher", "", "lock", "Sher@mail.ru", "Sherlock", "123456", "Pentagon", "mira 26", null, token);
        server.updateEmployer(gson.toJson(updateEmployerDtoRequest));

        GetEmployerDtoResponse dtoResponse = gson.fromJson(server.getEmployer(token), GetEmployerDtoResponse.class);

        assertEquals("Sher", dtoResponse.getName());
        assertEquals("", dtoResponse.getMiddleName());
        assertEquals("lock", dtoResponse.getLastName());
        assertEquals("Sher@mail.ru", dtoResponse.getEmail());
        assertEquals("Sherlock", dtoResponse.getLogin());
        assertEquals("123456", dtoResponse.getPassword());
        assertEquals("Pentagon", dtoResponse.getCompanyName());
        assertEquals("mira 26", dtoResponse.getAddress());

        try {
            UpdateEmployerDtoRequest wrongUpdateEmployerDtoRequest = new UpdateEmployerDtoRequest("Sher", "", "lock", "Sher@mail.ru", "Boris", "123456", "Pentagon", "mira 26", null, token);
            server.updateEmployer(gson.toJson(wrongUpdateEmployerDtoRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.PERSON_WRONG_LOGIN);
        }

        try {
            UpdateEmployerDtoRequest wrongUpdateEmployerDtoRequest = new UpdateEmployerDtoRequest("Sher", "", "lock", "Sher@mail.ru", "Sherlock", "123456", "Pentagon", "mira 26", null, "123");
            server.updateEmployer(gson.toJson(wrongUpdateEmployerDtoRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.SERVER_WRONG_TOKEN);
        }

    }

    @Test
    public void testCreateWrongEmployer() {
        try {
            RegisterEmployerDtoRequest dtoWrongCompanyName = new RegisterEmployerDtoRequest("Sherlok", null, "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "", "221B Baker Street");
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.EMPLOYER_WRONG_NULL_COMPANYNAME);
        }

        try {
            RegisterEmployerDtoRequest dtoWrongAddress = new RegisterEmployerDtoRequest("Sherlok", null, "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "", null);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.EMPLOYER_WRONG_NULL_ADDRESS);
        }
    }

    @Test
    public void testAddSkill() throws ServerException {
        RegisterEmployeeDtoRequest dtoRequest = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
        String token = gson.fromJson(server.registerEmployee(gson.toJson(dtoRequest)), String.class);
        SkillDtoRequest dtoSkillRequest = new SkillDtoRequest("Java", 5, token);
        SkillDtoRequest dtoSkillRequest2 = new SkillDtoRequest("SQL", 5, token);
        server.addSkill(gson.toJson(dtoSkillRequest));
        server.addSkill(gson.toJson(dtoSkillRequest2));
        GetSkillsDtoResponse dtoSkillResponse = gson.fromJson(server.getSkills(token), GetSkillsDtoResponse.class);
        assertTrue(dtoSkillResponse.getSkills().contains(new Skill("Java", 5)));
        assertTrue(dtoSkillResponse.getSkills().contains(new Skill("SQL", 5)));
        assertFalse(dtoSkillResponse.getSkills().contains(new Skill("English", 5)));
        assertEquals(dtoSkillResponse.getSkills().size(), 2);
    }

    @Test
    public void testRemoveSkill() throws ServerException {
        RegisterEmployeeDtoRequest dtoRequest = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
        String token = gson.fromJson(server.registerEmployee(gson.toJson(dtoRequest)), String.class);
        SkillDtoRequest dtoSkillRequest = new SkillDtoRequest("Java", 5, token);
        SkillDtoRequest dtoSkillRequest2 = new SkillDtoRequest("SQL", 5, token);

        server.addSkill(gson.toJson(dtoSkillRequest));
        server.addSkill(gson.toJson(dtoSkillRequest2));
        GetSkillsDtoResponse dtoGetSkillsResponse = gson.fromJson(server.getSkills(token), GetSkillsDtoResponse.class);
        assertEquals(2, dtoGetSkillsResponse.getSkills().size());

        RegisterEmployeeDtoRequest dtoEmployerRequest = new RegisterEmployeeDtoRequest("Boris", "", "Sl", "Dr.Watson@gmail.com", "Borsi", "kcolrehs", null);
        String token1 = gson.fromJson(server.registerEmployee(gson.toJson(dtoEmployerRequest)), String.class);
        SkillDtoRequest dtoSkillRequest21 = new SkillDtoRequest("Java", 3, token1);
        SkillDtoRequest dtoSkillRequest22 = new SkillDtoRequest("SQL", 2, token1);
        server.addSkill(gson.toJson(dtoSkillRequest21));
        server.addSkill(gson.toJson(dtoSkillRequest22));

        RemoveSkillDtoRequest removeSkillDtoRequest = new RemoveSkillDtoRequest("Java", 5, token);
        server.removeSkill(gson.toJson(removeSkillDtoRequest));
        dtoGetSkillsResponse = gson.fromJson(server.getSkills(token), GetSkillsDtoResponse.class);
        assertEquals(1, dtoGetSkillsResponse.getSkills().size());

        try {
            removeSkillDtoRequest = new RemoveSkillDtoRequest("WEB", 5, token);
            server.removeSkill(gson.toJson(removeSkillDtoRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.EMPLOYEE_SKILL_NOT_FOUND);
        }

        removeSkillDtoRequest = new RemoveSkillDtoRequest("SQL", 5, token);
        server.removeSkill(gson.toJson(removeSkillDtoRequest));
        dtoGetSkillsResponse = gson.fromJson(server.getSkills(token), GetSkillsDtoResponse.class);
        assertEquals(0, dtoGetSkillsResponse.getSkills().size());
    }

    @Test
    public void testChangeSkillScore() throws ServerException {
        RegisterEmployeeDtoRequest dtoRequest = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
        String token = gson.fromJson(server.registerEmployee(gson.toJson(dtoRequest)), String.class);
        SkillDtoRequest javaSkillRequest = new SkillDtoRequest("Java", 5, token);
        SkillDtoRequest sqlSkillRequest = new SkillDtoRequest("SQL", 5, token);
        server.addSkill(gson.toJson(javaSkillRequest));
        server.addSkill(gson.toJson(sqlSkillRequest));
        GetSkillsDtoResponse dtoGetSkillsResponse1 = gson.fromJson(server.getSkills(token), GetSkillsDtoResponse.class);

        for (Skill skill : dtoGetSkillsResponse1.getSkills()) {
            if (skill.getName().equals("Java")) {
                assertEquals(5, skill.getScore());
                break;
            }
            fail();
        }

        javaSkillRequest = new SkillDtoRequest("Java", 4, token);
        server.changeSkillScore(gson.toJson(javaSkillRequest));
        GetSkillsDtoResponse dtoGetSkillsResponse = gson.fromJson(server.getSkills(token), GetSkillsDtoResponse.class);

        for (Skill skill : dtoGetSkillsResponse.getSkills()) {
            if (skill.getName().equals("Java")) {
                assertEquals(4, skill.getScore());
                break;
            }
            fail();
        }
    }

    @Test
    public void testAddWrongSkill() throws ServerException {
        RegisterEmployeeDtoRequest dtoRequest = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "kcolrehs", null);
        String token = gson.fromJson(server.registerEmployee(gson.toJson(dtoRequest)), String.class);

        try {
            SkillDtoRequest dtoSkillRequest = new SkillDtoRequest("", 5, token);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.SKILL_WRONG_NAME);
        }
        try {
            SkillDtoRequest dtoSkillRequest = new SkillDtoRequest("Java", 500, token);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.SKILL_WRONG_SCORE);
        }
        try {
            SkillDtoRequest dtoSkillRequest = new SkillDtoRequest("Java", 5, "1234");
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.SERVER_WRONG_TOKEN);
        }

    }

    @Test
    public void testAddVacancy() throws ServerException {
        RegisterEmployerDtoRequest dtoRequest = new RegisterEmployerDtoRequest("Sherlok", "", "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String token = gson.fromJson(server.registerEmployer(gson.toJson(dtoRequest)), String.class);
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Front-end", 150000, null, token);
        VacancyDtoRequest vacancyDtoRequest2 = new VacancyDtoRequest("Back-end", 155000, null, token);
        server.addVacancy(gson.toJson(vacancyDtoRequest));
        server.addVacancy(gson.toJson(vacancyDtoRequest2));
        GetVacanciesDtoResponse vacanciesDTOResponse = gson.fromJson(server.getVacancies(gson.toJson(token)), GetVacanciesDtoResponse.class);
        assertEquals(vacanciesDTOResponse.getVacancies().size(), 2);
        assertTrue(vacanciesDTOResponse.getVacancies().contains(new Vacancy("Front-end", 150000, null)));
        assertTrue(vacanciesDTOResponse.getVacancies().contains(new Vacancy("Back-end", 155000, null)));
        assertFalse(vacanciesDTOResponse.getVacancies().contains(new Vacancy("Web-Designer", 115000, null)));
        AddVacancyRequirementDtoRequest requirementDTORequest = new AddVacancyRequirementDtoRequest("Java", 5, true, token, "Front-end");
        AddVacancyRequirementDtoRequest requirementDTORequest2 = new AddVacancyRequirementDtoRequest("SQL", 5, true, token, "Front-end");
        server.addVacancyRequirement(gson.toJson(requirementDTORequest));
        server.addVacancyRequirement(gson.toJson(requirementDTORequest2));
        vacanciesDTOResponse = gson.fromJson(server.getVacancies(gson.toJson(token)), GetVacanciesDtoResponse.class);
        assertEquals(vacanciesDTOResponse.getVacancies().size(), 2);
        Set<Requirement> requirements = new HashSet<>();
        requirements.add(new Requirement("Java", 5, true));
        requirements.add(new Requirement("SQL", 5, true));
        assertTrue(vacanciesDTOResponse.getVacancies().contains(new Vacancy("Front-end", 150000, requirements)));
        assertTrue(vacanciesDTOResponse.getVacancies().contains(new Vacancy("Back-end", 155000, null)));
        assertFalse(vacanciesDTOResponse.getVacancies().contains(new Vacancy("Web-Designer", 115000, requirements)));
    }

    @Test
    public void testRemoveVacancy() throws ServerException {
        RegisterEmployerDtoRequest dtoRequest = new RegisterEmployerDtoRequest("Sherlok", "", "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String token = gson.fromJson(server.registerEmployer(gson.toJson(dtoRequest)), String.class);
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Front-end", 150000, null, token);
        VacancyDtoRequest vacancyDtoRequest2 = new VacancyDtoRequest("Back-end", 155000, null, token);
        server.addVacancy(gson.toJson(vacancyDtoRequest));
        server.addVacancy(gson.toJson(vacancyDtoRequest2));
        GetEmployerDtoResponse getEmployerDtoResponse = gson.fromJson(server.getEmployer(gson.toJson(token)), GetEmployerDtoResponse.class);
        assertEquals(2, getEmployerDtoResponse.getVacancies().size());

        RemoveVacancyDtoRequest removeVacancyDtoRequest = new RemoveVacancyDtoRequest("Front-end", token);
        server.removeVacancy(gson.toJson(removeVacancyDtoRequest));
        getEmployerDtoResponse = gson.fromJson(server.getEmployer(gson.toJson(token)), GetEmployerDtoResponse.class);
        assertEquals(1, getEmployerDtoResponse.getVacancies().size());

        try {
            RemoveVacancyDtoRequest wrongVacancyNameDtoRequest = new RemoveVacancyDtoRequest("Front-end", token);
            server.removeVacancy(gson.toJson(wrongVacancyNameDtoRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.EMPLOYER_VACANCY_NOT_FOUND);
        }

        try {
            RemoveVacancyDtoRequest wrongTokenDtoRequest = new RemoveVacancyDtoRequest("Back-end", "123");
            server.removeVacancy(gson.toJson(wrongTokenDtoRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.SERVER_WRONG_TOKEN);
        }

    }

    @Test
    public void testSetVacancyActivity() throws ServerException {
        RegisterEmployerDtoRequest dtoRequest = new RegisterEmployerDtoRequest("Sherlok", "", "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String token = gson.fromJson(server.registerEmployer(gson.toJson(dtoRequest)), String.class);
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Front-end", 150000, null, token);
        server.addVacancy(gson.toJson(vacancyDtoRequest));

        GetEmployerDtoResponse getEmployerDtoResponse1 = gson.fromJson(server.getEmployer(gson.toJson(token)), GetEmployerDtoResponse.class);
        for (Vacancy vacancy : getEmployerDtoResponse1.getVacancies()) {
            if (vacancy.getName().equals("Front-end")) {
                assertEquals(true, vacancy.isActive());
            }
        }

        ActivityVacancyDtoRequest activityVacancyDtoRequest = new ActivityVacancyDtoRequest(false, token, "Front-end");
        server.setVacancyActivity(gson.toJson(activityVacancyDtoRequest));

        GetEmployerDtoResponse getEmployerDtoResponse = gson.fromJson(server.getEmployer(gson.toJson(token)), GetEmployerDtoResponse.class);
        for (Vacancy vacancy : getEmployerDtoResponse.getVacancies()) {
            if (vacancy.getName().equals("Front-end")) {
                assertEquals(false, vacancy.isActive());
            }
        }
    }

    @Test
    public void testAddWrongVacancy() throws ServerException {
        RegisterEmployerDtoRequest dtoRequest = new RegisterEmployerDtoRequest("Sherlok", null, "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String token = gson.fromJson(server.registerEmployer(gson.toJson(dtoRequest)), String.class);
        try {
            VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("", 150000, null, token);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.VACANCY_WRONG_NAME);
        }
        try {
            VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Front-end", 150000, null, "123");
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.SERVER_WRONG_TOKEN);
        }
    }

    @Test
    public void testRemoveRequirement() throws ServerException {
        RegisterEmployerDtoRequest dtoRequest = new RegisterEmployerDtoRequest("Sherlok", "", "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String token = gson.fromJson(server.registerEmployer(gson.toJson(dtoRequest)), String.class);
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Front-end", 150000, null, token);
        server.addVacancy(gson.toJson(vacancyDtoRequest));
        AddVacancyRequirementDtoRequest requirementDTORequest = new AddVacancyRequirementDtoRequest("Java", 5, true, token, "Front-end");
        AddVacancyRequirementDtoRequest requirementDTORequest2 = new AddVacancyRequirementDtoRequest("SQL", 5, true, token, "Front-end");
        server.addVacancyRequirement(gson.toJson(requirementDTORequest));
        server.addVacancyRequirement(gson.toJson(requirementDTORequest2));
        GetEmployerDtoResponse getEmployerDtoResponse1 = gson.fromJson(server.getEmployer(gson.toJson(token)), GetEmployerDtoResponse.class);

        for (Vacancy vacancy : getEmployerDtoResponse1.getVacancies()) {
            if (vacancy.getName().equals("Front-end")) {
                assertEquals(2, vacancy.getRequirements().size());
            }
        }

        RemoveVacancyRequirementDtoRequest removeVacancyRequirementDtoRequest = new RemoveVacancyRequirementDtoRequest("Java", 5, true, token, "Front-end");
        server.removeVacancyRequirement(gson.toJson(removeVacancyRequirementDtoRequest));

        GetEmployerDtoResponse getEmployerDtoResponse = gson.fromJson(server.getEmployer(gson.toJson(token)), GetEmployerDtoResponse.class);
        for (Vacancy vacancy : getEmployerDtoResponse.getVacancies()) {
            if (vacancy.getName().equals("Front-end")) {
                assertEquals(1, vacancy.getRequirements().size());
            }
        }

        try {
            RemoveVacancyRequirementDtoRequest wrongNameRequirementRequest = new RemoveVacancyRequirementDtoRequest("Web", 5, true, token, "Front-end");
            server.removeVacancyRequirement(gson.toJson(wrongNameRequirementRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.REQUIREMENT_NOT_FOUND);
        }

        try {
            RemoveVacancyRequirementDtoRequest wrongNameVacancyRequest = new RemoveVacancyRequirementDtoRequest("SQL", 5, true, token, "Web");
            server.removeVacancyRequirement(gson.toJson(wrongNameVacancyRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.EMPLOYER_VACANCY_NOT_FOUND);
        }
    }

    @Test
    public void testAddWrongRequirement() throws ServerException {
        RegisterEmployerDtoRequest dtoRequest = new RegisterEmployerDtoRequest("Sherlok", "", "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String token = gson.fromJson(server.registerEmployer(gson.toJson(dtoRequest)), String.class);
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Front-end", 150000, null, token);
        server.addVacancy(gson.toJson(vacancyDtoRequest));
        AddVacancyRequirementDtoRequest addRequirementRequest;

        try {
            addRequirementRequest = new AddVacancyRequirementDtoRequest("", 5, true, token, vacancyDtoRequest.getName());
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.REQUIREMENT_WRONG_NAME);
        }

        try {
            addRequirementRequest = new AddVacancyRequirementDtoRequest("Java", 6, true, token, vacancyDtoRequest.getName());
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.REQUIREMENT_WRONG_SCORE);
        }

        try {
            addRequirementRequest = new AddVacancyRequirementDtoRequest("Java", 5, true, token, null);
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.REQUEST_NULL_FIELD);
        }
        try {
            addRequirementRequest = new AddVacancyRequirementDtoRequest("Java", 5, true, "123", vacancyDtoRequest.getName());
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.SERVER_WRONG_TOKEN);
        }
        try {
            addRequirementRequest = new AddVacancyRequirementDtoRequest("Java", 5, true, token, "WEB");
            server.addVacancyRequirement(gson.toJson(addRequirementRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.EMPLOYER_VACANCY_NOT_FOUND);
        }
    }

    @Test
    public void testGetSelectionEmployees() throws ServerException {
        Set<Skill> watsonSkills = new HashSet<>();
        watsonSkills.add(new Skill("Deduction", 4));
        watsonSkills.add(new Skill("Intellect", 3));
        watsonSkills.add(new Skill("Can use weapon", 5));
        watsonSkills.add(new Skill("Doctor", 5));
        watsonSkills.add(new Skill("Good person", 5));
        RegisterEmployeeDtoRequest registerWatson = new RegisterEmployeeDtoRequest("John", "Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson", "sherlock", watsonSkills);
        String watsonToken = server.registerEmployee(gson.toJson(registerWatson));

        Set<Skill> ireneSkills = new HashSet<>();
        ireneSkills.add(new Skill("Deduction", 5));
        ireneSkills.add(new Skill("Intellect", 5));
        ireneSkills.add(new Skill("Can use weapon", 5));
        ireneSkills.add(new Skill("Good person", 3));
        RegisterEmployeeDtoRequest registerIrene = new RegisterEmployeeDtoRequest("Irene", "", "Adler", "IrenAdler@gmail.com", "ThisWoman", "kcolrehs", ireneSkills);
        String ireneToken = server.registerEmployee(gson.toJson(registerIrene));

        Set<Skill> jimSkills = new HashSet<>();
        jimSkills.add(new Skill("Deduction", 5));
        jimSkills.add(new Skill("Intellect", 5));
        jimSkills.add(new Skill("Thief", 5));
        jimSkills.add(new Skill("Can use weapon", 2));
        jimSkills.add(new Skill("Good person", 1));
        RegisterEmployeeDtoRequest registerMoriarty = new RegisterEmployeeDtoRequest("Jim", "", "Moriarty", "Moriarty@gmail.com", "Moriarty", "BadMen", jimSkills);
        String jimToken = server.registerEmployee(gson.toJson(registerMoriarty));

        Set<Skill> mollySkills = new HashSet<>();
        mollySkills.add(new Skill("Deduction", 1));
        mollySkills.add(new Skill("Intellect", 4));
        mollySkills.add(new Skill("Chemist", 5));
        mollySkills.add(new Skill("Doctor", 5));
        mollySkills.add(new Skill("Can use weapon", 1));
        mollySkills.add(new Skill("Good person", 5));
        RegisterEmployeeDtoRequest registerMolly = new RegisterEmployeeDtoRequest("Molly", "", "Hooper", "MollyHoop@gmail.com", "Hooper", "molly123", mollySkills);
        String mollyToken = gson.fromJson(server.registerEmployee(gson.toJson(registerMolly)), String.class);


        Set<Skill> hudsonSkills = new HashSet<>();
        hudsonSkills.add(new Skill("Intellect", 3));
        hudsonSkills.add(new Skill("Can use weapon", 2));
        hudsonSkills.add(new Skill("Good person", 5));
        RegisterEmployeeDtoRequest registerHudson = new RegisterEmployeeDtoRequest("Marta", "", "Hudson", "Hudson@gmail.com", "Mrs.Hudson", "Hudson123", hudsonSkills);
        String hudsotToken = gson.fromJson(server.registerEmployee(gson.toJson(registerHudson)), String.class);

        RegisterEmployerDtoRequest registerSherlock = new RegisterEmployerDtoRequest("Sherlok", "", "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String sherlockToken = gson.fromJson(server.registerEmployer(gson.toJson(registerSherlock)), String.class);
        Set<Requirement> requirements = new HashSet<>();
        requirements.add(new Requirement("Deduction", 4, true));
        requirements.add(new Requirement("Intellect", 3, true));
        requirements.add(new Requirement("Good person", 5, false));
        requirements.add(new Requirement("Can use weapon", 3, false));
        VacancyDtoRequest addSherlockVacancy = new VacancyDtoRequest("Assistant of the detective", 20000, requirements, sherlockToken);
        server.addVacancy(gson.toJson(addSherlockVacancy));

        GetBestEmployeesDtoResponse getBestEmployeesDtoResponse = gson.fromJson(server.getBestEmployees(gson.toJson(addSherlockVacancy)), GetBestEmployeesDtoResponse.class);
        for (Employee employee : getBestEmployeesDtoResponse.getEmployees()) {
            System.out.println(employee.getName() + "  BEST");
        }

        GetGoodEmployeesDtoResponse getGoodEmployeesDtoResponse = gson.fromJson(server.getGoodEmployees(gson.toJson(addSherlockVacancy)), GetGoodEmployeesDtoResponse.class);
        for (Employee employee : getGoodEmployeesDtoResponse.getEmployees()) {
            System.out.println(employee.getName() + "  GOOD");
        }

        GetAnyLevelEmployeesDtoResponse getAnyLevelEmployeesDtoResponse = gson.fromJson(server.getAnyLevelEmployees(gson.toJson(addSherlockVacancy)), GetAnyLevelEmployeesDtoResponse.class);
        for (Employee employee : getAnyLevelEmployeesDtoResponse.getEmployees()) {
            System.out.println(employee.getName() + "  ANY LEVEL");
        }

        GetOneRequirementEmployeesDtoResponse getOneRequirementEmployeesDtoResponse = gson.fromJson(server.getAsLeastOneRequirement(gson.toJson(addSherlockVacancy)), GetOneRequirementEmployeesDtoResponse.class);
        for (Employee employee : getOneRequirementEmployeesDtoResponse.getEmployees()) {
            System.out.println(employee.getName() + "  AS LEAST 1 SKILL");
        }
    }


    @Test
    public void testGetSelectetedVacancies() throws ServerException {
        Set<Skill> simpleEmployee = new HashSet<>();
        simpleEmployee.add(new Skill("Java", 4));
        simpleEmployee.add(new Skill("SQL", 3));
        simpleEmployee.add(new Skill("PHP", 2));
        simpleEmployee.add(new Skill("CSS", 3));
        simpleEmployee.add(new Skill("English", 3));
        RegisterEmployeeDtoRequest registerEmployee = new RegisterEmployeeDtoRequest("David", "", "Copperfield", "Copperfield@gmail.com", "HarryPotter", "abrakadabra", simpleEmployee);
        String davidToken = server.registerEmployee(gson.toJson(registerEmployee));

        RegisterEmployerDtoRequest registerSherlock = new RegisterEmployerDtoRequest("Sherlok", "", "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String sherlockToken = gson.fromJson(server.registerEmployer(gson.toJson(registerSherlock)), String.class);
        Set<Requirement> requirements = new HashSet<>();
        requirements.add(new Requirement("Java", 3, true));
        requirements.add(new Requirement("SQL", 2, true));
        requirements.add(new Requirement("PHP", 2, true));
        requirements.add(new Requirement("Can hack pentagon", 3, false));
        VacancyDtoRequest addSherlockVacancy = new VacancyDtoRequest("IT helper", 20000, requirements, sherlockToken);
        server.addVacancy(gson.toJson(addSherlockVacancy));

        RegisterEmployerDtoRequest registerSimpleEmployer = new RegisterEmployerDtoRequest("Boris", "Arkadievich", "Fasolko", "Fasolko@gmail.com", "BorFas", "123456", "IT it's a life", "Mira 21/2", null);
        String borisToken = gson.fromJson(server.registerEmployer(gson.toJson(registerSimpleEmployer)), String.class);
        Set<Requirement> borisRequirements = new HashSet<>();
        borisRequirements.add(new Requirement("Java", 4, true));
        borisRequirements.add(new Requirement("English", 3, true));
        borisRequirements.add(new Requirement("SQL", 2, true));
        VacancyDtoRequest addBorisVacancy = new VacancyDtoRequest("Back-end java developer", 250000, borisRequirements, borisToken);
        server.addVacancy(gson.toJson(addBorisVacancy));

        Set<Requirement> borisRequirements2 = new HashSet<>();
        borisRequirements2.add(new Requirement("PHP", 2, true));
        borisRequirements2.add(new Requirement("CSS", 2, true));
        borisRequirements2.add(new Requirement("Design", 4, true));
        VacancyDtoRequest addBorisVacancy2 = new VacancyDtoRequest("Web-designer", 210000, borisRequirements2, borisToken);
        server.addVacancy(gson.toJson(addBorisVacancy2));

        RegisterEmployerDtoRequest registerGena = new RegisterEmployerDtoRequest("Gena", "", "Bukin", "Bukin@gmail.com", "Buka", "123456", "Google", "Street", null);
        String genaToken = gson.fromJson(server.registerEmployer(gson.toJson(registerGena)), String.class);
        Set<Requirement> genaRequirements = new HashSet<>();
        genaRequirements.add(new Requirement("Java", 3, true));
        genaRequirements.add(new Requirement("PHP", 5, true));
        genaRequirements.add(new Requirement("CSS", 4, true));
        genaRequirements.add(new Requirement("English", 4, false));
        VacancyDtoRequest addGenaVacancy = new VacancyDtoRequest("Front-end developer", 250000, genaRequirements, genaToken);
        server.addVacancy(gson.toJson(addGenaVacancy));

        Set<Requirement> genaRequirements2 = new HashSet<>();
        genaRequirements2.add(new Requirement("Java", 4, true));
        genaRequirements2.add(new Requirement("Maven", 3, true));
        genaRequirements2.add(new Requirement("Git", 2, true));
        genaRequirements2.add(new Requirement("CSS", 2, false));
        genaRequirements2.add(new Requirement("English", 3, false));
        VacancyDtoRequest addGenaVacancy2 = new VacancyDtoRequest("Middle java developer", 300000, genaRequirements2, genaToken);
        server.addVacancy(gson.toJson(addGenaVacancy2));

        GetBestVacanciesDtoResponse getBestVacanciesDtoResponse = gson.fromJson(server.getBestVacancies(davidToken), GetBestVacanciesDtoResponse.class);
        for (Vacancy vacancy : getBestVacanciesDtoResponse.getVacancies()) {
            System.out.println(vacancy.getName() + " BEST");
        }

        GetGoodVacanciesDtoResponse getGoodVacanciesDtoResponse = gson.fromJson(server.getGoodVacancies(davidToken), GetGoodVacanciesDtoResponse.class);
        for (Vacancy vacancy : getGoodVacanciesDtoResponse.getVacancies()) {
            System.out.println(vacancy.getName() + " GOOD");
        }

        GetAnyLevelVacanciesDtoResponse getAnyLevelVacanciesDtoResponse = gson.fromJson(server.getAnyLevelVacancies(davidToken), GetAnyLevelVacanciesDtoResponse.class);
        for (Vacancy vacancy : getAnyLevelVacanciesDtoResponse.getVacancies()) {
            System.out.println(vacancy.getName() + " ANY LEVEL");
        }

        GetOneRequirementVacanciesDtoResponse getOneRequirementVacanciesDtoResponse = gson.fromJson(server.getAsLeastOneRequirementVacancies(davidToken), GetOneRequirementVacanciesDtoResponse.class);
        for (Vacancy vacancy : getOneRequirementVacanciesDtoResponse.getVacancies()) {
            System.out.println(vacancy.getName() + " AS LEAST 1 (Sorted)");
        }
    }

    @Test
    public void testHireEmployee() throws ServerException {
        RegisterEmployerDtoRequest registerSherlock = new RegisterEmployerDtoRequest("Sherlok", "", "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife", "London Police Department", "221B Baker Street", null);
        String sherlockToken = gson.fromJson(server.registerEmployer(gson.toJson(registerSherlock)), String.class);
        Set<Requirement> requirements = new HashSet<>();
        requirements.add(new Requirement("Java", 3, true));
        requirements.add(new Requirement("SQL", 2, true));
        requirements.add(new Requirement("PHP", 2, true));
        requirements.add(new Requirement("Can hack pentagon", 3, false));
        VacancyDtoRequest addSherlockVacancy = new VacancyDtoRequest("IT helper", 20000, requirements, sherlockToken);
        server.addVacancy(gson.toJson(addSherlockVacancy));


        Set<Skill> simpleEmployee = new HashSet<>();
        simpleEmployee.add(new Skill("Java", 4));
        simpleEmployee.add(new Skill("SQL", 3));
        simpleEmployee.add(new Skill("PHP", 2));
        simpleEmployee.add(new Skill("CSS", 3));
        simpleEmployee.add(new Skill("English", 3));
        RegisterEmployeeDtoRequest registerEmployee = new RegisterEmployeeDtoRequest("David", "", "Copperfield", "Copperfield@gmail.com", "HarryPotter", "abrakadabra", simpleEmployee);
        String davidToken = gson.fromJson(server.registerEmployee(gson.toJson(registerEmployee)), String.class);

        HireEmployeeDtoRequest hireEmployeeDtoRequest = new HireEmployeeDtoRequest(davidToken, sherlockToken, "IT helper");
        server.hireEmployee(gson.toJson(hireEmployeeDtoRequest));
        GetEmployeeDtoResponse getEmployeeDtoResponse = gson.fromJson(server.getEmployee(davidToken), GetEmployeeDtoResponse.class);
        assertEquals(false, getEmployeeDtoResponse.isActive());
        GetVacanciesDtoResponse getVacanciesDtoResponse = gson.fromJson(server.getVacancies(sherlockToken), GetVacanciesDtoResponse.class);
        for (Vacancy vacancy : getVacanciesDtoResponse.getVacancies()) {
            assertEquals(false, vacancy.isActive());
        }

        try {
            HireEmployeeDtoRequest wrongHireEmployeeDtoRequest = new HireEmployeeDtoRequest("123", sherlockToken, "IT helper");
            server.hireEmployee(gson.toJson(wrongHireEmployeeDtoRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.SERVER_WRONG_TOKEN);
        }

        try {
            HireEmployeeDtoRequest wrongHireEmployeeDtoRequest = new HireEmployeeDtoRequest(davidToken, "123", "IT helper");
            server.hireEmployee(gson.toJson(wrongHireEmployeeDtoRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.SERVER_WRONG_TOKEN);
        }

        try {
            HireEmployeeDtoRequest wrongHireEmployeeDtoRequest = new HireEmployeeDtoRequest(davidToken, sherlockToken, "IT help");
            server.hireEmployee(gson.toJson(wrongHireEmployeeDtoRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.EMPLOYER_VACANCY_NOT_FOUND);
        }
        try {
            HireEmployeeDtoRequest wrongHireEmployeeDtoRequest = new HireEmployeeDtoRequest(davidToken, sherlockToken, null);
            server.hireEmployee(gson.toJson(wrongHireEmployeeDtoRequest));
            fail();
        } catch (ServerException e) {
            assertEquals(e.getErrorCode(), ServerErrorCode.REQUEST_NULL_FIELD);
        }

    }

}
