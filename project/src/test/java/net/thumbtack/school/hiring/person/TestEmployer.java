package net.thumbtack.school.hiring.person;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestEmployer {
    @Test
    public void testCreateEmployer() throws ServerException {
        Employer sherlock = new Employer("Sherlok",  "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife","London Police Department","221B Baker Street");

        assertEquals("Sherlok", sherlock.getName());
        assertEquals("Holmes", sherlock.getLastName());
        assertEquals("Sherlok@gmail.com", sherlock.getEmail());
        assertEquals("Sherlock", sherlock.getLogin());
        assertEquals("riddlesislife", sherlock.getPassword());
        assertEquals("London Police Department", sherlock.getCompanyName());
        assertEquals("221B Baker Street", sherlock.getAddress());

        Employer watson = new Employer("John","Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson","kcolrehs", "London Police Department","221B Baker Street");
        assertEquals("John", watson.getName());
        assertEquals("Hamish", watson.getMiddleName());
        assertEquals("Watson", watson.getLastName());
        assertEquals("Dr.Watson@gmail.com", watson.getEmail());
        assertEquals("Dr.watson", watson.getLogin());
        assertEquals("kcolrehs", watson.getPassword());
        assertEquals("London Police Department",watson.getCompanyName());
        assertEquals("221B Baker Street",watson.getAddress());
    }

    @Test
    public void testCreateWrongCompanyName() throws ServerException{
        try {
            Employer employer = new Employer("Valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456","", "myStreet");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.EMPLOYER_WRONG_NULL_COMPANYNAME,e.getErrorCode());
        }
        try {
            Employer employer = new Employer("Valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456",null, "myStreet");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.EMPLOYER_WRONG_NULL_COMPANYNAME,e.getErrorCode());
        }
        try {
            Employer employer = new Employer("Valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456","AO METRO", "myStreet");
            employer.setCompanyName("");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.EMPLOYER_WRONG_NULL_COMPANYNAME,e.getErrorCode());
        }
        try {
            Employer employer = new Employer("Valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456","AO METRO", "myStreet");
            employer.setCompanyName(null);
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.EMPLOYER_WRONG_NULL_COMPANYNAME,e.getErrorCode());
        }



    }

    @Test
    public void testCreateWrongAddress() throws ServerException{
        try {
            Employer employer = new Employer("Valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456","AO METRO", "");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.EMPLOYER_WRONG_NULL_ADDRESS,e.getErrorCode());
        }
        try {
            Employer employer = new Employer("Valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456","AO METRO", "myStreet");
            employer.setAddress("");
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.EMPLOYER_WRONG_NULL_ADDRESS,e.getErrorCode());
        }
        try {
            Employer employer = new Employer("Valera", "Andreevich", "Puzov", "valera@mail.ru", "valeraPuz", "123456","AO METRO", "myStreet");
            employer.setAddress(null);
            fail();
        } catch (ServerException e) {
            assertEquals(ServerErrorCode.EMPLOYER_WRONG_NULL_ADDRESS,e.getErrorCode());
        }
    }


    @Test
    public void testEquals() throws ServerException {
        Employer sherlock = new Employer("Sherlok",  "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife","London Police Department","221B Baker Street");
        Employer watson = new Employer("John","Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson","kcolrehs", "London Police Department","221B Baker Street");
        assertFalse(sherlock.equals(watson));
        Employer watson1 = new Employer("John","Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson","kcolrehs", "London Police Department","221B Baker Street");
        assertTrue(watson.equals(watson1));
        Employer watson2 = new Employer("John","Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson","kcolrehs", "Spanish Police Deparnt","221B Baker Street");
        assertFalse(watson.equals(watson2));
        Employer watson3 = new Employer("John","Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson","kcolrehs", "London Police Department","221A Baker Street");
        assertFalse(watson.equals(watson3));
    }
    @Test
    public void testHashCode() throws ServerException {
        Employer sherlock = new Employer("Sherlok",  "Holmes", "Sherlok@gmail.com", "Sherlock", "riddlesislife","London Police Department","221B Baker Street");
        Employer watson = new Employer("John","Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson","kcolrehs","London Police Department","221B Baker Street");
        assertFalse(sherlock.hashCode() == watson.hashCode());
        Employer watson1 = new Employer("John","Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson","kcolrehs", "London Police Department","221B Baker Street");
        assertTrue(watson.hashCode() == watson1.hashCode());
        Employer watson2 = new Employer("John","Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson","kcolrehs", "Spanish Police Deparnt","221B Baker Street");
        assertFalse(watson.hashCode() == watson2.hashCode());
        Employer watson3 = new Employer("John","Hamish", "Watson", "Dr.Watson@gmail.com", "Dr.watson","kcolrehs","Spanish Police Deparnt","221A Baker Street");
        assertFalse(watson.hashCode() == watson3.hashCode());
    }

}
