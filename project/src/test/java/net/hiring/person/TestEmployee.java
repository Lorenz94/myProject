package net.thumbtack.school.hiring.person;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestEmployee {
    @Test
    public void testCreateEmployee() throws ServerException {
        Employee valera = new Employee("Valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");

        assertEquals("Valera", valera.getName());
        assertEquals("Andreevich", valera.getMiddleName());
        assertEquals("Puzov", valera.getLastName());
        assertEquals("valera@mail.ru", valera.getEmail());
        assertEquals("valeraPuz", valera.getLogin());
        assertEquals("123456", valera.getPassword());

        Employee empWithoutMid = new Employee("Dima", "Zlobin", "zloba@mail.ru", "Zlob94", "qwerty");
        assertEquals("Dima", empWithoutMid.getName());
        assertEquals("", empWithoutMid.getMiddleName());
        assertEquals("Zlobin", empWithoutMid.getLastName());
        assertEquals("zloba@mail.ru", empWithoutMid.getEmail());
        assertEquals("Zlob94", empWithoutMid.getLogin());
        assertEquals("qwerty", empWithoutMid.getPassword());
    }

    @Test
    public void testCreateWrongName(){
        try {
            Employee employee = new Employee("", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_EMPTY_NAME,e.getErrorCode());
        }
        try {
            Employee employee = new Employee(null, "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_EMPTY_NAME,e.getErrorCode());
        }try {
            Employee employee = new Employee("valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
            employee.setName(null);
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_EMPTY_NAME,e.getErrorCode());
        }try {
            Employee employee = new Employee("valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
            employee.setName("");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_EMPTY_NAME,e.getErrorCode());
        }
    }

//    @Test
//    public void testCreateWrongMiddleName(){
//        try {
//            employee employee = new employee("valera", "", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
//            fail();
//        } catch (ServerException e) {
//            assertEquals(ServerErrorCode.EMPLOYEE_WRONG_MIDDLENAME,e.getErrorCode());
//        }
//        try {
//            employee employee = new employee("valera", null, "Puzov", "valera@mail.ru", "valeraPuz", "123456");
//            fail();
//        } catch (ServerException e) {
//            assertEquals(ServerErrorCode.EMPLOYEE_WRONG_MIDDLENAME,e.getErrorCode());
//        }try {
//            employee employee = new employee("valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
//            employee.setMiddleName(null);
//            fail();
//        } catch (ServerException e) {
//            assertEquals(ServerErrorCode.EMPLOYEE_WRONG_MIDDLENAME,e.getErrorCode());
//        }
//        try {
//            employee employee = new employee("valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
//            employee.setMiddleName("");
//            fail();
//        } catch (ServerException e) {
//            assertEquals(ServerErrorCode.EMPLOYEE_WRONG_MIDDLENAME,e.getErrorCode());
//        }
//    }

    @Test
    public void testCreateWrongLastName(){
        try {
            Employee employee = new Employee("Dima", "Andreevich", "", "valera@mail.ru", "valeraPuz", "123456");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_EMPTY_LASTNAME,e.getErrorCode());
        }
        try {
            Employee employee = new Employee("Dima", "Andreevich", null, "valera@mail.ru", "valeraPuz", "123456");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_EMPTY_LASTNAME,e.getErrorCode());
        }
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
            employee.setLastName(null);
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_EMPTY_LASTNAME,e.getErrorCode());
        }
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
            employee.setLastName("");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_EMPTY_LASTNAME,e.getErrorCode());
        }
    }

    @Test
    public void testCreateWrongEmail(){
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", "", "valeraPuz", "123456");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_NULL_EMAIL,e.getErrorCode());
        }
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", null, "valeraPuz", "123456");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_NULL_EMAIL,e.getErrorCode());
        }
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
            employee.setEmail(null);
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_NULL_EMAIL,e.getErrorCode());
        }
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
            employee.setEmail("");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_NULL_EMAIL,e.getErrorCode());
        }
    }

    @Test
    public void testCreateWrongLogin(){
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", "", "123456");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_NULL_LOGIN,e.getErrorCode());
        }
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", null, "123456");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_NULL_LOGIN,e.getErrorCode());
        }
    }

    @Test
    public void testCreateWrongPassword(){
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_NULL_PASSWORD,e.getErrorCode());
        }
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", null);
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_NULL_PASSWORD,e.getErrorCode());
        }
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
            employee.setPassword("");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_NULL_PASSWORD,e.getErrorCode());
        }
        try {
            Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
            employee.setPassword(null);
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.PERSON_NULL_PASSWORD,e.getErrorCode());
        }
    }

    @Test
    public void testEqualse() throws ServerException {
        Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
        Employee employee1 = new Employee("Vasya", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
        assertFalse(employee.equals(employee1));
        Employee employee2 = new Employee("Vasya", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
        assertTrue(employee1.equals(employee2));
        Employee employee3 = new Employee("Vasya", "Anf", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
        assertFalse(employee1.equals(employee3));
        Employee employee4 = new Employee("Vasya", "Andreevich", "Pu", "valera@mail.ru", "valeraPuz", "123456");
        assertFalse(employee1.equals(employee4));
        Employee employee5 = new Employee("Vasya", "Andreevich", "Puzov", "vale@mail.ru", "valeraPuz", "123456");
        assertFalse(employee1.equals(employee5));
        Employee employee6 = new Employee("Vasya", "Andreevich", "Puzov", "valera@mail.ru", "valeraz", "123456");
        assertFalse(employee.equals(employee6));
        Employee employee7 = new Employee("Vasya", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456252");
        assertFalse(employee.equals(employee7));
    }

    @Test
    public void testHashCode() throws ServerException {
        Employee employee = new Employee("Dima", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
        Employee employee1 = new Employee("Vasya", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
        assertFalse(employee.hashCode() == employee1.hashCode());
        Employee employee2 = new Employee("Vasya", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
        assertTrue(employee1.hashCode() == employee2.hashCode());
        Employee employee3 = new Employee("Vasya", "Anf", "Puzov", "valera@mail.ru", "valeraPuz", "123456");
        assertFalse(employee1.hashCode() == employee3.hashCode());
        Employee employee4 = new Employee("Vasya", "Andreevich", "Pu", "valera@mail.ru", "valeraPuz", "123456");
        assertFalse(employee1.hashCode() == employee4.hashCode());
        Employee employee5 = new Employee("Vasya", "Andreevich", "Puzov", "vale@mail.ru", "valeraPuz", "123456");
        assertFalse(employee1.hashCode() == employee5.hashCode());
        Employee employee6 = new Employee("Vasya", "Andreevich", "Puzov", "valera@mail.ru", "valeraz", "123456");
        assertFalse(employee1.hashCode() == employee6.hashCode());
        Employee employee7 = new Employee("Vasya", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456252");
        assertFalse(employee1.hashCode() == employee7.hashCode());
    }


}
